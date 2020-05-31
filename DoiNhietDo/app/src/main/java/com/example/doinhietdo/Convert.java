package com.example.doinhietdo;

public class Convert {
    private double DoC,DoF;
    public Convert(){}

    public double getDoC() {
        return DoC;
    }

    public double getDoF() {
        return DoF;
    }

    public void setDoC(double doC) {
        DoC = doC;
    }

    public void setDoF(double doF) {
        DoF = doF;
    }

    public void convertCtoF()
    {
        DoF=DoC*9/5+32;
    }

    public void convertFtoC()
    {
        DoC=(DoF-32)*5/9;
    }
}
