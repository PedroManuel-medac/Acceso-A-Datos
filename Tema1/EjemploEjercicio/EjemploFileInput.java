package EjemploEjercicio;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class EjemploFileInput {
    public static void main(String[] args) {
        
        String path = "./Ejemplo2.txt";

        try{
        
        int data;
        
        FileInputStream entrada = new FileInputStream(path);

        while((data = entrada.read()) != -1){
            System.out.print((char)data);  
        }
        
        System.out.println();

        }catch(Exception e){
            System.out.println("Algo pasa");
        }
    }
}
