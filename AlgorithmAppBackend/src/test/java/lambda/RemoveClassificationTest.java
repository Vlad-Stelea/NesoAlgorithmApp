package lambda;

import RemoveClassification.RemoveClassificationHandler;
import RemoveClassification.RemoveClassificationResponse;
import RemoveClassification.RemoveClassificationRequest;
import db.AlgorithmDAO;
import db.ClassificationDAO;
import entities.Algorithm;
import entities.Classification;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RemoveClassificationTest {

    AlgorithmDAO algoDAO;
    ClassificationDAO classDAO;
    RemoveClassificationHandler handler;
    RemoveClassificationRequest request;
    ArrayList<Classification> classes;
    Classification rootClass;

    @Before
    public void setup() {
        algoDAO = mock(AlgorithmDAO.class);
        classDAO = mock(ClassificationDAO.class);
        handler = new RemoveClassificationHandler(algoDAO, classDAO);
        rootClass = createRootClass();
        classes = new ArrayList<>();
        classes.add(rootClass);
        classes.add(new Classification("someOtherClass"));  // throw another arbitrary class to make sure it doesn't get deleted as well
        classes.add(new Classification("someOtherSubclass", "someOtherClass"));
        request = new RemoveClassificationRequest("rootClass");
    }

    private Classification createRootClass() {
        Classification rootClass = new Classification("rootClass", null);
            Classification subclass1 = new Classification("subclass1", "rootClass");
                Classification subclass1sub1 = new Classification("subclass1sub1", "subclass1");
                    subclass1sub1.addAlgorithm(new Algorithm("algo_sc1s1", "subclass1sub1"));
                Classification subclass1sub2 = new Classification("subclass1sub2", "subclass1");
                    subclass1sub2.addAlgorithm(new Algorithm("algo_sc1s2", "subclass1sub2"));
                subclass1.addSubclassification(subclass1sub1);
                subclass1.addSubclassification(subclass1sub2);
            Classification subclass2 = new Classification("subclass2", "rootClass");
                Classification subclass2sub1 = new Classification("subclass2sub1", "subclass2");
                    subclass2sub1.addAlgorithm(new Algorithm("algo_sc2s1", "subclass2sub1"));
                    subclass2sub1.addAlgorithm(new Algorithm("algo_sc2s2", "subclass2sub1"));
                Classification subclass2sub2 = new Classification("subclass2sub2", "subclass2");
                subclass2.addSubclassification(subclass2sub1);
                subclass2.addSubclassification(subclass2sub2);
            Classification subclass3 = new Classification("subclass3", "rootClass");
                Classification subclass3sub1 = new Classification("subclass3sub1", "subclass3");
                    Classification subclass3sub1sub1 = new Classification("subclass3sub1sub1", "subclass3sub1");
                        subclass3sub1sub1.addAlgorithm(new Algorithm("algo_sc3s1s1", "subclass3sub1sub1"));
                        subclass3sub1sub1.addAlgorithm(new Algorithm("algo_sc3s1s2", "subclass3sub1sub1"));
                    subclass3sub1.addAlgorithm(new Algorithm("algo_sc3s1", "subclass3sub1"));
                    subclass3sub1.addSubclassification(subclass3sub1sub1);
                subclass3.addSubclassification(subclass3sub1);
            rootClass.addSubclassification(subclass1);
            rootClass.addSubclassification(subclass2);
            rootClass.addSubclassification(subclass3);

        return rootClass;
    }

    @Test
    public void testRemoveClassification() throws SQLException {
        // mock the classification hierarchy response with what's developed in our root classification
        when(algoDAO.getAllAlgorithms()).thenReturn(new ArrayList<>()); // we won't be using this in the test, as classes will hold all our algos
        when(algoDAO.removeAlgorithm(any(String.class))).thenReturn(true);
        when(classDAO.getAllClassifications()).thenReturn(classes);
        when(classDAO.removeClassification(any(String.class))).thenReturn(true);

        RemoveClassificationResponse actualResponse = handler.handle(request);
        RemoveClassificationResponse expectedResponse = new RemoveClassificationResponse("rootClass", 200);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(actualResponse.getHttpCode(), 200);
    }

    @Test
    public void testFailRemoveClassification() throws SQLException {
        // pretend that we can't find the classification to delete
        when(algoDAO.getAllAlgorithms()).thenReturn(new ArrayList<>()); // we won't be using this in the test, as classes will hold all our algos
        when(algoDAO.removeAlgorithm(any(String.class))).thenReturn(true);
        when(classDAO.getAllClassifications()).thenReturn(classes);
        when(classDAO.removeClassification("rootClass")).thenReturn(false);

        RemoveClassificationResponse actualResponse = handler.handle(request);
        RemoveClassificationResponse expectedResponse = new RemoveClassificationResponse(404, "Could not find parent class rootClass to remove.");
        assertEquals(expectedResponse, actualResponse);
        assertEquals(actualResponse.getHttpCode(), 404);
    }

}
