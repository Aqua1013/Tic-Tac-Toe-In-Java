import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void main(String[] args) {
        //When Program is executed
        startGame();
    }

    // Starts Game
    public static void startGame() {
        System.out.println("TIC TAC TOE: PLAYER vs COMPUTER");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        draw(board);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        promptUserPlacement();
    }

    // takes in the char[][] board and prints to screen
    public static void draw(char[][] board) {
        for (char[] i : board) {
            for (char f : i) {
                System.out.print(f + " ");
            }
            System.out.println("\r\n");
        }
    }

    //get the user's desired piece placement
    public static void promptUserPlacement() {
        int[] temp = {0, 0};
        System.out.println("Enter the row (1-3) to which you would like to place your piece: ");
        temp[0] = sc.nextInt();
        System.out.println("Enter the column (1-3) to which you would like to place your piece: ");
        temp[1] = sc.nextInt();
        temp[0] -= 1;
        temp[1] -= 1;
        if (positionHasPiece(temp)) {
            System.out.println();
            System.out.println("There is already a piece in this position! Please choose another position!");
            System.out.println();
            promptUserPlacement();
        }
        addPlacement(temp, 'X');
        try {
            aiTurn(board);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //game loops
    public static void userTurn() {
        if (checkIfDraw(board)) {
            endGame('t');
        }
        if (checkIfWin(board) == 'X') {
            endGame('p');
        }
        if (checkIfWin(board) == 'O') {
            endGame('c');
        } else if (checkIfWin(board) == ' ' && !checkIfDraw(board)) {
            promptUserPlacement();
        }
    }

    // adds a piece to the char[][] board and prints new board to screen
    public static void addPlacement(int[] placement, char piece) {
        int row = placement[0];
        int column = placement[1];
        board[row][column] = piece;
        draw(board);
    }

    //game loops
    public static void aiTurn(char[][] board) throws InterruptedException {
        if (checkIfDraw(board)) {
            endGame('t');
        }
        if (checkIfWin(board) == 'X') {
            endGame('p');
        }
        if (checkIfWin(board) == 'O') {
            endGame('c');
        } else if (checkIfWin(board) == ' ' && !checkIfDraw(board)) {
            aiTurnAnimation();
            Thread.sleep(1000);
            addPlacement(generateAiPlacement(), 'O');
            Thread.sleep(1000);
            userTurn();
        }
    }

    // cool animation thing in between turns
    public static void aiTurnAnimation() {
        System.out.println();
        System.out.println("COMPUTER'S TURN...");
        System.out.println();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this isn't an ai, it just generates two ints which for board placements
    public static int[] generateAiPlacement() {
        //placeholder
        int[] aiPlacement = {0, 0};
        aiPlacement[0] = rand.nextInt(3);
        aiPlacement[1] = rand.nextInt(3);
        while (positionHasPiece(aiPlacement)) {
            aiPlacement[0] = rand.nextInt(3);
            aiPlacement[1] = rand.nextInt(3);
        }
        return aiPlacement;
    }

    // check if a position already has a piece
    public static boolean positionHasPiece(int[] placement) {
        try {
            return board[placement[0]][placement[1]] == 'X' || board[placement[0]][placement[1]] == 'O';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Invalid Input! Please try again!");
            System.out.println();
            promptUserPlacement();
            return false;
        }
    }

    // Loops through each possible win situation and returns piece which wins. Returns ' ' if no one has won
    public static char checkIfWin(char[][] board) {
        //Check each row
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }

        //Check each column
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j];
            }
        }

        //Check the diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        //Otherwise nobody has not won yet
        return ' ';
    }

    // checks if there is a draw by looping through board
    public static boolean checkIfDraw(char[][] board) {
        for (char[] i : board) {
            for (char f : i) {
                if (f == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // ends game
    public static void endGame(char ending) {
        switch (ending) {
            case 't':
                System.out.println();
                System.out.print("Game Over! It's a Tie!");
                break;
            case 'p':
                System.out.println();
                System.out.print("Game Over! Player Wins!");
                break;
            case 'c':
                System.out.println();
                System.out.print("Game Over! Computer Wins!");
                break;
        }
    }
}
