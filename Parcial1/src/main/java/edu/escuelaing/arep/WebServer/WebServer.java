package edu.escuelaing.arep.WebServer;


import com.google.gson.Gson;
import static spark.Spark.*;

import java.util.ArrayList;

import edu.escuelaing.Calculator.Calculator;

public  class WebServer {
    static Calculator calc;
    public static void main(String[] args) {
        calc = new Calculator();
        
        staticFiles.location("/paginas");
        Gson gson = new Gson();
        port(getPort());
        get("/parcial", (request, response) -> {
            response.redirect("/index.html");
            response.status(200);
            return null;
        });
        


        post("/parcial/calcular/", (req, res) -> {
            //res.type("application/json");
            //res.status(201);
            
            ArrayList<Double> ordenados = calc.Ordenar(req.body());
            Double sum = calc.sumatoria(ordenados);
            //System.out.println(calculado);
            String resp = respuesta(ordenados,sum);
            System.out.println(resp);
			return gson.toJson(resp);
            
        });
        }
        
        private static String respuesta(ArrayList<Double> ordenados, Double sum) {
        String ord ="[";
        for (Double d: ordenados){
            if(!(ordenados.indexOf(d)==ordenados.size()-1)){
                ord+=String.valueOf(d)+",";
            }
            else{ ord+=String.valueOf(d);}
        }
        ord+="]";
        System.out.println(ord);
        
        String suma = String.valueOf(sum);
        String res ="{\"DatosOrdenados\":"+ord+",\"Sumatoria\":"+suma+"}";
        return res;

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
        }
    
}

