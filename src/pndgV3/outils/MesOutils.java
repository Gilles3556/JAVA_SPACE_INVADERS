package pndgV3.outils;

import java.time.LocalTime;

public final class MesOutils {
    private MesOutils(){}

    public static String recuperTemps(){
        LocalTime temps = LocalTime.now();
        return temps.toString();
    }

}
