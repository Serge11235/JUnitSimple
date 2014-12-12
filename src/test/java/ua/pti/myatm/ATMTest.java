package ua.pti.myatm;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;
import ua.pti.myatm.generateATMexception.*;

public class ATMTest {

//check  ATM initialisation
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeMoneyInATMThrownIllegalArgumentException() {
        ATM myatm = new ATM(-1);
    }

    @Test
    public void testATMconstructorParamEqualsGetMoney() {
        double expResult = 1000.;
        ATM myatm = new ATM(expResult);
        double result = myatm.getMoneyInATM();
        assertEquals(result, expResult, 0.0001);
    }

    @Test
    public void testGetMoneyInATMEqualsWithCorrectCreatingParam() {
        Double expResult = 100.;
        ATM atm = new ATM(expResult);
        double result = atm.getMoneyInATM();
        assertEquals(expResult, result, 0.0001);
    }

//check Validate    
    @Test
    public void testValidateCardPositiveCase() {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        assertTrue(atm.validateCard(card, 1234));
    }

    @Test
    public void testValidateCardUncorrectPin() {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(0000)).thenReturn(false);
        when(card.isBlocked()).thenReturn(false);
        assertFalse(atm.validateCard(card, 0000));
    }

    @Test
    public void testValidateCardIsBlockedTrue() {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(0000)).thenReturn(true);
        when(card.isBlocked()).thenReturn(true);
        assertFalse(atm.validateCard(card, 0000));
    }

//check Balance
    @Test
    public void testCheckBalanceParameterEqualsConstructor() throws NoCardInsertedException {
        double expResult = 1000.0;
        ATM atm = new ATM(expResult);
        Card card = mock(Card.class);
        Account account = mock(Account.class);

        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(expResult);
        double result = card.getAccount().getBalance();

        assertEquals(expResult, result, 0.0001);
    }

    @Test(expected = NoCardInsertedException.class)
    public void testCheckBalanceNoCardInATMException()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(1234)).thenReturn(false);
        when(card.isBlocked()).thenReturn(true);
        double result = atm.checkBalance();
    }

    //check getCash
    @Test
    public void testGetCashCorrectCase()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        double expResult = 950.;
        double ammount = 50.;
        ATM atm = new ATM(1000.);
        Card card = mock(Card.class);
        Account account = mock(Account.class);

        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(expResult);

        atm.validateCard(card, 1234);
        double result = atm.getCash(ammount);

        assertEquals(expResult, result, 0.0001);
        verify(account).withdrow(ammount);
    }

    @Test(expected = NoCardInsertedException.class)
    public void testGetCashWithoutValidateNoCardInATMException()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        ATM atm = new ATM(1000);
        atm.getCash(50);
    }

    @Test(expected = NotEnoughtMoneyInAccountException.class)
    public void testGetCashNotEnoughtMoneyInAccountException()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        Account account = mock(Account.class);
        double expectedBalanse = 500;

        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(expectedBalanse);

        atm.validateCard(card, 1234);
        atm.getCash(501);
    }

    @Test(expected = NotEnoughtMoneyInATMexception.class)
    public void testGetCashNotEnoughtMoneyInATMexception()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        Account account = mock(Account.class);
        double expectedBalanse = 2000;

        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(expectedBalanse);

        atm.validateCard(card, 1234);
        atm.getCash(1001);
    }

//other tests
    @Test(timeout = 10)
    public void testATMconstructorTimeLimits() {
        double init = 5000;
        ATM atm0 = new ATM(init);
        ATM atm1 = new ATM(init);
        ATM atm2 = new ATM(init);
        ATM atm3 = new ATM(init);
        ATM atm4 = new ATM(init);
        ATM atm5 = new ATM(init);
        ATM atm6 = new ATM(init);
        ATM atm7 = new ATM(init);
        ATM atm8 = new ATM(init);
        ATM atm9 = new ATM(init);
        ATM atm10 = new ATM(init);
    }

    @Test
    public void testGetCashMethodsCallsInCorrectOrder()
            throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        double moneyOnATM = 10000;
        double moneyOnAccount = 1000;
        double ammount = 100;
        ATM atm = new ATM(moneyOnATM);
        Account account = mock(Account.class);
        Card card = mock(Card.class);

        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(moneyOnAccount);
        atm.validateCard(card, 1234);
        atm.getCash(ammount);

        InOrder order = inOrder(account);
        order.verify(account).getBalance();
        order.verify(account).withdrow(ammount);
    }

}
