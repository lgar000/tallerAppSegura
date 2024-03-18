package edu.escuelaing.arem.ASE.app;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class User {

    static HashMap<String, String> users= new HashMap<>();

    public User() throws NoSuchAlgorithmException {
        users.put("john", EncryptString.shaPassword("1234"));
        users.put("jane", EncryptString.shaPassword("5678"));
    }

    public boolean isUser(String user, String password) throws NoSuchAlgorithmException {
        String hashedPassword = users.get(user);
        if (hashedPassword != null) {
            // Encriptamos la contraseña proporcionada por el usuario
            String encryptedInputPassword = EncryptString.shaPassword(password);

            // Comparamos la contraseña encriptada almacenada con la versión encriptada de la contraseña proporcionada por el usuario
            return hashedPassword.equals(encryptedInputPassword);
        }
        return false;
    }

    public static HashMap<String, String> getUsers() {
        return users;
    }
}
