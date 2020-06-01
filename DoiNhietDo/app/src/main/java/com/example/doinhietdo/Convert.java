package com.example.doinhietdo;

public class Convert {
    public enum TEMPERATURE{
        F,C
    }

    private double number;
    public Convert(){}

    public Convert(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public double convertCtoF() {
        return round(number * 9/5 + 32 );
    }

    public double convertFtoC() {
        return round((number - 32) * 5/9 );
    }

    public double round(double number){
        return Math.ceil(number*1000)/1000;
    }
}
