import java.util.Scanner;
import java.util.Random;

public class Game{

    private static char[][] board;
    private static Board board1;
    private static Scanner sc;
    private static Random rand;

    public Game(Board board){
        Game.board = board.getBoard();
        Game.board1 = board;
        sc = new Scanner(System.in);
        rand = new Random();
    }


    public static int[] promptUserPlacement() {
        int[] temp = {0, 0};
        System.out.println("Enter the row (1-3) to which you would like to place your piece: ");
        temp[0] = sc.nextInt();
        System.out.println("Enter the column (1-3) to which you would like to place your piece: ");
        temp[1] = sc.nextInt();
        temp[0] -= 1;
        temp[1] -= 1;
        if (board1.positionHasPiece(temp)) {
            System.out.println();
            System.out.println("There is already a piece in this position! Please choose another position!");
            System.out.println();
            promptUserPlacement();
        }
        return temp;
    }

    public static int[] userTurn() {
        if(board1.endGameIfWinner()){
            return null;
        }
        return promptUserPlacement();
    }

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

    public static void aiTurn(char[][] board) throws InterruptedException {
        board1.endGameIfWinner();
        if (board1.checkIfWin(board1.getBoard()) == ' ' && !board1.checkIfDraw(board1.getBoard())) {
            Thread.sleep(1000);
            board1.addPlacement(generateAiPlacement(), 'O');
            System.out.println("COMP TURN...");
            board1.draw();
            Thread.sleep(1000);
        }
    }

    public static int[] generateAiPlacement() {
        //placeholder
        int[] aiPlacement = {0, 0};
        aiPlacement[0] = rand.nextInt(3);
        aiPlacement[1] = rand.nextInt(3);
        while (board1.positionHasPiece(aiPlacement)) {
            aiPlacement[0] = rand.nextInt(3);
            aiPlacement[1] = rand.nextInt(3);
        }
        return aiPlacement;
    }


}
