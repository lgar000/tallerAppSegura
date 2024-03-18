package edu.escuelaing.arem.ASE.app;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;
import static spark.Spark.get;

public class HelloSpark1 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        port(getPort());
        staticFileLocation("/public");


        secure(getKeyStore(),getPasswordKeyStore(), null, null);
        User user= new User();


        before((req, res) -> {
            String username = req.session().attribute("username");
            String path = req.pathInfo();

            List<String> protectedRoutes = Arrays.asList("/local", "/remote");

            if (username == null && protectedRoutes.contains(path)) {
                res.redirect("/login.html");
            }
        });


        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            System.out.println("contrasea : " + password);
            System.out.println("usuario "+ username);
            System.out.println("credencieles validas: "+ user.isUser(username, password));
            if (user.isUser(username, password)) {
                System.out.println("las credenciales son correctas");
                req.session().attribute("username", username);
               return "Login successful";
            } else {
                System.out.println("las credenciales son incorrectas");

                return "Invalid credentials. Try again.";
            }

        });

        get("/local", (req, res) -> "Hello Spark 1");
        get("/remote", (req, res) -> SecureURLReader.secureURL(getUrl(), getOtherKeyStore(), getPasswordKeyStore()));

        get("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/login.html");
            return null;
        });

        post("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/login.html");
            return null;
        });

    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }

        return "credenciales/ecikeystore.p12";
    }

    static String getUrl(){
        if(System.getenv("URL") != null){
            return System.getenv("URL");
        }
        return "https://localhost:5001/local";
    }


    static String getOtherKeyStore() {
        if (System.getenv("KEY") != null) {
            return System.getenv("KEY");
        }

        return "credenciales/ecikeystore1.p12";
    }

    static String getPasswordKeyStore(){
        if(System.getenv("PWDKEYSTORE")!=null){
            return System.getenv("PWDKEYSTORE");
        }
        return "1234arep";
    }
}
