import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TowerOfHanoi 
{
	
private Map<Peg, Deque<Integer>> diskStacks = new HashMap<>();


public TowerOfHanoi(int numDisks, Peg start)
{ 
	Deque<Integer> number = new LinkedList<>();

	
	if (start == null)
	{ 
		throw new NullPointerException();
	}
	if (numDisks <= 0)
	{ 
		throw new IllegalArgumentException();
	}
    for (int i = 1; i <= numDisks; ++i)
    {
	number.add(i);
    }
	diskStacks.put(start, number);
	
	
}

public Deque<Integer> getDiskStack(Peg peg)
{ 
	if (peg.equals(null))
	{
		throw new NullPointerException();
	}
	
	
	if ((diskStacks.containsKey(peg)) == false)
	{ 
		Deque<Integer> empty = new LinkedList<>();
		return empty;
	}
	
	Deque<Integer> copy = new LinkedList<>();
	copy.addAll(diskStacks.get(peg));
	return copy;
	
}

public void moveDisk(Move move)
{ 
	if (move == null)
	{ 
		throw new NullPointerException();
	}

	if (getDiskStack(move.from).isEmpty())
	{ 
		throw new IllegalArgumentException();
	}
    
	if (getDiskStack(move.to).size() == 0)
	{ 
		Deque<Integer> temp = new LinkedList<>();
		temp.add(1);
		diskStacks.put(move.to, temp);
		diskStacks.get(move.to).push(diskStacks.get(move.from).pop());
		diskStacks.get(move.to).removeLast();
		return;
		
	}
	if (getDiskStack(move.from).pop() > getDiskStack(move.to).pop())
	{ 
		throw new IllegalArgumentException();
	}

	diskStacks.get(move.to).addFirst(diskStacks.get(move.from).pop());
	return;
}

public static List<Move> solve(int numDisks, Peg start, Peg end)
{ 
	if (numDisks < 0)
	{ 
		throw new IllegalArgumentException();
	}
	
	if (start == null || end == null)
	{ 
		throw new NullPointerException();
	}
	
	if (numDisks == 0 )
	{ 
		List<Move> temp = new LinkedList<>();
		return temp;
	}
	
	if (start == end)
	{ 
		List<Move> empty = new LinkedList<>();
		return empty;
	}
	
	List<Move> solver = new LinkedList<>();
	Peg x = Peg.other(start, end);
	if (numDisks == 1)
	{ 
		solver.add(Move.move(start, end));
		return solver;
	}
	else
	{
		solver.addAll(solve(numDisks-1, start, x));
		solver.add((Move.move(start, end)));
		solver.addAll(solve(numDisks-1, x, end));
	}
	return solver;
}

public String toString()
{ 
	Iterator<Integer> reverse = this.getDiskStack(Peg.LEFT).descendingIterator();
	Iterator<Integer> reverse2 = this.getDiskStack(Peg.MIDDLE).descendingIterator();
	Iterator<Integer> reverse3 = this.getDiskStack(Peg.RIGHT).descendingIterator();
	
	Deque<Object> leftReverse = new LinkedList<>();
	Deque<Object> middleReverse = new LinkedList<>();
	Deque<Object> rightReverse = new LinkedList<>();
	
	while(reverse.hasNext())
	{ 
		leftReverse.add(reverse.next());
	}
	while (reverse2.hasNext())
	{ 
		middleReverse.add(reverse2.next());
	}
	while(reverse3.hasNext())
	{ 
		rightReverse.add(reverse3.next());
	}
	
	return  "  LEFT: " + leftReverse + System.lineSeparator() + 
			"MIDDLE: " + middleReverse + System.lineSeparator() + 
			" RIGHT: " + rightReverse;

}
}
