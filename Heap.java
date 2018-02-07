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
	  Constructor for using the optimal method of creating a heap.  This method inserts all
	  the values of the int array into the heap at once and does a reheap until heap is valid
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

	/*
	  The insert method takes 1 integer parameter and inserts it into the heap.
	  Performs upHeap if necessary
	*/
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

	/*
	  This method removes the topmost value of the heap and performs a downHeap
	  to maintain heap order.
	*/
	public void removeTop()
	{		
		if (currentIndex > 100)
			currentIndex = 100;
		
		hArray[1] = hArray[currentIndex];
		hArray[currentIndex] = 0;
		--currentIndex;
		downHeap(1);
	}

	/*
	  This method simply returns the number of swaps so far.
	*/
	public int getSwaps()
	{
		return swaps;
	}

	// BEGIN CHOICE 1 SPECIFIC METHODS
	
	/*
	  This method sets all slots of hArray to 0, which "clears" the heap
	*/
	public void clear()
	{
		if (hArray.length > 0)
		{
			for (int i = 0; i < hArray.length; ++i)
				hArray[i] = 0;
		}
		
		currentIndex = 1;
		swaps = 0;
	}

	/*
	  This method replaces the contents of hArray with the contents of the
	  int array parameter to "reset" the heap with new values.  Performs
	  reheap when necessary to maintain max-heap order.
	*/
	public void reset(int[] arr)
	{
		swaps = 0;

		if (hArray.length == 0)
			hArray = new int[arr.length];
			
		for (int i = 0; i < arr.length; ++i)
			hArray[i] = arr[i];
		
		currentIndex = 101;

		for (int i = 50; i > 0; --i)
			reheap(i);
	}

	// END CHOICE 1 SPECIFIC METHODS

	/*
	Returns a string representation of the heap for console output purposes.  
	*/
	public String toString()
	{
		String output = "";

		for (int i = 1; i < hArray.length; ++i)
		{
			if (hArray[i] > 0)
				output = output + Integer.toString(hArray[i]) + ((i < 100) ? " " : "");
		}
		
		return output;
	}

	// BEGIN PRIVATE METHODS

	/*
	  This is the upHeap operation, which continually checks if a child's value
	  is bigger than its parent through the use of the index integer parameter.  
	  This goes upward and checks continually until the child is no longer 
	  bigger than the parent.
	*/
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

	/*
	  This method is typically performed when the root value is removed
	  or when a reheap is performed.  The index integer parameter is used
	  as the starting point and as a parent of some child.  It breaks once 
	  the child is no longer bigger than the parent.
	*/
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

	/*
	  This method is ran when creating the heap using the optimal method
	  or using reset to recreate the heap using the optimal method.  It does
	  a down heap starting from the index integer parameter if the index
	  is not a leaf node
	*/
	private void reheap(int index)
	{
		if (!isLeaf(index))
			downHeap(index);
	}

	/*
	  This method takes an index integer parameter, which is a node in the
	  heap and determines if it's a leaf.
	*/
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

	/*
	  This method takes two integers, a and b, and interprets
	  them as indices of the heap (hArray).  it checks which of the
	  two indices has a bigger value in hArray and returns the index
	  containing the max value.
	 */
	private int getMax(int a, int b)
	{
		return ((hArray[a] >= hArray[b]) ? a : b);
	}
}
