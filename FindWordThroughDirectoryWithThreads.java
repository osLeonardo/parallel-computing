import types.Response;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public final class FindWordThroughDirectoryWithThreads {

    public static Response execute(String pathToDirectory, String wordToLookFor, int THREAD_COUNT) throws InterruptedException, ExecutionException {
        Path dir = Paths.get(pathToDirectory);  // specify your directory here
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<Response>> futures = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                // Submit tasks to handle different stepping lines
                for (int i = 0; i < THREAD_COUNT; i++) {
                    futures.add(executor.submit(new LineTask(file, wordToLookFor, i, THREAD_COUNT)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Collect the results and shut down the executor
        executor.shutdown();

        // Iterate through the futures to find the result
        for (Future<Response> future : futures) {
            Response result = future.get();
            if (result != null) {
                return result; // Return the first match found
            }
        }
        return null;
    }

    // Task to search specific lines in a file
    private static class LineTask implements Callable<Response> {
        private final Path file;
        private final String wordToLookFor;
        private final int startLine;
        private final int stepSize;

        public LineTask(Path file, String wordToLookFor, int startLine, int stepSize) {
            this.file = file;
            this.wordToLookFor = wordToLookFor;
            this.startLine = startLine;
            this.stepSize = stepSize;
        }

        @Override
        public Response call() {
            return searchInFile(file, wordToLookFor, startLine, stepSize);
        }

        private Response searchInFile(Path file, String wordToLookFor, int startLine, int stepSize) {
            try {
                List<String> lines = Files.readAllLines(file);
                for (int lineNumber = startLine; lineNumber < lines.size(); lineNumber += stepSize) {
                    if (lines.get(lineNumber).equals(wordToLookFor)) {
                        return new Response(wordToLookFor, lineNumber + 1); // Line numbers are 1-based
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
