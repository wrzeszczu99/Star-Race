package ProjektJTP.Stateczki;

/**
 * Klasa DeltaFighter rozszerza klasę statek o własną pozycję początkową, długość i szerokość
 */
public class DeltaFighter extends Statek {

    /**
     *  Konstruktor przypisuje wartości początkowe dla modelu delta
     */
    public DeltaFighter(int x) {
        super(x);
        y = 625;
        ly = 55;
        lx = 50;
    }


}
