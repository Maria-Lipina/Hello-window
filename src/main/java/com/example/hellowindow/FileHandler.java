package com.example.hellowindow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileHandler {

    private Properties props;

    public FileHandler() {
        props = new Properties();
        try {
            props.load(new FileReader("src/main/resources/com/example/hellowindow/assets/file.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(
                    new FileReader((String) props.get("description"),
                            StandardCharsets.UTF_8));
            String s;
            while ((s = bf.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
