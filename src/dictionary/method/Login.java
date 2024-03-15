package dictionary.method;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    public static boolean loginRequest(String username, String password, String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String data;
        while ((data = br.readLine()) != null) {
            String[] dataParse = data.split("\\s");
            String usernameInFile = dataParse[0];
            String passwordInFile = dataParse[1];
            if (username.equals(usernameInFile) && password.equals(passwordInFile)) {
                return true;
            }
        }
        return false;
    }
}
