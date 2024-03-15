package dictionary.method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Admin {
    public static void createNewDictionary(String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
        }
    }

    public static void writeWordToDictionary(String firstWord, String secondWord, String filePath) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filePath, true))) {
            br.write(firstWord + " - " + secondWord);
            br.newLine();
        }
    }

    public static void removeWordFromDic(String word, String filePath) throws IOException {
        Map<String, String> mapDictionary = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String data;
        while ((data = br.readLine()) != null) {

            String[] dataParse = data.split("-");
            mapDictionary.put(dataParse[0].trim(), dataParse[1].trim());
        }
        if (mapDictionary.containsKey(word)) {
            mapDictionary.remove(word);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, String> entry : mapDictionary.entrySet()) {
                    bw.write(entry.getKey() + " - " + entry.getValue());
                    bw.newLine();
                }
            }
            System.out.println("The word has been successfully removed from the dictionary\n");
        } else {
            System.out.println("The word was not fount in the file");
        }
    }

    public static void changeWordInDic(String currentWord, String changeWord, String filePath) throws IOException {
        Map<String, String> mapDictionary = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String data;
        while ((data = br.readLine()) != null) {
            String[] dataParse = data.split("-");
            mapDictionary.put(dataParse[0].trim(), dataParse[1].trim());
        }
        if (mapDictionary.containsKey(currentWord)) {
            mapDictionary.replace(currentWord, changeWord);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, String> entry : mapDictionary.entrySet()) {
                    bw.write(entry.getKey() + " - " + entry.getValue());
                    bw.newLine();
                }
            }
            System.out.println("The word has been successfully changed in the dictionary\n");
        } else {
            System.out.println("The word was not found in file!!!\n");
        }
    }
}
