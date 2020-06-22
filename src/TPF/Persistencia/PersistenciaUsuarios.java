package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersistenciaUsuarios {

    public static HashSet<Usuario> leerUsuarios(){
        HashSet<Usuario> usuarios = null;
        try{
            File file = new File("usuarios.json");
            ObjectMapper mapper = new ObjectMapper();
            usuarios = mapper.readValue(file, new TypeReference<HashSet<Usuario>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de usuarios");
            Utilidades.pausar();
        }
        return usuarios;
    }

    public static void persistirUsuarios(HashSet<Usuario> usuarios){

        try{
            File file = new File("usuarios.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            mapper.writeValue(file, usuarios);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de aviones");
            Utilidades.pausar();
        }
    }

}
