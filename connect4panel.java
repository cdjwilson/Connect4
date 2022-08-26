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
    static final String[] numberPlayerChoices = {"1", "2"};
    static final String[] aiDifficultyChoices = {"easy", "hard"};
    static JLabel NUMBER_PLAYER_LABEL = new JLabel("Select the Number of Players:");
    static JComboBox<String> NUMBER_OF_PLAYERS = new JComboBox<String>(numberPlayerChoices);
    static JButton start = new JButton("Start Game");
    static int numPlayers = 0;
    static int playerChoice = 0;
    static String aiDifficulty= "";
    static JLabel PLAYER_CHOICE_LABEL = new JLabel("Select Which Player You Want to be:");
    static JComboBox<String> PLAYER_CHOICE = new JComboBox<String>(numberPlayerChoices);
    static JLabel AI_DIFFICULTY_LABEL = new JLabel("Select Which Player You Want to be:");
    static JComboBox<String> AI_DIFFICULTY = new JComboBox<String>(aiDifficultyChoices);
    JButton restart = new JButton("Restart Game");
    JButton mainMenu = new JButton("Main Menu");
    connect4panel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addMouseListener(this);
        mainMenu();
    }
    
    public void mainMenu() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(NUMBER_PLAYER_LABEL);
        NUMBER_PLAYER_LABEL.setAlignmentX(LEFT_ALIGNMENT);
        this.add(NUMBER_OF_PLAYERS);
        NUMBER_OF_PLAYERS.setMaximumSize(new Dimension(70, 40));
        NUMBER_OF_PLAYERS.setAlignmentX(LEFT_ALIGNMENT);
        this.add(PLAYER_CHOICE_LABEL);
        PLAYER_CHOICE_LABEL.setAlignmentX(LEFT_ALIGNMENT);
        this.add(PLAYER_CHOICE);
        PLAYER_CHOICE.setMaximumSize(new Dimension(70, 40));
        PLAYER_CHOICE.setAlignmentX(LEFT_ALIGNMENT);
        this.add(AI_DIFFICULTY_LABEL);
        AI_DIFFICULTY_LABEL.setAlignmentX(LEFT_ALIGNMENT);
        this.add(AI_DIFFICULTY);
        AI_DIFFICULTY.setMaximumSize(new Dimension(70, 40));
        AI_DIFFICULTY.setAlignmentX(LEFT_ALIGNMENT);
        this.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                numPlayers = Integer.parseInt( (String) NUMBER_OF_PLAYERS.getSelectedItem() );
                playerChoice = Integer.parseInt( (String) PLAYER_CHOICE.getSelectedItem() );
                aiDifficulty = (String) AI_DIFFICULTY.getSelectedItem();
                remove(NUMBER_OF_PLAYERS);
                remove(NUMBER_PLAYER_LABEL);
                remove(PLAYER_CHOICE_LABEL);
                remove(PLAYER_CHOICE);
                remove(AI_DIFFICULTY_LABEL);
                remove(AI_DIFFICULTY);
                remove(start);
                PLAYER.setFont(new Font("Helvetica", Font.PLAIN, 70));
                restart.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        board = new int[6][7];
                        running = false;
                        PLAYERNUM = 1;
                        startGame();
                    }
                });
                mainMenu.addActionListener(new ActionListener() {
            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        board = new int[6][7];
                        running = false;
                        removeAll();
                        mainMenu();
                        PLAYERNUM = 1;
                    }
            
                });
                add(mainMenu);
                add(PLAYER);
                add(restart);
                startGame();
            }
        });
        repaint();
    }
    
    public void startGame() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(0,150,255));
        running = true;
        if (numPlayers == 1) {
            if (playerChoice == 2) {
                AiMove();
            }
        }
        repaint();

    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        
    }
    
    public void AiMove() {
        if (ConnectFour.checkWinner(board)[0][0] == 0) {
            if (PLAYERNUM != playerChoice) {
                int move = -1;
                if (aiDifficulty == "easy") {
                    move = connect4ai.easyAi(board, PLAYERNUM);
                } else {
                    move = connect4ai.hardAi(board, PLAYERNUM);
                }
                if (ConnectFour.isLegalPlacement(board, move + 1, PLAYERNUM)) {
                    PLAYERNUM = (PLAYERNUM % 2) + 1;
                }
            }
        }
        repaint();
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
                    if (board[i][j] == 1) {
                        g.setColor(Color.RED);
                    } else if (board[i][j] == 2) {
                        g.setColor(Color.YELLOW);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillOval((j*UNIT_SIZE) + 15, ((i + 1)*UNIT_SIZE) + 15, 70, 70);
                    g.setColor(Color.BLACK);
                    g.drawOval((j*UNIT_SIZE) + 15, ((i + 1)*UNIT_SIZE) + 15, 70, 70);
                }
            }
            boolean finished = gameOver(g);
            if (!finished) {
                if (numPlayers == 1) {
                    if (playerChoice != PLAYERNUM) {
                        AiMove();
                    }
                }
            }
        }
    }
    
    public boolean gameOver(Graphics g) {
        int[][] winner = ConnectFour.checkWinner(board);
        

        if (winner[0][0] != 0) {
            g.drawLine(((winner[1][1])*UNIT_SIZE) + 50, ((winner[1][0] + 2)*UNIT_SIZE) - 50, ((winner[2][1])*UNIT_SIZE) + 50, ((winner[2][0] + 2)*UNIT_SIZE) - 50);
            PLAYER.setText("Player " + winner[0][0] + " Wins");
            running = false;
            return true;
        }
        if (!ConnectFour.placementLeft(board)) {
            PLAYER.setText("Draw");
            running = false;
            return true;
        }
        return false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (running) {
            int move;
            if (numPlayers == 1) {
                if (PLAYERNUM == playerChoice) {
                    move = (e.getX() / UNIT_SIZE) + 1;
                    if (ConnectFour.isLegalPlacement(board, move, PLAYERNUM)) {
                        PLAYERNUM = (PLAYERNUM % 2) + 1;
                        repaint();
                        AiMove();
                    }
                }
            } else {
                move = (e.getX() / UNIT_SIZE) + 1;
                if (ConnectFour.isLegalPlacement(board, move, PLAYERNUM)) {
                    PLAYERNUM = (PLAYERNUM % 2) + 1;
                    repaint();
                }
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
