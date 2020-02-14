package edu.escuelaing.arep.WebServer;

import java.net.*;

import edu.escuelaing.arep.Calculator.Calculator;

import java.io.*;

public class JavaServer {
    public static Calculator calc;

    public JavaServer(){
        
    }
    public static void main(String[] args) throws IOException {
        calc = new Calculator();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("Bye")){
                out.close();
                in.close();
                clientSocket.close();
                serverSocket.close();
            }
            System.out.println("Mensaje:" + inputLine);
            try {  
                Double[] ordenados = calc.Ordenar(inputLine);
                Double sum = calc.sumatoria(ordenados);
                outputLine = respuesta(ordenados, sum);  
            } catch(NumberFormatException e){  
                outputLine = "Respuesta" + inputLine ; 
            }  
            out.println(outputLine);
        } 
        outputLine = "Respuesta" + inputLine ;
        

            
            
        }



        private static String respuesta(Double[] ordenados, Double sum) {
            String ord ="[";
            for (int i=0; i<ordenados.length;i++){
                if(!(i==ordenados.length-1)){
                    ord+=String.valueOf(ordenados[i])+",";
                }
                else{ ord+=String.valueOf(ordenados[i]);}
            }
            ord+="]";
            System.out.println(ord);
            
            String suma = String.valueOf(sum);
            String res ="DatosOrdenados\":"+ord+",\"Sumatoria\":"+suma;
            return res;
    
        }
        
    }




