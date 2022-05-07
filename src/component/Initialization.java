package component;

import entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Initialization {

    //Coordonnées Aléatoire des robots à initialiser
    private int randomXPosition;
    private  int randomYPosition;


    private ArrayList<Wall> wallList = Wall.getWallList();
    private ArrayList<Cell> cellList = Cell.getCellList();
    private ArrayList<Card> cardList = Card.getCardList();
    private ArrayList<Player> playerList = Player.getPlayerList();

    //Liste des cellules ne contenant pas de cartes
    private ArrayList<Cell> cellListWhitoutCard = new ArrayList<>();


    private int playerNumber = Player.getPlayerNumber();

    //Nom des joueurs
    private ArrayList<String> playerNameList = Player.getPlayerNameList();



    private Cell [][]board = Board.getBoard();


    //TODO créer les 4 cartes de couleur de robot lorsqu'on les pose aléatoirement


    /**
     * Méthode qui lance toutes les fonctions d'initialisation du jeu
     */
    public void initializeGame() throws FileNotFoundException {
        createCell();
        initializeBoard();
        createPlayer();
        createCard();
        createWall();
        createRobot();

    }

    /**
     * Création des Cellules
     */
    public void createCell(){
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                Cell cell = new Cell(i, j);
                cellList.add(cell);
            }
        }
    }

    /**
     * Initialisation du plateau de jeu, on ajoute
     * les cellules crées dans le plateau.
     */
    public void initializeBoard(){
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++) {
                board[i][j] = cellList.get(i + j);
            }
        }
    }


    /**
     * Création des joueurs en fonction de leur nombre.
     * Le nombre de joueurs est trouvé en fonction du nombre de noms de joueurs initialisés
     */
    public void createPlayer(){
        for (int i=0; i<playerNumber; i++){
            //On récupère le nom des joueurs dans la liste des joueurs
            String playerName = playerNameList.get(i);
            int score = 0;
            Player player = new Player(playerName, 0);
            playerList.add(player);
        }
    }

    /**
     * Cette fonction initialise la liste cellListWhithoutCard qui comprend
     * toutes les cellules sauf celles avec une carte
     */
    public void initializeCellListWithoutCard() {
        //On commence par ajouter toutes les cellules possibles
        for (Cell cell : cellList) {
            cellListWhitoutCard.add(cell);
        }
        //on retire ensuite toutes les cellules avec une carte
        for (Card card : cardList) {
            cellListWhitoutCard.remove(card);
        }
    }

    /**
     * Verifie que l'on ne va pas placer un robot sur une cellule avec une carte.
     * @return
     */
    public Cell getRandomCellWithoutCard() {
        initializeCellListWithoutCard();
        int randomIndex = Draw.getRandomNumber(cellListWhitoutCard.size());
        Cell cell = cellListWhitoutCard.get(randomIndex);
        return cell;
    }


        /**
         * Création et initialisation des 4 robots.
         *Placement aléatoire des robots sur le board
         */
        public void createRobot() {
            for (int i = 0; i < 4; i++) {
                Color color = Color.RED;
                //On crée la cellule aléatoire du robot ou il n'y a pas de carte
                Cell robotCell = getRandomCellWithoutCard();
                //On supprime la cellule du robot de la liste pour ne pas placer 2 robots au même endroit
                cellListWhitoutCard.remove(robotCell);
                int x = robotCell.getX();
                int y = robotCell.getY();
                Robot robot = new Robot(board[x][y], color);
                color = color.next();
            }
        }


    public void createCard() throws FileNotFoundException {
        Scanner scannerCard = new Scanner(new File("src/card.txt"));
        while (scannerCard.hasNextLine()) {
            String line = scannerCard.nextLine();
            String[] item = line.split(" ");
            String cardName = item[0];
            int cardCellXPosition = parseInt(item[1]);
            int cardCellYPosition = parseInt(item[2]);
            Card card = new Card(new Cell(cardCellXPosition, cardCellYPosition), cardName);
            //On ajoute les cartes crées dans la liste
            cardList.add(card);
        }
    }

    public void createWall() throws FileNotFoundException {
        Scanner scannerWall = new Scanner(new File("src/wall.txt"));
        //Lecture du fichier wall contenant les coordonnées des cellules ayant un mur,
        // ainsi que l'endroit ou se situe le mur (top, left, bottom, right)
        while (scannerWall.hasNextLine()) {
            String line = scannerWall.nextLine();
            String[] item = line.split(" ");
            //Conversion des string en int
            int wallCellXPosition = parseInt(item[0]);
            int wallCellYPosition = parseInt(item[1]);
            String wallDirection = item[2];
            Wall wall = new Wall(new Cell(wallCellXPosition, wallCellYPosition), wallDirection);
            wallList.add(wall);
        }
    }
}
