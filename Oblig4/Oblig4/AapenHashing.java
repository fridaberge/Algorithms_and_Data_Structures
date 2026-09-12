import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.print.attribute.standard.Sides;

//jeg bruker de innebygde algoritene til arraylist (contains, add, remove) der contains og remove bruker O(n) tid.
//men her vil n være antall elementer i hver av arraylistene (B) i arrayet h.liste, derfor vil n i værste tilfelle være lik N,
//altså antall elementer i arrayet h.liste, men mest sannsynig vil hver av B-arraylistene være ganske korte
//da det rehashes ved kun halvfullt array, og dermed vil kompleksiteten på contains og remove nærme seg O(1),
//kunne også gjort letingen ved remove og contains raskere ved å bruke biærsøk på b (arraylisten)

public class AapenHashing { //seperate chaining

    int N;
    B[] liste;
    int size = 0;
    int antRehash = 0;


    AapenHashing(int N){
        this.N = N;
        liste = new B[N];
    }

    public static void main(String[] args) {
        AapenHashing hash = new AapenHashing(10);
        lesInput(hash);
        System.out.println("\nAntall rehash:\t"+hash.antRehash);
        System.out.println("plasser brukt: "+hash.size+"\tantall ledige plasser: " +(hash.liste.length-hash.size));
    }

    public static void lesInput(AapenHashing h){
        try{
            // FileWriter myWriter = new FileWriter("filename.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++){
                String[] kmd = br.readLine().split(" ");

                if(kmd[0].equals("insert")){
                    insert(h, kmd[1], true);

                }
                else if(kmd[0].equals("remove")){
                    remove(h, kmd[1]);
                }
                else if(kmd[0].equals("size")){
                    // myWriter.write(h.size);
                    System.out.println(h.size);
                }
                else if(kmd[0].equals("contains")){
                    // myWriter.write(""+contains(h, kmd[1]));
                    System.out.println(contains(h, kmd[1]));
                }
                // myWriter.close();
            }
    }
        catch(IOException e){
            System.err.println("Feil input");
        }
        
    }
    

    public static void insert(AapenHashing h, String tall, boolean tell){
        int i = hashString(tall, h.N);
        B b = h.liste[i];
        if(b != null && b.liste.contains(tall)){
            return;
        }
        if(h.size > h.N/2 && tell == true){ //rehasher når arrayet er bikket halvfullt
            insert(reHash(h), tall, true);
            return;
        }
        if(b == null){
            h.liste[i] = new B(tall);
        }
        else{
            b.liste.add(tall);
        }
        if(tell == true){
            h.size ++;
        }
    }

    public static AapenHashing reHash(AapenHashing h){
        h.antRehash ++;
        int gammelN = h.N;
        B[] gammelListe = h.liste;

        h.N = gammelN+(gammelN/2);
        h.liste = new B[gammelN+(gammelN/2)]; //gjør arrayet 50% større

        for(B b: gammelListe){
            if(b != null){
                for(String tall: b.liste){
                    insert(h, tall, false);
                }
            }
        }
        return h;
    }

    public static boolean contains(AapenHashing h, String tall){
        int i = hashString(tall, h.N);
        B b = h.liste[i];
        if(b == null){
            return false;
        }
        return b.liste.contains(tall);
    }

    public static void remove(AapenHashing h, String tall){
        int i = hashString(tall, h.N);
        B b = h.liste[i];
        if(b == null || !b.liste.contains(tall)){
            return;
        }
        if(b.liste.size() == 1){
            b = null;
        }
        else{
            b.liste.remove(tall);
        }
        h.size --;
    }

    //hasher: gjør en nøkkel om til et tall
   public static int hashString(String string, int N){
    int h = 0;
    char[] s = string.toCharArray();
    for(Character c : s){
        int i = Character.getNumericValue(c);
        h = 31*h+i;
    }
    return h%N;
   }
}


class B{
    ArrayList<String> liste = new ArrayList<>();
    B(String tall){
        liste.add(tall);
    }
}
