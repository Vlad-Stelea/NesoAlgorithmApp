package db;


import entities.*;
import db.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AlgorithmDAOTest {

    static AlgorithmDAO dao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new AlgorithmDAO();
    }

    @Test
    public void testCreateAlgorithm() throws SQLException {
        // create an Algorithm then check that we can find it in the database

        assertTrue(dao.createAlgorithm("daoTest", null));
        Algorithm expectedAlgorithm = new Algorithm("daoTest");
        assertEquals(expectedAlgorithm, dao.getAlgorithm("daoTest"));

        // clean up
        assertTrue(dao.removeAlgorithm("daoTest"));
    }

    @Test
    public void testRemoveAlgorithm() throws SQLException {
        dao.removeAlgorithm("daoTest2");
        // create an Algorithm then check that we can find it in the database
        assertTrue(dao.createAlgorithm("daoTest2", null));
        Algorithm expectedAlgorithm = new Algorithm("daoTest2");
        assertEquals(expectedAlgorithm, dao.getAlgorithm("daoTest2"));

        // check that we can remove the Algorithm, then check it doesn't exist
        assertTrue(dao.removeAlgorithm("daoTest2"));
        assertEquals(null, dao.getAlgorithm("daoTest2"));
    }

    @Test
    public void testGetAlgorithm() throws SQLException {
        // shouldn't be able to get a non-existent Algorithm
        assertNull(dao.getAlgorithm("daoTest3"));

        // create an Algorithm then get it from the database
        assertTrue(dao.createAlgorithm("daoTest3", null));
        Algorithm expectedAlgorithm = new Algorithm("daoTest3");
        Algorithm actualAlgorithm = dao.getAlgorithm("daoTest3");
        assertEquals(expectedAlgorithm, actualAlgorithm);

        // clean up
        assertTrue(dao.removeAlgorithm("daoTest3"));
    }

    @Test
    public void testGetAllAlgorithms() throws SQLException {
        // create an Algorithm then get it from the database
        dao.createAlgorithm("daoTest4", null);
        dao.createAlgorithm("daoTest5", null);
        dao.createAlgorithm("daoTest6", null);
        dao.createAlgorithm("daoTest7", null);
        ArrayList<Algorithm> rets = dao.getAllAlgorithms();
        dao.removeAlgorithm("daoTest4");
        dao.removeAlgorithm("daoTest5");
        dao.removeAlgorithm("daoTest6");
        dao.removeAlgorithm("daoTest7");
        ArrayList<Algorithm> retEmpty = dao.getAllAlgorithms();

        assertTrue(rets.size() == 4 + retEmpty.size() );
        boolean four = false;
        boolean five = false;
        boolean six = false;
        boolean seven = false;
        for(int i = 0; i < rets.size(); i++){
            if(rets.get(i).getAlgoName().equals("daoTest4")){
                four = rets.get(i).getParentClassification() == null;
            }else if(rets.get(i).getAlgoName().equals("daoTest5")){
                five = rets.get(i).getParentClassification() == null;
            }else if(rets.get(i).getAlgoName().equals("daoTest6")){
                six = rets.get(i).getParentClassification() == null;
            }else if(rets.get(i).getAlgoName().equals("daoTest7")){
                seven = rets.get(i).getParentClassification() == null;
            }
        }

        // clean up
        assertTrue(four && five && six && seven);
    }

}
