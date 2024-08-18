import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class booleana {

public static String[] newer = null;


public static void main(String[] args) {
    Scanner scan = new Scanner (System.in);
    String expressao = scan.nextLine();
    int    line      = 0;

    while(!expressao.trim().equals("FIM")){
         if(isVogalFilled(expressao.toCharArray(), 0)){
            System.out.printf("SIM NAO ");
         }else{
            System.out.printf("NAO ");
            if(isConsoanteFilled(expressao.toCharArray(), 0)){
                System.out.printf("SIM ");
            }else{
                System.out.printf("NAO ");
            }
         }

         if(isIntegerFilled(expressao.toCharArray(), 0)){
            System.out.printf("SIM SIM\n");
         }else{
            System.out.printf("NAO ");
            if(isRealNumber(expressao.toCharArray(), 0, 0)){
                System.out.printf("SIM\n");
            }else{
                System.out.printf("NAO\n");
            }
         }
            expressao = scan.nextLine();
            //System.out.println("\n");
          line++;
    }
}




// Crie um método recursivo que receba uma string e retorne true se a mesma for composta somente por vogais. 

public static Boolean  isVogalFilled(char[] palavra, int index){
    if (index <= palavra.length - 1){
        char ch = palavra[index];
        if (!(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')){
            return false;
        }
        return isVogalFilled(palavra, ++index);
    }else{
        return true;
    }
}


//Crie outro método recursivo que receba uma string e retorne true se a mesma for composta somente por consoantes.

public static Boolean isConsoanteFilled(char[] palavra, int index){
    char ch = Character.toLowerCase(palavra[index]);
    if (index <= palavra.length - 1){
        if (Character.isLetter(ch)  && !(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')){
            return isConsoanteFilled(palavra, ++index);
        }else{
            return false;
        }
    }else{
        return true;
    }

}



//Crie um terceiro método recursivo que receba uma string e retorne true se a mesma corresponder a um número inteiro.

public static Boolean isIntegerFilled(char[] palavra, int index){
    if (index <= palavra.length - 1){
        // Convertendo caractere numérico para número
        int numero = palavra[index] - '0';

        if (numero  >= 0 && numero <= 9){
            return isIntegerFilled(palavra, ++index);
        }else{
            return false;
        }
    }else{
        return true;
    }
}

// Crie um quarto método recursivo que receba uma string e retorne true se a mesma corresponder a um número real. 

public static Boolean  isRealNumber(char[] palavra, int index, int count){
    if (count <= 1){
        if (index <= palavra.length - 1){
        // Convertendo caractere numérico para número
        int numero = palavra[index] - '0';
            if (numero  >= 0 && numero <= 9){
                    return isRealNumber(palavra, ++index, count);
            }else{
                if (palavra[index]  == ',' || palavra[index] == '.'){
                    return isRealNumber(palavra, ++index, ++count);
                }else{
                    return false;
                }
            }
        }
                    return true;
    }else{
        return false;
    }
}

}

//Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada Xi é um booleano indicando se a entrada é: composta somente por vogais (X1); composta somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se Xi for verdadeiro, seu valor deverá ser SIM; caso contrário, NAO.