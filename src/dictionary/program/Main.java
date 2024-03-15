package dictionary.program;

import dictionary.method.Admin;
import dictionary.method.Method;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static dictionary.method.Admin.changeWordInDic;
import static dictionary.method.Admin.createNewDictionary;
import static dictionary.method.Admin.removeWordFromDic;
import static dictionary.method.Login.loginRequest;
import static dictionary.method.Method.check;
import static dictionary.method.Method.printArray;
import static dictionary.method.Method.showCurrentInformation;
import static dictionary.method.SuperAdmin.register;
import static dictionary.method.SuperAdmin.removeTheAdmin;
import static dictionary.method.User.translate;

public class Main {
    /*
     * Bir neçə dildən ibarət lüğət yaratmaq tələb olunur. Lüğət iki hissədən ibarət olacaq.(admin və user)
     * Adminin hüquqları:
     * Dinamik olaraq:::::
     *   Lugete istifadeci adi ve shifre ile qoshulmaq,
     *   Yeni lüğət yaratmaq,
     *   Lüğətə yeni söz əlavə etmək,
     *   Lüğətdən sözü silmək,
     *   Lüğətdə sözü dəyişmək,
     *   Luqetleri gormek,
     *   Yeni admin qeydiyyatdan kecirmek
     * İstifadəçinin hüquqları:
     *   Sistemdə olan cari lüğətləri görmək,
     *   Sİstemdə olan lüğətlərə görə bir istiqamətdə və əks istiqamətdə söz axtarmaq. Yəni ki,
     * (Məsələn: rus ingilis , əksi olaraq ingilis rus sözlərinə görə söz axtarmaq).
     */
    public static int chanceForAdmin = 3;
    public static final String ADMIN_LOGIN_PATH = "admin_login.txt";
    public static final String SUPER_ADMIN_LOGIN_PATH = "super_admin.txt";
    public static final String ALL_DICTIONARY_NAMES_PATH = "all_dictionaries.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "";
        String username, password, fromLanguage, toLanguage, firstWord, word, secondWord, filePath;
        int response;
        List<String> listDictionary, listWords;
        try {
            printArray(new String[]{"Select the role", "1. Super Admin", "2. Admin", "3. User",});
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    username = sc.next();
                    System.out.print("Enter password: ");
                    password = sc.next();
                    if (loginRequest(username, password, SUPER_ADMIN_LOGIN_PATH)) {
                        System.out.println("\nLogin successfully\n");
                        while (true) {
                            printArray(new String[]{"1. Create new admin", "2. Delete the admin", "3. Change the role", "4. Log out"});
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    System.out.print("Enter username: ");
                                    username = sc.next();
                                    System.out.print("Enter password: ");
                                    password = sc.next();
                                    register(username, password, ADMIN_LOGIN_PATH);
                                }
                                case 2 -> {
                                    System.out.print("Enter username: ");
                                    username = sc.next();
                                    removeTheAdmin(username, ADMIN_LOGIN_PATH);
                                }
                                case 3 -> main(args);
                                case 4 -> System.exit(0);
                                default -> System.out.println("Invalid input");
                            }
                        }
                    } else {
                        System.out.println("Wrong username or password please try again\n");
                        main(args);
                    }
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    username = sc.next();
                    System.out.print("Enter password: ");
                    password = sc.next();
                    if (loginRequest(username, password, ADMIN_LOGIN_PATH)) {
                        System.out.println("\nLogin successfully\n");
                        while (true) {
                            System.out.print("1 To continue 2 To log out 3 To main args: ");
                            switch (sc.nextInt()) {
                                case 1 -> {
                                    System.out.println();
                                    printArray(new String[]{"Which one would you like to do", "1. Create new dictionary",
                                            "2. Add a new word to the dictionary", "3. Remove the word from dictionary",
                                            "4. Change the word in dictionary"});
                                    switch (sc.nextInt()) {
                                        case 1 -> {
                                            System.out.println("Current dictionaries in the file");
                                            listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                            listDictionary.forEach(System.out::println);

                                            System.out.println("Creating a new dictionary");
                                            System.out.print("Enter first language: ");
                                            fromLanguage = sc.next();
                                            System.out.print("Enter second language: ");
                                            toLanguage = sc.next();
                                            filePath = fromLanguage + "_" + toLanguage + ".txt";
                                            createNewDictionary(filePath);
                                            Method.writeLang(fromLanguage, toLanguage, ALL_DICTIONARY_NAMES_PATH);
                                        }
                                        case 2 -> {
                                            System.out.println("Current dictionaries in the file");
                                            listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                            listDictionary.forEach(System.out::println);

                                            System.out.println("Which dictionary do you want to add a word to?");
                                            System.out.print("Enter first language: ");
                                            fromLanguage = sc.next();
                                            System.out.print("Enter second language: ");
                                            toLanguage = sc.next();
                                            filePath = fromLanguage + "_" + toLanguage + ".txt";
                                            response = check(filePath, ALL_DICTIONARY_NAMES_PATH);
                                            if (response == 1) {
                                                System.out.println("\nCurrent words in dictionary");
                                                listWords = showCurrentInformation(filePath);
                                                listWords.forEach(System.out::println);
                                                System.out.println();
                                                System.out.print("Enter " + fromLanguage + " word: ");
                                                firstWord = sc.next();
                                                System.out.print("Enter " + toLanguage + " word: ");
                                                secondWord = sc.next();
                                                Admin.writeWordToDictionary(firstWord, secondWord, filePath);
                                                System.out.println("The word has been successfully added to the file\n");
                                            } else if (response == 2) {
                                                System.out.println("This dictionary name doesn't exist\n");
                                            } else {
                                                System.out.println("Error");
                                            }
                                        }
                                        case 3 -> {
                                            System.out.println("Which dictionary you want to remove the word from");
                                            listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                            listDictionary.forEach(System.out::println);

                                            System.out.print("Enter first language: ");
                                            fromLanguage = sc.next();
                                            System.out.print("Enter second language: ");
                                            toLanguage = sc.next();
                                            filePath = fromLanguage + "_" + toLanguage + ".txt";
                                            response = check(filePath, ALL_DICTIONARY_NAMES_PATH);
                                            if (response == 1) {
                                                System.out.println("\nCurrent words in dictionary");
                                                listWords = showCurrentInformation(filePath);
                                                listWords.forEach(System.out::println);
                                                System.out.println();
                                                System.out.print("Enter " + fromLanguage + " word: ");
                                                word = sc.next();
                                                removeWordFromDic(word, filePath);
                                                listWords = showCurrentInformation(filePath);
                                                listWords.forEach(System.out::println);
                                                System.out.println();
                                            } else if (response == 2) {
                                                System.out.println("This dictionary name doesn't exist\n");
                                            } else {
                                                System.out.println("Error");
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("Which dictionary you want to change the word from");
                                            listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                            listDictionary.forEach(System.out::println);

                                            System.out.print("Enter first language: ");
                                            fromLanguage = sc.next();
                                            System.out.print("Enter second language: ");
                                            toLanguage = sc.next();
                                            filePath = fromLanguage + "_" + toLanguage + ".txt";
                                            response = check(filePath, ALL_DICTIONARY_NAMES_PATH);
                                            if (response == 1) {
                                                System.out.println("\nCurrent words in dictionary");
                                                listWords = showCurrentInformation(filePath);
                                                listWords.forEach(System.out::println);
                                                System.out.println();
                                                System.out.print("Enter " + fromLanguage + " word: ");
                                                firstWord = sc.next();
                                                System.out.print("Enter the word to change: ");
                                                secondWord = sc.next();
                                                changeWordInDic(firstWord, secondWord, filePath);
                                                listWords = showCurrentInformation(filePath);
                                                listWords.forEach(System.out::println);
                                                System.out.println();
                                            } else if (response == 2) {
                                                System.out.println("This dictionary name doesn't exist\n");
                                            } else {
                                                System.out.println("Error");
                                            }
                                        }
                                        default -> System.out.println("Invalid number\n");
                                    }
                                }
                                case 2 -> System.exit(0);
                                case 3 -> main(args);
                                default -> System.out.println("Invalid number\n");
                            }
                        }
                    } else {
                        System.out.println("\nWrong username or password");
                        chanceForAdmin--;
                        if (chanceForAdmin > 0) {
                            System.out.println("You have " + chanceForAdmin + " chances for enter\n");
                            main(args);
                        } else {
                            System.out.println("You have been blocked due to entered information incorrectly");
                            System.exit(0);
                        }
                    }
                }
                case 3 -> {
                    while (true) {
                        System.out.println();
                        printArray(new String[]{"Which one do you want to do", "1. View the current dictionaries in the system",
                                "2. Search the word in the current dictionary", "3. To log out 4. To main args"});
                        switch (sc.nextInt()) {
                            case 1 -> {
                                System.out.println("Current dictionaries in the file");
                                listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                listDictionary.forEach(System.out::println);
                            }
                            case 2 -> {
                                System.out.println("Which dictionary do you want to use");
                                listDictionary = showCurrentInformation(ALL_DICTIONARY_NAMES_PATH);
                                listDictionary.forEach(System.out::println);

                                System.out.print("Enter first language: ");
                                fromLanguage = sc.next();
                                System.out.print("Enter second language: ");
                                toLanguage = sc.next();
                                filePath = fromLanguage + "_" + toLanguage + ".txt";
                                response = check(filePath, ALL_DICTIONARY_NAMES_PATH);
                                if (response == 1) {
                                    System.out.println("Which language you want to search by\n1. " + fromLanguage + "\n2. " + toLanguage);
                                    switch (sc.nextInt()) {
                                        case 1 -> {
                                            System.out.print("Enter the " + fromLanguage + " word: ");
                                            secondWord = sc.next();
                                            result = "Translation: " + translate(secondWord, filePath, 1);
                                        }
                                        case 2 -> {
                                            System.out.print("Enter the " + toLanguage + " word: ");
                                            firstWord = sc.next();
                                            result = "Translation: " + translate(firstWord, filePath, 2);
                                        }
                                        default -> result = "Invalid dictionary type";
                                    }
                                    System.out.println(result);
                                } else if (response == 2) {
                                    System.out.println("This dictionary name doesn't exist");
                                } else {
                                    System.out.println("Error");
                                }
                            }
                            case 3 -> System.exit(0);
                            case 4 -> main(args);
                            default -> System.out.println("Invalid choose\n");
                        }
                    }
                }
                default -> {
                    System.out.println("Invalid role\n");
                    main(args);
                }
            }
        } catch (InputMismatchException ex) {
            System.err.println("Enter correct input type\n");
            main(args);
        } catch (Exception ex) {
            ex.printStackTrace();
            main(args);
        }
    }
}
