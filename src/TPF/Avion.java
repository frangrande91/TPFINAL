package TPF;

public abstract class Avion {
    protected int id;
    protected double capacidadCombustible;
    protected double costoPorKm;
    protected int capacidadMaxPasajeros;
    protected int velocidadMax;
    protected Propulsion propulsion;
    protected boolean catering;


    public Avion(int id, double combustible, double costoXkm, int pasajeros, int velMax, Propulsion propulsion, boolean catering){
        this.id = id;
        this.capacidadCombustible=combustible;
        this.costoPorKm=costoXkm;
        this.capacidadMaxPasajeros =pasajeros;
        this.velocidadMax=velMax;
        this.propulsion=propulsion;
        this.catering=catering;
    }

    public int getId(){return id;}
    public double getCapacidadCombustible() {
        return capacidadCombustible;
    }
    public int getCapacidadMaxPasajeros() {
        return capacidadMaxPasajeros;
    }
    public double getCostoPorKm() {return costoPorKm; }
    public int getVelocidadMax() { return velocidadMax; }
    public String getPropulsion() { return propulsion.toString(); }
    public abstract int getTarifa();

    public void setCapacidadCombustible(int capacidadCombustible) { this.capacidadCombustible = capacidadCombustible; }
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }
    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {this.capacidadMaxPasajeros = capacidadMaxPasajeros;}
    public void setVelocidadMax(int velocidadMax) { this.velocidadMax = velocidadMax; }
    public void setPropulsion(Propulsion propulsion) { this.propulsion = propulsion; }
    public boolean isCatering() { return catering; }
    public void setCatering(boolean catering) { this.catering = catering; }

    @Override
    public String toString() {
        return "Avion {" + this.getClass().getSimpleName() +
                ", Id: " + id +
                " - Capacidad combustible: " + capacidadCombustible +
                " lts., Costo por km.: $" + costoPorKm +
                ", Capacidad pasajeros: " + capacidadMaxPasajeros +
                ", Velocidad Max: " + velocidadMax +
                "km/h, " + propulsion.toString() +
                ", Catering: " + catering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avion avion = (Avion) o;

        return id == avion.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
