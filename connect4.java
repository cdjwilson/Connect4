import java.util.Scanner;

class ConnectFour {
    
    static void printBoard(int[][] board) {
        for (int[] element: board) {
            for (int number: element) {
                System.out.printf("%d ", number);
            }
            System.out.println(" ");
        }

    }
    
    
    public static void main(String args[]) {
        int[][] board = new int[6][7];
        
        printBoard(board);

        Scanner myObj = new Scanner(System.in);

    }

}