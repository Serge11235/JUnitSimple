package ua.pti.myatm;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ua.pti.myatm.generateATMexception.NoCardInsertedException;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInATMexception;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInAccountException;

public class ATMTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeMoneyInATMThrownIllegalArgumentException() {
        ATM myatm = new ATM(-1);
    }

    @Test
    public void testATMconstructorParamEqualsGetMoney() {
        double positiveMoney = 1000.;
        ATM myatm = new ATM(positiveMoney);
        assertTrue(myatm.getMoneyInATM() == positiveMoney);
    }

    @Test
    public void testGetMoneyInATMEqualsWithCreatingParam() {
        Double expResult = 100.;
        ATM atm = new ATM(expResult);
        double result = atm.getMoneyInATM();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testValidateCardPositiveCase() {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        assertTrue(atm.validateCard(card, 1234));
    }

    @Test
    public void testValidateCardNegativeCase() {
        ATM atm = new ATM(1000);
        Card card = mock(Card.class);
        when(card.checkPin(0000)).thenReturn(false);
        when(card.isBlocked()).thenReturn(false);
        assertFalse(atm.validateCard(card, 0000));
    }

    @Test
    public void testCheckBalanceReturnsParamEqualsConstructor() throws NoCardInsertedException {
        double expResult = 1000.0;
        ATM atm = new ATM(expResult);
        Card card = mock(Card.class);		
        Account account = mock(Account.class);
        
        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        when(account.getBalance()).thenReturn(expResult);

        assertEquals(expResult, card.getAccount().getBalance(), 0.01);
    }

   /* @Test
    public void testGetCash() throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        double expResult = 1000.0;
        ATM atm = new ATM(expResult);
        Card card = mock(Card.class);		
        Account account = mock(Account.class);
        
        when(card.checkPin(1234)).thenReturn(true);
        when(card.isBlocked()).thenReturn(false);
        when(card.getAccount()).thenReturn(account);
        
    }*/
    
    
	@Test (expected = NoCardInsertedException.class)
	public void getCashNoCardInATMNoCardInATMException() 
			throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception{
		ATM atm = new ATM(1000);
		atm.getCash(50);

	}
	
	@Test (expected = NotEnoughtMoneyInAccountException.class)
	public void getCashCardInATMEnoughtMoneyInATMNotEnoughtMoneyInAccountNotEnoughtMoneyInAccountException() 
			throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception{
		ATM atm = spy(new ATM(1000));
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 10;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111));
		
		atm.getCash(50);
	}
	
	@Test (expected = NotEnoughtMoneyInATMexception.class)
	public void getCashCardInATMNotEnoughtMoneyInATMEnoughtMoneyInAccountNotEnoughtMoneyInATMexception() 
			throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception{
		ATM atm = spy(new ATM(1000));
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 10000;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111)); 
		
		atm.getCash(5000);
	} 

}
