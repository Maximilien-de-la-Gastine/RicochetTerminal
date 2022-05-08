import component.*;
import entities.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayGame {



    //Nom des joueurs
    private ArrayList<String> playerNameList = Player.getPlayerNameList();

    private Cell[][]board = Board.getBoard();

    private ArrayList<Wall> wallList = Wall.getWallList();
    private ArrayList<Card> cardList = Card.getCardList();
    private ArrayList<Player> playerList = Player.getPlayerList();

    private  ArrayList<Robot> robotList = Robot.getRobotList();

    private int countDownStart = Time.getCountdownStarter();


    public void playerNumberInitialization(){
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

    public boolean isValidPlayerNumber(){
        int playerNumber = Player.getPlayerNumber();
        if(playerNumber > 1 && playerNumber < 9){
            return true;
        }
        return false;
    }

    public void playerNameInitialization(){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i< Player.getPlayerNumber() ;i++){
            int playerNumber = i + 1;
            System.out.println("Renseigner le nom du joueur " + playerNumber + " :");
            String playerName = scanner.nextLine();
            playerNameList.add(playerName);
            System.out.println("Nom du joueur " + playerNumber +" : " + Player.getPlayerNameList().get(i));
        }
    }

    public void displayBoard() {
        String concat;
        for (int i = 0; i < 18; i++) {
            concat = "";
            for (int j = 0; j < 18; j++) {
                if((j == 0 || j == 17) && (i !=0 && i != 17)) concat =  concat + "|";
                if((i == 0 || i == 17) && (j !=0 && j != 17)) concat =  concat + "____ ";
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
                            concat = concat + "R";
                        }
                    }
                    if(concat.contains(""))
                    concat = concat + "  ";
                }
            }
            System.out.println(concat);
        }
    }

    private Player playerWithShortestPath;


    public void play() throws FileNotFoundException {
        Initialization initialization = new Initialization();
        initialization.initializeGame();
        while(!isGameOver()){
            gameTurn();
        }
        //TODO Ajouter le score et le vainqueur
    }

    public boolean isGameOver(){
        return cardList.isEmpty();
    }

    private String actualCardName = Play.getActualCardName();

    /**
     * Tour de jeu
     */
    public void gameTurn(){
        Draw draw = new Draw();
        actualCardName = draw.drawRandomCard();

        ArrayList<Player> copyPlayerList = new ArrayList<>(this.playerList);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Un joueur est-il prêt ?");
        String response = scanner.nextLine();
        if(response.equals("ok")) {
            //TODO créer fonction StartTimer
            //Time.startTimer();
            if (countDownStart == 0) {
                for(Player player : playerList) {
                    System.out.println("Joueur " + player.getName() + ", rentrez nombre de coups : ");

                }
            }
        }

        //Ajoute un point au joueur ayant effectué le moins de coups
        Counter.addWinnerPlayerAPoint(Player.playerWithLessMove());

        //if(Player.isReady(playerWithShortestPath) == true){
        copyPlayerList.remove(playerWithShortestPath);

        //}
    }




    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Robot> robotList = Robot.getRobotList();


        DisplayGame displayGame = new DisplayGame();
        /*displayGame.playerNumberInitialization();
        displayGame.playerNameInitialization();*/
        displayGame.play();

        displayGame.displayBoard();

    }
}
