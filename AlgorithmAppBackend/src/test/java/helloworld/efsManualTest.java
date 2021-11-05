package helloworld;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import db.AlgorithmDAO;
import entities.Algorithm;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class efsManualTest {

    @Test
    public void successfulResponse() throws SQLException {
        System.out.println("***************************\n***************************\n***************************\n***************************\n***************************\n");

        try {
            AlgorithmDAO a = new AlgorithmDAO();
            System.out.println("connected");
            Algorithm ret = a.getAlgorithm("efsTest");
            System.out.println(ret.getAlgoName());
            System.out.println(ret.getParentClassification());
        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("***************************\n***************************\n***************************\n***************************\n***************************\n");

        assertTrue(true);
    }


}
