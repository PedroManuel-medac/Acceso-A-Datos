package com.example.AlquilerBicicletas;
public class Bicicleta {
    
    private int id;
    private Estacion id_estacion;
    private String estado;

    public Bicicleta(){

    }

    public Bicicleta(Estacion estacion, String estado){
        this.id_estacion = estacion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estacion getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(Estacion id_estacion) {
        this.id_estacion = id_estacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
