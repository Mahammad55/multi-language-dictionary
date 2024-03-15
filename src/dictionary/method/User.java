package dictionary.method;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User {
    public static String translate(String word, String filePath, int dicType) throws IOException {
        Map<String, String> mapDictionary = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String data;
        while ((data = br.readLine()) != null) {
            String[] dataParse = data.split("-");
            switch (dicType) {
                case 1 -> mapDictionary.put(dataParse[0].trim(), dataParse[1].trim());
                case 2 -> mapDictionary.put(dataParse[1].trim(), dataParse[0].trim());
                default -> System.out.println("Invalid Dictionary type");
            }
        }
        if (mapDictionary.containsKey(word)) {
            return mapDictionary.get(word);
        }
        return "Not Found";
    }
}
