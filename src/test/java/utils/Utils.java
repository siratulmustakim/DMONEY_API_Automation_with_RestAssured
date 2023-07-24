package utils;

import com.fasterxml.jackson.databind.util.JSONPObject;
import groovy.json.JsonParser;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Utils {
    public static void saveEnvVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");

        config.setProperty(key, value);
        config.save();
    }

    public static int generateRandomId(int max, int min){
       double number =  Math.random()*(max - min)+min;
       return (int)number;
    }

    //write user phone in json
    public static void saveInJSON(String phone_number, String role) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader("./src/test/resources/UserInfo.json"));

        JSONObject object = new JSONObject();
        object.put("phone_number", phone_number);
        object.put("role", role);

        array.add(object);

        FileWriter writer = new FileWriter("./src/test/resources/UserInfo.json");
        writer.write(array.toJSONString());
        writer.flush();
        writer.close();
    }

    //read user phone number from json array
    public  static String readFromJSON(int customerSerial) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(new FileReader("./src/test/resources/UserInfo.json"));

        JSONObject object = (JSONObject) array.get(customerSerial);
        String phone_number = object.get("phone_number").toString();

        return phone_number;

    }

//    public static void main(String[] args) throws IOException, ParseException {
//        readFromJSON(1);
//    }

}
