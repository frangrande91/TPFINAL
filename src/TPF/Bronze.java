package TPF;

public class Bronze extends Avion {


    public Bronze (int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering){
        super(combustible,costo,pasajeros,velMax,propulsion,catering);
    }


    @Override
    public String toString() {
        return super.toString()+"}";
    }
}