package SSAFY.study.week3.t10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Person> src = new ArrayList<>();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            src.add(new Person(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        Collections.sort(src, (o1, o2) ->{
            return o1.age - o2.age;
        });
        for(Person p : src) sb.append(p.age).append(" ").append(p.name).append("\n");
        System.out.println(sb.toString());
        br.close();
    }
}
class Person{
    int age;
    String name;
    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }
}