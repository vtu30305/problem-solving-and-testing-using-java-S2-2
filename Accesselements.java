import java.util.Scanner;

public class AccessElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {10, 20, 30, 40, 50};

        System.out.print("Enter the index: ");
        int index = sc.nextInt();

        if (index >= 0 && index < arr.length) {
            System.out.println("Element at index " + index + " is " + arr[index]);
        } else {
            System.out.println("Invalid Index");
        }

        sc.close();
    }
}