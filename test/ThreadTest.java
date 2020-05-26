import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ThreadTest {
    @Test
    public void testThread() {
        new TT().start();
        new Thread(new C()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous");
            }
        }).start();

        new Thread(()->{
            System.out.println("Hello");
        }).start(); //java8 lambda expression @FunctionalInterface


    }
}

class TT extends Thread {
    @Override
    public void run() {
        System.out.println("TT");
    }
}

class C implements Runnable {
    @Override
    public void run() {
        System.out.println("C");
    }
}


