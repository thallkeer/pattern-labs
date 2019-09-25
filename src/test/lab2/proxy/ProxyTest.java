package test.lab2.proxy;

import main.lab2.proxy.Client;
import main.lab2.proxy.Proxy;
import main.lab2.proxy.Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProxyTest {
    @Test
    public void testProxyCanMultiply() {
        //TODO: fix proxy
        Service service = new Service();
        Proxy proxy = new Proxy();
        Client client = new Client(proxy);
        double result = client.getMultiplicationResult(10,15);
        System.out.println(String.format("The result of multiplication of %s and %s is %s",10,15,result));
        assertEquals(150,result,0.0);
    }
}