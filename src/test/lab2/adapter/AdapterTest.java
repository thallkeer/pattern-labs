package test.lab2.adapter;

import main.lab2.adapter.StringArrayToBytesAdapter;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;

public class AdapterTest {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/lab2/adapterTestData.txt";

    @Test
    public void stringArrayToBytesTest() throws IOException {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();

        String[] strings = new String[]{"I", "am", "the", "best", "!"};
        try (OutputStream outputStream = new FileOutputStream(f, false)) {
            InputStream inputStream = new FileInputStream(FILE_PATH);
            StringArrayToBytesAdapter adapter = new StringArrayToBytesAdapter(outputStream);
            adapter.adapt(strings);
            byte[] buffer;
            while (inputStream.available() > 0) {
                buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String[] stringsFromFile = new String(buffer).split("\n");
                Arrays.stream(stringsFromFile).forEach(System.out::println);
                assertArrayEquals(strings, stringsFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
