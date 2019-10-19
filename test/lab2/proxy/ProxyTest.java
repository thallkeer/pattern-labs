package  lab2.proxy;

import main.lab2.proxy.Client;
import main.lab2.proxy.Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProxyTest {
    @Test
    public void testProxyCanMultiply() {
        //Service service = new Service();
        Client client = new Client();
        double result = client.getMultiplicationResult(10.5, 15.6);
        System.out.println(String.format("The result of multiplication of %s and %s is %s", 10, 15, result));
        //assertEquals(150, result, 0.0);
    }
}