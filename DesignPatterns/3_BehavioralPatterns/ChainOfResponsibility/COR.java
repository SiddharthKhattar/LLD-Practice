abstract class MoneyHandler{
    protected MoneyHandler nextHandler;

    public MoneyHandler(){
        this.nextHandler = null;
    }

    public void setNextHandler(MoneyHandler next){
        this.nextHandler = next;
    }

    public abstract void dispense(int amount);
}

class ThousandHandler extends MoneyHandler{
    private int numNotes;

    public ThousandHandler(int numNotes){
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount){
        int notesNeeded = amount / 1000;

        if(notesNeeded > numNotes){
            notesNeeded = numNotes;
            numNotes = 0;
        }else numNotes -= notesNeeded;

        if(notesNeeded > 0) System.out.println("Dispensing: " + notesNeeded + " x Rs.1000 notes");

        int remainingAmount = amount - (notesNeeded * 1000);
        if(remainingAmount > 0){
            if(nextHandler != null) nextHandler.dispense(remainingAmount);
            else System.out.println("Remaining amount: " + remainingAmount + "cannot be fulfilled");
        }
    }
}



class FiveHundredHandler extends MoneyHandler{
    private int numNotes;

    public FiveHundredHandler(int numNotes){
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount){
        int notesNeeded = amount / 500;

        if(notesNeeded > numNotes){
            notesNeeded = numNotes;
            numNotes = 0;
        }else numNotes -= notesNeeded;

        if(notesNeeded > 0) System.out.println("Dispensing: " + notesNeeded + " x Rs.500 notes");

        int remainingAmount = amount - (notesNeeded * 500);
        if(remainingAmount > 0){
            if(nextHandler != null) nextHandler.dispense(remainingAmount);
            else System.out.println("Remaining amount: " + remainingAmount + "cannot be fulfilled");
        }
    }
}

class HundredHandler extends MoneyHandler{
    private int numNotes;

    public HundredHandler(int numNotes){
        this.numNotes = numNotes;
    }

    @Override
    public void dispense(int amount){
        int notesNeeded = amount / 100;

        if(notesNeeded > numNotes){
            notesNeeded = numNotes;
            numNotes = 0;
        }else numNotes -= notesNeeded;

        if(notesNeeded > 0) System.out.println("Dispensing: " + notesNeeded + " x Rs.100 notes");

        int remainingAmount = amount - (notesNeeded * 100);
        if(remainingAmount > 0){
            if(nextHandler != null) nextHandler.dispense(remainingAmount);
            else System.out.println("Remaining amount: " + remainingAmount + "cannot be fulfilled");
        }
    }
}



public class COR{
    public static void main(String[] args){
        MoneyHandler thousandHandler = new ThousandHandler(3);
        MoneyHandler fiveHundredHandler = new FiveHundredHandler(10);
        MoneyHandler hundredHandler = new HundredHandler(20);

        thousandHandler.setNextHandler(fiveHundredHandler);
        fiveHundredHandler.setNextHandler(hundredHandler);

        int amountToWithdraw = 4000;
        System.out.println("Dispensing Amount: Rs " + amountToWithdraw);
        thousandHandler.dispense(amountToWithdraw);
    }
}