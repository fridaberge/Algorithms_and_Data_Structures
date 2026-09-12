import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Kattunge {
    ArrayList <Node> foreldre = new ArrayList<>();
    Node rot;
    int kattPlass;
    String sti = "";

    public static void main(String[] args) {
        Kattunge tre = new Kattunge();
        tre.lesInput();
        tre.settRot();
        Node kattNode = tre.finnBarn(tre.kattPlass);
        System.out.println(tre.printSti(kattNode));
        
    }

    public void lesInput(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            kattPlass = Integer.parseInt(br.readLine());
            while(true){
                String[] linje = br.readLine().split(" ");
                if(linje[0].equals("-1")){
                    return;
                }
                Node nyF = new Node(Integer.parseInt(linje[0]));
                foreldre.add(nyF);

                for(int i = 1; i < linje.length; i++){
                    Node nyB = new Node(Integer.parseInt(linje[i]));
                    nyB.forelder = nyF;
                    nyF.barn.add(nyB);
                }
            }
        }
        catch(IOException e){
            System.err.println("feil");
        }
    }

    public Node finnBarn(int x){
        for(Node forelder: foreldre){
            for(Node barn: forelder.barn){
                if(barn.verdi == x){
                    return barn;
                }
            }
        }
        return null;
    }

    public void settRot(){
        for(Node forelder: foreldre){
            if(finnBarn(forelder.verdi) == null){
                rot = forelder;
            }
        }
    }

    public String printSti(Node x){

        if(x == null){
            return sti;
        }
        if(x.verdi == rot.verdi){
            sti += x.verdi + " ";
            return sti;
        }
        if(x.forelder == null){
            return printSti(finnBarn(x.verdi));
        }
        sti += x.verdi + " ";
        return printSti(x.forelder);
    }

}

class Node{
    Node forelder;
    ArrayList<Node> barn = new ArrayList<>();
    int verdi;

    Node(int verdi){
        this.verdi = verdi;
    }
}
