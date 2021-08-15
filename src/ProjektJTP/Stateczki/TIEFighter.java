package ProjektJTP.Stateczki;

/**
 * Klasa TIEFighter rozszerza klasę statek o własną pozycję początkową, długość i szerokość
 */
public class TIEFighter extends Statek {

    /**
     *  Konstruktor przypisuje wartości początkowe dla modelu TIE
     */
    public TIEFighter(int x) {
        super(x);
        y = 625;
        ly = 40;
        lx = 50;
    }
}
