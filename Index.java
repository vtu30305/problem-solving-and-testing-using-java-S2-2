import java.util.Scanner;
public class Index{
public static void main(String[] args){
String[] num={"zero","one","two","three","four","five"};
Scanner sc = new Scanner(System.in);
System.out.print("enter the index value of num : ");
int index = sc.nextInt();
System.out.print(num[index]);
}
}