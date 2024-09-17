import types.Response;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;

public final class FindWordThroughDirectory {
	public static Response execute(String pathToDirectory, String wordToLookFor) {
		Path dir = Paths.get(pathToDirectory);  // specify your directory here
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {                
                // Reading the content of each .txt file
                List<String> lines = Files.readAllLines(file);
                
                // Printing each line of the file
                int lineNumber = 1;
                for (String line : lines) {
                    if (line.equals(wordToLookFor)) {
                    	return new Response(wordToLookFor, lineNumber);
                    }
                    lineNumber++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
}
