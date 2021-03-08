import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class hw6tst {
    public static void main(String[] args) {
        int[] tstArr = {1, 1, 2, 4, 1};

        System.out.println( Arrays.toString(getArrAfterLast4(tstArr)));
        System.out.println(checkExists1and4inArr(tstArr));
    }

    public static int[] getArrAfterLast4(int[] inArr){
        for (int i = inArr.length-1; i >= 0; i--) {
            if (inArr[i] == 4){
                return Arrays.copyOfRange(inArr,i+1,inArr.length);
            }
        }
        throw new RuntimeException();
    }

    public static boolean checkExists1and4inArr(int[] inArr){
        boolean check1 = false;
        boolean check4 = false;

        for (int i = 0; i < inArr.length; i++) {
            if(inArr[i] !=1 && inArr[i] !=4){
                return false;
            }
            if (inArr[i] == 1){
                check1 = true;
            } else {
                check4 = true;
            }
        }

        return (check1 && check4);
    }

    @Test
    public void getArrAfterLast4Case1(){
        int[] tstArr = {1, 1, 2, 4, 1};
        int[] expArr = {1};
        Assert.assertArrayEquals(expArr,getArrAfterLast4(tstArr));
    }

    @Test
    public void getArrAfterLast4Case2(){
        int[] tstArr = {1, 1, 2, 4};
        int[] expArr = {};
        Assert.assertArrayEquals(expArr,getArrAfterLast4(tstArr));
    }

    @Test (expected = RuntimeException.class)
    public void getArrAfterLast4Case3(){
        int[] tstArr = {1, 1, 2, 0, 0};
        int[] resultArr = getArrAfterLast4(tstArr);
    }

    @Test
    public void checkExists1and4inArrCase1(){
        Assert.assertTrue(checkExists1and4inArr(new int[]{1, 1, 1, 1, 4}));
    }

    @Test
    public void checkExists1and4inArrCase2(){
        Assert.assertFalse(checkExists1and4inArr(new int[]{1, 1, 0, 1, 4}));
    }

    @Test
    public void checkExists1and4inArrCase3(){
        Assert.assertFalse(checkExists1and4inArr(new int[]{4, 4, 4, 4, 4}));
    }

}
