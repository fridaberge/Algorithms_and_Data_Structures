//algoritme emd kjøretidskompleksitet O(n^2)

public class Selection extends Sorter{

    void sort() {
        for(int i = 0; i < n; i++){
            int k = i;
            for(int j = i+1; j < n; j++){
                if(lt(A[j], A[k])){
                    k = j;
                }
            }
            if(!eq(k, i)){
                swap(i, k);
            }
        }
    }

    

    String algorithmName() {
        return "selection";
    }
}