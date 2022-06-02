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

    private static ArrayList<Robot> robotList = new ArrayList<>();


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

    public boolean getPosition(Robot robot, Cell cell) {
        int colRobot = robot.getCell().getCol();
        int rowRobot = robot.getCell().getRow();
        if (colRobot == cell.getCol()) {
            int difference = cell.getRow() - rowRobot;
            if ( difference > 0 ) {
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
            }
            if ( difference < 0 ) {
                for (int i = rowRobot; i <= cell.getRow(); i--) {
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
            }
        }
        if (robot.getCell().getRow() == cell.getRow()) {
            int difference = cell.getCol() - colRobot;
            if ( difference > 0 ) {
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
            }
            if ( difference < 0 ) {
                for (int i = colRobot; i <= cell.getCol(); i--) {
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
            }
        }
        return false;
    }

    //TODO refaire cette méthode plus simplement
    public void getLeftAndTopPositions(Cell cell) {
        //on initialise les listes de positions et le nombre de cellules à parcourir au maximum
        initializeCellPositionList(cell);
        initializeWallPositionList();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i <= cellPositionList[j]; i++) {
                for (Wall wall : wallList) {
                    if ((i == 0 && wall.getDirection() == wallDirectionList[j]) || cellPositionList[j] - i == 0) {
                        if (j == 0) {
                            authorizedPosition.add(board[cell.getRow() - i][cell.getCol()]);
                        }
                        if (j == 1) {
                            authorizedPosition.add(board[cell.getRow()][cell.getCol() - i]);
                        }
                    }
                    if (((cellPositionList[j] - i == wall.getCell().getRow()) && (wall.getDirection() == wallDirectionList[j + 2]))) {
                        if (j == 0) {
                            authorizedPosition.add(board[cell.getRow() - i][cell.getCol()]);
                        }
                        if (j == 1) {
                            authorizedPosition.add(board[cell.getRow()][cell.getCol() - i]);
                        }
                        break;
                    }
                }
            }
        }
    }
    //TODO refaire cette methode pour right et bottom


    /**
     * Fonction qui vérifie si le mouvement est bien valide
     *
     * @param newCell
     * @return
     */
    public boolean isValidMove(Cell newCell) {
        for (Cell cell : authorizedPosition) {
            if (newCell == cell) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "color=" + color +
                ", cell=" + cell +
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
        if (isValidMove(newCell)) {
            for (Robot oldRobot : robotList) {
                if (oldRobot == robot) {
                    robotList.remove(oldRobot);
                    robot.setCell(newCell);
                    robotList.add(robot);
                }
            }
            Player.getRealMoveCount();
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


}
