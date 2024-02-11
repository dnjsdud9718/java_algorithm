package SSAFY.B형.비트연산.이진수_표현;

class Main{
    public static void main(String[] args) {
        char alpabet = 'A';
        for(int i=0; i<26; i++) {
            for(int j=7; j>=0; j--){
                if((alpabet & (1 << j)) == (1<<j)) System.out.printf("%d",1);
                else System.out.printf("%d", 0);
            }
            System.out.println(" <=> "+alpabet+" "+(int)alpabet);
            alpabet+=1;
        }
    }
}
