package ProjektJTP;

import ProjektJTP.Stateczki.Statek;

/**
 * Klasa zarządza poczynaniami gracza. Kontroluje jego statek i ilość jego punktów
 */
public class Player extends Thread {
    private final Statek s;
    private int score;

    /**
     * Konstruktor w którym podajemy statek i ustawiamy początkowy wynik na 0
     * @param s statek gracza
     */
    public Player(Statek s) {
        this.s = s;
        score = 0;
    }

    /**
     * getter dla punktów
     * @return punkty gracza
     */
    public int getScore() {
        return score;
    }

    /**
     * procedura wywoływana gdy gracz zdobędzie punkt. Dodaje mu punkt i cofa na miejsce startu.
     */
    public void setScore() {
        this.score++;
        this.getStateczek().punkt();
    }

    /**
     * getter statku
     * @return statek gracza
     */
    public Statek getStateczek() {return s;}
}
