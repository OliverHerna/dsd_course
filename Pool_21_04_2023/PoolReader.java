// Java program to illustrate
// ThreadPool
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Task class to be executed (Step 1)
class Task implements Runnable
{
	private String name;
    private int threat_counter;

	public Task(String s, int counter)
	{
		name = s;
        
        threat_counter = counter;
	}
	
	// Prints task name and sleeps for 1s
	// This Whole process is repeated 5 times
	public void run()
	{
		try
		{
			// Creating an empty HashMap
            Map<Character, Integer> hm = new HashMap<Character, Integer>();
            //Declaration arraylist 
            ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>();
            //reading the file 
            try {
                FileReader file = new FileReader("BIBLIA_COMPLETA.txt");
                //Scanner to Thraversing trhough the file
                Scanner sc = new Scanner(file);
                int start_line = threat_counter * 7037;
                int final_line = (threat_counter + 1) * 7037;
                while (start_line < final_line && sc.hasNextLine()){
                    //Store the line in a variable
                    String line = sc.nextLine();
                    for (int i = 0; i < line.length(); i ++){
                        //Obtainging the char 
                        char c = line.charAt(i);
                        //If the character doesnt exists whe add in the map 
                        if(!hm.containsKey(c)){
                            hm.put(c, 1);
                        }else{
                            int counter = hm.getOrDefault(c, null);
                            int new_counter = counter + 1;
                            hm.replace(c, counter, new_counter);
                        }
                    }
                    start_line ++;
                }
                
            //Reading the file
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("\n\nImprimiendo mapa "+ name+": " );
            for (Map.Entry<Character, Integer> me : hm.entrySet()) {
                // Printing keys
                System.out.print(me.getKey() + ":");
                System.out.print(me.getValue() + "  ");
                list.add(me);
                
            }  
            Thread.sleep(1000);
            System.out.println("\n" +name+" complete");
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}

	}
}
public class PoolReader
{
	// Maximum number of threads in thread pool
	static final int MAX_T = 3;			

	public static void main(String[] args)
	{
		// creates five tasks
		Runnable r1 = new Task("task 1", 0);
		Runnable r2 = new Task("task 2", 1);
		Runnable r3 = new Task("task 3", 2);
		Runnable r4 = new Task("task 4", 3);
		Runnable r5 = new Task("task 5", 4);	
		
		// creates a thread pool with MAX_T no. of
		// threads as the fixed pool size(Step 2)
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		
		// passes the Task objects to the pool to execute (Step 3)
		pool.execute(r1);
		pool.execute(r2);
		pool.execute(r3);
		pool.execute(r4);
		pool.execute(r5);
		
		// pool shutdown ( Step 4)
		pool.shutdown();	
	}
}
