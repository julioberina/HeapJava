import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
  This class represents the main class for the entire project containing the main method
*/
public class Project2
{
	private static Random rand = null;
	private static Heap heap = null;
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int choice = 0;

		addLines();
		System.out.println("Please select how to test the program:\n");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100\n");
		System.out.print("Enter choice:  ");

		choice = scan.nextInt();
		rand = new Random();
		System.out.print("\n");

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

		addLines();
		System.out.print("\n");
	}

	public static void addLines()
	{
		for (int i = 0; i < 70; ++i)
			System.out.print("=");

		System.out.print("\n");
	}

	public static int[] convertToIntArray(Object[] arr)
	{
		int[] result = new int[arr.length];

		for (int i = 0; i < arr.length; ++i)
			result[i] = ((int)arr[i]);

		return result;
	}

	public static int mean(int[] arr)
	{
		int total = 0;

		for (int num: arr)
			total += num;

		return (total / arr.length);
	}

	public static void choice1()
	{
		List<Integer> list = new ArrayList<Integer>();
		int[] swapStats = new int[20];
		heap = new Heap();

		for (int i = 1; i <= 100; ++i)
			list.add(i);

		for (int i = 0; i < 20; ++i)
		{
			Collections.shuffle(list);
			
			for (int j = 0; j < 100; ++j)
				heap.insert(list.get(j));

			swapStats[i] = heap.getSwaps();
			heap.clear();
		}

		System.out.printf("Average swaps for series of insertions:  %d\n", mean(swapStats));

		for (int i = 0; i < 20; ++i)
		{
			Collections.shuffle(list);
			heap.reset(convertToIntArray(list.toArray()));
			swapStats[i] = heap.getSwaps();
		}

		System.out.printf("Average swaps for optimal method:  %d\n", mean(swapStats));
	}

	public static void choice2()
	{
		int[] fixed = new int[101];

		for (int i = 0; i < fixed.length; ++i)
			fixed[i] = i;

		// Use sequential insertions first
		heap = new Heap();
		for (int i = 1; i < fixed.length; ++i)
			heap.insert(fixed[i]);

		System.out.print("Heap built using series of insertions:  ");
		System.out.println(heap);
		System.out.println("Number of swaps:  " + heap.getSwaps());
		System.out.print("Heap after 10 removals:  ");

		for (int i = 0; i < 10; ++i)
			heap.removeTop();
		
		System.out.println(heap);

		// Use optimal method next
		heap = new Heap(fixed);
		System.out.print("\n");

		System.out.print("Heap built using optimal method:  ");
		System.out.println(heap);
		System.out.println("Number of swaps:  " + heap.getSwaps());
		System.out.print("Heap after 10 removals:  ");

		for (int i = 0; i < 10; ++i)
			heap.removeTop();
		
		System.out.println(heap);
	}
}
