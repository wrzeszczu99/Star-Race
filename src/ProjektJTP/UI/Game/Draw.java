package ProjektJTP.UI.Game;

import ProjektJTP.Bonus.Bonus;
import ProjektJTP.Laserki.Laserek;
import ProjektJTP.Stateczki.Statek;

import javax.swing.*;
import java.awt.*;

/**
 *  Klasa która rysuje po ekranie
 */
public class Draw extends JPanel {
    private Laserek[] templaserki;
    private Statek tempS1, tempS2;
    private Bonus tempB1, tempB2;


    private int score1, score2;
    private boolean win = false;
    private int winner;



    /**
     *  rysowanie na ekranie
     *  @param g obiekt grafiki
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        rysujLaserek(g);
        rysujStateczki(g);
        rysujPunkciki(g);
        rysujBonusy(g);
        if(win){
            rysujZwyciezce(winner ,g);
        }
    }

    private void rysujBonusy(Graphics g) {
        g.setColor(Color.YELLOW);
        if(tempB1.isActive())g.fillRect(tempB1.getX()+20, tempB1.getY()-5, 10, 10);
        if(tempB2.isActive())g.fillRect(tempB2.getX()+20, tempB2.getY()-5, 10, 10);

    }

    /**
     *  rysowanie laserów na ekranie
     *  @param g obiekt grafiki
     */
    private void rysujLaserek(Graphics g) {
        for (int i = 0; i<30; i++){
            g.setColor(templaserki[i].getKolor());
            g.fillRect(templaserki[i].getX(),templaserki[i].getY(), templaserki[i].getLength(), 3);
        }
    }

    /**
     *  rysowanie statków na ekranie
     *  @param g obiekt grafiki
     */
    private void rysujStateczki(Graphics g) {
        //g.setColor(Color.WHITE);
        //Statek 1 Delta Fighter
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{tempS1.getX(), tempS1.getX()+(tempS1.getLx())/2,tempS1.getX()+tempS1.getLx() },new int[] {tempS1.getY()+tempS1.getLy()-10, tempS1.getY(),tempS1.getY()+tempS1.getLy()-10 }, 3);
        g.setColor(Color.RED);
        g.fillRect(tempS1.getX()+10, tempS1.getY()+tempS1.getLy()-10, 10, 10);
        g.fillRect(tempS1.getX()+30, tempS1.getY()+tempS1.getLy()-10, 10, 10);
        g.setColor(Color.CYAN);
        g.fillOval(tempS1.getX()+19, tempS1.getY()+tempS1.getLy()-28, 12 ,16);

        //Statek 2 TIE Fighter
        g.setColor(Color.GRAY);

        g.fillRect(tempS2.getX(),tempS2.getY()+5, 3, tempS2.getLy());
        g.fillRect(tempS2.getX(),tempS2.getY()+(tempS2.getLy()/2)+5, (tempS2.getLx()/2)-5, 3);
        g.fillRect(tempS2.getX()+(tempS2.getLx()/2)+5,tempS2.getY()+(tempS2.getLy()/2)+5, (tempS2.getLx()/2)-5, 3);
        g.fillRect(tempS2.getX()+tempS2.getLx(),tempS2.getY()+5, 3, tempS2.getLy());

        g.fillOval(tempS2.getX()+(tempS2.getLx()/2)-7,tempS2.getY()+(tempS2.getLy()/2)-2,15,15);
        g.setColor(Color.CYAN);
        g.fillOval(tempS2.getX()+(tempS2.getLx()/2)-4,tempS2.getY()+(tempS2.getLy()/2)+1,9,9);
    }

    /**
     *  ryowanie wyniku punktowego na ekranie
     *  @param g obiekt grafiki
     */
    private void rysujPunkciki(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString(String.valueOf(score1), 30, 680);
        g.drawString(String.valueOf(score2), 1230, 680);
    }

    /**
     *  rysowanie napisu określającego zwycięzce
     *  @param g obiekt grafiki
     *  @param winner numer gracza który wygrał
     */
    private void rysujZwyciezce(int winner, Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        if(winner ==1)
        g.drawString("Player 1 wins!", 500, 300);
        if(winner ==2)
        g.drawString("Player 2 wins!", 500, 300);
        g.drawString("Press Enter to play again", 400, 350);

    }

    /**
     * setter do tablicy tymczasowych wartości laserów
     * @param templaserki tymczasowe wartości laserków podane przez logikę gry
     */
    public void setTemplaserki(Laserek[] templaserki) {
        this.templaserki = templaserki;
    }

    /**
     *  setter do tymczasowych wartości statków
     * @param i numer statku
     * @param stateczek obiekt statku
     */
    public void setTempStateczek(int i, Statek stateczek) {
        switch (i) {
            case 1:
                this.tempS1 = stateczek;
            case 2:
                this.tempS2 = stateczek;
        }
    }

    /**
     *  setter aktualnej wartości punktowej gracza pierwszego
     * @param score1 punkty gracza pierwszego
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     *  setter aktualnej wartości punktowej gracza pierwszego
     * @param score2 punkty gracza pierwszego
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }


    public void setTempB1(Bonus tempB1) {
        this.tempB1 = tempB1;
    }

    public void setTempB2(Bonus tempB2) {
        this.tempB2 = tempB2;
    }


    /**
     *  procedura odpowiedzialna za ustawienie parametrów napisu do wyświetlenia
     * @param pause czy gra się zakończyła
     * @param w numer gracza który zwyciężył
     */
    public void winScreen(boolean pause, int w) {
        win = pause;
        winner = w;
    }
}
