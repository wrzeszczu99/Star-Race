package ProjektJTP.UI.Game;

import ProjektJTP.Game;

import javax.swing.*;
import java.awt.*;

/**
 *Klasa sterująca wątkiem rysowania na ekranie
 */
@SuppressWarnings("BusyWait")
public class UI extends Thread {
    private static JFrame frame;
    private Draw panel = new Draw();
    private Game game;
    /**
     *  Konstruktor
     * @param g logika gry która będzie obrazowana
     */
    public UI(Game g)
    {
        game = g;
    }
    /**
     * Metoda run() którą wykonuje wątek
     */
    @Override
    public void run(){
        SetFrame();
        frame.add(this.getPanel());
        frame.addKeyListener(game);

        while (!Thread.currentThread().isInterrupted()){
            long starttime = System.currentTimeMillis();
        //
        //Rysuj stateczki
        //
            panel.setTempStateczek(1, game.getP1().getStateczek());
            panel.setTempStateczek(2, game.getP2().getStateczek());

        //
        //Rysuj Laserki
        //
            panel.setTemplaserki(game.getLaserki());
        //
        //Rysuj Punkty
        //
            panel.setScore1(game.getP1().getScore());
            panel.setScore2(game.getP2().getScore());

        //
        // Rysuj Bonusy
        //
            panel.setTempB1(game.getBonus1());
            panel.setTempB2(game.getBonus2());

            panel.winScreen(game.isPause(), game.getWinner());

            panel.repaint();
            long delta = System.currentTimeMillis() - starttime;
            try {
                sleep( Math.max(16-delta, 0 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if(game.isReadyToReset()) {
                    game = new Game();
                    game.start();
                    frame.addKeyListener(game);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
    /**
     *  Metoda zwraca panel po którym rysujemy
     * @return Jpanel z narysowanym stanem gry
     */
    public Draw getPanel() {
        return panel;
    }

    private static void SetFrame() {
        frame = new JFrame("Star Race");
        frame.setResizable(false);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setSize(1280, 720);
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
