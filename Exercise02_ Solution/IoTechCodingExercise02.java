package IOTech;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IoTechCodingExercise02 {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            FileReader reader = new FileReader("/Users/ameennoor/Documents/exercises-main/exercise-02/data/data.json");
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
            JSONArray jsonArray = jsonObject.getJSONArray("Devices");
            
            
            
            //
            Scanner keyboardIn = new Scanner(System.in);

            int option;
           
            do
            {
               //Display Menu
               System.out.println("");
               System.out.println("");
               System.out.println("");
               System.out.println("1. Parse the data from json file");
               System.out.println("2. Discard the devices where the timestamp value is before the current time");
               System.out.println("3. Get the total of all value entries");
               System.out.println("4. Parse the uuid from the info field of each entry");
               System.out.println("5. Output the values total and the list of uuids to json file");
               System.out.println("0. Exit System");
               System.out.println("");
               System.out.println("");
               
               System.out.println("Enter your option, from 1-5");
               option = keyboardIn.nextInt();
               
               
               switch(option)
               {
                  case 1:
                	  parseAllDataFromFile(jsonArray); //call method 
                  break;
                  
                  case 2:
                	  dicardDevicesIf(jsonArray);
                  break;
                  
                  case 3:
                	  System.out.println("Total of all value => " + getTotalOfAllValue(jsonArray));
                  break;
                  
                  case 4:
                	  parseByUuid(jsonArray);
                  break;
                  
                  case 5:
                	  writeToJsonFile(jsonArray);
                  break;
                  
                  case 0:
                     System.out.println("Thanks!");
                     break;
                     
                  default:
                     System.out.println("Invalid option! please choose from 0-5");
                  
               }//close switch
               
            } while(option != 0);

               

        } catch (IOException e) {
            e.printStackTrace();
        } catch(InputMismatchException ex){
            System.out.println("Numeric values only, Please re-start the program again & select from 0-5");   
        } 
    }//close main method
    
    
    // Methods:
    
  //Method to Parse the data from exercise-02/data/data.json
    
    public static void parseAllDataFromFile(JSONArray jArray)
    {
       for(int i = 0; i < jArray.length(); i++){
          System.out.println(jArray.getJSONObject(i));
       }
    }//close method
    
    // Method discard the devices where the timestamp value is before the current time.
    public static void dicardDevicesIf(JSONArray jArray)
    {
       for(int i = 0; i < jArray.length(); i++){
    	   JSONObject devices = jArray.getJSONObject(i);
           String timestamp = devices.getString("timestamp");
    	   long lTimestamp = Long.parseLong(timestamp);
           long currentTime = System.currentTimeMillis() / 1000;
           if(lTimestamp < currentTime) {
        	   System.out.println(jArray.getJSONObject(i));  
           }
       }
    }//close method
    
    // Method to get the total of all value entries, values are base64 strings containing integer values
    public static int getTotalOfAllValue(JSONArray jArray)
    {
    	int total = 0;
    	for(int i = 0; i < jArray.length(); i++){
    	   JSONObject devices = jArray.getJSONObject(i);
    	   String value = devices.getString("value");
           byte[] decodedValue = Base64.getDecoder().decode(value);
           int intValue = Integer.parseInt(new String(decodedValue));
           total += intValue;
 
    	}
    	return total;
    	
    }//close method
    
    // Method to Parse the uuid from the info field of each entry
    public static void parseByUuid(JSONArray jArray)
    {
    	
    	for(int i = 0; i < jArray.length(); i++){
    	   JSONObject devices = jArray.getJSONObject(i);
    	   String info = devices.getString("Info");
    	   Pattern pattern = Pattern.compile("uuid:(\\S+)");
           Matcher matcher = pattern.matcher(info);
           if (matcher.find()) {
             String uuid = matcher.group(1);
             System.out.println("UUID: " + uuid);
           }
           

    	}
    	
    }//close method
    
    // Method to output the values total and the list of uuids to json file
    public static void writeToJsonFile(JSONArray jArray) 
    {
    	int totalValue = getTotalOfAllValue(jArray);
    	ArrayList<String> myArray = new ArrayList<String>();
    	for(int i = 0; i < jArray.length(); i++){
     	   JSONObject devices = jArray.getJSONObject(i);
     	   String info = devices.getString("Info");
     	   Pattern pattern = Pattern.compile("uuid:(\\S+)");
            Matcher matcher = pattern.matcher(info);
            if (matcher.find()) {
              String uuid = matcher.group(1);
              myArray.add(uuid);
            }

     	}
    	
    	
    	try {
    		JSONObject json = new JSONObject();
            json.put("ValueTotal", totalValue);
            json.put("UUIDS", myArray);
            
            FileWriter writer = new FileWriter("output.json");
            json.write(writer);
            System.out.println("Successfully file created!!");
            writer.close();
    	} catch (IOException ex) {
            System.out.println("Error!");
        }

    	
    	
    }//close method
    
    
} //close main class












