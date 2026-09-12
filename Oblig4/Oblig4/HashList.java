import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//lukket hashing
public class HashList {
    int N;
    String[] liste;
    Boolean[] lagtInn; //for at det skal gå ann å legge til 0
    int size = 0;
    int antRehash = 0;


    HashList(int N){
        this.N = N;
    }
    
    public static void main(String[] args) {
        HashList hash = new HashList(10);
        hash.liste = new String[hash.N];
        hash.lagtInn = new Boolean[hash.N];
        insert(hash, "3");
        for(int i = 0; i <30; i+=2){
            insert(hash,""+i);
        }
        // System.out.println(contains(hash, "3"));
        // System.out.println("str: "+hash.size);
        // System.out.println("ant rehash: "+hash.antRehash);
        // System.out.println("\n\n");
        // for(String tall: hash.liste){
        //     System.out.println(tall);
        // }
        // System.out.println("\n\n");
        // for(Boolean b: hash.lagtInn){
        //     System.out.println(b);
        // }
        //lesInput(hash);
    }


    // public static void lesInput(HashList h){
    //     try{
    //         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //         int n = Integer.parseInt(br.readLine());
    //         for(int i = 0; i < n; i++){
    //             String[] kmd = br.readLine().split(" ");

    //             if(kmd[0].equals("insert")){
    //                 insert(h, kmd[1]);
    //             }
    //             else if(kmd[0].equals("remove")){
    //                 remove(h, kmd[1]);
    //             }
    //             else if(kmd[0].equals("size")){
    //                 System.out.println(h.size);
    //             }
    //             else if(kmd[0].equals("contains")){
    //                 System.out.println(contains(h, kmd[1]));
    //             }
    //         }
    // }
    //     catch(IOException e){
    //         System.err.println("Feil input");
    //     }
        
    // }

    public static void insert(HashList h, String tall){
        int i = hashString(tall, h.N);
        if(h.lagtInn[i] == null || h.lagtInn[i] == false){
            h.liste[i] = tall;
            h.lagtInn[i] = true;
        }
        else if (!contains(h, tall)){
            int a = 0;
            System.out.println();
            while(a < h.N && h.lagtInn[i] != null){
                if(i >= h.N-1){
                    i = 0;
                }
                else{
                    i++;
                }
                a++;
            }
            if(a >= h.N){
                //System.out.println("feil her");
                reHash(h, tall);
                //System.out.println("gjennom");
                return;
            }
            else{
                System.out.println("feil 2");
                h.liste[i] = tall;
                h.lagtInn[i] = true;
            }
        }
        h.size ++;
        for(String t: h.liste){
            System.out.println(t);
        }
        System.out.println("\n");
    }

    public static HashList reHash(HashList h, String tall){
        System.out.println("rehash");
        h.antRehash ++;
        int gammelN = h.N;
        String[] gammelListe = h.liste;

        h.N = gammelN*2;
        h.liste = new String[gammelN*2]; //gjør arrayet 50% større
        h.lagtInn = new Boolean[gammelN*2];
        h.size = 0;
        // System.out.println(""l: gammelListe.length);
        for(String t: h.liste){
            System.out.println(t);
        }
        for(String gammelVerdi: gammelListe){
            //System.err.println(gammelVerdi);
            insert(h, gammelVerdi);

            // if(h.liste[i] != h.lagtInn[i] != null || h.lagtInn[i] != false){
            // }
        }
        
        insert(h, tall);
        return h;
    }

    public static Boolean contains(HashList h, String tall){
        int i = hashString(tall, h.N);
        for(String t: h.liste){
            if(t != null && t.equals(tall)){
                return true;
            }
        }
        return false;
    }

    // public static void remove(HashList h, String tall){
    //     int i = hashString(tall, h.N);
    //     if(!contains(h, tall)){
    //         return;
    //     }
    //     if(h.liste[i] == Integer.parseInt(tall)){
    //         h.liste[i] = 0;
    //     }
    //     else{
    //         int a = 0;
    //         while(h.liste[i] != Integer.parseInt(tall)){
    //             i++;
    //         }
    //         if(h.liste[i] == Integer.parseInt(tall)){
    //             h.liste[i] = 0;
    //             h.lagtInn[i] = false;
    //         }
    //     }
    //     h.lagtInn[i] = false;
    //     h.size --;
        
    // }

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


//må lage en klasse som inneholder en liste
