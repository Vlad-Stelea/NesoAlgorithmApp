package helloworld;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import db.AlgorithmDAO;
import entities.Algorithm;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class efsManualTest {

    @Test
    public void successfulResponse() throws SQLException {
        System.out.println("***************************\n***************************\n***************************\n***************************\n***************************\n");

        try {
            AlgorithmDAO a = new AlgorithmDAO();
            a.createAlgorithm("efsTestCrete", null);
            ArrayList<Algorithm> ret1 = a.getAllAlgorithms();
            System.out.println("first");
            for(int i = 0; i < ret1.size(); i++) {
                System.out.println(ret1.get(i).getAlgoName());
                System.out.println(ret1.get(i).getParentClassification());
            }
            a.removeAlgorithm("efsTestCrete");
            ArrayList<Algorithm> ret2 = a.getAllAlgorithms();
            System.out.println("second");
            for(int i = 0; i < ret2.size(); i++) {
                System.out.println(ret2.get(i).getAlgoName());
                System.out.println(ret2.get(i).getParentClassification());
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("***************************\n***************************\n***************************\n***************************\n***************************\n");

        assertTrue(false);
    }


}
