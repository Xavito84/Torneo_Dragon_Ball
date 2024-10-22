import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TorneoDragonBall {
    public static void main(String[] args) {

        //Crear lista de luchadores

        List<Luchador> luchadores= new ArrayList<Luchador>();

        luchadores.add(new Luchador("Goku", 90, 85, 75));
        luchadores.add(new Luchador("Vegeta", 85, 88, 70));
        luchadores.add(new Luchador("Piccolo", 70, 75, 80));
        luchadores.add(new Luchador("Gohan", 80, 82, 78));
        luchadores.add(new Luchador("Trunks", 85, 83, 76));
        luchadores.add(new Luchador("Freezer", 88, 90, 60));
        luchadores.add(new Luchador("Cell", 84, 87, 65));
        luchadores.add(new Luchador("Majin Buu", 82, 85, 85));

        //Comprobar si el numero de luchadores es una potencia de 2
        if(!esDividibleDeDos(luchadores.size())){
            System.out.println("El numero de luchadores tiene que ser divisible o potencia de 2");
            return;
        }

        //Ejecutar torneo

        startTorneo(luchadores);

    }

    //funciones


    public static boolean esDividibleDeDos(int numero) {
        return (numero & (numero - 1)) == 0;
    }

    public static void startTorneo(List<Luchador> luchadores) {
        int ronda = 1;
        while (luchadores.size() > 1) {
            System.out.println("\n--- Ronda " + ronda + " ---");
            Collections.shuffle(luchadores); // Mezclar luchadores para crear parejas aleatorias
            List<Luchador> ganadores = new ArrayList<>();

            for (int i = 0; i < luchadores.size(); i += 2) {
                Luchador luchador1 = luchadores.get(i);
                Luchador luchador2 = luchadores.get(i + 1);
                Luchador ganador = simularBatalla(luchador1, luchador2);
                ganadores.add(ganador);
            }

            luchadores = ganadores; // Los ganadores avanzan a la siguiente ronda
            ronda++;
        }

        // Mostrar el ganador del torneo
        System.out.println("\n¡El ganador del torneo es " + luchadores.get(0).nombre + "!");
    }

    public static Luchador simularBatalla( Luchador luchador1, Luchador luchador2 ){
        System.out.println("\nBatalla entre "+ luchador1.nombre+ " y " + luchador2.nombre);

        //Determinar quien ataca primero (el de mayor velocidad)
        Luchador atacante= luchador1.velocidad >= luchador2.velocidad ? luchador1 : luchador2;
        Luchador defensor= atacante == luchador1 ? luchador2 : luchador1;

        //simular batalla
        while( luchador1.salud> 0 && luchador2.salud>0) {
            System.out.println(atacante.nombre+ " ataca a "+defensor.nombre);

            //El defensor tiene 20% de probabilidad de esquivar el ataque
            if(defensor.esquivar()){
                System.out.println(defensor.nombre+" esquivo el ataque");
            }else{
                //Calculo el danyo y reduzco la salud del defensor
                int danyo = defensor.calcularDanyo(atacante.ataque);
                defensor.recibirDanyo(danyo);
                System.out.println(defensor.nombre+" recibio "+ danyo+ " de daño. Salud restante es: "+ defensor.salud);
            }

            //Cambiar roles: el defensor ataca el atacante defiende
            Luchador temp = atacante;
            atacante = defensor;
            defensor = temp;

        }
        //Determinar el ganador
        Luchador ganador= luchador1.salud >0 ? luchador1 : luchador2;
        System.out.println("¡ "+ ganador.nombre+ " gana la batalla!\n");
        return ganador;
    }

}
