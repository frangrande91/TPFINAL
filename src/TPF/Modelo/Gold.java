package TPF.Modelo;

import java.io.Serializable;

public class Gold extends Avion implements Serializable{

        private boolean wifi;

        public Gold(){
            super();
        }

        public Gold(int id, int combustible, double costo, int pasajeros, int velMax, String propulsion, boolean catering, boolean wifi) {
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
        public int obtenerTarifa(){
            return 6000;
        }

        @Override
        public String toString() {
        return super.toString()+", wifi: "+wifi+"}";
    }

}
