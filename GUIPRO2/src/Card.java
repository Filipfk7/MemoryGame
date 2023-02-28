import javax.swing.*;
import java.awt.*;



public class Card extends JButton {
    private boolean isCardOnFront = false;
    private boolean isMatched = false;
    private final CardType cardType;

    public Card(CardType cardType){
        this.cardType = cardType;
        setPreferredSize(new Dimension(80, 80));
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardOnFront(boolean set) {
        this.isCardOnFront = set;
        updateUI();
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(isCardOnFront) {
            g.drawImage(cardType.getIcon().getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        else {
             g.drawImage(CardType.Rewers.getIcon().getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
