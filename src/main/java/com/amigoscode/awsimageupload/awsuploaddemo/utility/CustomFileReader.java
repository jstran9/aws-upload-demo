package com.amigoscode.awsimageupload.awsuploaddemo.utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Provides functionality to read from a file for configurations.
 */
public class CustomFileReader {

    public CustomFileReader() { }

    /**
     * Reads a file and returns a JSONObject with key value pairs holding configuration information.
     * @param fileName The name of the file containing key and value pairs.
     * @return A JSONObject with configuration information.
     */
    public JSONObject getObjects(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new java.io.FileReader(fileName));
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Grabs the value from a corresponding key in a JSONObject.
     * @param jsonObject The JSONObject holding key/value pairs.
     * @param key The name of the key to obtain the value for.
     * @return Returns a value given a specific key.
     */
    public String getContentFromKey(JSONObject jsonObject, String key) {
        String value = null;
        try {
            value = (String) jsonObject.get(key);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }

}