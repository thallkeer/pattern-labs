package main.lab1.singleton;

import java.io.*;
import java.util.Properties;

public final class AppProperties {
    private static volatile AppProperties instance;
    private Properties properties;
    private static final String configPath = "config.properties";

    private AppProperties() {
        properties = new Properties();

        try (InputStream is = new FileInputStream("E:/JAVA_Projects/config/" + configPath)) {
            properties.load(is);
            System.out.println("Loaded props:");
            System.out.println(properties.getProperty("db.url"));
            System.out.println(properties.getProperty("db.user"));
            System.out.println(properties.getProperty("db.password"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static AppProperties getInstance() {
        AppProperties result = instance;
        if (result == null) {
            synchronized (AppProperties.class) {
                result = instance;
                if (result == null) {
                    instance = result = new AppProperties();
                }
            }
        }
        return instance;
    }

    public static void FillProps(boolean initialize) {
        try (OutputStream output = new FileOutputStream("E:/JAVA_Projects/config/" + configPath)) {
            Properties props = new Properties();
            props.setProperty("db.url", initialize ? "mydburl" : "newdburl");
            props.setProperty("db.user", initialize ? "mydbuser" : "newdbuser");
            props.setProperty("db.password", initialize ? "mypassword" : "newdbpassword");

            props.store(output, null);
            System.out.println("New props: " + props);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
