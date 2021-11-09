package entityTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import entities.Algorithm;
import entities.Classification;
import entities.Implementation;
import org.junit.Test;

import java.util.ArrayList;

public class AlgorithmTest {
    @Test
    public void testAddImpl() {
        Algorithm a = new Algorithm("test");
        ArrayList<Implementation> empty = new ArrayList<>();
        assertEquals(a.getAlgoName(), "test");
        assertEquals(a.getParentClassification(), null);
        assertEquals(a.getImplementations(), empty);
        Implementation I = new Implementation("testImpl","www.google.com","C", null);
        a.addImplementation(I);
        assertEquals(a.getImplementations().get(0), I);

    }

    @Test
    public void testRemoveImpl() {
        Algorithm a = new Algorithm("test");
        ArrayList<Implementation> empty = new ArrayList<>();
        Implementation I = new Implementation("testImpl","www.google.com","C",null);
        a.addImplementation(I);
        Classification parent = new Classification("parent");
        a.setAlgoName("test2");
        a.setParentClassification(parent);
        a.removeImplementation("testImpl");

        assertEquals(a.getAlgoName(), "test2");
        assertEquals(a.getParentClassification(), parent);
        assertEquals(a.getImplementations(), empty);

    }

}
