package Util;

import java.io.*;
import java.util.Properties;

public class Progress {

    private static Progress instance;
    private Properties properties;

    private Progress() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File("res/progress.properties"));
            properties.load(fis);
            fis.close();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public static Progress getInstance() {
        if(instance == null) {
            instance = new Progress();
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public void store() {
        try {
            FileOutputStream fos = new FileOutputStream(new File("res/progress.properties"));
            properties.store(fos, null);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
