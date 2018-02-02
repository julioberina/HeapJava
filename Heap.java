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

		currentIndex = 101;

		for (int i = 50; i > 0; --i)
			reheap(i);
	}

	public void insert(int value)
	{
		if (currentIndex < 101)
		{
			hArray[currentIndex] = value;
			upHeap(currentIndex);
			++currentIndex;
		}
		else
			System.out.println("Unable to add value.  Heap is full!");
	}

	public void removeTop()
	{		
		if (currentIndex > 100)
			currentIndex = 100;
		
		hArray[1] = hArray[currentIndex];
		hArray[currentIndex] = 0;
		--currentIndex;
		downHeap(1);
	}

	public int getSwaps()
	{
		return swaps;
	}

	public String toString()
	{
		String output = "";

		for (int i = 1; i < hArray.length; ++i)
			output = output + Integer.toString(hArray[i]) + ((i < 100) ? " " : "");
		
		return output;
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
				++swaps;
				index /= 2;
			}
			else
				break;
		}
	}

	private void downHeap(int index)
	{
		int temp = 0;
		int maxIndex = 0;
		
		while (index <= 50)
		{
			if (index == 50)
			{
				if (hArray[index * 2] > hArray[index])
				{
					temp = hArray[index];
					hArray[index] = hArray[index * 2];
					hArray[index * 2] = temp;
					++swaps;
				}
				
				break;
			}
			else
			{
				if (hArray[index * 2] > hArray[index] || hArray[index * 2 + 1] > hArray[index])
				{
					temp = hArray[index];
					maxIndex = getMax((index * 2), (index * 2 + 1));
					hArray[index] = hArray[maxIndex];
					hArray[maxIndex] = temp;
					++swaps;
					index = maxIndex;
				}
				else
					break;
			}
		}
	}

	private void reheap(int index)
	{
		if (!isLeaf(index))
			downHeap(index);
	}

	private boolean isLeaf(int index)
	{
		if ((index * 2) > 100) // if left child index is bigger than 100
			return true;
		else if (((index * 2) + 1) > 100) // if right child index is > 100, check if left child exists
		{
			if (hArray[index * 2] > 0)
				return false;
			else
				return true;
		}
		else
		{
			if (hArray[index * 2] == 0 && hArray[index * 2 + 1] == 0)
				return true;
			else
				return false;
		}
	}

	private int getMax(int a, int b)
	{
		return ((hArray[a] >= hArray[b]) ? a : b);
	}
}
