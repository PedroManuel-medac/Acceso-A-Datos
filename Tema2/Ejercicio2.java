

import java.io.FileReader;
import java.io.LineNumberReader;

public class Ejercicio2 {
    public static void main(String[] args) {
        try{

            LineNumberReader ln = new LineNumberReader(new FileReader("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\Acceso-A-Datos\\Tema2\\Ejermplo1.txt"));

            String linea = ln.readLine(); //lee cada linea

            while (linea != null) { //si linea es nula que haga lo siguiente
                System.out.println("El contenido de la linea numero: " + ln.getLineNumber()); //te dice la linea que va
                System.out.println(linea);
                linea = ln.readLine();
            }

            ln.close();

        }catch(Exception e){

        }
    }
}
