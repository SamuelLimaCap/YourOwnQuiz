package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\User\\Desktop\\Substantivos ingles\\objetos.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));

            ArrayList<String> stringsEnglish = new ArrayList<>();
            ArrayList<String> stringPortuguese = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] words = line.split(",");
                stringsEnglish.add(words[0]);
                stringPortuguese.add(words[1]);

            }
            Random random = new Random();
            int randomNum = random.nextInt(stringsEnglish.size());
            System.out.println(randomNum);
            System.out.println(stringsEnglish.get(randomNum));
            // TODO scanner -> mostra a palavra em ingles e o usuario terá que digitar a palavra em portugues.

        } catch (Exception e)  { e.printStackTrace(); }


    }
}
