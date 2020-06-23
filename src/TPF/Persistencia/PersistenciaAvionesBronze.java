package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Bronze;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class PersistenciaAvionesBronze {

    public static HashSet<Bronze> leerAvionesBronze(){
        HashSet<Bronze> aviones = null;
        try{
            File file = new File("avionesBronze.json");
            ObjectMapper mapper = new ObjectMapper();
            aviones = mapper.readValue(file, new TypeReference<HashSet<Bronze>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de aviones");
            Utilidades.pausar();
        }

        return aviones;
    }

    public static void persistirAvionesBronze(HashSet<Bronze> avionesBronze){
        try{
            File file = new File("avionesBronze.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            mapper.writeValue(file, avionesBronze);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de aviones");
            Utilidades.pausar();
        }
    }

}
