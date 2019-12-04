public class SnakesLadders {
    private static int[] board = new int[101];
    private static boolean turn = true; 
    private static int player1 = 0;
    private static int player2 = 0;
    public SnakesLadders() {}
    public String play(int die1, int die2) {
        //ladders
        board[2] = 38;
        board[7] = 14;
        board[8] = 31;
        board[15] = 26;
        board[28] = 84;
        board[21] = 42;
        board[36] = 44;
        board[51] = 67;
        board[78] = 98;
        board[87] = 94;
        board[71] = 91;
        //snakes
        board[16] = 6;
        board[49] = 11;
        board[46] = 25;
        board[62] = 19;
        board[74] = 53;
        board[64] = 60;
        board[89] = 68;
        board[95] = 75;
        board[99] = 80;
        board[92] = 88;
        for(int i = 0; i < board.length; i++) {
            if(board[i] == 0) board[i] = i;
        }
        boolean temp = turn;
        if((player2 == 100 && player1 != 100) || (player1 == 100 && player2 != 100)) {
            player1 = 0;
            player2 = 0;
            turn = true;
            return "Game over!";
        }

        if(turn) {
            player1 = movePlayer(die1, die2, player1);   
            turn = die1 == die2 ? true : false;
            
        }
        else {
            player2 = movePlayer(die1, die2, player2);    
            turn = die1 == die2 ? false : true;
        }
            
        if(player2 == 100) {
            return "Player 2 Wins!";
        }
        if(player1 == 100) {
            return "Player 1 Wins!";
        }              
        if(player1 != 100 && player2 != 100) {
            return temp ? "Player 1 is on square " + player1 : "Player 2 is on square " + player2;
        }      
        return "Game over!";
    }
    
    public static int movePlayer(int die1, int die2, int player) {
        if(die1==die2) {
            player += die1+die2;
            if(player > 100) {
                player = 100 - (player-100);
            }
            return board[player];
        }       
        player += die1+die2;
        if(player > 100) {
            player = 100 - (player-100);
        }
        return board[player];
    }
}
