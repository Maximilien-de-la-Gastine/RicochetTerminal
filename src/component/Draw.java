package component;

import entities.Card;

import java.util.ArrayList;
import java.util.Random;

public class Draw {

    private ArrayList<Card> cardList = Card.getCardList();



    /**
     * Cette fonction return le nom d'une carte aléatoire
     * @return the random card picked.
     */
    public String drawRandomCard(){
        Card pickedCard = cardList.get(getRandomNumber(cardList.size()));
        cardList.remove(pickedCard);
        return pickedCard.getCardName();
    }

    /**
     *
     * @param maxNumber
     * @return a random number between 0 and maxNumber.
     */
    public static int getRandomNumber(int maxNumber){
        Random random = new Random();
        return random.nextInt(maxNumber);
    }



}

