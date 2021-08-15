package ProjektJTP.Laserki;

import java.awt.*;
import java.util.Random;

/**
 *  Klasa zarządzająca laserami. Tworzy je i panuje nad ich ruchem
 */
public class LaserControl {
    private final Laserek[] laserki = new Laserek[30];
    private final Random rand = new Random();

    /**
     * Konstruktor. Odpowiada za wygenerowanie 30 laserów o losowych parametrach
     */
    public LaserControl() {
        for (int i = 0; i<30; i++){
            boolean s = rand.nextBoolean();
            int sign = -1;
            if(s) sign = 1;

            int tempY = 20*i+rand.nextInt(20);

            laserki[i] = new Laserek(rand.nextInt(1280),tempY, rand.nextBoolean()? Color.RED:Color.GREEN, sign*3);
        }
    }

    /**
     *  procedura wymuszenia ruchu na laserach
     */
    public void Oblicz(){
        for (int i = 0; i<30; i++){
            laserki[i].setX();
        }
    }

    /**
     *  getter tablicy laserów
     * @return tablica laserów
     */
    public Laserek[] getLaserki()
    {
        return laserki;
    }

}
