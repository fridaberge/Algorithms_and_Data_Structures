/*
1. kjør filen "BalansertSoketre.java" med en av testene "test.txt", "test2.txt" eller "test3.txt", koden vil lage to filer: "heapOutput.txt" og "tallOutput.txt"
2. kjør filen "BalanceChecker.java" med filen "heapOutput.txt" eller "tallOutput.txt" som input
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.FileWriter;

public class BalansertSoketre {
    ArrayList <Integer> tallListe = new ArrayList<>();
    ArrayList <Integer> tallOutput = new ArrayList<>();
    PriorityQueue <Integer> ko = new PriorityQueue<>();
    ArrayList <Integer> heapOutput = new ArrayList<>();
    String printe = "";

    public static void main(String[] args) {
        BalansertSoketre tre = new BalansertSoketre();
        tre.lesInput();
        tre.omOrganiserHeap(tre.ko);
        tre.omOrganiser(0, tre.tallListe.size()-1);
        tre.skrivFil("tallOut", tre.tallOutput);
        tre.skrivFil("heapOut", tre.heapOutput);
    }
    
    public void skrivFil(String filnavn, ArrayList<Integer> liste){
    try {
        FileWriter myWriter = new FileWriter(filnavn+".txt");
        for (int tall : liste) {
            String st = tall + "\n";
            myWriter.write(st);
        }
        myWriter.close();
        System.out.println("Skrevet til fil med navn: " +filnavn +".txt");
      } catch (IOException e) {
        System.out.println("Klarte ikke skrive til fil");
        e.printStackTrace();
      }
    }


    public void lesInput(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                String linje = br.readLine();
                if(linje == null){
                    return;
                }
                tallListe.add(Integer.parseInt(linje));
                ko.offer(Integer.parseInt(linje));
            }
        }
        catch(IOException e){
            System.err.println("feil");
        }
        
    }

    public ArrayList<Integer> omOrganiser(int lav, int hoy){
        if(lav > hoy){
            return tallOutput;
        }
        int midt = (lav+hoy)/2;
        tallOutput.add(tallListe.get(midt));
        omOrganiser(midt+1, hoy);
        omOrganiser(lav, midt-1);
        return null;
    }

    public void omOrganiserHeap(PriorityQueue<Integer> H){
        if(H.size() == 0){
            return;
        }
        int midt = (H.size()-1)/2;
        int teller = 0;
        PriorityQueue<Integer> lavHeap = new PriorityQueue<>();
        PriorityQueue<Integer> hoyHeap = new PriorityQueue<>();
        for(int tall: H){
            if(teller < midt){
                lavHeap.offer(tall);
            }
            else if(teller >= midt){
                hoyHeap.offer(tall);
            }
            teller++;
        }
        heapOutput.add(hoyHeap.poll());
        omOrganiserHeap(hoyHeap);
        omOrganiserHeap(lavHeap);
    } 

}

