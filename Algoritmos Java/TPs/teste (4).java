import java.util.Scanner;

public class teste {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);
        String entrada = scan.nextLine();

        while (!entrada.equals("FIM")) {
            if (ehPalindromo(entrada)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
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
}
