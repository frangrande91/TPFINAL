package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Avion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class PersistenciaAviones {

    public static HashSet<Avion> leerAviones(){
        HashSet<Avion> aviones = null;
        try{
            File file = new File("\\Users\\franc\\Desktop\\TPFINAL\\files\\aviones.json");
            ObjectMapper mapper = new ObjectMapper();
            aviones = mapper.readValue(file, new TypeReference<HashSet<Avion>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de aviones");
            Utilidades.pausar();
        }

        return aviones;
    }

    public static void persistirAviones(HashSet<Avion> aviones){

        try{
            File file = new File("\\Users\\franc\\Desktop\\TPFINAL\\files\\aviones.json");
            ObjectMapper mapper = new ObjectMapper();

            //ESCRIBIR JSON
            mapper.writeValue(file, aviones);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de aviones");
            Utilidades.pausar();
        }
    }
}
