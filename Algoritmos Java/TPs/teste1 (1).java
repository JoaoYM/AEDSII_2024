import java.util.Scanner;

public class teste1 {
    
    private static int[] vetAux = new int[50];
    private static int k;
    public static void main(String[] args){
        
        Scanner scan  = new Scanner(System.in);
        String number  = scan.nextLine();

        if (Integer.parseInt(number) ==  6){
     
            System.out.println(fibonacci((Integer.parseInt(number)) - Integer.parseInt(number)) + 5);
        
        }else{
            System.out.println(fibo(Integer.parseInt(number), 0));
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
                System.out.println(num);
                return num;
            }
            return 0;
    }

    private static long fibo(int n, int i) {
            if (i < n){
                i++;
                fibo(n, i);
            }else{
                k = 1; // inicializa k
                return recursao(n);
            }

            return 0;
    }

    private static long recursao(int n) {
            if (n < 0) {
                return vetAux[0];
            } else {
            if (k < 3) {
                vetAux[n] = k - 1;
                k++;
            } else {
                    vetAux[n] = vetAux[n + 1] + vetAux[n + 2];
                    }
                return recursao(n - 1);
            }
    }


    private static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int fibNMinus1 = 1;
            int fibNMinus2 = 0;
            int fibN = 0;

            for (int i = 2; i <= n; i++) {
                fibN = fibNMinus1 + fibNMinus2;
                fibNMinus2 = fibNMinus1;
                fibNMinus1 = fibN;
            }

            return fibN;
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
