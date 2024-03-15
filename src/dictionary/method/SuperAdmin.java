package dictionary.method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SuperAdmin {
    public static void register(String username,String password,String filePath) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(filePath));
        String data;
        boolean isHave=true;
        while ((data= br.readLine())!=null){
            String [] dataParse=data.split(" ");
            String usernameInFile=dataParse[0];
            if(username.equals(usernameInFile) ){
                System.out.println("This admin is now available\n");
                isHave=false;
                break;
            }
        }
        if(isHave){
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(filePath,true))) {
                bw.write(username+" "+password);
                bw.newLine();
            }
            System.out.println("Register successfully\n");
        }
    }

    public static void removeTheAdmin(String username,String filePath) throws IOException {
        Map<String,String> stringMap=new HashMap<>();
        BufferedReader br=new BufferedReader(new FileReader(filePath));
        String data;
        while ((data= br.readLine())!=null){
            String [] dataParse=data.split("\\s");
            stringMap.put(dataParse[0],dataParse[1]);
        }
        if(stringMap.containsKey(username)){
            stringMap.remove(username);
            try(BufferedWriter bw=new BufferedWriter(new FileWriter(filePath))) {
                for(Map.Entry<String,String> entry : stringMap.entrySet()){
                    bw.write(entry.getKey()+" "+entry.getValue());
                    bw.newLine();
                }
            }
            System.out.println("The admin removed successfully");
        }
        else{
            System.out.println("The admin not found");
        }
    }
}
