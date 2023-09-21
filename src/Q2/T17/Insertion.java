package Q2.T17;
//Insertion Sort 삽입 정렬 연습
// 이미 정렬된 상태에 현재 값을 적절한 위치에 삽입한다고 생각핮.
public class Insertion {
    public static void main(String[] args){
        int[] arr = new int[10];
        for(int i=0; i<arr.length; i++) arr[i] = (int)(Math.random()*100);
        for(int x : arr) System.out.printf("%d ", x);
        System.out.println("");
        for(int i=1; i<arr.length; i++){
            int now = arr[i]; //i 번째 이전에는 이미 정렬된 상태, 정렬된 배열에 now을 적절하게 삽입한다는 개념
            int j = i-1;
            for( ; j>=0; j--){
                if(arr[j] > now) {
                    arr[j+1] = arr[j];
                }else break;
            }
            j++;
            arr[j] = now;
        }
        for(int x: arr) System.out.printf("%d ", x);
    }    
}
