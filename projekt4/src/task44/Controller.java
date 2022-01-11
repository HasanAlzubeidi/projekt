package task44;

import java.util.ArrayList;
import java.util.List;



public class Controller {

	private final DAO Db;

    
    public Controller() throws BankDBException {
        Db = new DAO();
    }
    
    public void createRent(int sIdd,  int iIdd) throws InstrumentException 
    {
        String failureMsg = "Could not create account for: ";

        
        try
        {
            Db.rent1(sIdd , iIdd);
        }
        catch (Exception e)
        
        {
            throw new InstrumentException(failureMsg, e);
        }
    }
    
    
    public void terminateRent(int sIdd,  int iIdd) throws InstrumentException 
    {
        String failureMsg = "Could not create account for: ";

        
        try
        {
            Db.terminate(sIdd , iIdd);
        }
        catch (Exception e)
        
        {
            throw new InstrumentException(failureMsg, e);
        }
    }
    
    
    public List<? extends intrument_in_stock > getAllAccounts(String COL) throws  InstrumentException  {
        try {
            return Db.findAllInstrument(COL);
        } catch (Exception e) {
            throw new  InstrumentException ("Unable to list instruments.", e);
        }
    }
        
        
    public StringBuilder ensemble1()
    {
    	 
              return Db.showLesson();
         
    	
    }
    
    
    
    
    
	
	
	
	
}

