import component.Initialization;
import component.Play;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        Initialization ini = new Initialization();
        ini.readFile("src/wall.txt");


        Play play = new Play();
        play.play();
    }
}
