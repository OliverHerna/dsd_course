import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountCharacters {
    public static void main(String [] args) {
        // Creating an empty HashMap
	    Map<Character, Integer> hm = new HashMap<Character, Integer>();
        //Declaration arraylist 
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>();
        //reading the file
        try {
            FileReader file = new FileReader("BIBLIA_COMPLETA.txt");
            //Scanner to Thraversing trhough the file
            Scanner sc = new Scanner(file);
            //Reading the file
            while(sc.hasNextLine()){
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
            }
        } catch (Exception e) {
            // TODO: handle exception
        }     
        //Printing characters
        // Printing keys
        System.out.println("Caracterés encontrados: ");
		for (Map.Entry<Character, Integer> me : hm.entrySet()) {
            System.out.print(me.getKey() + " ");
        }  

        System.out.println("\n\nNúmero total de caracteres: " + hm.size());

        // Traversing through Map using for-each loop
        System.out.println("\n\nImprimiendo mapa: ");
		for (Map.Entry<Character, Integer> me : hm.entrySet()) {
            // Printing keys
            System.out.print(me.getKey() + ":");
            System.out.print(me.getValue() + "  ");
            list.add(me);
            
        }  

        //Printing sort list 
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2){
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        // Traversing through Map using for-each loop
        System.out.println("\n\nImprimiendo lista ordenada: ");
        for(Map.Entry<Character, Integer> entry : list){
            // Printing keys
            System.out.print(entry.getKey() + ":");
            System.out.print(entry.getValue() + "  ");
        }
    }

}
