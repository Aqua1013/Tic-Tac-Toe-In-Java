import java.util.Random;
import java.util.Scanner;

public class Main{

    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();
    private static Board b = new Board();
    private static Game g;


    public static void main(String[] args) throws InterruptedException {
        //When Program is executed
        g = new Game(b);
        System.out.println("TIC TAC TOE: PLAYER vs COMPUTER");
        b.draw();
        int[] turn;
        b.addPlacement(g.promptUserPlacement(), b.getPLAYER());
        b.draw();
        while(true){
            g.aiTurn(Board.getBoard());
            if((turn = g.userTurn()) == null){
                break;
            }
            b.addPlacement(turn, 'X');
            b.draw();
        }
    }
}
