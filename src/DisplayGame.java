import com.sun.source.tree.Tree;
import component.*;
import entities.*;

import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class DisplayGame {



    //Nom des joueurs
    private static ArrayList<String> playerNameList = Player.getPlayerNameList();

    private static Cell[][]board = Board.getBoard();

    private static ArrayList<Wall> wallList = Wall.getWallList();
    private static ArrayList<Card> cardList = Card.getCardList();
    private static ArrayList<Player> playerList = Player.getPlayerList();

    private  static ArrayList<Robot> robotList = Robot.getRobotList();

    private static int countDownStart = Time.getCountdownStarter();

//private int  announcedMoveCount = Player.getAnnouncedMoveCount();
    private static TreeMap<Integer, Player> sortPlayerByLessCountMap = Play.getSortPlayerByLessCountMap();

    public static void playerNumberInitialization(){
        System.out.println("Nombre de joueurs");
        Scanner scanner = new Scanner(System.in);
        Player.setPlayerNumber(scanner.nextInt());
        if(isValidPlayerNumber()) {
            System.out.println(Player.getPlayerNumber() + " joueurs");
        }
        else{
            System.out.println("Erreur : nombre de joueur non valide");
            playerNumberInitialization();
        }
    }

    public static boolean isValidPlayerNumber(){
        int playerNumber = Player.getPlayerNumber();
        if(playerNumber > 1 && playerNumber < 9){
            return true;
        }
        return false;
    }

    public static void playerNameInitialization(){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i< Player.getPlayerNumber() ;i++){
            int playerNumber = i + 1;
            System.out.println("Renseigner le nom du joueur " + playerNumber + " :");
            String playerName = scanner.nextLine();
            playerNameList.add(playerName);
            System.out.println("Nom du joueur " + playerNumber +" : " + Player.getPlayerNameList().get(i));
        }
    }

    public static void displayBoard() {
        String concat;
        for (int i = 0; i < 18; i++) {
            concat = " ";
            for (int j = 0; j < 18; j++) {
                if((j == 0 || j == 17) && (i !=0 && i != 17)) concat =  concat + "|";
                if((i == 0 || i == 17) && (j !=0 && j != 17)) concat =  concat + "__ ";
                else{
                    for(Wall wall : wallList){
                        if (i == wall.getCell().getRow() && j == wall.getCell().getCol()) {
                            if(wall.getDirection().equals("left")) concat = concat + "|";
                            if(wall.getDirection().equals("bottom")) concat = concat + "__";
                        }
                        }
                    for(Card card : cardList) {
                        if (i == card.getCell().getRow() && j == card.getCell().getCol()) {
                            concat = concat + "c";
                        }
                    }
                    for(Robot robot : robotList){
                        if(i == robot.getCell().getRow() && j == robot.getCell().getRow()){
                            if(robot.getColor().toString().equals("RED")) concat = concat + "R";
                            if(robot.getColor().toString().equals("YELLOW")) concat = concat + "Y";
                            if(robot.getColor().toString().equals("BLUE")) concat = concat + "B";
                            if(robot.getColor().toString().equals("GREEN")) concat = concat + "G";
                        }
                    }
                    concat = concat + "  ";
                }
            }
            System.out.println(concat);
        }
    }

    private Player playerWithShortestPath;


    public static void play() throws FileNotFoundException {
        Initialization initialization = new Initialization();
        initialization.initializeGame();
        while(!isGameOver()){
            gameTurn();
        }
        //TODO Ajouter le score et le vainqueur
    }

    public static boolean isGameOver(){
        return cardList.isEmpty();
    }

    private static String actualCardName = Play.getActualCardName();

    /**
     * Tour de jeu
     */
    public static void gameTurn(){
        Draw draw = new Draw();
        actualCardName = draw.drawRandomCard();

        ArrayList<Player> copyPlayerList = new ArrayList<>(playerList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Un joueur est-il prêt ?");
        String response = scanner.next();
        if(response.contains("ok")) {
            //TODO créer fonction StartTimer
            //Time.startTimer();
            //if (countDownStart == 0) {
                for(Player player : playerList) {
                    int announcedMoveCount = 0;
                    System.out.println("Joueur " + player.getName() + ", rentrez nombre de coups : ");
                    announcedMoveCount = scanner.nextInt();
                    //Trie les joueurs en fonction du nombre de coups annoncés par chacuns
                    sortPlayerByLessCountMap.put(announcedMoveCount, player );
                }
                Set<Integer> keys = sortPlayerByLessCountMap.keySet();
                for(Integer key : keys){
                    Player actualPlayer = sortPlayerByLessCountMap.get(key);
                    System.out.println("Joueur " + actualPlayer.getName() + ", c'est votre tour de déplacez un robot : ");
                    System.out.println("Couleur du robot a déplacer : ");
                    scanner.nextLine();
                    String robotColor = scanner.nextLine();
                    System.out.println("Nouvelles coordonnées du robot : ");
                    String newRobotCoordinate = scanner.nextLine();
                    String[] item = newRobotCoordinate.split(" ");
                    int robotCellXPosition = parseInt(item[0]);
                    int robotCellYPosition = parseInt(item[1]);
                    for(Robot robot : robotList) {
                        if (robot.getColor().toString().equals(robotColor)){
                            Cell oldCell = robot.getCell();
                            Cell newCell = new Cell(robotCellXPosition, robotCellYPosition);
                            Player.moveARobot(new Robot(oldCell, robot.getColor()), newCell);
                            //TODO actualiser le board
                            DisplayGame.displayBoard();
                        }
                    }
                }

            //}
        }
        //else{
            //gameTurn();
        //}

        //Ajoute un point au joueur ayant effectué le moins de coups
        //Counter.addWinnerPlayerAPoint(Player.playerWithLessMove());

        //if(Player.isReady(playerWithShortestPath) == true){
        //copyPlayerList.remove(playerWithShortestPath);

        //}
    }




    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Robot> robotList = Robot.getRobotList();

        Initialization initialization = new Initialization();
        initialization.initializeGame();
        DisplayGame.playerNumberInitialization();
        DisplayGame.playerNameInitialization();
        DisplayGame.displayBoard();
        DisplayGame.play();


    }
}
