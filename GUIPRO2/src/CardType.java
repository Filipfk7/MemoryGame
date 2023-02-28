import javax.swing.*;


public enum CardType {
    A("Cards/karta1.png"),
    B("Cards/karta2.png"),
    C("Cards/karta3.png"),
    D("Cards/karta4.png"),
    E("Cards/karta5.png"),
    F("Cards/karta6.png"),
    G("Cards/karta7.png"),
    H("Cards/karta8.png"),
    I("Cards/karta9.png"),
    J("Cards/karta10.png"),
    K("Cards/karta11.png"),
    L("Cards/karta12.png"),
    M("Cards/karta13.png"),
    N("Cards/karta14.png"),
    O("Cards/karta15.png"),
    P("Cards/karta16.png"),
    R("Cards/karta17.png"),
    S("Cards/karta18.png"),
    T("Cards/karta19.png"),
    U("Cards/karta20.png"),
    W("Cards/karta21.png"),
    Y("Cards/karta22.png"),
    Z("Cards/karta23.png"),
    X("Cards/karta24.png"),
    Rewers("Cards/Rewers.png");

    private final ImageIcon icon;

    CardType(String imagePath) {
        icon = new ImageIcon(imagePath);
    }

    public ImageIcon getIcon() {
        return icon;
    }
}

