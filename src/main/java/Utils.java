import java.util.Scanner;

public class Utils {
    static Scanner scan = new Scanner(System.in);

    public static String getString(){
        return scan.nextLine();
    }

    public static int getInt(){
        return Integer.parseInt(getString());
    }
}