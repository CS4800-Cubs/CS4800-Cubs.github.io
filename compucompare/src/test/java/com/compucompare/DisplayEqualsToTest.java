package com.compucompare;

import com.compucompare.compucompare.App;
import com.compucompare.compucompare.components.DisplayComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = App.class)

public class DisplayEqualsToTest {

    private DisplayComponent display1;
    private DisplayComponent display2;
    private DisplayComponent display3;
    private DisplayComponent display4;

    @BeforeEach
    public void setUp(){
        display1 = new DisplayComponent("BENQ", "GL2460", 24, 1920, 1080, 60);
        display2 = new DisplayComponent("BENQ", "GL2460", 24, 1920, 1080, 60);
        display3 = new DisplayComponent("BENQ", "GL2450", 21, 1920, 1080, 60);
        display4 = new DisplayComponent("ASUS", "VG278Q", 24, 1920, 1080, 144);
    }

    /**
     * Test case to check if the equals to method works for
     * the DisplayComponent class.
     * The method should return true or false
     */


    @Test
    public void CheckingDisplaysEqual(){
        Assertions.assertTrue(display1.equals(display2));
        System.out.println("Display1 is the same as display2");
        Assertions.assertFalse(display1.equals(display3));
        System.out.println("Display1 is not the same as display3");
        Assertions.assertFalse(display1.equals(display4));
        System.out.println("Display1 is not the same as display4");
        Assertions.assertFalse(display2.equals(display3));
        System.out.println("Display2 is not the same as display4");
        Assertions.assertFalse(display2.equals(display4));
        System.out.println("Display2 is not the same as display4");
        Assertions.assertFalse(display3.equals(display4));
        System.out.println("Display3 is not the same as display4");
    }
}
