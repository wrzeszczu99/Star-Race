package ProjektJTP.Bonus;

public class Timer extends Thread{

    int counter;

    public Timer() {
        this.counter= 0;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
            if(counter >6) counter = 0;
        }
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
