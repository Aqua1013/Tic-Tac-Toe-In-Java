public class Main{

    private static boolean playing;
    private static Board board;
    private static Game game;

    public static void main(String[] args) throws InterruptedException {
        //When Program is executed
        board = new Board();
        game = new Game(board);
        System.out.println("TIC TAC TOE: PLAYER vs COMPUTER");
        board.draw();
        int[] turn;
        board.addPlacement(game.promptUserPlacement(), board.getPLAYER());
        board.draw();
        playing = true;
        while(playing){
            game.aiTurn(Board.getBoard());
            if((turn = game.userTurn()) != null){
                board.addPlacement(turn, 'X');
                board.draw();
            }
            else{
                playing = false;
            }   
        }
    }// end Main.main
}// end Main
