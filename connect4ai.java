import java.util.Random;

import javax.sound.midi.MidiEvent;

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

    static void placeIndex(int[][] board, int index, int val) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][index] == 0) {
                board[i][index] = val;
                return;
            }
        }
    }

    static void removeIndex(int[][] board, int index) {
        for (int i = 0; i < 7; i++) {
            if (board[i][index] != 0) {
                board[i][index] = 0;
                return;
            }
        }
    }

    static int three(int[][] board, int player) {
        int value = 0;
        boolean isPlayer = false;
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (j < board[i].length - 3) {
                    int count = 0;
                    if (board[i][j] != 0) {
                        count++;
                        if (board[i][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i][j+1] != 0) {
                        count++;
                        if (board[i][j+1] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i][j+2] != 0) {
                        count++;
                        if (board[i][j+2] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i][j+3] != 0) {
                        count++;
                        if (board[i][j+3] == player) {
                            isPlayer = true;
                        }
                    }
                    if (count == 3) {
                        if (isPlayer) {
                            isPlayer = false;
                            value += 3 + i;
                        } else {
                            value -=3;
                        }
                    }
                }
                if (i > 2) {
                    int count = 0;
                    if (board[i][j] != 0) {
                        count++;
                        if (board[i][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-1][j] != 0) {
                        count++;
                        if (board[i-1][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-2][j] != 0) {
                        count++;
                        if (board[i-2][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-3][j] != 0) {
                        count++;
                        if (board[i-3][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (count == 3) {
                        if (isPlayer) {
                            isPlayer = false;
                            value += 3 + i;
                        } else {
                            value -=3;
                        }
                    }
                }
                if (i > 2 && j < board[i].length -3) {
                    int count = 0;
                    if (board[i][j] != 0) {
                        count++;
                        if (board[i][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-1][j+1] != 0) {
                        count++;
                        if (board[i-1][j+1] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-2][j+2] != 0) {
                        count++;
                        if (board[i-2][j+2] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-3][j+3] != 0) {
                        count++;
                        if (board[i-3][j+3] == player) {
                            isPlayer = true;
                        }
                    }
                    if (count == 3) {
                        if (isPlayer) {
                            isPlayer = false;
                            value += 3 + i;
                        } else {
                            value -=3;
                        }
                    }
                }
                if (i > 2 && j > 2) {
                    int count = 0;
                    if (board[i][j] != 0) {
                        count++;
                        if (board[i][j] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-1][j-1] != 0) {
                        count++;
                        if (board[i-1][j-1] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-2][j-2] != 0) {
                        count++;
                        if (board[i-2][j-2] == player) {
                            isPlayer = true;
                        }
                    }
                    if (board[i-3][j-3] != 0) {
                        count++;
                        if (board[i-3][j-3] == player) {
                            isPlayer = true;
                        }
                    }
                    if (count == 3) {
                        if (isPlayer) {
                            isPlayer = false;
                            value += 3 + i;
                        } else {
                            value -=3;
                        }
                    }
                }
            }
        }
        return value;
    }

    static int bestMove(int[][] board, int player) {
        int bestMove = -1;
        int val = -100000;
        for(int i = 0; i < 7; i++) {
            if (board[0][i] == 0) {
                placeIndex(board, i, player);
                int moveVal = minimax(board, false, 0, -100000, 100000, player);
                removeIndex(board, i);
                if (moveVal > val) {
                    val = moveVal;
                    bestMove = i;
                } else if (moveVal == val && Math.abs(i - 3) < Math.abs(bestMove - 3)) {
                    val = moveVal;
                    bestMove = i;   
                }
            }
        }
        return bestMove;
    }

    static int minimax(int[][] board, boolean maximizing, int count, int alpha, int beta, int player) {
        int winner = ConnectFour.checkWinner(board)[0][0];
        if (winner == player) {
            if (count == 0) {
                return 10000;
            }
            return 1000 - count + three(board, player);
        } else if (winner == (player % 2) + 1) {
            return -1000 + count - three(board, (player % 2) + 1);
        } else if (count > 8) {
            if(maximizing) {
                return three(board, player);
            } else {
                return -three(board, (player % 2) + 1);
            }
        }
        int bestVal = maximizing ? -10000 : 10000;
        for(int i = 0; i < 7; i++) {
            if (maximizing) {
                if (board[0][i] == 0) {
                    placeIndex(board, i, player);
                    bestVal = Math.max(bestVal, minimax(board, false, count + 1, alpha, beta, player));
                    removeIndex(board, i);
                    alpha = Math.max(alpha, bestVal);
                    if (bestVal >= beta) {
                        break;
                    }
                }
            } else {
                if (board[0][i] == 0) {
                    placeIndex(board, i, (player % 2) + 1);
                    bestVal = Math.min(bestVal, minimax(board, true, count + 1, alpha, beta, player));
                    removeIndex(board, i);
                    beta = Math.min(beta, bestVal);
                    if (bestVal <= alpha) {
                        break;
                    }
                }
            }
        }
        return bestVal;
    }

    static int hardAi(int[][] board, int player) {
        int move = bestMove(board, player);
        return move;
    }
}
