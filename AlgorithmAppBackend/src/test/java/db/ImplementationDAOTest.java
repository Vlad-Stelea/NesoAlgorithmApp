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

public class ImplementationDAOTest {

    static ImplementationDAO dao;

    @BeforeClass
    public static void setup() throws SQLException {
        dao = new ImplementationDAO();
    }

    @Test
    public void testCreateImplementation() throws SQLException {
        // create an Implementation then check that we can find it in the database
        dao.removeImplementation("daoTest","daoTest");
        assertTrue(dao.createImplementation("daoTest","daoTest","daoTest","daoTest"));
        Implementation expectedImplementation= new Implementation("daoTest","daoTest","daoTest",new Algorithm("daoTest"));
        assertEquals(expectedImplementation, dao.getImplementation("daoTest","daoTest"));

        // clean up
        assertTrue(dao.removeImplementation("daoTest","daoTest"));
    }




}
