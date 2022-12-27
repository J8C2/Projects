import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author Jace Blackwell
 * @version JDK 17
 * Project 1
 *
 */

public class Blocks 
{
	/**
	 * Class variables
	 */
private int number;
private String miner;
private static ArrayList<Blocks> blocks;

/**
 * 
 * Default constructors for this class
 * 
 */
public Blocks()
{ 
}
public Blocks(int number)
{ 
	this.number = number;
	
}
public Blocks(int number, String miner)
{ 
	this.number = number;
	this.miner = miner;
}

/**
 * 
 *
 * @return number and miner
 * 
 * 
 */
public int getNumber()
{ 
	return number;
}
public String getMiner()
{ 
	return miner;
}

/**
 * @param None
 * @return ArrayList<Blocks>
 * 
 */
public static ArrayList<Blocks> getBlocks()
{ 
	ArrayList<Blocks> blox = new ArrayList<>();
	for (Blocks blocky : blocks)
	{ 
		blox.add(blocky);
	}
	return blox;
}
/**
 * @param None
 * @return None
 * Used to identity the amount of unique Miners and how frequent they are!
 * 
 * 
 */
public static void calUniqMiners()
{ 
	ArrayList<String> uniqAddress = new ArrayList<>();
	ArrayList<Integer> frequency = new ArrayList<>();
	for (int i = 0; i < blocks.size(); ++i)
	{ 
		String address = blocks.get(i).getMiner();
		if (!uniqAddress.contains(address))
		{ 
			uniqAddress.add(address);
			frequency.add(1);
		}
		else
		{ 
		int index = uniqAddress.indexOf(address);
		int value = frequency.get(index);
		value = value + 1;
		frequency.set(index, value);
		}
	}
	System.out.println("Number of unique Miners: " + uniqAddress.size());
	System.out.println();
	System.out.println("Each unique Miner and its frequency:");
	for (int x = 0; x < uniqAddress.size(); ++x)
	{ 
		System.out.println("Miner Address: " + uniqAddress.get(x));
		System.out.println("Miner Frequency: " + frequency.get(x));
		System.out.println();
	}
}


/**
 * 
 * @param A
 * @param B
 * @return integer
 */
public static int blockDiff(Blocks A, Blocks B)
{ 
	return A.getNumber() - B.getNumber();
}

/**
 * 
 * @param num
 * @return a Blocks object
 */
public static Blocks getBlockByNumber(int num)
{ 
	for (int i = 0; i < blocks.size(); ++i)
	{ 
		if (num == blocks.get(i).getNumber())
		{ 
			return blocks.get(i);
		}
	}
	return null;
}

/**
 * returns a String of your object
 */
public String toString()
{ 
	if (getNumber() > 0 && getMiner() != null)
	{ 
		return "Block Number: " + getNumber() + " Miner Address: " + getMiner();
	}
	else if (getNumber() > 0)
	{ 
		return "Block Number: " + getNumber();
	}
	else 
	{ 
		return "Empty Block";
	}
}

/**
 * 
 * @param filename
 * @throws IOException
 */
public static void readFile(String filename) throws IOException
{ 
	BufferedReader br = new BufferedReader(new FileReader(filename));
	blocks = new ArrayList<Blocks>();
	String line;
	while ((line = br.readLine()) != null)
	{ 
		String[] lines = line.split(",");
		int number = Integer.parseInt(lines[0]);
		String miner = lines[9];
		Blocks b = new Blocks(number, miner);
		blocks.add(b);
	}
	br.close();
}
/**
 * Close bufferedReader
 */
}
