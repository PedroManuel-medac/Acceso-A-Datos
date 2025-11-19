package com.example.prueba;
public class Customer {
    
    private int id;
    private String firstName;
    private String lastName;



    //constructor
     public Customer() {
        
    }

    public Customer(String fn, String ln) {
        this.firstName = fn;
        this.lastName = ln;
    }

    //getters y setters
    public int getId() {
        return id;
    }
   
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    
}
