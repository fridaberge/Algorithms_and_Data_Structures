import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Teque{

    int size = 0;
    Node start;
    Node end;
    Node middle;

    public Teque(){}

    public static void main(String[] args) {
        lesInput();
    }

    public static void lesInput(){
        try{
            Teque ko = new Teque();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N; i++){
                String[] kmd = br.readLine().split(" ");

                if(kmd[0].equals("push_back")){
                    ko.push_back(Integer.parseInt(kmd[1]));
                }
                else if(kmd[0].equals("push_front")){
                    ko.push_front(Integer.parseInt(kmd[1]));

                }
                else if(kmd[0].equals("push_middle")){
                    ko.push_middle(Integer.parseInt(kmd[1]));
                }
                else if(kmd[0].equals("get")){
                    System.out.println(ko.get(Integer.parseInt(kmd[1])).value);
                }

                // System.out.println("\nlista:");
                // ko.printliste();
            }
            
        }
        catch(IOException e){
            System.err.println("feil");
        }
        
    }

    public void push_front(int x){
        Node ny = new Node(x);
        if(size == 0){
            start = ny;
            end = ny;
        }
        else{
            ny.next = start;
            start.forrige = ny;
            start = ny;
        }
        if(size == 2){
            middle = start.next;
        }
        else if(size > 2 && size%2 == 0){
            middle = middle.forrige;
        }
        size ++;
    }

    public void push_back(int x){
        Node ny = new Node(x);
        if(size == 0){
            start = ny;
            end = ny;
        }
        else{
            end.next = ny;
            ny.forrige = end;
            end = ny;
        }
        if(size == 2){
            middle = start.next;
        }
        else if(size > 2 && size%2 != 0){
            middle = middle.next;
        }
        size ++;
    }

    public void push_middle(int x){
        Node ny = new Node(x);
        if(size == 0){
            start = ny;
            end = ny;
        }
        if(size == 1){
            start.next = ny;
            ny.forrige = start;
            end = ny;
        }
        else if(size == 2){
            start.next = ny;
            ny.forrige = start;
            ny.next = end;
            end.forrige = ny;
            middle = ny;
        }
        else if(size > 2 && size%2 != 0){
            Node kopi = middle.next;
            middle.next = ny;
            ny.forrige = middle;
            ny.next = kopi;
            kopi.forrige = ny;
            middle = ny;
        }
        else if(size > 2 && size%2 == 0){
            Node kopi = middle.forrige;
            kopi.next = ny;
            ny.forrige = kopi;
            ny.next = middle;
            middle.forrige = ny;
            middle = ny;
        }
        size ++;
    }

    public Node get(int i){
        if(i == 0){
            return start;
        }
        else if(i == size-1){
            return end;
        }
        else{
            Node tmp = start;
            int a = 0;
            while(a < i){
                tmp = tmp.next;
                a++;
            }
            return tmp;
        }
    }

    public void printliste(){
        Node tmp = start;
        int a = 0;
        while(a < size){
            System.out.println(tmp.value);
            tmp = tmp.next;
            a++;
        }
        
    }
}

class Node{
    int value;
    Node next;
    Node forrige;

    Node(int value){
        this.value = value;
    }
}