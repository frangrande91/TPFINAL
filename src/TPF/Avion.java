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

    public void setCapacidadCombustible(int capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    public double getCostoPorKm() {
        return costoPorKm;
    }

    public void setCostoPorKm(double costoPorKm) {
        this.costoPorKm = costoPorKm;
    }

    public int getCapacidadMaxPasajeros() {
        return capacidadMaxPasajeros;
    }

    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {
        this.capacidadMaxPasajeros = capacidadMaxPasajeros;
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
                ", capacidadPasajeros=" + capacidadMaxPasajeros +
                ", velocidadMax=" + velocidadMax +
                ", propulsion=" + propulsion +
                ", catering=" + catering+", ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avion avion = (Avion) o;

        if (capacidadCombustible != avion.capacidadCombustible) return false;
        if (Double.compare(avion.costoPorKm, costoPorKm) != 0) return false;
        if (capacidadMaxPasajeros != avion.capacidadMaxPasajeros) return false;
        if (velocidadMax != avion.velocidadMax) return false;
        if (catering != avion.catering) return false;
        return propulsion.equals(avion.propulsion);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = capacidadCombustible;
        temp = Double.doubleToLongBits(costoPorKm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + capacidadMaxPasajeros;
        result = 31 * result + velocidadMax;
        result = 31 * result + propulsion.hashCode();
        result = 31 * result + (catering ? 1 : 0);
        return result;
    }
}
