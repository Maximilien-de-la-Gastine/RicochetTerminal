package entities;

import java.util.ArrayList;

//Classe des cartes sur le plateau de jeu
public class Card {


    private String cardName;
    private Cell cell;

    //Liste de tout les objets cartes.
    private static ArrayList<Card> cardList = new ArrayList<>();



    public static ArrayList<Card> getCardList() {
        return cardList;
    }

    public Card(Cell cell, String cardName){
        this.cardName = cardName;
        this.cell = cell;

    }

    public Cell getCell() {
        return cell;
    }
    public String getCardName() {
        return cardName;
    }
}
