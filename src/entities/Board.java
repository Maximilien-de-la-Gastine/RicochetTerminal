package entities;

public class Board {

    private static Cell [][]board = new Cell[16][16];


    public Board(){

    }

    public static Cell[][] getBoard() {
        return board;
    }
}
