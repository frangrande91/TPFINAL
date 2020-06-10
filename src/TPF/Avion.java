package TPF;

public abstract class Avion {
    protected int capacidadCombustible;
    protected double costoPorKm;
    protected int capacidadMaxPasajeros;
    protected int velocidadMax;
    protected String propulsion;
    protected boolean catering;


    public Avion(int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering){
        this.capacidadCombustible=combustible;
        this.costoPorKm=costo;
        this.capacidadMaxPasajeros =pasajeros;
        this.velocidadMax=velMax;
        this.propulsion=propulsion;
        this.catering=catering;
    }

    public int getCapacidadCombustible() {
        return capacidadCombustible;
    }
    public int getCapacidadMaxPasajeros() {
        return capacidadMaxPasajeros;
    }
    public double getCostoPorKm() {return costoPorKm; }
    public int getVelocidadMax() { return velocidadMax; }
    public String getPropulsion() { return propulsion; }
    public abstract int getTarifa();

    public void setCapacidadCombustible(int capacidadCombustible) { this.capacidadCombustible = capacidadCombustible; }
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }
    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {this.capacidadMaxPasajeros = capacidadMaxPasajeros;}
    public void setVelocidadMax(int velocidadMax) { this.velocidadMax = velocidadMax; }
    public void setPropulsion(String propulsion) { this.propulsion = propulsion; }
    public boolean isCatering() { return catering; }
    public void setCatering(boolean catering) { this.catering = catering; }

    @Override
    public String toString() {
        return "Avion {" + this.getClass().getSimpleName() +
                " - capacidadCombustible=" + capacidadCombustible +
                " lts., costoPorKm= $" + costoPorKm +
                ", capacidadPasajeros=" + capacidadMaxPasajeros +
                ", velocidadMax=" + velocidadMax +
                "km/h, propulsion=" + propulsion +
                ", catering=" + catering+", ";
    }
}
