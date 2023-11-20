import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

interface Algorithm {
    String crypt(String inputWord);

    String decrypt(String inputWord);
}

class ROTXX implements Algorithm {
    private final String alphabet;
    private final int rotation;

    public ROTXX(String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", int rotation = 11) {
        this.alphabet = alphabet;
        this.rotation = rotation;
    }

    @Override
    public String crypt(String inputWord) {
        return "null";
    }

    @Override
    public String decrypt(String inputWord) {
        return "null";
    }
}

class Polibiusz implements Algorithm {
    private final String alphabet;
    private final int size;

    public Polibiusz(String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", int size = 5) {
        this.alphabet = alphabet;
        this.size = size;
    }

    @Override
    public String crypt(String inputWord) {
        return "null";
    }

    @Override
    public String decrypt(String inputWord) {
        return "null";
    }
}

class Cryptographer {
    public void cryptfile(String pathToFileIn, String pathToFileOut, Algorithm algorithm){
      try (BufferedReader br = new BufferedReader(new FileReader(pathToFileIn));
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFileOut))) {

           String line;
           int count = 0;
           while ((line = br.readLine()) != null) {
               if (count != 0)
                   bw.newLine();
               String[] words = line.split("\\s");
               for (int i=0; i<words.length; i++) {
                   bw.write(algorithm.crypt(words[i]));
                   if (i != words.length-1)
                      bw.write(" ");
               }
               count += 1;
           }
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
      }
    }

    public void decryptfile(String pathToFileIn, String pathToFileOut, Algorithm algorithm){
       try (BufferedReader br = new BufferedReader(new FileReader(pathToFileIn));
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFileOut))) {

           String line;
           int count = 0;
           while ((line = br.readLine()) != null) {
               if (count != 0)
                   bw.newLine();
               String[] words = line.split("\\s");
               for (int i=0; i<words.length; i++) {
                   bw.write(algorithm.decrypt(words[i]));
                   if (i != words.length-1)
                      bw.write(" ");
               }
               count += 1;
           }
       } catch (FileNotFoundException e) {
       } catch (IOException e) {
       }
        
    }  
}

public class Main {
    public static void main(String[] args) {
        if(args.length == 4){
            Algorithm algorithm;
            if(args[3].equals("rot")){
                algorithm = new ROTXX("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 11);
            }
            else if(args[3].equals("pol")){
                algorithm = new Polibiusz("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 5);
            }
            else
                return;
            if(args[2].equals("crypt")){
                Cryptographer cr = new Cryptographer();
                cr.cryptfile(args[0], args[1], algorithm);
            }
            else if(args[2].equals("decrypt")){
                Cryptographer cr = new Cryptographer();
                cr.decryptfile(args[0], args[1], algorithm);
            }
        }
    }
}
