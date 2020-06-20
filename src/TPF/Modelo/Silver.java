package TPF.Modelo;

import TPF.Modelo.Avion;

import java.io.Serializable;

public class Silver extends Avion implements Serializable {

    public Silver(){}

    public Silver (int id, int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering){
        super(id, combustible,costo,pasajeros,velMax,propulsion,catering);
    }

    @Override
    public int obtenerTarifa(){
        return 4000;
    }

    @Override
    public String toString() {
        return super.toString()+"}";
    }
}
