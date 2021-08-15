package ProjektJTP.Laserki;

import java.awt.*;

/**
 *  Klasa odpowiadająca za jeden laser widoczny na ekranie
 */
public class Laserek {
    private final int y;
    private int x;
    private final int length;
    private final Color kolor;
    private final int dx;

    /**
     * Konstruktor jednego lasera
     * @param x początkowa pozycja na osi x
     * @param y początkowa pozycja na osi y
     * @param kolor kolor lasera
     * @param dx prędkość lasera
     */
    public Laserek(int x, int y, Color kolor, int dx) {
        this.x = x;
        this.y = y;
        this.kolor = kolor;
        this.dx = dx;
        this.length = 15;
    }

    /**
     * getter ziennej y
     * @return pozycja na osi y
     */
    public int getY() {
        return y;
    }

    /**
     * getter zmiennej x
     * @return pozycja na osi x
     */
    public int getX() {
        return x;
    }

    /**
     * procedura przesuwająca laser o zadaną prędkość
     */
    public void setX() {

        this.x= (x+dx) % 1300;
        if(x<0)
            x = 1280;

    }

    /**
     * getter długośći lasera
     * @return długość lasera
     */
    public int getLength() {
        return length;
    }

    /**
     * getter koloru lasera
     * @return kolor lasera
     */
    public Color getKolor() {
        return kolor;
    }
}
