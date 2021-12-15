package entities;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BenchmarkTests {
    @Test
    public void testSetsAndGets() {
        Benchmark benchmark = new Benchmark();

        String benchId = "bid";
        String benchName = "bName";
        String algoName = "algoName";
        String mConfigName = "mConfigName";
        String implName = "implName";
        String probInstanceName = "pInstanceName";
        Date dateRun = new Date(1);
        Long timeRun = 22L;

        benchmark.setBenchID(benchId);
        benchmark.setBenchName(benchName);
        benchmark.setAlgoName(algoName);
        benchmark.setMachineConfigName(mConfigName);
        benchmark.setImplName(implName);
        benchmark.setProblemInstanceName(probInstanceName);
        benchmark.setDateRun(dateRun);
        benchmark.setTimeToRun(timeRun);

        assertEquals(benchId, benchmark.getBenchID());
        assertEquals(benchName, benchmark.getBenchName());
        assertEquals(algoName, benchmark.getAlgoName());
        assertEquals(mConfigName, benchmark.getMachineConfigName());
        assertEquals(implName, benchmark.getImplName());
        assertEquals(probInstanceName, benchmark.getProblemInstanceName());
        assertEquals(dateRun, benchmark.getDateRun());
        assertTrue(timeRun == benchmark.getTimeToRun());
    }
}
