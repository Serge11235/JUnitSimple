package ua.pti.myatm.generateATMexception;

public class NotEnoughtMoneyInATMexception extends Exception {

    public NotEnoughtMoneyInATMexception(String message) {
        super(message);
    }
}
