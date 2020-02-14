package edu.escuelaing.Calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    public Calculator(){

    }

    public  ArrayList<Double> Ordenar(String sNumeros){
        List<String> Lnums = Arrays.asList(sNumeros.split(","));
        ArrayList<Double> nums = new ArrayList<Double>();
        for(String num: Lnums){
            nums.add(Double.valueOf(num));
        }
        return nums;
    }

    public Double sumatoria(ArrayList<Double> numeros){
        Double sum =0.0;
        for (Double d: numeros){
            sum+=d;

        }
        return sum;
    }
}