package Ejercicio1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Ejercicio1 {
    public static void main(String[] args) {
        
        String origen = "./origen.txt";
        String destino = "./destino.txt";

        int TamañoBuffer = 100;
        
        try{

            BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\Acceso-A-Datos\\Tema1\\Ejercicio1\\origen.txt"), TamañoBuffer); //lee el archivo
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\PC204\\Desktop\\2DAM\\Acceso a datos\\Acceso-A-Datos\\Tema1\\Ejercicio1\\destino.txt", true)); //escribe en el archivo

            byte[] buffer = new byte[TamañoBuffer];
            int bytesLeidos;
            int bloque = 1;

            while ((bytesLeidos = in.read(buffer)) != -1) {
                //Escribe exactamente lo que va leyendo
                out.write(buffer, 0, bytesLeidos);
                System.out.println("Fin del bloque " + bloque);
                bloque++;
            }

            in.close();
            out.close();

        }catch (Exception e){
            System.out.println("Algo pasa");
        }
    }
}
