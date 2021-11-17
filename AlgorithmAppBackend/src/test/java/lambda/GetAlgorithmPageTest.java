package lambda;

import GetAlgorithmPage.GetAlgorithmPageHandler;
import GetAlgorithmPage.GetAlgorithmPageRequest;
import GetAlgorithmPage.GetAlgorithmPageResponse;
import db.AlgorithmDAO;
import db.ImplementationDAO;
import db.MachineConfigurationDAO;
import db.ProblemInstanceDAO;
import entities.*;
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
    MachineConfigurationDAO machDAO;
    ProblemInstanceDAO probDAO;
    GetAlgorithmPageHandler handler;
    Algorithm algo;
    ArrayList<Implementation> impls;
    ArrayList<ProblemInstance> probs;
    ArrayList<MachineConfiguration> machs;
    AlgorithmPage expected;


    @Before
    public void setup() {
        algoDao = mock(AlgorithmDAO.class);
        impDao = mock(ImplementationDAO.class);
        machDAO = mock(MachineConfigurationDAO.class);
        probDAO = mock(ProblemInstanceDAO.class);
        handler = new GetAlgorithmPageHandler(algoDao, impDao, probDAO, machDAO);
        algo = new Algorithm("efsTest", "efsTopClass");
        impls = makeImps();
        probs = makeProbs();
        machs = makeMachs();

        expected = makeAlgoPage();




    }

    private ArrayList<MachineConfiguration> makeMachs() {
        MachineConfiguration m1 = new MachineConfiguration("m1", "m1", 1, 2, "jfvh", 4);
        MachineConfiguration m2 = new MachineConfiguration("m2", "m2", 10, 2, "jfvh", 4);
        MachineConfiguration m3 = new MachineConfiguration("m3", "m3", 100, 2, "jfvh", 4);
        ArrayList<MachineConfiguration> ms = new ArrayList<>();
        ms.add(m1);
        ms.add(m2);
        ms.add(m3);
        return ms;
    }

    private ArrayList<ProblemInstance> makeProbs() {
        ProblemInstance p1 = new ProblemInstance("p1", "p1", "pdfgdfg", "efsTest");
        ProblemInstance p2 = new ProblemInstance("p2", "p2", "pdfgdfg", "efsTest");
        ProblemInstance p3 = new ProblemInstance("p3", "p3", "bbb", "efsTest");

        ArrayList<ProblemInstance> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        return ps;
    }

    private AlgorithmPage makeAlgoPage() {
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

        ProblemInstance p1 = new ProblemInstance("p1", "p1", "pdfgdfg", "efsTest");
        ProblemInstance p2 = new ProblemInstance("p2", "p2", "pdfgdfg", "");
        ProblemInstance p3 = new ProblemInstance("p3", "p3", "bbb", "efsTest");

        ArrayList<ProblemInstance> ps = new ArrayList<>();
        ps.add(p1);
        ps.add(p2);
        ps.add(p3);
        algo.setProblemInstances(ps);

        MachineConfiguration m1 = new MachineConfiguration("m1", "m1", 1, 2, "jfvh", 4);
        MachineConfiguration m2 = new MachineConfiguration("m2", "m2", 10, 2, "jfvh", 4);
        MachineConfiguration m3 = new MachineConfiguration("m3", "m3", 100, 2, "jfvh", 4);
        ArrayList<MachineConfiguration> ms = new ArrayList<>();
        ms.add(m1);
        ms.add(m2);
        ms.add(m3);

        AlgorithmPage page = new AlgorithmPage();
        page.setMachineConfigurations(ms);
        page.setAlgorithm(algo);

        return page;
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
        when(probDAO.getAllAlgosProblemInstances("efsTest")).thenReturn(probs);
        when(machDAO.getAllMachineConfigurations()).thenReturn(machs);


        GetAlgorithmPageResponse handleResponse = handler.handle(new GetAlgorithmPageRequest("efsTest"));

        assertTrue(handleResponse.algorithmPage.equals(expected));
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
