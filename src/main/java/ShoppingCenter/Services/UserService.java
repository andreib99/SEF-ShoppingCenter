package ShoppingCenter.Services;

import ShoppingCenter.Model.Client;
import ShoppingCenter.Model.Manager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {
    public static List<Client> clients;
    public static List<Manager> managers;

    public static boolean verifyClient(String username, String password) {
        if(clients.isEmpty())
        {
            return false;
        }
        for (Client client : clients) {
            if (username.equals(client.getUsername()) && Objects.equals(encodePassword(username, password), client.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean verifyManager(String username, String password){
        if(managers.isEmpty())
        {
            return false;
        }
        for (Manager manager : managers) {
            if (Objects.equals(username, manager.getUsername()) && Objects.equals(encodePassword(username,password), manager.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
