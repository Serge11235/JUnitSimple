package ua.pti.myatm;

import ua.pti.myatm.generateATMexception.NoCardInsertedException;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInATMexception;
import ua.pti.myatm.generateATMexception.NotEnoughtMoneyInAccountException;

public class ATM {

    private double moneyInATM;
    private Card cardInATM = null;

    ATM(double moneyInATM) {
        this.moneyInATM = moneyInATM;
    }

    public double getMoneyInATM() {
        return moneyInATM;
    }

    public boolean validateCard(Card card, int pinCode) {
        if ((card.isBlocked() == false) && (card.checkPin(pinCode) == true)) {
            cardInATM = card;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() throws NoCardInsertedException {
        if (cardInATM == null) {
            throw new NoCardInsertedException("Card not inserted");
        }
        return cardInATM.getAccount().getBalance();
    }

    public double getCash(double amount) throws NoCardInsertedException, NotEnoughtMoneyInAccountException, NotEnoughtMoneyInATMexception {
        if (cardInATM == null) {
            throw new NoCardInsertedException("Card not inserted");
        }
        if (checkBalance() < amount) {
            throw new NotEnoughtMoneyInAccountException("Not enought money in account");
        }
        if (getMoneyInATM() < amount) {
            throw new NotEnoughtMoneyInATMexception("Not enought money in ATM");
        }

        cardInATM.getAccount().withdrow(amount);
        moneyInATM -= amount;
        return cardInATM.getAccount().getBalance();
    }
}
