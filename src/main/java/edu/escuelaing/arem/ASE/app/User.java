package edu.escuelaing.arem.ASE.app;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class User {

    HashMap<String, String> users= new HashMap<>();

    public void saveUser(String user, String password) throws NoSuchAlgorithmException {
        String shaCadena= ShaCadena.shaPassword(password);
        users.put(user, shaCadena);
    }

    public boolean isUser(String user, String shaCadena){
        return users.containsKey(user);
    }

    
}
