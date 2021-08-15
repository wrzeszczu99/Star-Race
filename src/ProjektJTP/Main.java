package ProjektJTP;


import ProjektJTP.UI.Game.UI;
import jdk.nashorn.internal.runtime.regexp.JdkRegExp;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa Main jest klasą inicjalizującą wątki gry i interfejsu a także twórcą okienka
 */
public class Main{

    private static Game game = new Game();
    private static UI ui = new UI(game);


    /**
     * Metoda main
     * @param args
     */
    public static void main(String[] args) {
        // realna gra
        ui.start();
        game.start();
    }

    /**
     * Metoda inicjalizująca okienko aplikacji
     */

}


