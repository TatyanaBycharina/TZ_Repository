package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File testsJSON = new File(args[0]);
        File valuesJSON = new File(args[1]);

        Object tests = new JSONParser().parse(new FileReader(testsJSON));
        JSONObject testsObj = (JSONObject) tests;
        JSONArray testsArr = (JSONArray) testsObj.get("tests");

        Object values = new JSONParser().parse(new FileReader(valuesJSON));
        JSONObject valuesObj = (JSONObject) values;
        JSONArray valueArr = (JSONArray) valuesObj.get("values");

        Map<Long, String> valuesMap = new HashMap<>();
        for (Object it : valueArr) {
            JSONObject valueAndId = (JSONObject) it;
            Long id = (Long) valueAndId.get("id");
            String value = (String) valueAndId.get("value");
            valuesMap.put(id, value);
        }
        processJSONarray(testsArr, valuesMap);

        //Path reportFile = Paths.get("C:\\Users\\tanya\\Desktop\\TZ_repos\\task3\\src\\main\\resources\\report.json");
        //List<String> jsonList = new ArrayList<>();
        /*String[] arrJSON = new String[testsArr.toArray().length];
        for(int i = 0; i < arrJSON.length; i++) {
            arrJSON[i] = (String) testsArr.toArray()[i];
        }
        Files.write(reportFile, arrJSON, StandardCharsets.UTF_8);*/
        try (FileWriter writer = new FileWriter("C:\\\\Users\\\\tanya\\\\Desktop\\\\TZ_repos\\\\task3\\\\src\\\\main\\\\resources\\\\report.json", false)) {
            String reportJSON = "{" +
                    "\"tests\":" + testsArr.toJSONString() + "}";
            writer.write(reportJSON);
        } catch (IOException e) {
            e.getMessage();
        }


    }
    public static void processJSONarray(JSONArray obj, Map<Long, String> values) {

        for (Object it : obj) {
            processJSONentry((JSONObject) it, values);
        }
    }
    public static void processJSONentry(JSONObject test, Map<Long, String> values) {
        test.put("value", values.get(test.get("id")));

        if (test.containsKey("values")) {
            JSONArray testArr = (JSONArray) test.get("values");

            for (Object it : testArr) {
                processJSONentry((JSONObject) it, values);
            }
        }
    }
}