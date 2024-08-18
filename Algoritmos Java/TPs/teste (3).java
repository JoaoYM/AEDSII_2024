import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class teste {

public static String[] newer = null;


public static void main(String[] args) {
    Scanner scan = new Scanner (System.in);
    String expressao = scan.nextLine();
    int    line      = 0;

    while(!expressao.trim().equals("FIM")){
          try{
            randomSort(expressao);
          }catch(Exception e){
            System.out.println(line);
          }
            expressao = scan.nextLine();
          line++;
    }
}

public static void randomSort(String expressao){
    Random random = new Random(4);
    String palavra    = expressao;
    char[] partes     = palavra.toCharArray();
    ArrayList<Character> lowerCase = new ArrayList<>();
    lowerCase = getRandoms(partes, 0, lowerCase);
    ArrayList<Character> randoms = new ArrayList<>();

    int indiceAleatorio = random.nextInt(lowerCase.size());
    randoms.add(lowerCase.get(indiceAleatorio));
    indiceAleatorio = random.nextInt(lowerCase.size());
    randoms.add(lowerCase.get(indiceAleatorio));

    genNewWord(partes, randoms);
}

public static ArrayList<Character>  getRandoms(char[] palavra, int index, ArrayList<Character> lowerCase){
    if (index <= palavra.length - 1){
        if (palavra[index]  >= 'a' && palavra[index] <= 'z'){
            lowerCase.add(palavra[index]);
        }
        return getRandoms(palavra, ++index, lowerCase);
    }else{
        return lowerCase;
    }
}


public static void genNewWord(char[] expressao, ArrayList<Character> randoms){
    for (char character : expressao){
        if (character  >= 'a' && character <= 'z' && character == randoms.get(0)){
            character = randoms.get(1);
        }
    }

    System.out.println(expressao);

}



}

