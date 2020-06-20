package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Silver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class PersistenciaAvionesSilver {

    public static HashSet<Silver> leerAvionesSilver(){
        HashSet<Silver> aviones = null;
        try{
            File file = new File("avionesSilver.json");
            ObjectMapper mapper = new ObjectMapper();
            aviones = mapper.readValue(file, new TypeReference<HashSet<Silver>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de aviones");
            Utilidades.pausar();
        }

        return aviones;
    }

    public static void persistirAvionesSilver(HashSet<Silver> avionesSilver){

        try{
            File file = new File("avionesSilver.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            //ESCRIBIR JSON
            mapper.writeValue(file, avionesSilver);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de aviones");
            Utilidades.pausar();
        }
    }
}
