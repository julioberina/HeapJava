/*
  This class represents the heap data structure with all of its methods
*/
public class Heap
{
	private int[] hArray; // array storing heap data
	private int swaps;
	private int currentIndex;
	
	/*
	  Default constructor for creating heap. Allocates 101 spaces for array as max heap size
	*/
	public Heap()
	{
		hArray = new int[101];
		swaps = 0;
		currentIndex = 1;
	}

	/*
	  Constructor for using the optimal method of creating a heap
	*/
	public Heap(int[] arr)
	{
		hArray = new int[arr.length];
		swaps = 0;
		
		for (int i = 0; i < arr.length; ++i)
			hArray[i] = arr[i];
	}

	public void insert(int value)
	{
		try
		{
			hArray[currentIndex] = value;
			upHeap(currentIndex);
			++currentIndex;
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Heap is already at max amount of stored values!");
		}
	}

	// BEGIN PRIVATE METHODS
	
	private void upHeap(int index)
	{
		int temp = 0;
		
		while (index > 1)
		{
			if (hArray[index] > hArray[index / 2])
			{
				// swap the value of the child with the parent if child > parent
				temp = hArray[index];
				hArray[index] = hArray[index / 2];
				hArray[index / 2] = temp;
				index /= 2;
			}
			else
				break;
		}
	}
}
