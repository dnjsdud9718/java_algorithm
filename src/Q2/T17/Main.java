package Q2.T17;
/**
 * 1427 silver 5 정렬(삽입정렬을 이용해보자) 내림차순
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chArr = s.toCharArray();
        for(int i=1; i<chArr.length; i++){
            char now = chArr[i];
            int j = i-1;
            for(; j>=0; j--){
                if(chArr[j]-'0' < now - '0') chArr[j+1] = chArr[j];
                else break;
            }
            j++;
            chArr[j] = now;
        }
        for(char x : chArr) System.out.printf("%c", x);
        sc.close();
    }
}
