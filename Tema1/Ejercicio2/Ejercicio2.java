package Ejercicio2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Ejercicio2 {
    public static void main(String[] args)  {
        
        try{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./Ejercicio2/davante.jpg"));

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./Ejercicio2/MoverImagen/davante1.jpg"));

        byte[] buffer = new byte[4*1024];
        int bytesLeidos;
        
        while ((bytesLeidos = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesLeidos);
        }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
