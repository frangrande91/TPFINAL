package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Gold;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class PersistenciaAvionesGold{

    public static HashSet<Gold> leerAvionesGold(){
        HashSet<Gold> aviones = null;
        try{
            File file = new File("avionesGold.json");
            ObjectMapper mapper = new ObjectMapper();
            aviones = mapper.readValue(file, new TypeReference<HashSet<Gold>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de aviones");
            Utilidades.pausar();
        }

        return aviones;
    }

    public static void persistirAvionesGold(HashSet<Gold> avionesGold){

        try{
            File file = new File("avionesGold.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            mapper.writeValue(file, avionesGold);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de aviones");
            Utilidades.pausar();
        }
    }
}
