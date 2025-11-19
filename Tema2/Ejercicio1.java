import java.io.StreamTokenizer;
import java.io.StringReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Ejercicio1 {
    public static void main(String[] args) throws FileNotFoundException {
    
        //StreamTokenizer st = new StreamTokenizer(new StringReader("Hola mi edad es 21"));
        StreamTokenizer st = new StreamTokenizer(new FileReader("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\Acceso-A-Datos\\Tema2\\Ejermplo1.txt"));
        st.eolIsSignificant(true); //para señalar donde hay un fin de linea

        try {
            
            while (st.nextToken() != StreamTokenizer.TT_EOF){
                if (st.ttype == StreamTokenizer.TT_WORD) {
                    System.out.println("Palabra - " + st.sval); //Texto -> sval
                }else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("Numero - " + st.nval); //Numeros -> nval
                }else if (st.ttype == StreamTokenizer.TT_EOL) {
                    System.out.println("Fin");
                }

            }

        } catch (Exception e) {
        
        }
    }
}
