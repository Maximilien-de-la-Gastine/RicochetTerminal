package component;

import entities.Card;

import entities.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Play {


    private ArrayList<Player> playerList = Player.getPlayerList();
    private Player playerWithShortestPath;
    private int playerNumber = playerList.size();

    public static String getActualCardName() {
        return actualCardName;
    }

    //Nom de la carte piochée
    private static String actualCardName;

    private ArrayList<Card> cardList = Card.getCardList();



    public void play() throws FileNotFoundException {
        Initialization initialization = new Initialization();
        initialization.initializeGame();
        while(!isGameOver()){
            gameTurn();
        }


        //TODO Ajouter le score et le vainqueur
    }

    public
    for(Player player : playerList ){
        if()
    }


    /**
     * Vérifie si la pioche est vide,
     * si oui return true
     * @return
     */
    public boolean isGameOver(){
        return cardList.isEmpty();
    }

    /**
     * Tour de jeu
     */
    public void gameTurn(){
        Draw draw = new Draw();
        actualCardName = draw.drawRandomCard();

        ArrayList<Player> copyPlayerList = new ArrayList<>(this.playerList);


                //TODO créer fonction StartTimer
        //startTimer();

        //Ajoute un point au joueur ayant effectué le moins de coups
        Counter.addWinnerPlayerAPoint(Player.playerWithLessMove());

        if(Player.isReady(playerWithShortestPath) == true){
            copyPlayerList.remove(playerWithShortestPath);

        }
    }










}
