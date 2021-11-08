package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

    // These should never be stored directly in code.  I am doing this quickly complete the
    // demonstration code. The appropriate solution is to store these values in environment
    // variables that are accessed by the Lambda function at run time like this
    //
    //  dbUsername = System.getenv("dbUsername");
    //  dbPassword = System.getenv("dbPassword");
    //  rdsMySqlDatabaseUrl = System.getenv("rdsMySqlDatabaseUrl");
    //
    // https://docs.aws.amazon.com/lambda/latest/dg/env_variables.html
    //
    // The above link shows how to do that.
    public final static String rdsMySqlDatabaseUrl = "nesodb.cii9vvhuk8vg.us-east-2.rds.amazonaws.com";
    public final static String dbUsername = "nesoAdmin";
    public final static String dbPassword = "teamNeso...b21!" ;

    public final static String jdbcTag = "jdbc:mysql://";
    public final static String rdsMySqlDatabasePort = "3306";
    public final static String multiQueries = "?allowMultiQueries=true";

    public final static String dbName = "algoApp";    // default created from MySQL WorkBench

    // pooled across all usages.
    static Connection conn;

    /**
     * Singleton access to DB connection to share resources effectively across multiple accesses.
     */
    protected static Connection connect() throws Exception {
        if (conn != null) { return conn; }

        System.out.println("start connecting......");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    jdbcTag + rdsMySqlDatabaseUrl + ":" + rdsMySqlDatabasePort + "/" + dbName + multiQueries,
                    dbUsername,
                    dbPassword);
            System.out.println("Database has been connected successfully.");
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Failed in database connection");
        }
    }

}
