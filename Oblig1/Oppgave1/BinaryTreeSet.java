import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BinaryTreeSet{

    Node root = null;
    static int size = 0;

    public static void main(String[] args) {

        try {
            makeTree();
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Skriv inn input-fil");
        }
    }

    public static void makeTree(){
        try{
            BinaryTreeSet tre = new BinaryTreeSet();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++){
                String[] kmd = br.readLine().split(" ");

                if(kmd[0].equals("insert")){
                    tre.root = insert(tre.root, Integer.parseInt(kmd[1]));
                }
                else if(kmd[0].equals("remove")){
                    tre.root = remove(tre.root, Integer.parseInt(kmd[1]));
                }
                else if(kmd[0].equals("size")){
                    System.out.println(size);
                }
                else if(kmd[0].equals("contains")){
                    System.out.println(search(tre.root, Integer.parseInt(kmd[1])));
                }
            }
        }
        catch(IOException e){
            System.err.println("Feil input");
        }
    }

    public static Node insert(Node v, int x){
        if(v == null){
            size ++;
            v = new Node(x);
        }
        if(x > v.value){
            v.right = insert(v.right, x);
        }
        else if(x < v.value){
            v.left = insert(v.left, x);
        }
        return v;
    }

    public static Node remove(Node v, int x){

        if(v == null){
            return null;
        }
        if(x < v.value){
            v.left = remove(v.left, x);
            return  v;
        }
        if(x > v.value){
            v.right = remove(v.right, x);
            return v;
        }
        if(v.left == null){
            size -=1;
            return v.right;
        }
        if(v.right == null){
            size -= 1;
            return v.left;
        }
        Node u = findMin(v.right);
        v.value = u.value;
        v.right = remove(v.right, u.value);
        
        return v;
    }

    public static Node findMin(Node v){
        if(v.left == null){
            return v;
        }
        return findMin(v.left);
    }

    public static Boolean search(Node v, int x){
        if(v == null){
            return false;
        }
        if(x == v.value){
            return true;
        }
        if(x > v.value){
            return search(v.right, x);
        }
        if(x < v.value){
            return search(v.left, x);
        }
        else{
            return true;
        }
    }
}

class Node{
    Node right;
    Node left;
    int value;

    Node(int value){
        this.value = value;
    }
}