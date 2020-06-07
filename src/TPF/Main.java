package TPF;

public class Main {

    public static void main(String[] args) {
        Gold Boeing = new Gold(123,1024.5,300,700,Propulsion.REAC.toString(),true,true);
        System.out.println(Boeing.toString());
        System.out.println("Tarifa: "+Boeing.getTarifa());
    }
}
