package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;

public class HttpRequest {

    public static void main(String... args){

        port(getPort());
        staticFileLocation("/public");

        get("hello", (req,res) -> "Hello Docker!");

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
