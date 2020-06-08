package TPF;

public enum TipoVuelo {
    UNO("Buenos Aires", "Córdoba", 695),
    DOS("Buenos Aires", "Santiago", 1400),
    TRES("Buenos Aires", "Montevideo", 950),
    CUATRO("Córdoba", "Montevideo", 1190),
    CINCO("Córdoba", "Santiago", 1050),
    SEIS("Montevideo", "Santiago", 2100);

    private String origen;
    private String destino;
    private int distancia;

    private TipoVuelo(String origen, String destino, int distancia){
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return  "Origen: " + origen +
                " - Destino: " + destino +
                " - distancia: " + distancia;
    }

}
