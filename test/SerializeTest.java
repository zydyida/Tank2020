import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerializeTest {

    @Test
    public void testSave() {
        try {
            T t = new T();
            File f = new File("C:/test/s.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testLoad() {
        try {
            File f = new File("C:/test/s.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            T t = (T)(ois.readObject());

            System.out.println(t.m + " " + t.n);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class T implements Serializable {
    int m = 4;
    int n = 8;
}