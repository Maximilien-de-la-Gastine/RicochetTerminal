import component.Initialization;
import component.Play;
import entities.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayGame {



    //Nom des joueurs
    private ArrayList<String> playerNameList = Player.getPlayerNameList();


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





    public static void main(String[] args) throws FileNotFoundException {
        DisplayGame displayGame = new DisplayGame();
        displayGame.playerNumberInitialization();
        displayGame.playerNameInitialization();
        Initialization initialization = new Initialization();
        initialization.initializeGame();
        Play play = new Play();
        play.play();
    }
}
