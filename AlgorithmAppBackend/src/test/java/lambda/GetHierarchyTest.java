package lambda;

import CreateAlgorithm.CreateAlgorithmHandler;
import CreateAlgorithm.CreateAlgorithmRequest;
import CreateAlgorithm.CreateAlgorithmResponse;
import GetHierarchy.GetHierarchyHandler;
import GetHierarchy.GetHierarchyResponse;
import db.AlgorithmDAO;
import db.ClassificationDAO;
import db.ImplementationDAO;
import entities.Algorithm;
import entities.Classification;
import entities.Implementation;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GetHierarchyTest extends LambdaTest {

    AlgorithmDAO algoDao;
    ClassificationDAO classDao;
    ImplementationDAO implDao;
    GetHierarchyHandler handler;
    ArrayList<Algorithm> algos;
    ArrayList<Classification> classes;
    ArrayList<Implementation> impls;
    ArrayList<Classification> hierarchy;


    @Before
    public void setup() {
        algoDao = mock(AlgorithmDAO.class);
        classDao = mock(ClassificationDAO.class);
        implDao = mock(ImplementationDAO.class);
        handler = new GetHierarchyHandler(classDao, algoDao, implDao);
        algos = makeAlgos();
        classes = makeClasses();
        impls = makeImpls();
        hierarchy = makeHierarchy();



    }

    private ArrayList<Implementation> makeImpls() {
        ArrayList<Implementation> ret = new ArrayList<>();
        Implementation IaA = new Implementation("IaA", "sjdf", "ksjdh", "aA");
        Implementation IaA2 = new Implementation("IaA2", "sjdf", "ksjdh", "aA2");
        Implementation IaA1a = new Implementation("IaA1a", "sjdf", "ksjdh", "aA1a");
        Implementation IaA1ab = new Implementation("IaA1ab", "sjdf", "ksjdh", "aA1a");
        ret.add(IaA);
        ret.add(IaA2);
        ret.add(IaA1a);
        ret.add(IaA1ab);
        return ret;
    }

    private ArrayList<Classification> makeClasses() {
        ArrayList<Classification> ret = new ArrayList<>();
        Classification A = new Classification("A", null);
        Classification A1 = new Classification("A1", "A");
        Classification A1a = new Classification("A1a", "A1");
        Classification A1b = new Classification("A1b", "A1");
        Classification A2 = new Classification("A2", "A");
        Classification A3 = new Classification("A3", "A");
        Classification B = new Classification("B", null);
        Classification C = new Classification("C", null);


        ret.add(A);
        ret.add(A1);
        ret.add(A1a);
        ret.add(A1b);
        ret.add(A2);
        ret.add(A3);
        ret.add(B);
        ret.add(C);
        return ret;
    }

    private ArrayList<Algorithm> makeAlgos() {
        ArrayList<Algorithm> ret = new ArrayList<>();

        Algorithm aA = new Algorithm("aA", "A");
        Algorithm aB = new Algorithm("aB", "B");
        Algorithm aA2 = new Algorithm("aA2", "A2");
        Algorithm aA1a = new Algorithm("aA1a", "A1a");
        Algorithm aA1ab = new Algorithm("aA1ab", "A1a");


        ret.add(aA);
        ret.add(aB);
        ret.add(aA2);
        ret.add(aA1a);
        ret.add(aA1ab);

        return ret;
    }

    private ArrayList<Classification> makeHierarchy() {
        ArrayList<Classification> ret = new ArrayList<>();
        Classification A = new Classification("A", null);
            Classification A1 = new Classification("A1", null);
            A.addSubclassification(A1);
                Classification A1a = new Classification("A1a", null);
                A1.addSubclassification(A1a);
                Classification A1b = new Classification("A1b", null);
                A1.addSubclassification(A1b);
            Classification A2 = new Classification("A2", null);
            A.addSubclassification(A2);
            Classification A3 = new Classification("A3", null);
            A.addSubclassification(A3);
        Classification B = new Classification("B", null);
        Classification C = new Classification("C", null);

        Algorithm aA = new Algorithm("aA", null);
        A.addAlgorithm(aA);
        Algorithm aB = new Algorithm("aB", null);
        B.addAlgorithm(aB);
        Algorithm aA2 = new Algorithm("aA2", null);
        A2.addAlgorithm(aA2);
        Algorithm aA1a = new Algorithm("aA1a", null);
        A1a.addAlgorithm(aA1a);
        Algorithm aA1ab = new Algorithm("aA1ab", null);
        A1a.addAlgorithm(aA1ab);

        Implementation IaA = new Implementation("IaA", "sjdf", "ksjdh", null);
        aA.addImplementation(IaA);
        Implementation IaA2 = new Implementation("IaA2", "sjdf", "ksjdh", null);
        aA2.addImplementation(IaA2);
        Implementation IaA1a = new Implementation("IaA1a", "sjdf", "ksjdh", null);
        aA1a.addImplementation(IaA1a);
        Implementation IaA1ab = new Implementation("IaA1ab", "sjdf", "ksjdh", null);
        aA1a.addImplementation(IaA1ab);
        ret.add(A);
        ret.add(B);
        ret.add(C);
        return ret;
    }

    @Test
    public void testGetHierarchy() throws SQLException {
        // add the "child" and make sure we get the correct response
        when(algoDao.getAllAlgorithms()).thenReturn(algos);
        when(classDao.getAllClassifications()).thenReturn(classes);
        when(implDao.getAllImplementation()).thenReturn(impls);

        GetHierarchyResponse handleResponse = handler.handle();

        assertTrue(handleResponse.topClassifications.contains(hierarchy.get(0)));
        assertTrue(handleResponse.topClassifications.contains(hierarchy.get(1)));
        assertTrue(handleResponse.topClassifications.contains(hierarchy.get(2)));
        assertEquals(handleResponse.statusCode, 200);
    }

    @Test
    public void testFailGetHierarchy() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately
       assertTrue(true);
        when(algoDao.getAllAlgorithms()).thenThrow(NullPointerException.class);
        when(classDao.getAllClassifications()).thenReturn(classes);
        when(implDao.getAllImplementation()).thenReturn(impls);
        GetHierarchyResponse handleResponse = handler.handle();
        assertEquals(handleResponse.statusCode, 400);
        assertEquals(handleResponse.error, "Unable to get hierarchy");


    }



}
