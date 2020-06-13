package TPF;

public class Gold extends Avion {
    private boolean wifi;

    public Gold(int id, int combustible, double costo, int pasajeros, int velMax, Propulsion propulsion, boolean catering, boolean wifi) {
        super(id, combustible, costo, pasajeros, velMax, propulsion, catering);
        this.wifi = wifi;
    }


    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public int getTarifa(){
        return 6000;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Wifi: " + wifi +
                '}';
    }
}



    /*
    public Gold(Gold clon){ //clonar avion
        super(clon.capacidadCombustible,clon.costoPorKm,clon.capacidadMaxPasajeros,clon.velocidadMax,clon.propulsion,clon.catering);
        this.wifi=clon.wifi;
    }

     */