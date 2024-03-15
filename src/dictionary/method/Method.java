package dictionary.method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Method {
    public static void printArray(String [] sentences){
        for(String sentence : sentences){
            System.out.println(sentence);
        }
    }
    public static int check(String filePath, String dictionaryPath) {
        int response = 2;
        String[] dataFile = filePath.split("[_.]+");
        try {
            boolean isFlag = false;  // 1 - success 2 - dictionary doesn't exist  3 - error
            BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
            String data;
            while ((data = br.readLine()) != null) {
                String[] dataParse = data.split("-");
                if (dataParse[0].trim().equalsIgnoreCase(dataFile[0]) && dataParse[1].trim().equalsIgnoreCase(dataFile[1])) {
                    isFlag = true;
                    break;
                }
            }
            if (isFlag) {
                response = 1;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            response = 3;
        }
        return response;
    }
    public static void writeLang(String fromLanguage, String toLanguage, String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String data;
        boolean isHave = true;
        while ((data = br.readLine()) != null) {
            String[] dataParse = data.split("-");
            String fromLanguageInFile = dataParse[0].trim();
            String toLanguageInFile = dataParse[1].trim();
            if (fromLanguage.equals(fromLanguageInFile) && toLanguage.equals(toLanguageInFile)) {
                System.out.println("This dictionary is now available\n");
                isHave = false;
                break;
            }
        }
        if (isHave) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
                bw.write(fromLanguage + " - " + toLanguage);
                bw.newLine();
            }
            System.out.println();
            System.out.println("The dictionary added successfully\n");
        }
    }
    public static List<String> showCurrentInformation(String filePath) throws IOException {
        List<String> listDictionary = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while (br.ready()) {
            String data = br.readLine();
            listDictionary.add(data);
        }
        return listDictionary;
    }
}
