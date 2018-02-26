public class Tester {
    public static void main(String[] args){
        Simulation sim1 = new Simulation(8, 0, 0, 0);
        sim1.runSimulation();
        System.out.println(sim1.getStats());
    }
}
