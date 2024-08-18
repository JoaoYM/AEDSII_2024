import java.util.Scanner;

public class teste {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);
        // String number  = scan.nextLine();
        String entrada = scan.nextLine();
        String word = "";

        while (!entrada.equals("FIM")) {
            CesarEncripty(entrada,0,word); 
            entrada = scan.nextLine();
        }
        
    }

    public static boolean ehPalindromo(String str) {
        String strProcessada = str;
        return verificaPalindromo(strProcessada, 0, strProcessada.length() - 1);
    }

    private static boolean verificaPalindromo(String str, int inicio, int fim) {
        if (inicio >= fim) {
            return true; 
        } else {
            
            // Verifica se os caracteres nas posições inicio e fim são iguais
            return (str.charAt(inicio) == str.charAt(fim)) &&
                   verificaPalindromo(str, inicio + 1, fim - 1);
        }
    }

    private static String CesarEncripty(String currentWord, int indice, String word){
        int size     = currentWord.length();
        if (indice <= size - 1){
            char[] caracteres = currentWord.toCharArray();
            word += (char) ( ((int) caracteres[indice]) + 3 );
            CesarEncripty(currentWord, ++indice, word);
        }else{
            System.out.println(word);
            return word;
        }
        return word;
    }
}


