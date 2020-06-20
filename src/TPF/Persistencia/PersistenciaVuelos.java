package TPF.Persistencia;

import TPF.Menu.Utilidades;
import TPF.Modelo.Usuario;
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
            File file = new File("\\Users\\franc\\Desktop\\TPFINAL\\files\\vuelos.json");
            ObjectMapper mapper = new ObjectMapper();

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


            //ESCRIBIR JSON
            mapper.writeValue(file, vuelos);
        }
        catch (IOException e){
            System.out.println("No se pudo escribir el archivo de vuelos");
        }
    }

    public static ArrayList<Vuelo> leerVuelos(){
        ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
        try{
            File file = new File("\\Users\\franc\\Desktop\\TPFINAL\\files\\vuelos.json");
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

}



    /*

//    private List<Vuelo> vuelos;
     public static String path = "C:\\Users\\franc\\Desktop\\TPFINAL\\files\\users.json";
    //private static String path = "/files/vuelos.json";


//    public persistenciaVuelos(List<Vuelo> vuelos){
//        vuelos = new ArrayList<Vuelo>();
//        this.vuelos = vuelos;
//}


    public static void escribirJsonVuelos(Set<Usuario> usuarios){
        try{
            File fileusers = new File("C:\\Users\\franc\\Desktop\\TPFINAL\\files\\users.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            //for(Vuelo vuelo : vuelos){
                mapper.writeValue(fileusers, usuarios);
          //  }
        }
        catch (IOException e){
            System.err.println("No se pudo escribir el archivo");
        }
    }

    public static void leerJsonVuelos() {

        try {
            File fileuserss = new File("C:\\Users\\franc\\Desktop\\TPFINAL\\files\\users.json");
            ObjectMapper mapper2 = new ObjectMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper2.registerModule(javaTimeModule);
            mapper2.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            fileuserss = new File("C:\\Users\\franc\\Desktop\\TPFINAL\\files\\users.json");
            List<Usuario> aux = mapper2.readValue(fileuserss, List.class);
            for (Usuario u : aux){
                System.out.println(u.toString());
            }
        } catch (IOException e) {

            System.err.println("No se pudo leer el archivo");
        }

    }

     */





//    }   Vuelo aux = null;
//        Vuelo[] arrayy = null;
//        List <Vuelo> Vvuelos= null;
//        String v = "";
//        try{
//            File fileVuelos = new File(path);
//            ObjectMapper mapper = new ObjectMapper();
//            JavaTimeModule javaTimeModule = new JavaTimeModule();
//            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
//            mapper.registerModule(javaTimeModule);
//            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//           // TypeReference<Vuelo> tipoReferencia = ;
//            v= new String(Files.readAllBytes(Paths.get(path)));
//
//            System.out.println(v);
//
//           //  vuelos.toArray(arrayy);
//            aux = mapper.readValue(fileVuelos, Vuelo.class);
//            //System.out.println("Vuelo desde JSON: " +aux);
//
//          //  vuelos.add(aux);
//        }
//        catch (IOException e){
//
//            System.err.println("No se pudo leer el archivo");
//        }
//
//        return Vvuelos;
//    }


/* ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule=new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

 */