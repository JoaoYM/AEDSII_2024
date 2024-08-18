package Lista;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class algebra {
    public static String[] newer = null;
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        String expressao = scan.nextLine();
        int    line      = 0;

        while(!expressao.trim().equals("0")){

            AlgebraBooleana(expressao);
            expressao = scan.nextLine();
            line++;
        }
    }



    private static String[] instruction(String[] operations, int index, String instructions){
        int    opened             = 0;
        int    closed             = 0;       

        // Converta a string em um array de caracteres
        String word = (instructions.length() > 0) ? instructions : operations[index];
        char[] caracteres = word.toCharArray();
        
        // Imprima os caracteres individualmente
        for (int i = 0; i < caracteres.length; i++) {
           if (caracteres[i] == '('){
               opened++;
           }

           if (caracteres[i] == ')'){
               closed++;
           }
            
        }
        
        if (opened > closed){
            String[] newElement = getValidWord(operations, ++index);
            word = word.concat(",").concat(newElement[0]);
            instruction(operations, Integer.parseInt(newElement[1]), word);
        }else{
            String[] result = { word, Integer.toString(index) };
            newer = result;
        }
        
        String[] result = { word, Integer.toString(index) };

        return result;
    }

    private static String[] getValidWord(String[] operations, int index){
        while(operations[++index].trim().length() == 0 && index < operations.length - 1){
            // ... Searching valid string
        }

        String[] result = { operations[index], Integer.toString(index) };
        return result;
    }
    
    private static boolean AlgebraBooleana(String expressao){
        String  mainOperator = null;
        int     index              = 3;
        Boolean newStringStart    = false;
        
        // Dividir a expressão usando os caracteres '(', ')', e ','
        String[] partes = expressao.split("[ ,]");
        String[] operacoes = expressao.split("[(), ]");
        int tamanho = Integer.parseInt((partes[0]));

        if(tamanho == 3){
            index = 4;
            mainOperator = operacoes[index];
        }else{
            mainOperator = operacoes[tamanho + 1];
        }

        char[] caracteres = partes[index].toCharArray();

        String newString = "";

        for (int i = 2; i < caracteres.length; i++){
            if (newStringStart){
                newString += caracteres[i];
            }

            if (caracteres[i] == '('){
                newStringStart = true;
            }

        }

        partes[index] = newString;

        String[] resultado = {"", Integer.toString(index)};
        ArrayList<String> newFormulation = new ArrayList<>();

        
        newer = resultado;

        Boolean indexChanged = false;

        while(Integer.parseInt(newer[1]) < partes.length - 1){
            if(Integer.parseInt(newer[1]) > index || indexChanged){
                newer[1] = Integer.toString(Integer.parseInt(newer[1]) + 1);
            }

            instruction(partes,Integer.parseInt(newer[1]), "");
            String[] splitedString  = newer[0].split("[,()]");
            if (newer[0].trim().length() > 0){
                newFormulation.add("!");
                Collections.addAll(newFormulation, splitedString);
                indexChanged = true;
            }
        }

        newFormulation.add("!");

        int A = 1, B = 1 , C = 1, i=0;

        // Obter valores binarios correspontes aos caracteres booleanos
        A = Integer.parseInt(partes[++i]);  // 1
        B = Integer.parseInt(partes[++i]);  // 2
        if (tamanho == 3){                  // 3
            C = Integer.parseInt(partes[++i]); 
        }else{
        }

        ArrayList<Boolean> booleans = new ArrayList<>();

        booleans                    = getBooleans(newFormulation, A, B, C, mainOperator);
        
        if (mainOperator.equals("and")){
            for (Boolean bool : booleans) {
                if (bool == false){
                    System.out.println(0);
                    return false;
                }
            }
            System.out.println(1);
            return true;
        }else{
            if(mainOperator.equals("or")){
                for (Boolean bool : booleans) {
                    if (bool == true){
                       System.out.println(1);
                        return true;
                    }
                }
                System.out.println(0);
                return false;
            }else{
                for (Boolean bool : booleans) {
                    int result;
                    result = (bool == false) ? 1 : 0; 
                    System.out.println(result);
                    return   bool;
                }
            }
        } 
        return false;    
    } 

    // Validar instruções obtidas ----------------------------------------------------------------------------------------------------------------------------------------------
    private static ArrayList<Boolean> getBooleans( ArrayList<String> instructions, int A, int B, int C, String main){
        ArrayList<Integer> booleans               = new ArrayList<>();
        ArrayList<Boolean>  results               = new ArrayList<>();
        ArrayList<Boolean>  resultOperations      = new ArrayList<>();
        ArrayList<String>   operations            = new ArrayList<>();
        ArrayList<String>   tempOperations            = new ArrayList<>();
        String mainOperator = null;
        String subOperator  = null;
        Boolean response    = false;
        int     nextOperation = 0;
        Boolean hasExecuted = false;
        int totalOperations = 0;
        

        for (String instruction : instructions){ 
            String line = instruction.trim();

            if (line.length() > 0){
                if (line.charAt(0) >= 'A' && line.charAt(0) <= 'C' ){ // Se binário
                    if (line.charAt(0) == 'A'){
                        booleans.add(A);
                    }else{
                        if (instruction.charAt(0) == 'B'){
                            booleans.add(B);
                        }else{
                            booleans.add(C);
                        }
                    } 
                }else{
                    if(!(line.equals("!")) || booleans.size() > 0){                                
                        // Verificar se existem operações a serem realizadas
                        if (booleans.size() > 0 ){
                            if (operations.size() > 0){    
                                int tamanho = operations.size() - 1;
                                
                                for (int i = tamanho; i >= 0; i--){
                                    booleans = resultOfMix(operations.get(i), booleans);
                                }

                                hasExecuted = true;

                                for (Integer bool : booleans){
                                    results.add((bool == 1) ? true : false);
                                }

                                booleans.clear();
                                operations.clear();
                            }else{
                                for (Integer bool : booleans){
                                    results.add((bool == 1) ? true : false);
                                }

                                booleans.clear();
                            }

                            if(!(line.equals("!"))){
                                operations.add(line);                 // Existindo uma nova instrução essa deve ser adicionada ao array de operações a serem efetuadas
                                if(mainOperator == null){
                                    mainOperator = line;
                                }
                            }else{
                                if (hasExecuted && (totalOperations > 1 || results.size() > 1)){
                                    resultOperations.addAll(getOperations(results, mainOperator));  
                                    results.clear();
                                    booleans.clear();
                                    mainOperator = null;
                                    hasExecuted  = false;
                                    totalOperations = 0;
                                }else{
                                    resultOperations.addAll(results);
                                    results.clear();
                                    totalOperations = 0;
                                }
                            }
                        }else{
                            if(!(line.equals("!"))){
                                totalOperations++;
                                operations.add(line);                 // Existindo uma nova instrução essa deve ser adicionada ao array de operações a serem efetuadas
                                if(mainOperator == null){
                                    mainOperator = line;
                                }
                            }
                        }
                    }
                }
            }else{
                    if (booleans.size() > 0){
                        int tamanho = operations.size() - 1;
                        
                        for (int i = tamanho; i >= 0; i--){
                            booleans = resultOfMix(operations.get(i), booleans);
                        }

                        hasExecuted = true;

                        for (Integer bool : booleans){
                            results.add((bool == 1) ? true : false);
                        }

                        booleans.clear();
                        operations.clear();
                    }
            } 
        }
        

        if (resultOperations.size() == 0){
            resultOperations.addAll(results);
        }else{
            if (booleans.size() > 0){
                int tamanho = operations.size() - 1;
                
                if (operations.size() > 0){
                    for (int i = tamanho; i >= 0; i--){
                        booleans = resultOfMix(operations.get(i), booleans);
                    }
                }

                hasExecuted = true;

                for (Integer bool : booleans){
                    results.add((bool == 1) ? true : false);
                }

                booleans.clear();
                operations.clear();

                if (mainOperator != null){
                    resultOperations.addAll(getOperations(results, mainOperator));  
                }else{
                    resultOperations.addAll(results);
                } 
                results.clear();
                booleans.clear();
                mainOperator = null;
                hasExecuted  = false;
            }
        }

        return resultOperations;
    }

    private static ArrayList<Integer> resultOfMix(String operator, ArrayList<Integer>booleans){
        Integer response =  0;
        ArrayList<Integer>  results               = new ArrayList<>();


        if (operator.equals("and")){
            response = 1;
            for (Integer bool : booleans) {
                if (bool == 0){
                    response = 0;
                }
            }
                results.add(response);
        }else{
            response = 0;
            if(operator.equals("or")){
                for (Integer bool : booleans) {
                    if (bool == 1){
                        response = 1;
                    }
                }
                results.add(response);
            }else{
                for (Integer bool : booleans) {
                    results.add((bool == 0) ? 1 : 0); 
                }
            }
        }  

        return results;
    }

    private static ArrayList<Boolean> getOperations(ArrayList<Boolean> results, String mainOperator){
        ArrayList<Boolean> resultOperations = new ArrayList<>();
        Boolean response                    =  null;      
            // Efetuar operação principal
            if (mainOperator.equals("and")){  
                response     = true;  
                for (Boolean bool : results) {
                    if (bool == false){
                        response = bool;
                    }           
                }
                resultOperations.add(response);
            }else{
                if (mainOperator.equals("or")){
                    response = false;
                    for (Boolean bool : results) {
                        if (bool == true){
                            response = bool;
                        }
                    }
                    resultOperations.add(response);
                }else{
                    resultOperations.add((results.get(0) == true) ? false : true);
                }
            }
        return resultOperations;
    }
}