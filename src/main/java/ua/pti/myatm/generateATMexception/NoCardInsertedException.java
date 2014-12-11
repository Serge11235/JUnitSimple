package ua.pti.myatm.generateATMexception;

public class NoCardInsertedException extends Exception {

    public NoCardInsertedException(String message) {
        super(message);
    }
}
