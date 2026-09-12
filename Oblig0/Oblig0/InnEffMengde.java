import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InnEffMengde {
    public static void main(String[] args) {

        //oppgi navn på input ved kjøring av program
        les("input/"+args[0]);
        
    }

    public static void les(String filbane){
        ArrayList<Integer> liste = new ArrayList<>();
        
        try{
            File fil = new File(filbane);
            Scanner sc = new Scanner(fil);
            while (sc.hasNextLine()){
                String data = sc.nextLine();
                String[] kmd = data.split(" ");

                if(kmd[0].equals("insert")){
                    if(!sjekkTall(liste, kmd[1])){
                        liste.add(Integer.parseInt(kmd[1]));
                    }
                }
                else if(kmd[0].equals("remove")){

                    liste.remove(Integer.valueOf(kmd[1]));
                }
                else if(kmd[0].equals("size")){
                    System.out.println(liste.size());
                }
                else if(kmd[0].equals("contains")){
                    System.out.println(sjekkTall(liste, kmd[1]));
                }
            }
            sc.close();

        }
        catch(FileNotFoundException e){
            System.err.println("fant ikke fil");
        }
    }

    public static boolean sjekkTall(ArrayList<Integer> tallListe, String tall){
        for(int t: tallListe){
            if(Integer.parseInt(tall) == t){
                return true;
            }
        }
        return false;
    }
}
