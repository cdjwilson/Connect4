import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class connect4panel extends JPanel implements MouseListener {
    
    static final int UNIT_SIZE = 100;
    static final int SCREEN_HEIGHT = UNIT_SIZE*7;
    static final int SCREEN_WIDTH = UNIT_SIZE*7;
    static int PLAYERNUM = 1;
    static JLabel PLAYER = new JLabel();
    static int[][] board = new int[6][7];
    static boolean running = false;
    connect4panel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addMouseListener(this);
        
        PLAYER.setFont(new Font("Helvetica", Font.PLAIN, 70));
        this.add(PLAYER);
        
        startGame();
    }
    
    public void startGame() {
        running = true;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }
    
    public void draw(Graphics g) {
        if(running) {
            PLAYER.setText("Turn: Player " + PLAYERNUM);
            
            for (int i = 0; i < SCREEN_WIDTH/UNIT_SIZE;i++) {
                //y axis
                g.drawLine(i*UNIT_SIZE, UNIT_SIZE, i*UNIT_SIZE, SCREEN_HEIGHT);
                //x axis
                g.drawLine(0, (i + 1)*UNIT_SIZE, SCREEN_WIDTH, (i + 1)*UNIT_SIZE);
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != 0) {
                        if (board[i][j] == 1) {
                            g.setColor(Color.RED);
                        } else if (board[i][j] == 2) {
                            g.setColor(Color.YELLOW);
                        }
                        g.fillOval(j*UNIT_SIZE, (i + 1)*UNIT_SIZE, 99, 99);
                        g.setColor(Color.BLACK);
                        g.drawOval(j*UNIT_SIZE, (i + 1)*UNIT_SIZE, 99, 99);
                    }
                }
            }
            gameOver(g);
        }
    }
    
    public void gameOver(Graphics g) {
        int[][] winner = ConnectFour.checkWinner(board);
        JButton restart = new JButton("Restart Game");
        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                board = new int[6][7];
                remove(restart);
                running = true;
                repaint();
                PLAYERNUM = 1;
            }
        });
        if (winner[0][0] != 0) {
            g.drawLine(((winner[1][1])*UNIT_SIZE) + 50, ((winner[1][0] + 2)*UNIT_SIZE) - 50, ((winner[2][1])*UNIT_SIZE) + 50, ((winner[2][0] + 2)*UNIT_SIZE) - 50);
            PLAYER.setText("Player " + winner[0][0] + " Wins");
            this.add(restart);
            running = false;
        }
        if (!ConnectFour.placementLeft(board)) {
            PLAYER.setText("Draw");
            this.add(restart);
            running = false;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

        if (running) {
             int x = (e.getX() / UNIT_SIZE) + 1;
             if (ConnectFour.isLegalPlacement(board, x, PLAYERNUM)) {
                 PLAYERNUM = (PLAYERNUM % 2) + 1;
                 repaint();
             }

         }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
