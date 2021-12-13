package lambda;

import GetAlgorithmPage.GetAlgorithmPageHandler;
import GetAlgorithmPage.GetAlgorithmPageRequest;
import GetAlgorithmPage.GetAlgorithmPageResponse;
import db.*;
import entities.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAlgorithmPageTest {
    AlgorithmDAO algoDao;
    ImplementationDAO impDao;
    MachineConfigurationDAO machDAO;
    ProblemInstanceDAO probDAO;
    BenchmarkDAO bmDAO;

    GetAlgorithmPageHandler handler;
    Algorithm algo;
    ArrayList<Implementation> impls;
    ArrayList<ProblemInstance> probs;
    ArrayList<MachineConfiguration> machs;
    ArrayList<Benchmark> bms;


    AlgorithmPage expected;


    @Before
    public void setup() {
        algoDao = mock(AlgorithmDAO.class);
        impDao = mock(ImplementationDAO.class);
        machDAO = mock(MachineConfigurationDAO.class);
        probDAO = mock(ProblemInstanceDAO.class);
        bmDAO = mock(BenchmarkDAO.class);
        handler = new GetAlgorithmPageHandler(algoDao, impDao, probDAO, machDAO, bmDAO);
        algo = new Algorithm("efsTest", "efsTopClass");
        impls = makeImps();
        probs = makeProbs();
        machs = makeMachs();
        bms = makeBms();

        expected = makeAlgoPage();




    }

    private ArrayList<Benchmark> makeBms() {
        Benchmark b1 = new Benchmark("b1","b1",5,new Date(111, 11,11),"efsTest","i1","m1","p1");
        Benchmark b2 = new Benchmark("b2","b2",5,new Date(111,11,11),"efsTest","i1","m2","p2");
        Benchmark b3 = new Benchmark("b3","b3",5,new Date(111,11,11),"efsTest","i1","m3","p1");
        Benchmark b4 = new Benchmark("b4","b4",5,new Date(111,11,11),"efsTest","i2","m1","p1");
        Benchmark b5 = new Benchmark("b5","b5",5,new Date(111,11,11),"efsTest","i2","m2","p1");
        ArrayList<Benchmark> bms = new ArrayList<>();
        bms.add(b1);
        bms.add(b2);
        bms.add(b3);
        bms.add(b4);
        bms.add(b5);
        return bms;
    }

    private ArrayList<MachineConfiguration> makeMachs() {
        MachineConfiguration m1 = new MachineConfiguration("m1", 1, 2, "jfvh", 4);
        MachineConfiguration m2 = new MachineConfiguration("m2", 10, 2, "jfvh", 4);
        MachineConfiguration m3 = new MachineConfiguration("m3", 100, 2, "jfvh", 4);
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

        MachineConfiguration m1 = new MachineConfiguration("m1", 1, 2, "jfvh", 4);
        MachineConfiguration m2 = new MachineConfiguration("m2", 10, 2, "jfvh", 4);
        MachineConfiguration m3 = new MachineConfiguration("m3", 100, 2, "jfvh", 4);
        ArrayList<MachineConfiguration> ms = new ArrayList<>();
        ms.add(m1);
        ms.add(m2);
        ms.add(m3);

        Benchmark b1 = new Benchmark("b1","b1",5,new Date(111, 11,11),"efsTest","i1","m1","p1");
        Benchmark b2 = new Benchmark("b2","b2",5,new Date(111,11,11),"efsTest","i1","m2","p2");
        Benchmark b3 = new Benchmark("b3","b3",5,new Date(111,11,11),"efsTest","i1","m3","p1");
        Benchmark b4 = new Benchmark("b4","b4",5,new Date(111,11,11),"efsTest","i2","m1","p1");
        Benchmark b5 = new Benchmark("b5","b5",5,new Date(111,11,11),"efsTest","i2","m2","p1");

        i1.addBenchmark(b1);
        i1.addBenchmark(b2);
        i1.addBenchmark(b3);
        i2.addBenchmark(b4);
        i2.addBenchmark(b5);

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
        when(algoDao.getAlgorithm("efsTest")).thenReturn(algo);
        when(impDao.getImplementationForAlgo("efsTest")).thenReturn(impls);
        when(probDAO.getAllAlgosProblemInstances("efsTest")).thenReturn(probs);
        when(machDAO.getAllMachineConfigurations()).thenReturn(machs);
        when(bmDAO.getBenchmarkForAlgo("efsTest")).thenReturn(bms);


        GetAlgorithmPageResponse handleResponse = handler.handle(new GetAlgorithmPageRequest("efsTest"));

        assertTrue(handleResponse.algorithmPage.equals(expected));
        assertEquals(handleResponse.statusCode, 200);
    }

    @Test
    public void testFailGetHierarchy() throws SQLException {
        when(algoDao.getAllAlgorithms()).thenThrow(NullPointerException.class);
        when(impDao.getImplementationForAlgo("efsTest")).thenReturn(impls);

        GetAlgorithmPageResponse handleResponse = handler.handle(new GetAlgorithmPageRequest("efsTest"));

        assertEquals(handleResponse.statusCode, 400);
        assertTrue(handleResponse.error.contains("Unable to get Algorithm Page: efsTest"));


    }
}
