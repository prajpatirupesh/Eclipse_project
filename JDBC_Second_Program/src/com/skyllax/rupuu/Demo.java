package com.skyllax.rupuu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet res;

    public static void main(String[] args) 
    {
       // MyConnector.fetchData();
//    	MyConnector.fetchDataById();
     //  MyConnector.close(res, stmt, con);
    
//    	MyConnector.insertData();
    	//MyConnector.updateData();
//    	MyConnector.deleteData();
//  	MyConnector.insertMulData();
   // 	MyConnector.fetchMetaData();
    //	MyConnector.fetchDataUsingStoreProcedure();
    //	MyConnector.fetchSpecificDataUsingStoreProcedure();
    	MyConnector.getCourseEnrollCount();
    }
}
