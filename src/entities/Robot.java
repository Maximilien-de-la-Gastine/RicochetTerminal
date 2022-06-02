package entities;


import component.Play;

import java.util.ArrayList;

public class Robot {

    private Color color;


    private final ArrayList<Wall> wallList = Wall.getWallList();

    private final String[] wallDirectionList = Wall.getWallDirectionList();

    private String actualCardName = Play.getActualCardName();


    private ArrayList<Card> cardList = Card.getCardList();

    private final Cell[][] board = Board.getBoard();


    private static ArrayList<Cell> authorizedPosition = new ArrayList<Cell>();

    public static ArrayList<Cell> getAuthorizedPosition() {
        return authorizedPosition;
    }


    public Color getColor() {
        return color;
    }

    public entities.Cell getCell() {
        return cell;
    }

    private Cell cell;

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public static ArrayList<Robot> getRobotList() {
        return robotList;
    }

    public static ArrayList<Robot> getClonedRobotList() {
        return clonedRobotList;
    }


    private static ArrayList<Robot> robotList = new ArrayList<>();
    private static ArrayList<Robot> clonedRobotList = new ArrayList<>();


    public Robot(Cell cell, Color color) {
        this.color = color;
        this.cell = cell;
    }


    /**
     * Fonction qui renvoie true si un mur est sur la cellule en paramètre.
     *
     * @param cell
     * @return false is la cellule ne comporte pas de murs
     * <p>
     * public boolean isWallOnCell(Cell cell) {
     * for (Wall wall : wallList) {
     * if (wall.getCell().equals(cell)) {
     * return true;
     * break;
     * }
     * }
     * }
     */

    int[] cellPositionList = new int[4];

    public void initializeCellPositionList(Cell cell) {
        //
        cellPositionList[0] = cell.getRow();
        cellPositionList[1] = cell.getCol();
        cellPositionList[2] = 16 - cell.getRow();
        cellPositionList[3] = 16 - cell.getCol();
    }

    public void initializeWallPositionList() {
        //
        wallDirectionList[0] = "left";
        wallDirectionList[1] = "top";
        wallDirectionList[2] = "right";
        wallDirectionList[3] = "bottom";
    }


    /**
     * Fonction qui vérifie si le mouvement est bien valide
     *
     * @return
     */

    public boolean isValidMove(Robot robot, Cell cell) {
        int colRobot = robot.getCell().getCol();
        int rowRobot = robot.getCell().getRow();
        if (colRobot == cell.getCol()) {
            int difference = cell.getRow() - rowRobot;
            if (difference > 0) {
                for (int i = rowRobot; i <= cell.getRow(); i++) {
                    for (Wall wall : wallList) {
                        int rowWall = wall.getCell().getRow();
                        int colWall = wall.getCell().getCol();
                        if (rowWall == i && wall.getDirection().equals("bottom") && colWall == colRobot && i != cell.getRow()) {
                            return false;
                        }
                        if (rowWall == i && wall.getDirection().equals("bottom") && colWall == colRobot && i == cell.getRow()) {
                            return true;
                        }
                    }
                }
                return true;
            }
            if (difference < 0) {
                for (int i = rowRobot; i >= cell.getRow(); i--) {
                    for (Wall wall : wallList) {
                        int rowWall = wall.getCell().getRow();
                        int colWall = wall.getCell().getCol();
                        if (rowWall == i && wall.getDirection().equals("top") && colWall == colRobot && i != cell.getRow()) {
                            return false;
                        }
                        if (rowWall == i && wall.getDirection().equals("top") && colWall == colRobot && i == cell.getRow()) {
                            return true;
                        }
                    }
                }
                return true;
            }
        }
        if (robot.getCell().getRow() == cell.getRow()) {
            int difference = cell.getCol() - colRobot;
            if (difference > 0) {
                for (int i = colRobot; i <= cell.getCol(); i++) {
                    for (Wall wall : wallList) {
                        int rowWall = wall.getCell().getRow();
                        int colWall = wall.getCell().getCol();
                        if (colWall == i && wall.getDirection().equals("right") && rowWall == rowRobot && i != cell.getCol()) {
                            return false;
                        }
                        if (colWall == i && wall.getDirection().equals("right") && rowWall == rowRobot && i == cell.getCol()) {
                            return true;
                        }
                    }
                }
                return true;
            }
            if (difference < 0) {
                for (int i = colRobot; i >= cell.getCol(); i--) {
                    for (Wall wall : wallList) {
                        int rowWall = wall.getCell().getRow();
                        int colWall = wall.getCell().getCol();
                        if (colWall == i && wall.getDirection().equals("left") && rowWall == rowRobot && i != cell.getCol()) {
                            return false;
                        }
                        if (colWall == i && wall.getDirection().equals("left") && rowWall == rowRobot && i == cell.getCol()) {
                            return true;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Robot{" +
                "color=" + color +
                ", cell=" + cell.toString() +
                '}';
    }

    /**
     * Cette fonction vérifie si la nouvelle cellule est valide,
     * si oui, la cellule du robot est changé en nouvelle cellule.
     * Si le placement est validé, on ajoute 1 au compteur de coups
     *
     * @param robot
     * @param newCell
     */
    public void changeRobotCell(Robot robot, Cell newCell) {
        if (isValidMove(robot, newCell)) {
            for (Robot oldRobot : robotList) {
                if (oldRobot.equals(robot)) {
                    robotList.remove(oldRobot);
                    robot.setCell(newCell);
                    robotList.add(robot);
                    break;
                }
            }
            Player.getRealMoveCount();
        } else System.out.println("erreur");
    }


    public static void changeClonedList() {
        for (Robot clonedRobot : clonedRobotList) {
            robotList.clear();
            robotList.add(clonedRobot);
        }
    }


    /**
     * Cette fonction return true si la cellule du robot est arrivée sur la carte piochée
     *
     * @param robot
     * @return
     */
    public boolean isRobotOnCard(Robot robot) {
        for (Card card : cardList) {
            if ((card.getCardName() == actualCardName) && (robot.getCell() == card.getCell())) {
                return true;
            }
        }
        return false;

    }


    @Override
    public boolean equals(Object robot) {
        if (this == robot) return true;
        if (!(robot instanceof Robot)) return false;
        return getCell() == ((Cell) cell) && getCell() == ((Cell) cell);
    }

}
