package task44;

public class Main {
    
    public static void main(String[] args) {
        try {
        new BlockingInterpreter(new Controller()).handleCmds();
        } catch(BankDBException bdbe) {
            System.out.println("Could not connect to Soundgood db.");
            bdbe.printStackTrace();
        }
    }
}
