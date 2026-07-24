import java.util.*;

// Observer Pattern
interface IObserver{
    void update(String msg);
}

// Sample Observer Implementation
class SnakeAndLadderConsoleNotifier implements IObserver{
    public void update(String msg){
        System.out.println("[NOTIFICATION] " + msg);
    }
}


// Dice class
class Dice{
    private int faces;

    public Dice(int f){
        faces = f;
    }

    public int roll(){
        return (int)(Math.random() * faces) + 1;
    }
}

// Base class for Snake and Ladder (both have start and end positions)
abstract class BoardEntity{
    protected int startPosition;
    protected int endPosition;

    public BoardEntity(int start, int end){
        startPosition = start;
        // endPosi
    }
}






// Snake class









// Ladder class





// Board class








// Strategy Pattern for Board Setup









// Random Strategy with difficuly levels






// Custom Strategy - User provides count





// Standard Board Strategy - Traditional Snake and Ladder Positions




// Player class





// Strategy Pattern for game rules




// Standard rules


// Game class



// Factory Pattern





// Main class for Snake and Ladder
public class SnakeAndLadder {
    
}
