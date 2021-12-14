package lambda;
import entities.Algorithm;
import entities.Classification;

import MergeClassification.*;
import db.ClassificationDAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MergeClassificationTest {
    ClassificationDAO  dao;
    String parentName;
    String child1Name;
    String child2Name;
    String child3Name;
    String newName;
    Classification child1;
    Classification child2;
    Classification child3;
    MergeClassificationHandler mcHandler;
    MergeClassificationRequest mcRequest;
    @Before
    public void setup() {
        dao = mock(ClassificationDAO.class);
        child1 = new Classification("test1Name","parent_class");
        child2 =new Classification("test2Name","parent_class");
        child3 = new Classification("test3Name","oops");
        parentName = "parent_class";
        child1Name = "test1Name";
        child2Name = "test2Name";
        child3Name = "test3Name";
        newName = "Success";
        mcHandler = new MergeClassificationHandler(dao);
        mcRequest = new MergeClassificationRequest(child1Name, child2Name,newName);
    }

    @Test
    public void testReclassifyAlgorithm() throws SQLException {
        when(dao.createClassification(newName, parentName)).thenReturn(true);
        when(dao.mergeClassification(child1Name, newName)).thenReturn(true);
        when(dao.mergeClassification(child2Name, newName)).thenReturn(true);
        when(dao.removeClassification(child1Name)).thenReturn(true);
        when(dao.removeClassification(child2Name)).thenReturn(true);
        when(dao.getClassification(child2Name)).thenReturn(child2);
        when(dao.getClassification(child1Name)).thenReturn(child1);
        MergeClassificationResponse handleResult = mcHandler.handle(mcRequest);
        assertEquals(handleResult.response, "Success");
        assertEquals(handleResult.httpCode, 200);
    }

    @Test
    public void testparentsNotEqualReclassifyAlgorithm() throws SQLException {
        when(dao.createClassification(newName, parentName)).thenReturn(true);
        when(dao.mergeClassification(child1Name, newName)).thenReturn(true);
        when(dao.mergeClassification(child2Name, newName)).thenReturn(true);
        when(dao.removeClassification(child1Name)).thenReturn(true);
        when(dao.removeClassification(child2Name)).thenReturn(true);
        when(dao.getClassification(child2Name)).thenReturn(child3);
        when(dao.getClassification(child1Name)).thenReturn(child1);
        MergeClassificationResponse handleResult = mcHandler.handle(mcRequest);
        assertEquals(handleResult.response, "test1Name test2Name");
        assertEquals(handleResult.httpCode, 404);
        assertEquals(handleResult.error, "Classification parents aren't the same.");
    }
    @Test
    public void testAlreadyExistsReclassifyAlgorithm() throws SQLException {
        when(dao.createClassification(newName, parentName)).thenReturn(false);
        when(dao.mergeClassification(child1Name, newName)).thenReturn(true);
        when(dao.mergeClassification(child2Name, newName)).thenReturn(true);
        when(dao.removeClassification(child1Name)).thenReturn(true);
        when(dao.removeClassification(child2Name)).thenReturn(true);
        when(dao.getClassification(child2Name)).thenReturn(child2);
        when(dao.getClassification(child1Name)).thenReturn(child1);
        MergeClassificationResponse handleResult = mcHandler.handle(mcRequest);
        assertEquals(handleResult.response, "Success");
        assertEquals(handleResult.httpCode, 404);
        assertEquals(handleResult.error, "Classification already exists.");
    }

}
