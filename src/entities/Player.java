package entities;



import component.Counter;
import component.Play;

import java.util.ArrayList;

public class Player {

    //Liste des joueurs
    private static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    //Nombre de joueurs
    private static int playerNumber;
    public static int getPlayerNumber() {
        return playerNumber;
    }
    public static void setPlayerNumber(int playerNumber) {
        Player.playerNumber = playerNumber;
    }

    //Nom des joueurs
    private static  ArrayList<String> playerNameList = new ArrayList<>();
    public void setPlayerNameList(ArrayList<String> playerNameList) {
        this.playerNameList = playerNameList;
    }



    public static ArrayList<String> getPlayerNameList() {
        return playerNameList;
    }

    //Nombre de coups effectués par le joueur
    private static int realMoveCount;
    //Nombre de coups annoncés par le joueur
    private int announcedMoveCount;

    public static int getRealMoveCount() {
        return realMoveCount;
    }

    private String name;
    private  int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player(String name, int score){
        this.score = score;
        this.name = name;
    }


    /**
     * un joueur peut modifier la position d'un robot
     * @param robot
     * @param newCell
     */
    public void moveARobot(Robot robot, Cell newCell){
        robot.changeRobotCell(robot, newCell);
    }



    /**
     * Cette fonction return le joueur ayant effectué le moins de coups
     * @return
     */
    public static Player playerWithLessMove(){
        for(Player player : playerList){

        }
        return null;
    }




    public boolean isPredictionMoveRespected(Player player){
        if(announcedMoveCount == realMoveCount){

        }
        return false;
    }


    /**
     * Cette fonction ajoute un point au joueur ayant effectué le moins de coups
     */
    public void calculScore(){
        Player winnnerPlayer = Player.playerWithLessMove();


    }



    /**
     * Vérifie que le joueur a trouvé un chemin pour lancer
     * le compte a rebours
     * @param player
     * @return
     */
    public void startTimer(Player player){

        //Lancer le timer

    }








}

