/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bintree;

/**
 *
 * @author csc190
 */
public class BSTNode {
    protected BSTNode left, right;
    protected int val;
    
    public BSTNode(int val){
        this.val = val;
    }
    
    public void insert(int val){
        if(val==this.val) return; //no dup node
        if(val<this.val){
            if(this.left==null){
                this.left = new BSTNode(val);
            }else{
                this.left.insert(val);
            }
        }else{
            if(this.right ==null){
                this.right = new BSTNode(val);
                
            }else{
                this.right.insert(val);
            }
        }
    }
    
    @Override 
    public boolean equals(Object other){
        if(other==null) return false;
        if(!(other instanceof BSTNode)){
            return false;
        }
        BSTNode nOther = (BSTNode) other;
        if(this.val!=nOther.val){
            return false;
        }
        if(this.left==null && nOther.left!=null) return false;
        if(this.left!=null && nOther.left==null) return false;
        if(this.right==null && nOther.right!=null) return false;
        if(this.right!=null && nOther.right==null) return false;
        return (this.left==null || this.left.equals(nOther.left)) && 
                (this.right==null || this.right.equals(nOther.right));
    }
    
    public static BSTNode buildTree(int [] arr){
        if(arr==null || arr.length==0) return null;
        BSTNode root = new BSTNode(arr[0]);
        for(int i=1; i<arr.length; i++){
            root.insert(arr[i]);
        }
        return root;
    }
}
