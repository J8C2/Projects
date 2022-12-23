import java.util.ArrayList;
import java.util.List;

public class BinarySearch 
{
	/**
	 * Search a sorted list of strings using binary search. Return a list of
	 * the indices of the strings checked during the search in the order they
	 * are checked. If the target string is not found, append -1 to the end of
	 * the list. Otherwise, the last element is the index of the target.
	 *
	 * @param strings  the list to be searched
	 * @param target  the string to be searched for
	 * @param fromIdx  the index of the first string in the range of strings to
	 *                 be searched (inclusive)
	 * @param toIdx  the index of the last string in the range of strings to be
	 *               searched (inclusive)
	 * @return a list of the indices of the strings checked during the search.
	 *         If the target is not in the list, the last element is -1.
	 */
	

	public static List<Integer> binarySearch(List<String> strings,
			String target, int fromIdx, int toIdx) 
	{
		List<Integer> indices = new ArrayList<>();
	    int midIdx = (fromIdx + toIdx) / 2;
	    
		if (toIdx >= fromIdx)
		{ 
			indices.add(midIdx);
			if (strings.get(midIdx).equals(target))
			{
				return indices;
			}
			
			if (strings.get(midIdx).compareTo(target) > 0)
		    { 
				toIdx = midIdx - 1;
				indices.addAll(binarySearch(strings, target, fromIdx, toIdx));
				return indices;
			}
		    if (strings.get(midIdx).compareTo(target) < 0)
		    {
		        fromIdx = midIdx + 1;
		        indices.addAll(binarySearch(strings, target, fromIdx, toIdx));
		        return indices;
			}
		}
		indices.add(-1);
		return indices;
	}
	/*
	if (fromIdx > toIdx)
	{ 
		indices.add(-1);
		return indices;
	}
	
	if (strings.get(midIdx) == target)
	{ 
		indices.add(midIdx);
		return indices;
	}
	while (fromIdx <= toIdx)
	{
		indices.add(midIdx);
		if (strings.indexOf(target) > midIdx)
		{
			toIdx = midIdx - 1;
		}
		else if (strings.get(midIdx) == target)
		{ 
			indices.add(midIdx);
			return indices;
		}
		else
		{ 
			fromIdx = midIdx + 1;
		}
		midIdx = (fromIdx + toIdx) / 2;
	}
	**/
	

}
