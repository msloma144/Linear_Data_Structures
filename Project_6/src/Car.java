import java.util.Random;

public class Car {
    private int startTime;
    private int serviceTime;
    private int waitTime;
    private int endTime;

    Car(int startTime){
        this.startTime = startTime;
        generateServiceTime();
    }

    private void generateServiceTime(){
        Random randInt = new Random();
        serviceTime = randInt.nextInt(4) + 2;
    }

    int getStartTime(){
        return startTime;
    }

    void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    void calcEndTime(){
        endTime = startTime + waitTime + serviceTime;
    }

    int getEndTime(){
        return endTime;
    }
}
