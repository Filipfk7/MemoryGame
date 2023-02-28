

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Game extends JFrame {
    int row;
    int col;
    int countMatches;

    private Card previousCard = null;
    private boolean gameLocked = false;

    private int timeElapsed;

    private Timer timer;


    GameLevel level;
    Game(GameLevel level) {
        this.level = level;
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setPreferredSize(new Dimension(1200, 800));
        GridBagLayout gridLayout = new GridBagLayout();
        if (level.equals(GameLevel.EASY)) {
            col = 4;
            row = 4;
        }
        if (level.equals(GameLevel.NORMAL)){
            col = 6;
            row = 4;

        }
        if (level.equals(GameLevel.HARD)){
            col = 8;
            row = 6;
        }
        panel.setLayout(gridLayout);

        //0 0S
        //0 1
        //0 2
        //0 3
        //1 0
        //1 1..

        //A B C D E F G H
        //A B C D E F G H
        List<Card> cards = new ArrayList<>();
        CardType[] cardTypes = CardType.values(); //A B C D E F G H


        //budowanie listy: A B C D E F G H | A B C D E F G H (na podstawie tablicy cardTypes)
        final int halfCards = col * row / 2; //8

        for(int i = 0; i < row * col; i++) {

            if(i < halfCards) {
                cards.add(new Card(cardTypes[i]));
            } else {
                //i=8 , halfCards = 8, wynik = 0 (czyli A)
                //i=9, halfCards = 8, wynik = 1 (czyli B)
                //i=10, halfCards = 8, wynik = 2 (czyli C)

                cards.add(new Card(cardTypes[i - halfCards]));
            }
        }


        Collections.shuffle(cards);

        int cardToPlaceId = 0;
        for (int completeRow = 0; completeRow < row; completeRow++) {
            for (int completeCol = 0; completeCol <col; completeCol++) {

                GridBagConstraints c = new GridBagConstraints();
                Card cardToPlace = cards.get(cardToPlaceId);
                c.gridx = completeCol;
                c.gridy = completeRow;

                gridLayout.setConstraints(cardToPlace, c);
                panel.add(cardToPlace);
                cardToPlaceId++;

                // Add card listener
                cardToPlace.addActionListener(event -> cardClicked(event));
            }
        }

        pack();
        setVisible(true);

        JLabel timeElapsedLabel = new JLabel("0 seconds", SwingConstants.CENTER);
        timeElapsedLabel.setFont(new Font("Arial", Font.BOLD ,17));

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = row;
        c.gridx = 0;
        c.gridwidth = col;
        gridLayout.setConstraints(timeElapsedLabel, c);
        panel.add(timeElapsedLabel);

        timer = new Timer(1000, e -> {
            timeElapsed += 1;
            timeElapsedLabel.setText(timeElapsed + " seconds");
        });

        timer.setRepeats(true);
        timer.start();

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false);
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "Q");
        this.getRootPane().getActionMap().put("Q", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new Menu();
                dispose();
                timer.stop();
            }
        });

    }

    private void cardClicked(ActionEvent event) {
        if (!gameLocked) {
            Card card = (Card) (event.getSource());
            if(card.isMatched()){
                return;
            }
            System.out.println(card.getCardType());

            card.setCardOnFront(true);

            if (previousCard == null) {
                previousCard = card;
            } else if (previousCard != card) {
                if (card.getCardType() == previousCard.getCardType()) {
                    System.out.println("Te same karty!");
                    card.setMatched(true);
                    previousCard.setMatched(true);
                    countMatches++;
                    if(countMatches==row*col/2){
                        timer.stop();
                        JOptionPane.showConfirmDialog(null, "Congratulations!",
                                "Congratulations! You havee finished the game in "+timeElapsed+" seconds!",
                                JOptionPane.DEFAULT_OPTION);
                        dispose();
                        new Menu();
                        return;
                    }
                    previousCard = null;
                } else {
                    System.out.println("Bledne karty!");
                    gameLocked = true;

                    Timer timer = new Timer(2500, e -> {
                        System.out.println("koniec czasu");
                        previousCard.setCardOnFront(false);
                        card.setCardOnFront(false);
                        previousCard = null;
                        gameLocked = false;
                    });

                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }
}
