package edu.escuelaing.arem.ASE.app;

import static spark.Spark.*;
import static spark.Spark.get;

public class HelloSpark2 {

    public static void main(String[] args) {
        port(getPort());
        secure(getKeyStore(), getPasswordKeyStore(), null,null);
        get("/local", (req, res) -> "Hello Spark 2");
        get("/remote", (req, res) -> SecureURLReader.secureURL(getUrl(), getOtherKeyStore(), getPasswordKeyStore()));
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }



    static String getUrl(){
        if(System.getenv("URL") != null){
            return System.getenv("URL");
        }
        return "https://localhost:5000/local";
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "credenciales/ecikeystore1.p12";
    }


    static String getOtherKeyStore() {
        if (System.getenv("KEY") != null) {
            return System.getenv("KEY");
        }
        return "credenciales/ecikeystore.p12";
    }

    static String getPasswordKeyStore(){
        if(System.getenv("PWDKEYSTORE")!=null){
            return System.getenv("PWDKEYSTORE");
        }
        return "1234arep";
    }
}
