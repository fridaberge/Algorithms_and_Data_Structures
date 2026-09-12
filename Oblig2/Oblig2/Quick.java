class Quick extends Sorter {

    void sort(){
        quickSort(0, n-1);
    }

    void quickSort(int low, int high) {
        if(geq(low, high)){
            return;
        }
        int p = partition(A, low, high);
        quickSort(low, p-1);
        quickSort(p+1, high);
    }


    String algorithmName() {
        return "quick";
    }

    public int partition(int[] A, int low, int high){
        int p = choosePivot(low, high);
        swap(p, high);
    
        int pivot = A[high];
        int left = low;
        int right = high-1;
        while(leq(left, right)){
            while(leq(left, right) && leq(A[left], pivot)){
                left ++;
            }
            while(leq(left, right) && geq(A[right], pivot)){
                right --;
            }
            if(lt(left, right)){
                swap(left, right);
            }
        }
        swap(left, high);
        return left;
    }

    public static int choosePivot(int low, int high){
        return (low+high)/2;
    }
}
