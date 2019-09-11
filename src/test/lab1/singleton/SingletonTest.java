package test.lab1.singleton;

import main.lab1.singleton.AppProperties;
import org.junit.Test;

import static main.lab1.singleton.AppProperties.FillProps;
import static org.junit.Assert.assertEquals;

public class SingletonTest {
    @Test
    public void testSingleton() {
        FillProps(true);

        AppProperties firstProps = AppProperties.getInstance();
        System.out.println("first instance url: " + firstProps.getProperty("db.url"));

        FillProps(false);
        System.out.println("first instance url after setting new props: " + firstProps.getProperty("db.url"));

        AppProperties secondProps = AppProperties.getInstance();
        System.out.println("second instance url: " + secondProps.getProperty("db.url"));

        assertEquals(firstProps,secondProps);
    }
}
