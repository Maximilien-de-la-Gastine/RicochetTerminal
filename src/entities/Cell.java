package entities;


import java.util.ArrayList;

public class Cell {

    private int x;
    private int y;



    //Liste de tout les objets cellule.
    private static ArrayList<Cell> cellList = new ArrayList<>();
    public static ArrayList<Cell> getCellList() {
        return cellList;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object cell) {
        if (this == cell) return true;
        if (!(cell instanceof Cell)) return false;
        return getX() == ((Cell) cell).getX() && getY() == ((Cell) cell).getY();
    }

}





