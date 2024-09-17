import java.util.Date;
import java.util.concurrent.ExecutionException;

import types.Response;

public class FindersCompare {
	public static void execute(String directoryPath, String nameToFind) {
        
        long start = new Date().getTime();
        Response response = FindWordThroughDirectory.execute(
        		directoryPath, 
        		nameToFind
		);
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) sem paralelismo: " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (2 threads): " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (4 threads): " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (8 threads): " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (2 threads com busca reversa): " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (4 threads com busca reversa): " + (new Date().getTime() - start));
        
        System.out.println("---------------------------------");
        
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
        System.out.println("Response: " + response);
        System.out.println("Tempo (ms) com paralelismo (8 threads com busca reversa): " + (new Date().getTime() - start));
	}
}
