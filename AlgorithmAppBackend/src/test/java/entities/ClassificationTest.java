package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ClassificationTest {

    private Classification classification;

    @Before
    public void setup() {
        classification = new Classification("entities");
    }

    @Test
    public void testInit() {
        String className = "className";
        String parentClassName = "parent";
        List<Algorithm> algorithms = Arrays.asList(new Algorithm(null));
        List<Classification> subClassifications = Arrays.asList(new Classification("Test"));

        Classification classification = new Classification(className, parentClassName, algorithms, subClassifications);
        assertEquals(className, classification.getClassName());
        assertEquals(parentClassName, classification.getParentClassificationName());
        assertEquals(algorithms, classification.getAlgorithms());
        assertEquals(subClassifications, classification.getSubclassifications());
    }

    @Test
    public void testGetsAndSets() {
        Classification classification = new Classification("className");
        String parentClassName = "parent";
        List<Algorithm> algorithms = Arrays.asList(new Algorithm(null));
        List<Classification> subClassifications = Arrays.asList(new Classification("Test"));

        classification.setParentClassificationName(parentClassName);
        classification.setAlgorithms(algorithms);
        classification.setSubclassifications(subClassifications);

        assertEquals(parentClassName, classification.getParentClassificationName());
        assertEquals(algorithms, classification.getAlgorithms());
        assertEquals(subClassifications, classification.getSubclassifications());
    }

    @Test
    public void testAddSubclassification() {
        Classification childClass = new Classification("childClass", null);
        // add a subclassification and check that its parent is this classification and that it has been add to the list
        classification.addSubclassification(childClass);
        assertEquals(childClass.getParentClassificationName(), "entities");
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
        assertEquals(algorithm.getParentClassificationName(), "entities");
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
        Classification childClass = new Classification("childClass", "entities");
        assertFalse(childClass.isTopLevel());
    }

}
