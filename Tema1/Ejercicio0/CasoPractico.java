package Ejercicio0;

/*Crea un programa en Java que permita a los usuarios modificar el contenido de un archivo de texto llamado 
 "datos.txt". El programa debe de seguir los siguiente pasos.

1º Escribe el abecedario en el fichero de texto mediante FileWriter.

2º Abre el archivo "datos.txt" en modo de lectura y escritura.

3º Pide al usuario que ingrese una posición (un número entero) en el archivo donde desea realizar la modificación.

4º Pide al usuario que ingrese los datos (un carácter, como un espacio) que desea escribir en esa posición. 

5º Utiliza RandomAccessFile para posicionarte en la posición especificada por el usuario y sobrescribir el contenido con los nuevos datos ingresados.

6º Cierra el archivo después de realizar la modificación.
*/

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CasoPractico {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
       
        try{
        
        String path = "./datos.txt";

        File datos = new File(path);

        if (datos.exists()) {
            datos.delete();
        } else{
            datos.createNewFile();
        }
        
        FileWriter escribir = new FileWriter(path);
        escribir.write("abcdefghijklmnñopqrstuvwxyz");
        escribir.close();

        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        System.out.println("Escriba la posicion: ");
        int posicion = sc.nextInt();
        sc.nextLine();

        System.out.println("Escribe el caracter: ");
        String caracter = sc.nextLine();

        raf.seek(posicion);
        raf.writeBytes(caracter);

        raf.close();

        }catch(Exception e){

        }
    }
}
