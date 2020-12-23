package test;

import java.util.Scanner;

public class T1 {
    public static void main(String[] args) {
        String first="BJ";
        String second="_";
        String third="GY";
        String finall;
        while (true){
            System.out.println("请输入一个数字");
            Scanner scanner=new Scanner(System.in);
            int i = scanner.nextInt();
            if (i/10==0){
                finall=first+second+third+"00"+i;
                System.out.println(finall);
            }else if (9<i&&i/10<10){
                finall=first+second+third+"0"+i;
                System.out.println(finall);
            }else if(i/100>=0){
                finall=first+second+third+i;
                System.out.println(finall);
            }
        }
    }
}
