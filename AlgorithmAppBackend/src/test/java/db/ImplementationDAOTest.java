package db;


import db.*;
import entities.Algorithm;
import entities.Implementation;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ImplementationDAOTest {


    static ImplementationDAO dao;
    static AlgorithmDAO algoDao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new ImplementationDAO();
        algoDao = new AlgorithmDAO();
    }

    @Test
    public void testCreateImplementation() throws SQLException {
        // create an Implementation then check that we can find it in the database
        dao.removeImplementation("daoTest","daoTest");
        algoDao.removeAlgorithm("daoTest");
        assertTrue(algoDao.createAlgorithm("daoTest", null));
        assertTrue(dao.createImplementation("daoTest","daoTest","daoTest","daoTest"));
        assertTrue(dao.hasImplementation("daoTest", "daoTest"));
        Implementation expectedImplementation1 = new Implementation("daoTest", "daoTest", "daoTest", "daoTest");
        assertTrue(dao.getImplementationForAlgo("daoTest").contains(expectedImplementation1));

        // clean up
        Optional<Implementation> retrieved = dao.getImplementation("daoTest","daoTest");
        assertTrue(dao.removeImplementationsByAlgorithm("daoTest"));
        assertFalse(dao.removeImplementation("daoTest","daoTest"));
        assertTrue(algoDao.removeAlgorithm("daoTest"));

        //test after so the clean up always runs
        Implementation expectedImplementation2 = new Implementation("daoTest","daoTest","daoTest","daoTest");
        assertEquals(expectedImplementation2, retrieved.get());

    }





}
