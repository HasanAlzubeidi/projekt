
/*
 * The MIT License (MIT)
 * Copyright (c) 2020 Leif Lindbäck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction,including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so,subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package task44;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {


    private Connection connection;
    private PreparedStatement showAllTheInstrument;
    private PreparedStatement showAllLesson;
    private PreparedStatement updateQuota;
    private PreparedStatement showQuota;
    private PreparedStatement makeRentInstrument;
    private PreparedStatement updateNumber;
    private PreparedStatement updateNumber1;
    private PreparedStatement updateRentEnd;
    private PreparedStatement updateQuota1;

    public DAO() throws BankDBException {
        try {
            connectToBankDB();
            prepareStatements();
        } catch (ClassNotFoundException | SQLException exception) {
            throw new BankDBException("Could not connect to datasource.", exception);
        }
    }
    
    public List<intrument_in_stock> findAllInstrument(String COL ) throws BankDBException {
        String failureMsg = "Could not list accounts.";
        ResultSet result = null;
        List<intrument_in_stock> instruments = new ArrayList<>();
        try {   showAllTheInstrument.setString(1, COL);
        		 result = showAllTheInstrument.executeQuery(); 
            while (result.next()) {
                instruments.add(new intrument_in_stock (result.getInt(1),
                                                        result.getString(2),
                                                        result.getString(3), 
                                                        result.getString(4),
                                                        result.getInt(5)));
            }
            connection.commit();
        } catch (SQLException sqle) {
            
        	handleException(failureMsg, sqle);
        }
        return instruments;
    }
    
    public void rent1(int sId, int iId)  {
        String failureMsg = "Could not list accounts.";
        ResultSet result = null;
        int result11 = 0;
        int checker=0 ;
        int updatedRows = 0;
        int upp = 0;
       
       
        try { 
        	showQuota.setInt(1, sId);
        	result= showQuota.executeQuery();
            while(result.next())
        	checker=result.getInt(1);
            if(checker==2)
            	System.out.println("YOUR QUOTE IS TWO, YOU CAN NOT RENT MORE");
            else {
            	updateQuota.setInt(1, checker+1);
                updateQuota.setInt(2, sId);
                result11=updateQuota.executeUpdate();
            	makeRentInstrument.setInt(1, sId);	
                makeRentInstrument.setInt(2, iId);	
        	    updatedRows=makeRentInstrument.executeUpdate();
        	    updateNumber.setInt(1, iId);
        	    updateNumber.setInt(2, iId);
        	    upp=updateNumber.executeUpdate();
        	    }
        		 
          
        

        connection.commit();
    } catch (SQLException sqle) {
    	 sqle.printStackTrace();
    }
        
    }

    public void terminate(int sIdf, int iIdf)  {
       
       
        int updatedRows1 = 0;
        int upp123 = 0;
        int result11=0;
       
        try { 
        	
            	
               
                updateRentEnd.setInt(1, iIdf);
                updateRentEnd.setInt(2, sIdf);
        	    updatedRows1= updateRentEnd.executeUpdate();
        	    
        	    
        	    updateQuota1.setInt(1, sIdf);
                updateQuota1.setInt(2, sIdf);
                result11=updateQuota1.executeUpdate();
        	   
        	    updateNumber1.setInt(1, iIdf);
        	    updateNumber1.setInt(2, iIdf);
        	    upp123=updateNumber1.executeUpdate();
        	    
        		 
          
        

        connection.commit();
    } catch (SQLException sqle) {
    	 sqle.printStackTrace();
    }
        
    }
    
    
    
    public StringBuilder showLesson() 
    {
    	StringBuilder container = new StringBuilder();
 
    	 {
    		    try (ResultSet result1 =showAllLesson.executeQuery()) {
    		      while (result1.next()) {
    		    	  container.append("day of this month:" + result1.getString(1) + ", " + "Gener:" +  result1.getString(2) + ", " + "Status: " + result1.getString(3) + "\n" ); 
    		      }
    		    } catch (SQLException sqle) {
    		      System.out.println();
    		    }
    		  }
    	 return container;
    }
    	
    	
    
    
    
    
    
    //hasan
    
    
    private void handleException(String failureMsg, Exception cause) throws BankDBException {
        String completeFailureMsg = failureMsg;
        try {
            connection.rollback();
        } catch (SQLException rollbackExc) {
            completeFailureMsg = completeFailureMsg + 
            ". Also failed to rollback transaction because of: " + rollbackExc.getMessage();
        }

        if (cause != null) {
            throw new BankDBException(failureMsg, cause);
        } else {
            throw new BankDBException(failureMsg);
        }
    }
    
    public void commit() throws BankDBException {
        try {
            connection.commit();
        } catch (SQLException e) {
            handleException("Failed to commit", e);
        }
    }



    private void connectToBankDB() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/soundgood",
                "postgres", "1234");

        connection.setAutoCommit(false);
    }

    private void prepareStatements() throws SQLException
    {
    	
      showAllTheInstrument= connection.prepareStatement("SELECT * \n" +
              "FROM instrument_in_stock \n" +
              "WHERE type_of_instrment =? AND number_of_pcs_in_stock >= 1;\n");
      
      showAllLesson= connection.prepareStatement("Select EXTRACT('day' FROM l.time_slot) AS day , e.gener,CASE \r\n"
      		+ "WHEN (SELECT COUNT(se.lesson_id) \r\n"
      		+ "	 	FROM students_in_ensemble_lessons AS se\r\n"
      		+ "	 	WHERE l.lesson_id = se.lesson_id) \r\n"
      		+ "			= 20 THEN ' full booked'\r\n"
      		+ "			WHEN (SELECT COUNT(se.lesson_id) \r\n"
      		+ "	 	FROM students_in_ensemble_lessons AS se\r\n"
      		+ "	 	WHERE l.lesson_id = se.lesson_id) \r\n"
      		+ "			=19 THEN '1-2 seats left'\r\n"
      		+ "			WHEN (SELECT COUNT(se.lesson_id) \r\n"
      		+ "	 	FROM students_in_ensemble_lessons AS se\r\n"
      		+ "	 	WHERE l.lesson_id = se.lesson_id) \r\n"
      		+ "			=18 THEN '1-2 seats left'\r\n"
      		+ "			 ELSE 'more seats left' END AS status\r\n"
      		+ "\r\n"
      		+ "FROM lesson AS l \r\n"
      		+ "INNER JOIN ensemble_lesson AS e\r\n"
      		+ "ON l.lesson_id=e.lesson_id\r\n"
      		+ "WHERE l.type_of_lesson ='ensemble lesson' AND  EXTRACT ('week' FROM l.time_slot ) = EXTRACT ('week' FROM CURRENT_DATE ) +1\r\n"
      		+ "ORDER BY e.gener, day;\r\n"
      		+ "");
      
      /*
       * 
       * \r\n is a new line 
       */
      updateQuota=connection.prepareStatement("UPDATE student\r\n"
      		+ "SET quota=?  \r\n"
      		+ "WHERE student_id=? \r\n"
      		+ "");
      
      showQuota= connection.prepareStatement("SELECT quota \r\n"
      		+ "FROM student\r\n"
      		+ "WHERE student_id=? \r\n"
      		+ "");
      
      makeRentInstrument=connection.prepareStatement("INSERT INTO renting_instruments VALUES \r\n"
      		+ "(" + " ? " +  "," + " ? "  + ", CURRENT_DATE , CURRENT_DATE + INTERVAL '1 year'  )");
      
     
     
      updateNumber=connection.prepareStatement("UPDATE instrument_in_stock \r\n"
      		+ "SET number_of_pcs_in_stock = (SELECT number_of_pcs_in_stock\r\n"
      		+ "							 FROM instrument_in_stock\r\n"
      		+ "							  WHERE instrument_id= ? \r\n"
      		+ "							 ) - 1 \r\n"
      		+ " WHERE instrument_id= ?");
      
      
      updateRentEnd=connection.prepareStatement("UPDATE renting_instruments\r\n"
      		+ "SET end_instrument_period = CURRENT_DATE \r\n"
      		+ "WHERE instrument_id = ? AND student_id= ? ");
      
    
      updateNumber1=connection.prepareStatement("UPDATE instrument_in_stock \r\n"
        		+ "SET number_of_pcs_in_stock = (SELECT number_of_pcs_in_stock\r\n"
        		+ "							 FROM instrument_in_stock\r\n"
        		+ "							  WHERE instrument_id= ? \r\n"
        		+ "							 ) + 1 \r\n"
        		+ " WHERE instrument_id= ? ");
      
      
      updateQuota1=connection.prepareStatement ("UPDATE student \r\n"
      		+ "SET quota = (SELECT quota \r\n"
      		+ "			FROM student \r\n"
      		+ "			WHERE student_id = ? \r\n"
      		+ "			) - 1 \r\n"
      		+ "WHERE student_id = ?		\r\n"
      		+ "			");
      			
      
    }
    
    
    

	
    
    

}
