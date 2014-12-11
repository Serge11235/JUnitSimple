package ua.pti.myatm;

public class MyATM {

    public static void main(String[] args) {
        double moneyInATM = 1000;
        ATM atm = new ATM(moneyInATM);
        Card card = null;
        atm.validateCard(card, 1234);
        try {
            atm.checkBalance();
            atm.getCash(999.99);
        } catch (Exception exept) {
            System.out.println(exept.getStackTrace());
        }
    }
}
