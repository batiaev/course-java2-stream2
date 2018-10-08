package com.batiaev.java2.HW6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * Created by vedeshkin on 08.10.2018.
 */

@RunWith(Parameterized.class)
public class ArrayUtilTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[] {1, 2, 4, 4, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int [10],new int[]{}},
                {new int[] {1, 2, 4, 4, 2,5,6,7,8}, new int[]{2,5,6,7,8}},
                {new int[] {1, 2, 4}, new int[]{}},
        });
    }

    private int [] data;
    private int [] result;
    private ArrayUtil au;
    public ArrayUtilTest(int[] data, int[] result) {
        this.data = data;
        this.result = result;
    }
    @Before
    public void init() {
        au  = new ArrayUtil();
    }

    @Test(expected = RuntimeException.class)
    public void splitFromFour() throws Exception {
        Assert.assertArrayEquals(String.format("Unexpeted result for array %s ",Arrays.toString(data)),result,au.splitFromFour(data));

    }

}