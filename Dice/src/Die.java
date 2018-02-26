/**
 * This is the object class for constructing die objects
 * as in a pair of dice.
 * Author: Michael Sloma, September 2017
 */
public class Die {
    private int dieValue;

    Die(){
        this.dieValue = -1;
    }

    public int getDieValue() {
        return dieValue;
    }

    public void setDieValue(int dieValue) {
        if(dieValue > 0 && dieValue <= 6) this.dieValue = dieValue;
        else System.out.println("This is not a valid die value");

    }

    @Override
    public String toString() {
        switch (this.dieValue){
            case 1: return "\n  *\n\n";
            case 2: return "*\n\n    *";
            case 3: return "*\n  *\n    *";
            case 4: return "*   *\n\n*   *";
            case 5: return "*   *\n  *\n*   *";
            case 6: return "*   *\n*   *\n*   *";
        }
        return null;
    }
}
