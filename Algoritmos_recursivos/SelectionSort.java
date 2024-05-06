package AEDSII_2024.Algoritmos_recursivos;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] numbers  = {7,5,1,8,3,9,0};
        selectionSort(numbers, 0, 0);
        System.out.println(Arrays.toString(numbers));
    }

    public static int[] selectionSort(int[] numbers, int i, int j){
        if (i <= numbers.length - 1){
            int min_index = i;
           
            if(j == 0){
                j = i + 1;
            }

            if(j < numbers.length ){
                if(numbers[j] <  numbers[min_index]){
                    min_index = j;
                }
            }else{
                return selectionSort(numbers, ++i, 0);    
            }  

            if(numbers[i] > numbers[min_index]){
                int temp = numbers[i];
                numbers[i] = numbers[min_index];
                numbers[min_index] = temp;
            }

            return selectionSort(numbers, i, ++j);
        }
    
        return numbers;
    }
}
