import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayOperations {
    public int[] multiplyArrays(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null) {
            return null;
        }

        int length;
        if (arr1.length > arr2.length){
            length = arr1.length;
        }
        else {
            length = arr2.length;
        }

        int[] result = new int[length];
        for (int i=0; i < length; i++)
        {
            if (arr1.length == length)
            {
                if (i < arr2.length){
                    result[i] = arr1[i] * arr2[i];
                }
                else {
                    result[i] = arr1[i];
                }
            }
            else {
                if (i < arr1.length){
                    result[i] = arr1[i] * arr2[i];
                }
                else {
                    result[i] = arr2[i];
                }
            }
        }

        return result;
    }

    public String[] shuffleElement(String[] arr) {
        String[] result = arr.clone();
        Random rnd = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = result[index];
//            arr[index] = arr[i];
            result[index] = result[i];
//            arr[i] = a;
            result[i] = a;
        }

        return result;
    }
}
