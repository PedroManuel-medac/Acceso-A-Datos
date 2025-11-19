package EjemploEjercicio;
import java.io.FileReader;
import java.io.FileWriter;

public class EjemploFileReader {

    public static void main(String[] args) {
        
        String path = "./Ejemplo2.txt";
        String pathEscritura = "./Ejemplo2.txt";

        try{

            FileReader lector = new FileReader(path);
            int data;

            while((data = lector.read()) != -1){

              System.out.print((char)data);  
            }

            System.out.println("");
            
            lector.close();
            System.out.println("Lectura completada");

        // Para escribir

            FileWriter escribir = new FileWriter ("./Ejemplo2.txt");
            escribir.write("Esto es una prueba");
            escribir.close();

        }catch (Exception e){

        }

    }
}
