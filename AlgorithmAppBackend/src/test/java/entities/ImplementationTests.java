package entities;

import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ImplementationTests {
    @Test
    public void testCreation() {
        String implName = "implname";
        String codeUrl = "codeUrl";
        String language = "language";
        String algoName = "algoName";
        List<Benchmark> benchmarks = Arrays.asList(new Benchmark("bId", "bName", 22, new Date(0), "algoName", "implName", "machineConfig", "probInstanceName"));

        Implementation impl = new Implementation(implName, codeUrl, language, algoName, benchmarks);

        assertEquals(implName, impl.getImplName());
        assertEquals(codeUrl, impl.getCodeURL());
        assertEquals(language, impl.getLanguage());
        assertEquals(algoName, impl.getAlgorithmName());
        assertEquals(benchmarks, impl.getBenchmark());

        // Test the sets too :)
        implName = "implname2";
        codeUrl = "codeUrl2";
        language = "language2";
        algoName = "algoName2";
        benchmarks = Arrays.asList(new Benchmark("bId2", "bName", 22, new Date(0), "algoName", "implName", "machineConfig", "probInstanceName"));

        impl.setImplName(implName);
        impl.setCodeURL(codeUrl);
        impl.setLanguage(language);
        impl.setAlgorithmName(algoName);
        impl.setBenchmark(benchmarks);

        assertEquals(implName, impl.getImplName());
        assertEquals(codeUrl, impl.getCodeURL());
        assertEquals(language, impl.getLanguage());
        assertEquals(algoName, impl.getAlgorithmName());
        assertEquals(benchmarks, impl.getBenchmark());
    }
}
