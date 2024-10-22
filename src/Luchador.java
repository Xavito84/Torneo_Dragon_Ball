import java.util.Random;

public class Luchador {
    String nombre;
    int velocidad, ataque, defensa, salud;

    public Luchador(String nombre, int velocidad, int ataque, int defensa) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.salud = 100;
    }

    public boolean esquivar() {

        Random rand= new Random();
        return rand.nextDouble() <0.2;

    }

    public int calcularDanyo(int ataqueOponente){
        if(defensa >= ataqueOponente){
            // Si la defensa es mayor o igual que el ataque, se recibe solo el 10% del daño de ataque
            return (int) (ataqueOponente*0.1);

        }else{
            //EL daño se calcula restando la defensa del ataque del oponente
            return ataqueOponente - defensa;
        }
    }

    public void recibirDanyo(int danyo){
        salud -= danyo;

        if (salud < 0){
            salud = 0;
        }
    }
    @Override
    public String toString(){
        return nombre + " (Salud: " + salud + ", Velocidad: " + velocidad + ", Ataque: " + ataque + ", Defensa: " + defensa + ")";
    }



}
