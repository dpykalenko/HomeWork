import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class ArrayOperationsTest {
    ArrayOperations arrayOperations = new ArrayOperations();

    @Test
    public void testMultiplyArrays_WhenArray1IsNull_ThenNull() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        assertNull(arrayOperations.multiplyArrays(arr1, null));
    }

    @Test(dependsOnMethods = {"testMultiplyArrays_WhenArray1IsNull_ThenNull"})
    public void testMultiplyArrays_WhenDifferentLength_ThenFalse() {
        int[] arr1 = new int[]{2, 3, 4};
        int[] arr2 = new int[]{2, 3, 4, 5};
        int[] result = arrayOperations.multiplyArrays(arr1, arr2);
        assertFalse(arr1.length == result.length);
    }

    @Test(dependsOnMethods ={"testMultiplyArrays_WhenDifferentLength_ThenFalse"})
    public void testMultiplyArrays_WhenDifferentLength_ThenTrue() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{2, 3, 4};
        int[] result = arrayOperations.multiplyArrays(arr1, arr2);
        assertTrue(arr1.length == result.length);
    }

    @Test(dependsOnMethods ={"testMultiplyArrays_WhenDifferentLength_ThenTrue"} )
    public void shuffleElement() {
        String[] input = new String[]{"one", "two", "three"};
        String[] output = arrayOperations.shuffleElement(input);
        assertEqualsNoOrder(input, output);
    }
    @Test
    public void testMultiplyArrays_ArrayEqual_ThenTrue() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{2, 3, 4, 5};
        int[] result = arrayOperations.multiplyArrays(arr1,arr2);
        assertFalse(arr1 == result);
    }
    @Parameters({ "param" })
    @Test
    public void testParam() {
        System.out.println("Parameter is checked");
    }
}