package entities;

public enum Color {
    RED,
    YELLOW,
    BLUE,
    GREEN;

    public Color next(){return values()[ordinal()+1];}
}
