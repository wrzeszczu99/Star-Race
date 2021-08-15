package ProjektJTP.Bonus;

public class Bonus {
    final private  int x;
    private int y;
    boolean active;

    public Bonus(int x) {
        this.x = x;
        active = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
