package TPF.Modelo;

import TPF.Modelo.Avion;

import java.io.Serializable;

public class Silver extends Avion implements Serializable {

    public Silver(){super();}

    public Silver (int id, int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering, boolean wifi){
        super(id, combustible,costo,pasajeros,velMax,propulsion,catering,wifi);
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
