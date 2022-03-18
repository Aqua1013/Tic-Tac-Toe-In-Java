public class Board {

    private final static char PLAYER = 'X';
    private final char COMPUTER = 'O';

    private static char[][] board;

    public Board(){
        board = new char[][]{{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    }

    public static void draw() {
        for (char[] i : board) {
            for (char f : i) {
                System.out.print(f + " ");
            }
            System.out.println("\r\n");
        }
    }

    public static char[][] getBoard(){
        return board;
    }

    public void setBoard(char[][] board){
        Board.board = board;
    }

    public static char getPLAYER(){
        return PLAYER;
    }

    public char getCOMPUTER(){
        return COMPUTER;
    }

    public static boolean endGameIfWinner(){
        if (checkIfDraw(board)) {
            Game.endGame('t');
            return true;
        }
        if (checkIfWin(board) == 'X') {
            Game.endGame('p');
            return true;
        }
        if (checkIfWin(board) == 'O') {
            Game.endGame('c');
            return true;
        }
        return false;
    }

    public static void addPlacement(int[] placement, char piece) {
        int row = placement[0];
        int column = placement[1];
        board[row][column] = piece;
    }

    public static boolean positionHasPiece(int[] placement) {
        try {
            return board[placement[0]][placement[1]] == 'X' || board[placement[0]][placement[1]] == 'O';
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Invalid Input! Please try again!");
            System.out.println();
            Game.promptUserPlacement();
            return false;
        }
    }

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

}
