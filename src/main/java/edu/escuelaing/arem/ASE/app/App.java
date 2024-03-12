package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;


public class App 
{
    public static void main(String... args){
        secure("credenciales/ecikeystore.p12", "1234arep", null, null);
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

}
