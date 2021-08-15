package ProjektJTP;

import ProjektJTP.Bonus.Bonus;
import ProjektJTP.Bonus.Timer;
import ProjektJTP.Laserki.LaserControl;
import ProjektJTP.Laserki.Laserek;
import ProjektJTP.Stateczki.DeltaFighter;
import ProjektJTP.Stateczki.TIEFighter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Klasa Game jest wątkiem paniującym nad logiką gry
 */
@SuppressWarnings("BusyWait")
public class Game extends Thread implements KeyListener {

    private Random rand = new Random();
    private Timer timer1, timer2;

    private final Player p1;
    private final Player p2;
    private final LaserControl laserki;
    private final boolean[] klawisze = new boolean[4];
    private boolean pause;
    private boolean [] boost = new boolean[2];
    private Bonus bonus1;
    private Bonus bonus2;
    int winner;
    private boolean readyToReset;

    /**
     * Konstruktor inicjalizuje obiekt gry który ustawia odpowiednie parametry swoich pól
     */
    public Game() {
        p1 = new Player(new DeltaFighter(320));
        p2 = new Player(new TIEFighter(960));
        laserki = new LaserControl();
        pause = false;
        boost[0] = false;
        boost[1] = false;
        bonus1 = new Bonus(320);
        bonus2 = new Bonus(960);
        timer1 = new Timer();
        timer2 = new Timer();
        readyToReset = false;
    }

    /**
     * Metoda run którą wywołuje wątek gdy jest uruchamiany. Zawiera główną pętlę gry
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            timer1.start();
            timer2.start();
            while (!pause) {
                long starttime = System.currentTimeMillis();

                laserki.Oblicz();
                sprawdzStatki();
                sprawdzKolizje();
                sprawdzBonusy();

                if (klawisze[0]) {
                    p1.getStateczek().decY();
                    if(boost[0]) p1.getStateczek().decY();
                }
                if (klawisze[1]){
                    p1.getStateczek().incY();
                    if(boost[0]) p1.getStateczek().incY();
                }

                if (klawisze[2]) {
                    p2.getStateczek().decY();
                    if(boost[1]) p2.getStateczek().decY();
                }
                if (klawisze[3]) {
                    p2.getStateczek().incY();
                    if(boost[1]) p2.getStateczek().incY();
                }
                long delta = System.currentTimeMillis() - starttime;
                try {
                    sleep(Math.max(16 - delta, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                timer1.join();
                timer2.join();

                this.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void sprawdzBonusy() {
        //Generowanie bonusów
        if(!bonus1.isActive()){
            int temp = rand.nextInt(100);
            if(temp < 1){
                bonus1.setY(rand.nextInt(600));
                bonus1.setActive(true);
            }
        }

        if(!bonus2.isActive()){
            int temp = rand.nextInt(100);
            if(temp < 1){
                bonus2.setY(rand.nextInt(600));
                bonus2.setActive(true);
            }
        }

        //Czas trwania bonusów
        if(timer1.getCounter()>5) boost[0] = false;
        if(timer2.getCounter()>5) boost[1] = false;
    }

    /**
     * Metoda sprawdza kolizje statków z laserami i bonusami
     */
    private void sprawdzKolizje() {
        //Kolizje statków z laserami
        for (int i = 0; i < 30; i++) {
            if (p1.getStateczek().getY() + p1.getStateczek().getLy() > getLaserki()[i].getY() && p1.getStateczek().getY() < getLaserki()[i].getY()) {
                int lxx;
                int z;
                lxx = (getLaserki()[i].getY() - p1.getStateczek().getY()) * p1.getStateczek().getLx() / p1.getStateczek().getLy();
                z = (p1.getStateczek().getLx() - lxx) / 2;
                if (getLaserki()[i].getX() < z + p1.getStateczek().getX() + lxx && getLaserki()[i].getX() > z + p1.getStateczek().getX()) {
                    p1.getStateczek().punkt();
                    boost[0] = false;
                }
            }
            if (p2.getStateczek().getY() + p2.getStateczek().getLy() > getLaserki()[i].getY() && p2.getStateczek().getY() < getLaserki()[i].getY()) {
                if (getLaserki()[i].getX() < p2.getStateczek().getX() + p2.getStateczek().getLx() && getLaserki()[i].getX() > p2.getStateczek().getX()) {
                    p2.getStateczek().punkt();
                    boost[1] = false;
                }
            }
        }
        //kolizje statków z bonusami
        if(p1.getStateczek().getY() + p1.getStateczek().getLx() > bonus1.getY() && p1.getStateczek().getY() < bonus1.getY() && bonus1.isActive()){
            boost[0] = true;
            timer1.setCounter(0);
            bonus1.setActive(false);
        }
        if(p2.getStateczek().getY() + p2.getStateczek().getLx() > bonus2.getY() && p2.getStateczek().getY() < bonus2.getY() && bonus2.isActive()){
            boost[1] = true;
            timer2.setCounter(0);
            bonus2.setActive(false);
        }

    }

    /**
     * Metoda sprawdza czy statek zdobył punkt i sprawdza warunki zwycięstwa ustawiając flagę
     */
    private void sprawdzStatki() {
        if (p1.getStateczek().getY() < 0) {
            p1.setScore();
        }
        if (p2.getStateczek().getY() < 0) {
            p2.setScore();
        }

        if (p1.getScore() == 5) {
            pause = true;
            winner =1;
        }
        if (p2.getScore() == 5){
            pause = true;
            winner = 2;
        }


    }

    /**
     * Override metody interfejsu KeyListener. Pusty
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Override metody interfejsu KeyListener. Ustawia flagi przycisków jeśli je wciśnięto
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            klawisze[0] = true;
        if (e.getKeyCode() == KeyEvent.VK_S)
            klawisze[1] = true;
        if (e.getKeyCode() == KeyEvent.VK_I)
            klawisze[2] = true;
        if (e.getKeyCode() == KeyEvent.VK_K)
            klawisze[3] = true;
        if(pause && e.getKeyCode() == KeyEvent.VK_ENTER)
            readyToReset = true;
    }

    /**
     * Override metody interfejsu KeyListener. Ustawia flagi przycisków jeśli je puszczono
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            klawisze[0] = false;
        if (e.getKeyCode() == KeyEvent.VK_S)
            klawisze[1] = false;
        if (e.getKeyCode() == KeyEvent.VK_I)
            klawisze[2] = false;
        if (e.getKeyCode() == KeyEvent.VK_K)
            klawisze[3] = false;
    }

    /**
     * getter dla gracza pierwszego
     * @return obiekt gracza pierwszego
     */
    public Player getP1() {
        return p1;
    }

    /**
     * getter dla gracza drugiego
     * @return obiekt gracza drugiego
     */
    public Player getP2() {
        return p2;
    }

    public Bonus getBonus1() {
        return bonus1;
    }

    public Bonus getBonus2() {
        return bonus2;
    }

    /**
     * getter dla tabilicy laserów
     * @return tabilca laserów
     */
    public Laserek[] getLaserki() {
        return laserki.getLaserki();
    }

    /**
     * getter dla zmiennej przechowującej id gracza który wygrał
     * @return zmienna przechowująca id gracza który wygrał
     */
    public int getWinner() {
        return winner;
    }

    /**
     * getter dla flagi zatrzymania gry
     * @return flaga zatrzymania gry
     */
    public boolean isPause() {
        return pause;
    }

    public boolean isReadyToReset() {
        return readyToReset;
    }
}
