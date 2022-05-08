package entities;


import java.util.ArrayList;

public class Cell {

    private int row;
    private int col;



    //Liste de tout les objets cellule.
    private static ArrayList<Cell> cellList = new ArrayList<>();
    public static ArrayList<Cell> getCellList() {
        return cellList;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    @Override
    public boolean equals(Object cell) {
        if (this == cell) return true;
        if (!(cell instanceof Cell)) return false;
        return getRow() == ((Cell) cell).getRow() && getCol() == ((Cell) cell).getCol();
    }

}





