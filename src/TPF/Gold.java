package TPF;

public class Gold extends Avion {
    private boolean wifi;

    public Gold(int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering, boolean wifi) {
        super(combustible, costo, pasajeros, velMax, propulsion, catering);
        this.wifi = wifi;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return super.toString() +
                "wifi=" + wifi +
                '}';
    }
}