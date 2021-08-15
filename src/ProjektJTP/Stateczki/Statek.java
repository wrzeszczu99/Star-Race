package ProjektJTP.Stateczki;

/**
 * Klasa statek to abstrakcyjna klasa łącząca możliwości i parametry wszystkich statków
 */
public abstract class Statek {
    final int x;
    int y;
    int lx, ly;

    /**
     * Konstruktor
     * @param x ustawienie początkowe statku na swojej linii
     */
    public Statek(int x) {
        this.x = x;
    }

    /**
     * procedura ruchu statku w dół
     */
    public void incY(){
        y+=3;
        if (y>625) y = 625;
    }

    /**
     * procedura ruchu statku w górę
     */
    public void decY(){y-=3;}

    /**
     * getter pozycji na osi x statku
     * @return pozycja na osi x statku
     */
    public int getX() {
        return x;
    }

    /**
     * getter pozycji na osi y statku
     * @return pozycja na osi y statku
     */
    public int getY() {
        return y;
    }

    /**
     * getter długości statku
     * @return długość statku
     */
    public int getLx() {
        return lx;
    }

    /**
     *  getter wysokości statku
     *  @return wysokość statku
     */
    public int getLy() {
        return ly;
    }

    /**
     * procedura ustawia statki na punkcie startowym
     */
    public void punkt() {y = 625;}
}
