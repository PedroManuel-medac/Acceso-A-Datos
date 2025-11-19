package com.example.AlquilerBicicletas;
import java.util.Date;

public class Alquiler {
    
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private Usuario id_usuario;
    private Bicicleta id_bicicleta;
    private int coste;

    public Alquiler(){

    }

    public Alquiler(String fi, String ff, Usuario id_usu, Bicicleta id_bici, int coste){
        this.fecha_fin = ff;
        this.fecha_inicio = fi;
        this.id_usuario = id_usu;
        this.id_bicicleta = id_bici;
        this.coste = coste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Bicicleta getId_bicicleta() {
        return id_bicicleta;
    }

    public void setId_bicicleta(Bicicleta id_bicicleta) {
        this.id_bicicleta = id_bicicleta;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    
    
}
