import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//JFrame - okno
//JPanel -> plotno

public class Menu {


    public static void main(String[] args) {
        new Menu();
    }

    public Menu() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        Dimension dimensions = new Dimension(800, 400);

        panel.setPreferredSize(dimensions);
        panel.setMaximumSize(dimensions);
        panel.setMinimumSize(dimensions);


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel studentIndex = new JLabel("S24778");
        panel.add(studentIndex);
        JLabel title = new JLabel("MEMORY THE GAME");
        panel.add(title);

        JButton newGame = new JButton("New Game");
        panel.add(newGame);
        JButton highScores = new JButton("High Scores");
        panel.add(highScores);
        JButton exit = new JButton("Exit");
        panel.add(exit);




        newGame.addActionListener(e -> {
            Object selectedLeveLObj = JOptionPane.showInputDialog(null, "Wybierz poziom", "Wybierz poziom tytul",JOptionPane.QUESTION_MESSAGE, null, GameLevel.values(), GameLevel.EASY);
            GameLevel selectedLevel = (GameLevel) selectedLeveLObj;
            frame.dispose();
            new Game(selectedLevel);
        });


        exit.addActionListener(e -> {
            int exitDecision = JOptionPane.showConfirmDialog(exit, "Confirm if you want to Exit", "Memory", JOptionPane.YES_NO_OPTION);

            if(exitDecision == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });


        frame.add(panel);
        frame.setPreferredSize(new Dimension(800, 400));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
