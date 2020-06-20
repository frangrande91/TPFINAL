package TPF.Modelo;

import java.io.Serializable;
//
//import com.fasterxml.jackson.annotation.JsonSubTypes;
//import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
//
//@JsonTypeInfo (use = Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = ", tipo")
//@JsonSubTypes ({ @Type (value = Gold.class), @Type (value = Silver.class), @Type (value = Bronze.class),
//})


public abstract class Avion implements Serializable  {
    protected int id;
    protected double capacidadCombustible;
    protected double costoPorKm;
    protected int capacidadMaxPasajeros;
    protected int velocidadMax;
    protected String propulsion;
    protected boolean catering;

    public Avion() {
    }

    public Avion(int id, double combustible, double costoXkm, int pasajeros, int velMax, String propulsion, boolean catering){
        this.id = id;
        this.capacidadCombustible=combustible;
        this.costoPorKm=costoXkm;
        this.capacidadMaxPasajeros =pasajeros;
        this.velocidadMax=velMax;
        this.propulsion=propulsion;
        this.catering=catering;

    }

    public int getId(){return id;}
    public double getCapacidadCombustible() {return capacidadCombustible;}
    public int getCapacidadMaxPasajeros() {return capacidadMaxPasajeros; }
    public double getCostoPorKm() {return costoPorKm; }
    public int getVelocidadMax() { return velocidadMax; }
    public String getPropulsion() { return propulsion; }
    public boolean isCatering() { return catering; }


    public void setId(int id) {this.id = id; }
    public void setCapacidadCombustible(double capacidadCombustible) {this.capacidadCombustible = capacidadCombustible;}
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }
    public void setCapacidadMaxPasajeros(int capacidadMaxPasajeros) {this.capacidadMaxPasajeros = capacidadMaxPasajeros;}
    public void setVelocidadMax(int velocidadMax) { this.velocidadMax = velocidadMax; }
    public void setPropulsion(String propulsion) { this.propulsion = propulsion; }
    public void setCatering(boolean catering) { this.catering = catering; }

    public int obtenerTarifa(){return 0;};

    @Override
    public String toString() {
        return "Avion {" + this.getClass().getSimpleName() +
                ", Id: " + id +
                " - Capacidad combustible: " + capacidadCombustible +
                " lts., Costo por km.: $" + costoPorKm +
                ", Capacidad pasajeros: " + capacidadMaxPasajeros +
                ", Velocidad Max: " + velocidadMax +
                "km/h, " + propulsion +
                ", Catering: " + catering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avion avion = (Avion) o;

        return (id != avion.id);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(capacidadCombustible);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(costoPorKm);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + capacidadMaxPasajeros;
        result = 31 * result + velocidadMax;
        result = 31 * result + propulsion.hashCode();
        result = 31 * result + (catering ? 1 : 0);
        return result;
    }


}
