package AEDSII_2024.Algoritmos_recursivos;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] numbers  = {7,5,1,8,3,9,0};
        bubbleSort(numbers, 0, 0);
        System.out.println(Arrays.toString(numbers));
    }

    public static int[] bubbleSort(int[] numbers, int i, int j) {
        if (i < numbers.length - 1){
            if (j < numbers.length - i - 1){        
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }else{
                return bubbleSort(numbers, ++i, 0);
            }
                return bubbleSort(numbers, i, ++j);
        }
        return numbers;
    }
}
