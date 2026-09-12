import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class BalanserListe {
    ArrayList <Integer> tallListe = new ArrayList<>();
    ArrayList <Integer> tallOutput = new ArrayList<>();

    public static void main(String[] args) {
        BalansertSoketre tre = new BalansertSoketre();
        tre.lesInput();
        tre.omOrganiser(0, tre.tallListe.size()-1);
        tre.skrivFil("tallOut", tre.tallOutput);
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

}

