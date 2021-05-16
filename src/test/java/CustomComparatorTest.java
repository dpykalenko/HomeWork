import org.testng.annotations.*;
import utils.PropertiesReaderClassLoader;

import static org.testng.Assert.*;

public class CustomComparatorTest {
    CustomComparator customComparator = new CustomComparator();

    @BeforeClass
    public void checkBeforeClass() {
        System.out.println("Before class");
    }

    @BeforeMethod
    public void checkBeforeMethod() {
        System.out.println("Before method");
    }

    @AfterClass
    public void checkAfterClass() {
        System.out.println("After class");
    }

    @AfterMethod (alwaysRun = true)
    public void checkAfterMethod() {
        System.out.println("After method");
    }

    @Test(timeOut = 3000)
    public void testIsListOrderEqual_WhenOrderEqual_ThenTrue() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{2, 3, 4, 5};
        boolean expectedValue = true;
        assertEquals(customComparator.IsArraysOrderEqual(arr1, arr2), expectedValue);
    }
    @Test(expectedExceptions = {NullPointerException.class})
    public void checkException() {
        int[] arr1 = new int[]{2, 3, 5, 6};
        int[] arr2 = new int[]{3, 5, 6};
        boolean expectedValue = true;
        assertEquals(customComparator.IsArraysOrderEqual(null, arr2), expectedValue);
    }

    @Test(priority = 2)
    public void testIsListOrderEqual_WhenArray1IsNull_ThenNull() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{1, 3, 4, 5};
        assertFalse(customComparator.IsArraysOrderEqual(arr1, arr2));
    }

    @Test(priority = 2)
    public void testIsListOrderEqual_WhenOrderTrue_ThenTrue() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{2, 3, 4, 5};
        assertTrue(customComparator.IsArraysOrderEqual(arr1, arr2));
    }

    @Test(priority = 1)
    public void testIsListOrderEqual_WhenLengthDifferent_ThenFalse() {
        int[] arr1 = new int[]{2, 3, 4, 5};
        int[] arr2 = new int[]{2, 3, 4};
        assertFalse(customComparator.IsArraysOrderEqual(arr1, arr2));
    }

    @Test(dataProvider="getData")
    public void testDataProvider(int[] arr1, int[] arr2) {
        assertFalse(customComparator.IsArraysOrderEqual(arr1, arr2));
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {new int[]{1, 2, 3}, new int[]{2, 3, 4}},
                {new int[]{6, 9, 1}, new int[]{1, 6, 9}}};
    }
    @Test
    public void test2() {
        String baseUrl = PropertiesReaderClassLoader.getInstance().getValueFromProperty( "baseUrl");
        String defaultTimeoutValue = PropertiesReaderClassLoader.getInstance().getValueFromProperty( "defaultTimeout");
        System.out.println("baseUrl = " + baseUrl);
        System.out.println("defaultTimeoutValue = " + defaultTimeoutValue);
    }
}
