import java.util.Scanner;

public class JavaEx {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name, please: ");
        String name = scanner.nextLine();

        String test = String.format("Your name is %s", name);
        System.out.println(test);
    }
}
