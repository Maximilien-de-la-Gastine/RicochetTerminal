package entities;


import java.util.ArrayList;

public class Wall {

    private Cell cell;
    private String direction;

    private static String wallDirectionList[] = new String [4];

    public static String[] getWallDirectionList() {
        return wallDirectionList;
    }

    public Cell getCell() {
        return cell;
    }

    public String getDirection() {
        return direction;
    }


    //Liste de tout les objets wall.
    private static ArrayList<Wall> wallList = new ArrayList<>();
    public static ArrayList<Wall> getWallList() {
        return wallList;
    }


    public Wall(Cell cell, String direction){
        this.cell = cell;
        this.direction = direction;


    }







}
