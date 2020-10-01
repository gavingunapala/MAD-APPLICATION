package com.example.mad_project;

public class addtocart_inc {
    String cakename;
    String quentity="abc";



    public addtocart_inc(String cakename, String quentity) {
        this.cakename = cakename;
        this.quentity = quentity;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public String getQauntity() {

        return quentity;
    }

    public void setquentity(String quentity) {
        this.quentity = quentity;
    }
    public addtocart_inc(){}

}
