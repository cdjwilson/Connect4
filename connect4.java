import java.util.Scanner;


class ConnectFour {
    
    static int[][] checkWinner(int[][] board) {
        int[][] result = new int[3][2];
        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    int player = board[i][j];
                    if (j < board[i].length - 3) {
                        if (player == board[i][j+1] && player == board[i][j+2] && player == board[i][j+3]) {
                            result[0][0] = player;
                            result[1][0] = i;
                            result[1][1] = j;
                            result[2][0] = i;
                            result[2][1] = j+3;
                            return result;
                        }
                    }
                    if (i > 2) {
                        if (player == board[i-1][j] && player == board[i-2][j] && player == board[i-3][j]) {
                            result[0][0] = player;
                            result[1][0] = i;
                            result[1][1] = j;
                            result[2][0] = i-3;
                            result[2][1] = j;
                            return result;
                        }
                    }
                    if (i > 2 && j < board[i].length -3) {
                        if (player == board[i-1][j+1] && player == board[i-2][j+2] && player == board[i-3][j+3]) {
                            result[0][0] = player;
                            result[1][0] = i;
                            result[1][1] = j;
                            result[2][0] = i-3;
                            result[2][1] = j+3;
                            return result;
                        }
                    }
                    if (i > 2 && j > 2) {
                        if (player == board[i-1][j-1] && player == board[i-2][j-2] && player == board[i-3][j-3]) {
                            result[0][0] = player;
                            result[1][0] = i;
                            result[1][1] = j;
                            result[2][0] = i-3;
                            result[2][1] = j-3;
                            return result;
                        }
                    }
                }
            }
        }
        return result;
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

    static int getNumberOfPlayers() {
        Scanner input = new Scanner(System.in);
        int numplayers = 0;
        while (true) {
            System.out.println("Number of players, 1 or 2: ");
            try {
                numplayers = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                input.nextLine();
            }
            if (numplayers == 1 || numplayers == 2) {
                return numplayers;
            }
        }
    }

    static int getPlayer() {
        Scanner input = new Scanner(System.in);
        int numplayers = 0;
        while (true) {
            System.out.println("Choose if you want to be player 1 or 2: ");
            try {
                numplayers = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                input.nextLine();
            }
            if (numplayers == 1 || numplayers == 2) {
                return numplayers;
            }
        }
    }

    static int getDifficulty() {
        Scanner input = new Scanner(System.in);
        int difficulty = 0;
        while (true) {
            System.out.println("Type number for difficulty you want, 1: easy, 2: hard ");
            try {
                difficulty = input.nextInt();
            } catch (java.util.InputMismatchException e) {
                input.nextLine();
            }
            if (difficulty == 1 || difficulty == 2) {
                return difficulty;
            }
        }
    }

    public static void main(String args[]) {

        int[][] board = new int[6][7];
        int player = 1;
        int[][] winner;
        int numPlayers = getNumberOfPlayers();
        int playerchoice = 0;
        int difficulty = 0;
        if (numPlayers == 1) {
            playerchoice = getPlayer();
            difficulty = getDifficulty();
        }
        printBoard(board);

        while (true) {
            if (numPlayers == 1) {
                if (player != playerchoice) {
                    int move = -1;
                    if (difficulty == 1) {
                        move = connect4ai.easyAi(board, player);
                    } else {
                        move = connect4ai.hardAi(board, player);
                    }
                    if (isLegalPlacement(board, move+1, player)) {
                        player = (player % 2) + 1;
                    }
                } else {
                    getPiecePlacement(board, player);
                    player = (player % 2) + 1;
                }
                printBoard(board);
                winner = checkWinner(board);
                if (winner[0][0] != 0) {
                    System.out.println("Winner is player " + winner[0][0]);
                    return;
                }
                if (!placementLeft(board)) {
                    return;
                }
            } else {
                getPiecePlacement(board, player);
                printBoard(board);
                player = (player % 2) + 1;
                winner = checkWinner(board);
                if (winner[0][0] != 0) {
                    System.out.println("Winner is player " + winner[0][0]);
                    return;
                }
                if (!placementLeft(board)) {
                    return;
                }
            }
        }
    }
}