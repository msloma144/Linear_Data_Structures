import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private int currentMinute, totalMinutes, priorCars, idleMinutes, stepValue;
    private Car nextCar, currentCar;
    private Queue<Car> carQueue;
    private ArrayList<Car> arrivedCars, servicedCars;

    public Simulation(int hours, int minutes, int priorCars, int stepValue){
        this.currentMinute = 0;
        this.totalMinutes = hours * 60;
        this.totalMinutes += minutes;
        this.priorCars = priorCars;
        this.stepValue = stepValue;
        this.carQueue = new Queue<>(8);
    }

    private void generateInitialCars(){
        Random randInt = new Random();
        if(priorCars > 0){
            for(int i = 0; i < priorCars; i++){
                if(!carQueue.isFull()){
                    carQueue.enqueue(new Car(0));
                }
                arrivedCars.add(new Car(0));
            }
            currentCar = carQueue.dequeue();
            nextArrival();
        }
        else {
            nextCar = new Car(randInt.nextInt(5) + 1);
            nextCar.setWaitTime(0);
            nextCar.calcEndTime();
        }
    }

    private void nextArrival(){
        Random randInt = new Random();
        int startTime = randInt.nextInt(5) + 1;

        this.nextCar = new Car(startTime + currentMinute);
    }

    private void handleArrival(){
        arrivedCars.add(nextCar);

        if(carQueue.isFull());
        else if(carQueue.isEmpty() && currentCar == null){
            currentCar = nextCar;
            nextCar = null;
        }
        else {
            carQueue.enqueue(nextCar);
        }
        nextArrival();
    }

    private void handleDepart(){
        servicedCars.add(currentCar);

        if(!carQueue.isEmpty()) {
            currentCar = carQueue.dequeue();

            int waitTime = currentMinute - currentCar.getStartTime();
            currentCar.setWaitTime(waitTime);
            currentCar.calcEndTime();
        }
        else {
            currentCar = null;

            nextCar.setWaitTime(0);
            nextCar.calcEndTime();
        }
    }

    public String getStats(){
        int totalNumCust = servicedCars.size();
        int idleMinutes = this.idleMinutes;
        int bypassed = arrivedCars.size() - servicedCars.size();
        int longestWait = 0;
        int avgWait = 0;

        for (Car tmp: servicedCars) {
            avgWait += tmp.getWaitTime();
            if(tmp.getWaitTime() > longestWait) longestWait = tmp.getWaitTime();
        }

        if(!(servicedCars.size() == 0))
            avgWait = avgWait / servicedCars.size();
        else avgWait = 0;

        return  "Total number of customers served: " + totalNumCust + "\n" +
                "Number of minutes the car wash was idle: " + idleMinutes + "\n" +
                "Average wait time: " + avgWait + "\n" +
                "Longest wait time: " + longestWait + "\n" +
                "The number of customers that bypassed the car wash: " + bypassed;
    }

    public String queueStatus(){
        StringBuilder output = new StringBuilder("   Queue Status\n");
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        for (int i =0; i < carQueue.size(); i++) {
            line1.append(" _____       ");
            line2.append("/ |   | \\      ");
            line3.append("O -- O     ");
        }

        output.append(line1 + "\n");
        output.append(line2 + "\n");
        output.append(line3 + "\n");
        return output.toString();
    }

    public void runSimulation(){
        arrivedCars = new ArrayList<>();
        servicedCars = new ArrayList<>();

        generateInitialCars();

        while (currentMinute < totalMinutes){
            if(currentCar == null){
                idleMinutes++;
            }

            if(nextCar.getStartTime() == currentMinute){
                handleArrival();
            }

            if(!(currentCar == null) && (currentCar.getEndTime() == currentMinute)){
                handleDepart();
            }
            currentMinute++;
        }
    }

    public void runStep(int stepValue){
        //int stepValue = this.stepValue;
        if(currentMinute == 0){
            arrivedCars = new ArrayList<>();
            servicedCars = new ArrayList<>();

            generateInitialCars();
        }

        while ((currentMinute < totalMinutes) && !(stepValue == -1)){
            if (currentCar == null) {
                idleMinutes++;
            }

            if (nextCar.getStartTime() == currentMinute) {
                handleArrival();
            }

            if(!(currentCar == null) && (currentCar.getEndTime() == currentMinute)) {
                handleDepart();
            }
            currentMinute++;
            stepValue--;
        }
    }
}
