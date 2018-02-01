import java.util.Scanner;

/*
  This class represents the main class for the entire project containing the main method
 */
public class Project2
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("Please select how to test the program:\n");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100\n");
		System.out.print("Enter choice:  ");

		choice = scan.nextInt();

		switch (choice)
		{
		case 1:
			System.out.println("You chose 1");
			break;
		case 2:
			System.out.println("You chose 2");
			break;
		default:
			System.out.println("Invalid input!  Exiting...");
		}
	}
}
