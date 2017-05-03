/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Hashtable;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author csc190
 */
public class BubbleSortTest {
    
    public BubbleSortTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    protected int [] dupArr(int [] arr){
        int [] arrNew = new int [arr.length];
        for(int i=0; i<arr.length; i++){
            arrNew[i] = arr[i];
        }
        return arrNew;
    }
    /**
     * Test of sort method, of class BubbleSort.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        int [][][] arrTestCases = new int [][][]{
            new int [][]{ {3,2,1}, {1,2,3}}, 
            new int [][]{ {3,2}, {2,3}}, 
            new int [][]{ {1}, {1}}, 
            new int [][]{ {1,2}, {1,2}}, 
            new int [][]{ {}, {}}, 
            
        };
        for(int i=0; i<arrTestCases.length; i++){
            int [] arrInput= arrTestCases[i][0];
            int [] arrExpected = arrTestCases[i][1];
            BubbleSort.sort(arrInput);
            assertArrayEquals(arrExpected, arrInput);            
        }

    }
    //---------------- the following are for boundary testing ---------------
    
    // build an array, every element increase by "step"
    int [] buildArr(int start, int size, int step){
        int [] arr = new int [size];
        int val = start;
        for(int i=0; i<size; i++, val+=step){
            arr[i] = val;
        }
        return arr;
    }
    
    boolean isArrEq(int [] arr1, int [] arr2){
        if(arr1.length!=arr2.length) return false;
        for(int i=0; i<arr1.length; i++){
            if(arr1[i]!=arr2[i]) return false;
        }
        return true;
    }
    
    @Test
    public void boundary_test(){
        //two classes: (1) no changes at all, and (2) with changes.
        //1. class 1
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        int smallsize = 5;
        int midsize = 20;
        int largesize = 100;
        int [][][] arrCases = new int [][][] {
          //class 1  
          new int [][]{buildArr(min, smallsize, 1), buildArr(min,smallsize,1)},
          new int [][]{buildArr(min+1, smallsize, 1), buildArr(min+1,smallsize,1)},
          new int [][]{buildArr(1000, smallsize, 1), buildArr(1000,smallsize,1)},
          new int [][]{buildArr(max-smallsize, smallsize, 1), buildArr(max-smallsize,smallsize,1)},
          new int [][]{buildArr(max-smallsize-1, smallsize, 1), buildArr(max-smallsize-1,smallsize,1)},
          //class 2
           new int [][]{buildArr(min+smallsize-1, smallsize, -1), buildArr(min,smallsize,1)},
           new int [][] { {2, 1}, {1, 2}},
           new int [][] { {2,1,4,3}, {1,2,3,4}},
            new int [][]{buildArr(min+largesize-1, largesize, -1), buildArr(min,largesize,1)},
            new int [][]{buildArr(max, largesize, -1), buildArr(max-largesize+1,largesize,1)},
        };
        
        for(int i=0; i<arrCases.length; i++){
            int [] inpArr = arrCases[i][0];
            int [] expArr = arrCases[i][1];
            BubbleSort.sort(inpArr);
            if(!isArrEq(inpArr, expArr)){
                fail("Error at case i: " + i);
            }
        }
    }
    
    //---------------- the following are for randomized testing -------------
    
    //utility function, generate array
    int [] duplicateArr(int [] arr){
        return (int []) arr.clone();
    }
    
    int [] randArr(){
        Random rand = new Random();
        int size = rand.nextInt()%10000;
        size = size<0? -size: size;
        int [] arr = new int [size];
        for(int i=0; i<size; i++){
            arr[i] = rand.nextInt();
        }
        return arr;
    }
    
    Hashtable<Integer,Integer> buildCounter(int [] arr){
        Hashtable<Integer,Integer> hash= new Hashtable<Integer,Integer>();
        for(int i=0; i<arr.length; i++){
            if(!hash.containsKey(arr[i])){
                hash.put(arr[i], 1);
            }else{
                int val = hash.get(arr[i]);
                hash.put(arr[i], val+1);
            }
        }
        return hash;
    }
    
    boolean isPermutation(int [] arr1, int [] arr2){
        Hashtable<Integer,Integer> hash1 = buildCounter(arr1);
        Hashtable<Integer,Integer> hash2 = buildCounter(arr2);
        return hash1.equals(hash2);
    }
    
    boolean isAscending(int [] arr){
        for(int i=0; i<arr.length-1; i++){
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;
    }
    
    @Test 
    public void testUtilities(){
        int [] arr1 = new int [] {1,2,1,3};
        int [] arr2 = new int [] {2,1,3,1};
        if(!isPermutation(arr1,arr2)){
            fail("Error isPerm not working!");
        }
       
    }
    
    @Test
    public void randomTest(){
        int times = 10;
        for(int i=0; i<times; i++){
            int [] randarr = randArr();
            int [] arrcopy = duplicateArr(randarr);
            BubbleSort.sort(randarr);
            if(!isPermutation(arrcopy, randarr)){
                fail("arrcopy is not a permutation of randarr!");
            }
            if(!isAscending(randarr)){
                fail("It's not sorted!");
                
            }
        }
    }
}
