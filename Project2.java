import java.util.Scanner;
import java.util.Random;

/*
  This class represents the main class for the entire project containing the main method
*/
public class Project2
{
	private Random rand = null;
	private Heap heap = null;
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("Please select how to test the program:\n");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100\n");
		System.out.print("Enter choice:  ");

		choice = scan.nextInt();
		rand = new Random();

		switch (choice)
		{
		case 1:
			choice1();
			break;
		case 2:
			choice2();
			break;
		default:
			System.out.println("Invalid input!  Exiting...");
			break;
		}
	}

	public static void choice1()
	{
		
	}

	public static void choice2()
	{
		int[] fixed = new int[101];

		for (int i = 0; i < fixed.length; ++i)
			fixed[i] = i;

		// Use sequential insertions first
		heap = new Heap();
		for (int num : fixed)
			heap.insert(num);

		System.out.println("Average swaps for series of insertions:  " + heap.getSwaps());

		// Use optimal method next
		heap = new Heap(fixed);

		System.out.println("Average swaps for optimal method:  " + heap.getSwaps());
	}
}
