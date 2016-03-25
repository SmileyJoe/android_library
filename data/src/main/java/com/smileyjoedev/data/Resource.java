package com.smileyjoedev.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cody on 2016/03/25.
 */
public class Resource {

    public static String get(String name){
        String contents = "";
        try {
            InputStream inputStream = Resource.class.getClassLoader().getResourceAsStream(name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                contents = builder.toString();
            } finally {
                reader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return contents;
    }

}
