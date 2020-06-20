package TPF.Modelo;

import TPF.Modelo.Avion;

import java.io.Serializable;

public class Gold extends Avion implements Serializable {

    public Gold(){
        super();
    }

    public Gold(int id, int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering, boolean wifi) {
        super(id, combustible, costo, pasajeros, velMax, propulsion, catering, wifi);
    }


    @Override
    public int obtenerTarifa(){
        return 6000;
    }

}




/*
    public Gold(Gold clon){ //clonar avion
        super(clon.id,clon.capacidadCombustible,clon.costoPorKm,clon.capacidadMaxPasajeros,clon.velocidadMax,clon.propulsion,clon.catering);
        this.wifi=clon.wifi;
    }*/