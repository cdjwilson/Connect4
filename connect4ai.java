import java.util.Random;

public class connect4ai {

    static int threeInARow(int[][] board, int playernum) {
        int result = -1;
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    int player = board[i][j];
                    if (j < board[i].length - 3) {
                        if (player == board[i][j+1] && player == board[i][j+2]) {
                            if (board[i][j+3] == 0) {
                                if (i == board.length-1) {
                                    result = j+3;
                                    if (player == playernum) {
                                        return result;
                                    }   
                                } else if (board[i+1][j+3] != 0) {
                                    result = j+3;
                                    if (player == playernum) {
                                        return result;
                                    }
                                }
                            }
                        }
                    }
                    if (i > 2) {
                        if (player == board[i-1][j] && player == board[i-2][j] && board[i-3][j] == 0) {
                            result = j;
                            if (player == playernum) {
                                return result;
                            }
                        }
                    }
                    if (i > 2 && j < board[i].length - 3) {
                        if (player == board[i-1][j+1] && player == board[i-2][j+2] && board[i-2][j+3] != 0 && board[i-3][j+3] == 0) {
                            result = j+3;
                            if (player == playernum) {
                                return result;
                            }
                        }
                    }
                    if (i > 2 && j > 2) {
                        if (player == board[i-1][j-1] && player == board[i-2][j-2] && board[i-2][j-3] != 0 && board[i-3][j-3] == 0) {
                            result = j-3;
                            if (player == playernum) {
                                return result;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    static int easyAi(int[][] board, int player) {
        int move = threeInARow(board, player);
        if (move != -1) {
            return move;
        }
        Random rand = new Random();
        while (true) {
            move = rand.nextInt(board[0].length);
            if(board[0][move] == 0) {
                return move;
            }
        }
    }


    static int getBestMove(int[][] board) {
        int move = 1;
        
        return move;
    }

}
