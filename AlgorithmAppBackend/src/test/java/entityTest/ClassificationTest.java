package entityTest;

import static org.junit.Assert.*;

import entities.Algorithm;
import entities.Classification;
import org.junit.Before;
import org.junit.Test;

public class ClassificationTest {

    private Classification classification;

    @Before
    public void setup() {
        classification = new Classification("entityTest");
    }

    @Test
    public void testAddSubclassification() {
        Classification childClass = new Classification("childClass", null);
        // add a subclassification and check that its parent is this classification and that it has been add to the list
        classification.addSubclassification(childClass);
        assertEquals(childClass.getParentClassificationName(), "entityTest");
        assertTrue(classification.getSubclassifications().contains(childClass));
    }

    @Test
    public void testRemoveClassification() {
        Classification childClass = new Classification("childClass", null);
        // start by adding a subclassification
        classification.addSubclassification(childClass);
        assertTrue(classification.getSubclassifications().contains(childClass));

        // now, remove the classification and check if the list still contains it (and that its parent is no longer this classification)
        classification.removeSubclassification("childClass");
        assertFalse(classification.getSubclassifications().contains(childClass));
        assertNull(childClass.getParentClassificationName());

        // shouldn't be able to remove a classification that doesn't exist
        assertFalse(classification.removeSubclassification("childClass"));
    }

    @Test
    public void testAddAlgorithm() {
        Algorithm algorithm = new Algorithm("algo");
        // add an algorithm and check that it exists
        classification.addAlgorithm(algorithm);
        assertTrue(classification.getAlgorithms().contains(algorithm));
        assertEquals(algorithm.getParentClassificationName(), "entityTest");
    }

    @Test
    public void testRemoveAlgorithm() {
        Algorithm algorithm = new Algorithm("algo");
        // start by adding an algorithm
        classification.addAlgorithm(algorithm);
        assertTrue(classification.getAlgorithms().contains(algorithm));

        // remove the algorithm and check that it's removed
        assertTrue(classification.removeAlgorithm("algo"));
        assertFalse(classification.getAlgorithms().contains(algorithm));
        assertNull(algorithm.getParentClassificationName());
        // make sure we can't remove an algo that doesn't exist
        assertFalse(classification.removeAlgorithm("algo"));
    }

    @Test
    public void testIsTopLevel() {
        // check that a classification with no parents is considered top level
        assertTrue(classification.isTopLevel());

        // create a classification with a parent and check that it's not considered top level
        Classification childClass = new Classification("childClass", "entityTest");
        assertFalse(childClass.isTopLevel());
    }

}
