package ua.pti.myatm.generateATMexception;

public class NotEnoughtMoneyInAccountException extends Exception {

    public NotEnoughtMoneyInAccountException(String message) {
        super(message);
    }
}
