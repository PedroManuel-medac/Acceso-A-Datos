package com.example.prueba;

import java.util.Date;

public class Pedido {
    
    private int id;
    private Date fecha;
    private int cantidad;
    private Double importe;
    private Customer customer;
    private Producto producto;


    public Pedido() {
    }


    public Pedido(Date fecha, int cantidad, Double importe, Customer c, Producto p) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.importe = importe;
        this.customer = c;
        this.producto = p;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public int getCantidad() {
        return cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Double getImporte() {
        return importe;
    }


    public void setImporte(Double importe) {
        this.importe = importe;
    }


    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Producto getProducto() {
        return producto;
    }


    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    

    
}
