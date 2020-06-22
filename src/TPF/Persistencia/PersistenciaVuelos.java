package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Vuelo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaVuelos {

    public static void persistirVuelos(List<Vuelo> vuelos){

        try{
            File file = new File("vuelos.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            mapper.writeValue(file, vuelos);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de vuelos");
        }
    }

    public static ArrayList<Vuelo> leerVuelos(){
        ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
        try{
            File file = new File("vuelos.json");
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            vuelos = mapper.readValue(file, new TypeReference<ArrayList<Vuelo>>(){});
        }
        catch (IOException e){
            System.err.println("No se pudo leer el archivo de vuelos");
            Utilidades.pausar();
        }

        return vuelos;
    }

    public static int cantidadVuelos(){        //obtener el mayor id de todos los vuelos
        int rta = 0;
        ArrayList<Vuelo> vuelos = leerVuelos();
        for(Vuelo v:vuelos){
            if(v.getId()>rta)
                rta= v.getId();
        }

        return rta;
    }
}
