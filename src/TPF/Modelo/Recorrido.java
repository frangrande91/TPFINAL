package TPF.Modelo;

public class Recorrido {
    private static String[][] matriz={
        {null, "BuenosAires", "Córdoba", "Santiago", "Montevideo"},
        {"BuenosAires", null, "695", "1400", "950"},
        {"Córdoba", null, null, "1050", "1190"},
        {"Santiago", null, null, null, null},
        {"Montevideo", null, null, "2100", null}
    };

    public Recorrido() {
    }

    public static String getOrigen(int origen) {
        return matriz[origen][0];
    }
    public static String getDestino(int destino) {
        return matriz[0][destino];
    }
    public static String getDistancia(int origen, int destino) {
        return matriz[origen][destino];
    }


    public static void imprimirMatriz() {
        for (int origen = 0; origen < matriz.length; origen++)
            System.out.println(matriz[origen][0] + "\t\t\t" + matriz[origen][1] + "\t\t\t" + matriz[origen][2] + "\t\t\t" + matriz[origen][3] + "\t\t\t" + matriz[origen][4]);
    }

    public static String recorridoToString(int []recorrido) {
        return "[" + matriz[recorrido[0]][0] + " - " + matriz[0][recorrido[1]] + "]";
    }

}