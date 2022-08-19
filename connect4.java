import java.util.Scanner;

class ConnectFour {
    
    static int checkWinner(int[][] board) {
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    int player = board[i][j];
                    if (j < board[i].length - 3) {
                        if (player == board[i][j+1] && player == board[i][j+2] && player == board[i][j+3]) {
                            System.out.println("checking for winner");
                            return player;
                        }
                    }
                    if (i > 2) {
                        if (player == board[i-1][j] && player == board[i-2][j] && player == board[i-3][j]) {
                            return player;
                        }
                    }
                    if (i > 2 && j < board[i].length -3) {
                        if (player == board[i-1][j+1] && player == board[i-2][j+2] && player == board[i-3][j+3]) {
                            return player;
                        }
                    }
                    if (i > 2 && j > 2) {
                        if (player == board[i-1][j-1] && player == board[i-2][j-2] && player == board[i-3][j-3]) {
                            return player;
                        }
                    }
                }
            }
        }
        return 0;
    }

    static void printBoard(int[][] board) {
        for (int[] element: board) {
            for (int number: element) {
                if (number == 1) {
                    System.out.printf("\u001B[31m%d \u001B[0m", number);
                } else if (number == 2) {
                    System.out.printf("\u001B[33m%d \u001B[0m", number);
                } else {
                    System.out.printf("%d ", number);
                }
            }
            System.out.println(" ");
        }

    }

    static boolean isLegalPlacement(int[][] board, int placement, int player) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][placement - 1] == 0) {
                board[i][placement - 1] = player;
                player = player*-1;
                return true;
            }
        }
        return false;
    }

    static void getPiecePlacement(int[][] board, int player) {
        Integer placement = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Player " + player + ", select a Column from 1 - 7 to place your piece: ");
            try {
                placement = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                input.nextLine();
            }
            if (placement > 0 && placement < 8) {
                if (isLegalPlacement(board, placement, player)) {
                    return;
                }
            }
        }
    }
    
    static boolean placementLeft(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {

        int[][] board = new int[6][7];
        int player = 1;
        int winner = 0;
        printBoard(board);
        
        while (true) {
        
            getPiecePlacement(board, player);
            printBoard(board);
            player = (player % 2) + 1;
            winner = checkWinner(board);
            if (winner != 0) {
                System.out.println("Winner is player " + winner);
                return;
            }
            if (!placementLeft(board)) {
                return;
            }
        }

    }

}