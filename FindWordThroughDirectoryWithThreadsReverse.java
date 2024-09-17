import types.Response;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public final class FindWordThroughDirectoryWithThreadsReverse {

    public static Response execute(String pathToDirectory, String wordToLookFor, int THREAD_COUNT) throws InterruptedException, ExecutionException {
        Path dir = Paths.get(pathToDirectory);  // specify your directory here
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<Response>> futures = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                for (int i = 0; i < (THREAD_COUNT / 2); i++) {
                	// Submit tasks for searching from start to end and end to start
                    futures.add(executor.submit(new SearchTask(file, wordToLookFor, true, i, THREAD_COUNT)));  // Start to end
                    futures.add(executor.submit(new SearchTask(file, wordToLookFor, false, i, THREAD_COUNT))); // End to start
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

    // Task to search a file in a specified direction
    private static class SearchTask implements Callable<Response> {
        private final Path file;
        private final String wordToLookFor;
        private final boolean forward;
        private final int threadIndex;
        private final int stepLine;

        public SearchTask(Path file, String wordToLookFor, boolean forward, int startLine, int stepLine) {
            this.file = file;
            this.wordToLookFor = wordToLookFor;
            this.forward = forward;
            this.threadIndex = startLine;
            this.stepLine = stepLine;
        }

        @Override
        public Response call() {
            return searchInFile(file, wordToLookFor, forward, threadIndex, stepLine);
        }

        private Response searchInFile(Path file, String wordToLookFor, boolean forward, int startLine, int stepLine) {
            try {
                List<String> lines = Files.readAllLines(file);
                // Search from start to end
                for (int lineNumber = forward ? 0 + startLine : (lines.size() - 1) - startLine; forward ? lineNumber < lines.size() : lineNumber >= 0; lineNumber = forward ? lineNumber + stepLine : lineNumber - stepLine) {
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
