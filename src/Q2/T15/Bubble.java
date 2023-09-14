package Q2.T15;
//Bubble sort, 버블 정렬
public class Bubble {
    public static void main(String[] args){
        int[] arr = new int[10];
        for(int i=0; i<10; i++) { arr[i] = (int)(Math.random()*100);}
        System.out.println("---- before sort ----");
        for(int x : arr) System.out.printf("%d ", x);
        System.out.println();

        for(int i=9; i>0; i--){
            for(int j=0; j<i; j++){
                if(arr[j] < arr[j+1]) { // 부호에 따라 오름 또는 내림차순 정렬
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        for(int x : arr) System.out.println(x);

    }
}
