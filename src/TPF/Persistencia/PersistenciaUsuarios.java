package TPF.Persistencia;

import TPF.Modelo.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersistenciaUsuarios {

        public static HashSet<Usuario> leerUsuarios(){
            HashSet<Usuario> usuarios = null;
            try{
            File file = new File("\\Users\\franc\\Desktop\\TPFINAL\\files\\users.json");
            ObjectMapper mapper = new ObjectMapper();
            usuarios = mapper.readValue(file, new TypeReference<HashSet<Usuario>>(){});
            }

            catch (IOException e){
            System.out.println("No se pudo leer el archivo");
            }

            return usuarios;
        }

}
