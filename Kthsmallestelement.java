import java.util.Arrays;
import java.util.Scanner;

public class KthSmallest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {12, 3, 5, 7, 19};

        System.out.print("Enter the value of K: ");
        int k = sc.nextInt();

        Arrays.sort(arr);

        if (k > 0 && k <= arr.length) {
            System.out.println("The " + k + "th smallest element is " + arr[k - 1]);
        } else {
            System.out.println("Invalid value of K");
        }

        sc.close();
    }
}