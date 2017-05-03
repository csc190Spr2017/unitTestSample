/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bintree;

import java.util.HashSet;
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
public class BSTNodeTest {

    public BSTNodeTest() {
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

    private BSTNode getOrCreateNode(Hashtable<Integer, BSTNode> valToNode, int val) {
        if (val == Integer.MIN_VALUE) {
            return null; //min is NULL
        }
        if (!valToNode.containsKey(val)) {
            valToNode.put(val, new BSTNode(val));
        }
        return valToNode.get(val);
    }

    protected BSTNode buildFromTriplets(int[][] arrTriplets) {
        Hashtable<Integer, BSTNode> valToNode = new Hashtable<Integer, BSTNode>();
        BSTNode ret = null;
        for (int i = 0; i < arrTriplets.length; i++) {
            int[] triplet = arrTriplets[i];
            BSTNode root = getOrCreateNode(valToNode, triplet[0]);
            BSTNode left = getOrCreateNode(valToNode, triplet[1]);
            BSTNode right = getOrCreateNode(valToNode, triplet[2]);
            root.left = left;
            root.right = right;
            if (i == 0) {
                ret = root;
            }
        }
        return ret;
    }

    /**
     * Test of buildTree method, of class BSTNode.
     */
    @Test
    public void equivTest() {
        int min = Integer.MIN_VALUE;

        int[][] arrInputs = {
            {1, 2, 3},
            {2, 1, 3},};

        int[][][] arrOutputs = {
            {{1, min, 2}, {2, min, 3}, {3, min, min}},
            {{2, 1, 3}}
        };

        for (int i = 0; i < arrInputs.length; i++) {
            BSTNode tree1 = BSTNode.buildTree(arrInputs[i]);
            BSTNode tree2 = buildFromTriplets(arrOutputs[i]);
            if (!tree1.equals(tree2)) {
                fail("Failed equivTest at i: " + i);
            }
        }

    }

    int[] buildRandArray() {
        Random rand = new Random();
        int size = rand.nextInt();
        size = size < 0 ? 0 - size : size;
        size = size % 100;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    HashSet<Integer> extractInt(int[] arr) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        return set;
    }

    HashSet<Integer> extractInt(BSTNode node) {
        HashSet<Integer> set = new HashSet<Integer>();
        if (node != null) {
            set.add(node.val);
            HashSet<Integer> set1 = extractInt(node.left);
            HashSet<Integer> set2 = extractInt(node.right);
            set.addAll(set1);
            set.addAll(set2);
        }

        return set;
    }

    boolean isBST(BSTNode node) {
        if (node == null) {
            return true;
        }
        boolean bLeftOK = false, bRightOK = false;
        if (node.left == null) {
            bLeftOK = true;
        } else {
            bLeftOK = isBST(node.left) && node.left.val < node.val;
        }
        bRightOK = node.right == null ? true : (isBST(node.right) && node.val < node.right.val);
        return bLeftOK && bRightOK;

    }

    @Test
    public void randTest() {
        for (int x = 0; x < 10; x++) {
            int[] arr = buildRandArray();
            BSTNode root = BSTNode.buildTree(arr);
            if (!isBST(root)) {
                fail("it's not a binary tree");
            }
            HashSet set1 = extractInt(arr);
            HashSet set2 = extractInt(root);
            if (!set1.equals(set2)) {
                fail("contents not right!");
            }
        }
    }

}
