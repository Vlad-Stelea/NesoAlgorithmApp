package lambda;

import GetAlgorithmPage.GetAlgorithmPageHandler;
import GetAlgorithmPage.GetAlgorithmPageRequest;
import GetAlgorithmPage.GetAlgorithmPageResponse;
import db.AlgorithmDAO;
import db.ImplementationDAO;
import entities.Algorithm;
import entities.Implementation;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAlgorithmPageTest {
    AlgorithmDAO algoDao;
    ImplementationDAO impDao;
    GetAlgorithmPageHandler handler;
    Algorithm algo;
    ArrayList<Implementation> impls;
    Algorithm expected;


    @Before
    public void setup() {
        algoDao = mock(AlgorithmDAO.class);
        impDao = mock(ImplementationDAO.class);
        handler = new GetAlgorithmPageHandler(algoDao, impDao);
        algo = new Algorithm("efsTest", "efsTopClass");
        impls = makeImps();
        expected = makeAlgoPage();



    }

    private Algorithm makeAlgoPage() {
        Algorithm algo = new Algorithm("efsTest", "efsTopClass");
        Implementation i1 = new Implementation("i1","123","efs","");
        Implementation i2 = new Implementation("i2","skdjf","sdfsd","");
        Implementation i3 = new Implementation("i3","1sdls23","efsdfsdgfs","efsTest");
        Implementation i4 = new Implementation("i4","124659384753","efs","efsTest");
        Implementation i5 = new Implementation("iLongName","google.com","efs","efsTest");

        algo.addImplementation(i1);
        algo.addImplementation(i2);
        algo.addImplementation(i3);
        algo.addImplementation(i4);
        algo.addImplementation(i5);
        return algo;
    }

    private ArrayList<Implementation> makeImps() {
        Implementation i1 = new Implementation("i1","123","efs","efsTest");
        Implementation i2 = new Implementation("i2","skdjf","sdfsd","efsTest");
        Implementation i3 = new Implementation("i3","1sdls23","efsdfsdgfs","efsTest");
        Implementation i4 = new Implementation("i4","124659384753","efs","efsTest");
        Implementation i5 = new Implementation("iLongName","google.com","efs","efsTest");
        ArrayList<Implementation> imps = new ArrayList<>();
        imps.add(i1);
        imps.add(i2);
        imps.add(i3);
        imps.add(i4);
        imps.add(i5);

        return imps;
    }


    @Test
    public void testGetHierarchy() throws SQLException {
        // add the "child" and make sure we get the correct response
        when(algoDao.getAlgorithm("efsTest")).thenReturn(algo);
        when(impDao.getImplementationForAlgo("efsTest")).thenReturn(impls);

        GetAlgorithmPageResponse handleResponse = handler.handle(new GetAlgorithmPageRequest("efsTest"));

        assertTrue(handleResponse.algorithm.equals(expected));
        assertEquals(handleResponse.statusCode, 200);
    }

    @Test
    public void testFailGetHierarchy() throws SQLException {
        // add the classification with a null parent, mock that the parent was added already, and ensure the handler responds appropriately

        when(algoDao.getAllAlgorithms()).thenThrow(NullPointerException.class);
        when(impDao.getImplementationForAlgo("efsTest")).thenReturn(impls);

        GetAlgorithmPageResponse handleResponse = handler.handle(new GetAlgorithmPageRequest("efsTest"));

        assertEquals(handleResponse.statusCode, 400);
        assertEquals(handleResponse.error, "Unable to get Algorithm Page");


    }
}
