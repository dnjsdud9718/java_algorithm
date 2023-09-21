package Q2.T19;


// quick sort, O(nlogn)
// pivot이 우측 끝에 있는 경우.

public class Quick {    
    private static int [] arr;
    public static void main(String[] args){
        arr = new int[10];
        for(int i=0; i<10; i++) arr[i] = (int)(Math.random()*100);
        for(int x: arr) System.out.printf("%d ", x);
        System.out.println();
        sort(arr,0, 9);
        for(int x: arr) System.out.printf("%d ", x);

    }
    public static void sort(int[] arr, int lt, int rt) {
        if(lt < rt) {
            int pivot = arr[rt];
            int i = lt - 1;
            int j = rt;
            do {
                do { // pivot보다 크거나 같은 수를 찾는다.
                    i++;
                }while(i < rt && arr[i] < pivot);
                do { // pivot보다 작거나 같은 수를 찾는다.
                    j--;
                }while(j >= lt && arr[j] > pivot);
                if(i < j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }while(i < j);
            if(i < rt) {
                int tmp = arr[i];
                arr[i] = arr[rt];
                arr[rt] = tmp;
            }

            sort(arr, lt, i-1);
            sort(arr, i+1, rt);
        }    

        
    }
}
