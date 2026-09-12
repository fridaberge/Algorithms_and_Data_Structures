//algoritme emd kjøretidskompleksitet O(n*(log(n)))

public class Merge extends Sorter{

    void sort() {
        mergeSort(A);
    }

    public int[] mergeSort(int[] A){
        if(leq(A.length, 1)){
            return A;
        }
        int midt = A.length/2;
        int[] A1 = new int[midt];
        int[] A2 = new int[A.length-midt];
        for(int i = 0; i < A.length; i++){
            if(lt(i,midt)){
                A1[i] = A[i];
            }
            else{
                A2[i-midt] = A[i];
            }
        }
        A1 = mergeSort(A1);
        A2 = mergeSort(A2);
        return merge(A1, A2, A);
    }

    public int[] merge(int[] A1, int[] A2, int[] A){
        int i = 0;
        int j = 0;
        while(lt(i, A1.length) && lt(j, A2.length)){
            if(leq(A1[i], A2[j])){
                A[i+j] = A1[i];
                swaps ++;
                i ++;
            }
            else{
                A[i+j] = A2[j];
                swaps ++;
                j ++;
            }
        }
        while(lt(i, A1.length)){
            A[i+j] = A1[i];
            swaps ++;
            i ++;
        }
        while(lt(j, A2.length)){
            A[i+j] = A2[j];
            swaps ++;
            j ++;
        }
        return A;
    }

    String algorithmName() {
        return "merge";
    }
}