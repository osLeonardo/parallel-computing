import java.util.Date;
import java.util.concurrent.ExecutionException;

import types.Response;

import javax.swing.*;

public class FindersCompare {
	public static void execute(String directoryPath, String nameToFind, JTextArea logTextArea) {
        
        long start = new Date().getTime();
        Response response = FindWordThroughDirectory.execute(
        		directoryPath, 
        		nameToFind
		);
        logTextArea.append(response.toString());
        logTextArea.append("\nTempo (ms) sem paralelismo: " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreads.execute(
					directoryPath, 
					nameToFind,
					2
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (2 threads): " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreads.execute(
					directoryPath, 
					nameToFind,
					4
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (4 threads): " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreads.execute(
					directoryPath, 
					nameToFind,
					8
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (8 threads): " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreadsReverse.execute(
					directoryPath, 
					nameToFind,
					2
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (2 threads com busca reversa): " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreadsReverse.execute(
					directoryPath, 
					nameToFind,
					4
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (4 threads com busca reversa): " + (new Date().getTime() - start));
        
        logTextArea.append("\n---------------------------------");
        
        start = new Date().getTime();
        try {
			response = FindWordThroughDirectoryWithThreadsReverse.execute(
					directoryPath, 
					nameToFind,
					8
			);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        logTextArea.append("\nResponse: " + response.toString());
        logTextArea.append("\nTempo (ms) com paralelismo (8 threads com busca reversa): " + (new Date().getTime() - start));
	}
}
