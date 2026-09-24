import java.util.Scanner;

public class Average_Temperature {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 5 temperatures: ");

        double sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += input.nextDouble();
        }

        double average = sum / 5;
        System.out.println("Average temperature: " + average);
        input.close();
    }
}
