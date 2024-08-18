import java.util.Scanner;

public class teste {
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);
        String entrada = scan.nextLine();

        while (!entrada.equals("#")) {
            if (ehPalindromo(entrada)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            entrada = scan.nextLine();
        }
    }

    private static int countInt(String num, int indice){
        int size     = num.length();
        if (indice <= size - 1){
            countInt(num, ++indice);
        }else{
            System.out.println(indice);
            return indice;
        }
        return 0;
    }

    private static int sumInt(String num, int indice, int total){
        int size     = num.length();
        if (indice <= size - 1){
            String[] numberArray = num.split("");
            total += Integer.parseInt(numberArray[indice]);
            sumInt(num, ++indice, total);
        }else{
            System.out.println(total);
            return total;
        }
        return 0;
    }

    private static int countDiv(int num, int num2, int iteration){
        int result = num - num2;
            if (result >= 0){
                countDiv(result, num2, ++iteration);
            }else{
                if (iteration == 0){
                    System.out.println(Math.round(result) * -1);    
                    return result;
                }
                System.out.println(iteration);
                return iteration;
            }
            return num;
    }

    private static int countRest(int num, int num2, int iteration){
        int result = num - num2;
            if (result > 0){
                countRest(result, num2, ++iteration);
            }else{
                if (iteration == 0 && result == 0 || result == 0){
                    System.out.println(0);    
                    return 0;
                }
                System.out.println(1);
                return 1;
            }
            return 0;
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static boolean ehPalindromo(String str) {
        // Remover espaços em branco e tornar minúsculas para verificar o palíndromo
        String strProcessada = str.replaceAll("\\s", "").toLowerCase();
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
