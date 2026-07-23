import java.util.*;

// Observer Pattern - for future notification service
interface IObserver{
    void update(String msg);
}

// Sample observer implementation
class ConsoleNotifier implements IObserver{
    public void update(String msg){
        System.out.println("[Notification] " + msg);
    }
}

// Symbol / Mark Class
class Symbol{
    private char mark;
    public Symbol(char m){
        mark = m;
    }
    public char getMark(){
        return mark;
    }
}

// Board Class - Dumb object that only manages the grid
class Board{
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;

    public Board(int s){
        size = s;
        emptyCell = new Symbol('-');
        grid = new Symbol[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = emptyCell;
            }
        }
    }

    public boolean isCellEmpty(int row, int col){
        if(row < 0 || row >= size 
        || col < 0 || col >= size){
            return false;
        }

        return grid[row][col] == emptyCell;
    }

    public boolean placeMark(int row, int col, Symbol mark){
        if(row < 0 || row >= size || col < 0 || col >= size){
            return false;
        }

        if(!isCellEmpty(row, col)){
            return false;
        }

        grid[row][col] = mark;
        return true;
    }

    public Symbol getCell(int row, int col){
        if(row < 0 || row >= size || col < 0 || col >= size){
            return emptyCell;
        } 
        return grid[row][col];
    }

    public int getSize(){
        return size;
    }

    public Symbol getEmptyCell(){
        return emptyCell;
    }

    public void display(){
        System.out.print("\n ");
        for(int i = 0; i < size; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < size; i++){
            System.out.print(i + " ");
            for(int j = 0; j < size; j++){
                System.out.print(grid[i][j].getMark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



//  Player class
class TicTacToePlayer{
    private int playerID;
    private String name;
    private Symbol symbol;
    private int score;

    public TicTacToePlayer(int playerID, String n, Symbol s){
        this.playerID = playerID;
        name = n;
        symbol = s;
        score = 0;
    }

    public String getName(){
        return name;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(){
        score++;
    }
}



// Strategy Pattern for game rules
interface TicTacToeRules{
    boolean isValidMove(Board board, int row, int col);
    boolean checkWinCondition(Board board, Symbol symbol);
    boolean checkDrawCondition(Board board);
}



// Standard Tic Tac Toe rules



// Game Class -> Observable





// Enum & Factory Pattern for game creation
enum GameType{
    STANDARD
}

class TicTacToeFactory{
    public static TicTacToeGame createGame(GameType gt, int boardSize){
        if(GameType.STANDARD == gt){
            return new TicTacToeGame(boardSize);
        }
        return null;
    }
}


// Main class for Tic Tac Toe
public class TicTacToeMain {
    public static void main(String[] args){

        // Create a game with custom board size

        // Add observer


        // Create players with custom symbols


        // Play the game



    }
}
