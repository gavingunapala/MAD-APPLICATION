package com.example.mad_project;

public class Cake {

    private Integer cake_quantity;
    private String cake_greeting;
    private String cake_name;

    public Cake() {
    }

    public String getCake_name() {
        return cake_name;
    }

    public void setCake_name(String cake_name) {
        this.cake_name = cake_name;
    }

    public Integer getCake_quantity() {
        return cake_quantity;
    }

    public void setCake_quantity(Integer cake_quantity) {
        this.cake_quantity = cake_quantity;
    }

    public String getCake_greeting() {
        return cake_greeting;
    }

    public void setCake_greeting(String cake_greeting) {
        this.cake_greeting = cake_greeting;
    }
}
