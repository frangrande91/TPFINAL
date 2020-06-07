package TPF;

public enum Propulsion {
    HEL ("MotorAHelice"),
    REAC ("MotorAReaccion"),
    PIST ("MotorDePistones");

    private String motor;

    Propulsion(String motor) {
        this.motor=motor;
    }
}
