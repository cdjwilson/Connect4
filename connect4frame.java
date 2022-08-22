import javax.swing.JFrame;

public class connect4frame extends JFrame {    
    
    connect4frame() {
        this.add(new connect4panel());
        this.setTitle("Connect 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}