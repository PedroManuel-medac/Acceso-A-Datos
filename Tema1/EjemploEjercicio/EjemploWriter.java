package EjemploEjercicio;
import java.io.FileWriter;

public class EjemploWriter {
    public static void main(String[] args) {
        // Para escribir

        try{
            FileWriter escribir = new FileWriter ("./Ejemplo2.txt");
            escribir.write("Esto es una prueba 2");
            escribir.close();

            }catch(Exception e){
                
            }
    }

 }

