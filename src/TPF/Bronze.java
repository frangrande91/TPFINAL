package TPF;

public class Bronze extends Avion {


    public Bronze (int id, int combustible, double costo, int pasajeros, int velMax, Propulsion propulsion, boolean catering){
        super(id, combustible,costo,pasajeros,velMax,propulsion,catering);
    }

    @Override
    public int getTarifa(){
        return 3000;
    }

    @Override
    public String toString() {
        return super.toString()+"}";
    }
}