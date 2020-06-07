package TPF;

public abstract class Avion {
    protected int capacidadCombustible;
    protected double costoPorKm;
    protected int capacidadPasajeros;
    protected int velocidadMax;
    protected String propulsion;
    protected boolean catering;


    public Avion(int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering){
        this.capacidadCombustible=combustible;
        this.costoPorKm=costo;
        this.capacidadPasajeros=pasajeros;
        this.velocidadMax=velMax;
        this.propulsion=propulsion;
        this.catering=catering;
    }

    public int getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(int capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public double getCostoPorKm() {
        return costoPorKm;
    }

    public void setCostoPorKm(double costoPorKm) {
        this.costoPorKm = costoPorKm;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public String getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(String propulsion) {
        this.propulsion = propulsion;
    }

    public boolean isCatering() {
        return catering;
    }

    public void setCatering(boolean catering) {
        this.catering = catering;
    }

    public abstract int getTarifa();

    @Override
    public String toString() {
        return "Avion {" + this.getClass().getSimpleName() +
                " - capacidadCombustible=" + capacidadCombustible +
                ", costoPorKm=" + costoPorKm +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion +
                ", catering=" + catering+", ";
    }
}
