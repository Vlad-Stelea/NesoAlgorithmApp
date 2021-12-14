package entities;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

public class AlgorithmTest {
    @Test
    public void testAddImpl() {
        Algorithm a = new Algorithm("test");
        ArrayList<Implementation> empty = new ArrayList<>();
        assertEquals(a.getAlgoName(), "test");
        assertEquals(a.getParentClassificationName(), null);
        assertEquals(a.getImplementations(), empty);
        Implementation I = new Implementation("testImpl","www.google.com","C", null);
        a.addImplementation(I);
        assertEquals(a.getImplementations().get(0), I);
        assertEquals(I.getAlgorithmName(), "test");

    }

    @Test
    public void testRemoveImpl() {
        Algorithm a = new Algorithm("test");
        ArrayList<Implementation> empty = new ArrayList<>();
        Implementation I = new Implementation("testImpl","www.google.com","C",null);
        a.addImplementation(I);
        a.setAlgoName("test2");
        a.setParentClassificationName("parent");
        a.removeImplementation("testImpl");

        assertEquals(a.getAlgoName(), "test2");
        assertEquals(a.getParentClassificationName(), "parent");
        assertEquals(a.getImplementations(), empty);
        assertEquals(I.getAlgorithmName(), null);

    }

}
