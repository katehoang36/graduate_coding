/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pexachallenge;

/**
 *
 * @author kayhoang
 */
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.parser.ParseException;

public class App {

    public static void main(String[] args) {
        JSONParser jsonparser = new JSONParser();
        //create an arrayList that will contain all element 'State' of each customer object,
        //this list will looks like {SA, SA, QLD,...)
        ArrayList<String> stateArray = new ArrayList<>();

        try {
            //read the json file
            FileReader reader = new FileReader("../../data/members.json");
            //parse JSON data and store them in an jsonArray 
            JSONArray jsonArray = (JSONArray) jsonparser.parse(reader);
            //travel accross each element in jsonArray
            Iterator i = jsonArray.iterator();
            //while iteration has more elements 
            while (i.hasNext()) {
                //return next element in the interation which is customer json object
                JSONObject customer = (JSONObject) i.next();
                //get customer address value using the key "address" inside each customer object
                String cAddress = (String) customer.get("address");
                //split customer address by using "," delimitor and then store into addressSplit array
                String[] addressSplit = cAddress.split(",");
                //get the second element in addressSplit array which is STATE then store in stateArray
                stateArray.add(addressSplit[1]);
            }
            //create a HashMap to store State name (value) and its occurrence (key)
            Map<String, Integer> stateOccurrenceMap = new HashMap();
            
            //count state occurrence by using 'countStateOccurrence()' method
            countStateOccurrence(stateArray, stateOccurrenceMap);
            //write to file using 'writeMapToSCV()' method
            writeMapToCSV(stateOccurrenceMap);

        }  
        catch (FileNotFoundException exception) {
            System.out.println("Json file not found!");
        }
        catch (IOException | ParseException e) {
        }
    }
    
    public static void countStateOccurrence(ArrayList<String> stateArray, Map<String, Integer> stateOccurrenceMap){
            //loop through the State array
            stateArray.forEach(x -> {
                //if an item in stateArray haven't been mapped as a key in the stateOccurrenceMap,
                //create a new item with key = new state and value = 1
                if (!stateOccurrenceMap.containsKey(x)) {
                    stateOccurrenceMap.put(x, 1);
                } 
                //if an item in stateArray is already stored in the stateOccurrenceMap,
                //then add 1 to its occurrence,
                //since the item's key is unique, only the item's value is overrided
                else {
                    stateOccurrenceMap.put(x, stateOccurrenceMap.get(x) + 1);
                }
            });
    }

    public static void writeMapToCSV(Map<String, Integer> stateOccurrenceMap) throws IOException {
        //set the output csv file location and name
        try (Writer writer = new FileWriter("../../data/output.csv")) {
            //loop through the Map to write each item (state name, occurrence) into csv file
            for (Map.Entry<String, Integer> entry : stateOccurrenceMap.entrySet()) {
                //get and write key to csv
                writer.append(entry.getKey())
                        //add delimitor
                        .append(',')
                        //get and wirte value to csv
                        .append(Integer.toString(entry.getValue()))
                        //add new line after each element
                        .append(System.getProperty("line.separator"));
            }
        }
    }
}
