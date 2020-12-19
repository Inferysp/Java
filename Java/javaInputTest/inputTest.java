import java.util.*;

public class inputTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Jak się nazywasz?");
        String name = in.nextLine();

        System.out.println("Ile masz lat?");
        int age = in.nextInt();

        System.out.println("Witaj użytkowniku" + name + ". W przyszłym roku będziesz miec " + (age + 1) + "lat.");
    }
}