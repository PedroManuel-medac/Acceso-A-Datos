import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.stream.Stream;

import javax.sound.sampled.Line;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        
        int palabras = 0;
        int numeros = 0;

        try {
            
            LineNumberReader ln = new LineNumberReader(new FileReader("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\Acceso-A-Datos\\Tema2\\entrada.txt"));
            String linea = ln.readLine();

            while (linea != null) {
                StreamTokenizer st = new StreamTokenizer(new StringReader(linea));
                System.out.println("Linea " + ln.getLineNumber() + ": " + linea );
                linea = ln.readLine();

                while (st.nextToken() != StreamTokenizer.TT_EOF) {
                    if (st.ttype == StreamTokenizer.TT_WORD) {
                        palabras++;
                    }
                    if (st.ttype == StreamTokenizer.TT_NUMBER) {
                        numeros++;
                    }
                    
                }
                System.out.println("Palabras: " + palabras + " Numeros: " + numeros);
                palabras = 0;
                numeros = 0;
            }


        } catch (Exception e) {
            // TODO: handle exception
        }

            
    }
}
