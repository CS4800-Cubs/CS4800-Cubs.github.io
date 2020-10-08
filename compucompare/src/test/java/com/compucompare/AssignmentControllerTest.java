package com.compucompare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.compucompare.compucompare.AssignmentController;

import org.junit.Before;
import org.junit.Test;

public class AssignmentControllerTest
{
    AssignmentController testController;

    @Before
    public void setup()
    {
        testController = new AssignmentController();
    }

    /**
     * Tests the determinant function by ensuring
     * the results of a known case match its 
     * expected results.
     */
    @Test
    public void calculateDeterminantTest()
    {
        String testMatrix = "["
            + "[1,3,5,9],"
            + "[1,3,1,7],"
            + "[4,3,9,7],"
            + "[5,2,0,9]"
            + "]";
        String result = testController.determinant(testMatrix);
        assertEquals("The Determinant Is: -376.0", result);
    }
}
