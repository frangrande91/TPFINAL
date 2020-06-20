package TPF.Menu;

import java.io.IOException;

public final class Utilidades {
    public static void clearScreen() {
        for (int i = 0; i < 80 * 300; i++)
            System.out.println("\b");
    }

    public static void pausar() {
        System.out.println("Presione ENTER para continuar..");
        try {
            System.in.read();
        } catch (IOException e) {

        }
    }
}
