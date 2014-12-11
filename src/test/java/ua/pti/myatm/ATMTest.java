package ua.pti.myatm;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ua.pti.myatm.generateATMexception.NoCardInsertedException;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInATMexception;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInAccountException;

public class ATMTest {

    @Test
    public void testGetMoneyInATMEqualsWithCreatingParam() {
        System.out.println("getMoneyInATM");
        Double expResult = anyDouble();
        ATM atm = new ATM(expResult);
        double result = atm.getMoneyInATM();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testPositiveValidateCard() {
        System.out.println("validateCard");
        Card card = null;
        int pinCode = 0;
        ATM instance = null;
        boolean expResult = false;
        boolean result = instance.validateCard(card, pinCode);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCheckBalance() throws NoCardInsertedException {
        System.out.println("checkBalance");
        ATM instance = null;
        double expResult = 0.0;
        double result = instance.checkBalance();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCash() throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        System.out.println("getCash");
        double amount = 0.0;
        ATM instance = null;
        double expResult = 0.0;
        double result = instance.getCash(amount);
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }

}
