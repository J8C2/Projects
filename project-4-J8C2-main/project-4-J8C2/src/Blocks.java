import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.text.SimpleDateFormat;


public class Blocks implements Comparable<Blocks> {
	private int number;				// Block number
	private String miner;			// Miner address
	private long timestamp; 		// Unix timestamp
	private int transactionCount;	// Transaction count
	private Set<Transaction> temp = new HashSet<>();
	private ArrayList<Transaction> transactions = new ArrayList<>();
	private static ArrayList<Blocks> blocks = null;
	private StringBuilder returnString = new StringBuilder();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMMM yyyy HH:mm:ss z");
	private Date date;				// date in the format of "dateFormat

	public Blocks() {
		returnString.append("Empty Block");
	}

	public Blocks(int number) {
		this.number = number;
		returnString.append("Block Number: " + number);
	}

	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
		returnString.append("Block Number: " + number + " Miner Address: " + miner);
	}
	
	public Blocks(int number, String miner, long timestamp, int transactionCount) {
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactionCount = transactionCount;
		returnString.append("Block Number: " + number + " Miner Address: " + miner);
	}

	public int getNumber() {
		return this.number;
	}

	public String getMiner() {
		return this.miner;
	}
	
	public int getTransactionCount() {
		return this.transactionCount;
	}
	
	public static ArrayList<Blocks> getBlocks() {
		return new ArrayList<>(blocks);
	}

	// given an ArrayList of Blocks, find each unique miner address and
	// the frequency of times it appears and print according to output
	public static void calUniqMiners() throws FileNotFoundException, IOException {	
		// if blocks ArrayList has not been read, do so now
		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
		}
		
		// initialize ArrayLists to store addresses and frequencies
		ArrayList<String> uniqMiners = new ArrayList<String>();
		ArrayList<Integer> uniqMinersFreq = new ArrayList<Integer>();
		// holds each miner address
		String miner;
		// loop through all Blocks
		for (int i = 0; i < blocks.size(); ++i)
		{
			miner = blocks.get(i).getMiner();
			// enter if the miner is new
			if (!(uniqMiners.contains(miner)))
			{
				// add the miner and add the frequency of 1
				uniqMiners.add(miner);
				uniqMinersFreq.add(1);
			}
			// otherwise increment the frequency of that miner
			else
			{
				for (int j = 0; j < uniqMiners.size(); ++j)
				{
					if (uniqMiners.get(j).equals(miner))
					{
						uniqMinersFreq.set(j, uniqMinersFreq.get(j) + 1);
					}
				}
			}
		}

		// print according to output
		System.out.println("Number of unique Miners: " + uniqMiners.size() + "\n");
		System.out.println("Each unique Miner and its frequency:");
		for (int i = 0; i < uniqMiners.size(); ++i)
		{
			System.out.println("Miner Address: " + uniqMiners.get(i) + "\nMiner Frequency: " + uniqMinersFreq.get(i) + "\n");
		}
	}

	// calculate the difference in the block numbers of two blocks
	public static int blockDiff(Blocks minuend, Blocks subtrahend) {
		int diff = minuend.getNumber() - subtrahend.getNumber();

		return diff;
	}

	// given the Block number retrieve the Blocks object that corresponds to that number from blocks ArrayList and return it
	public static Blocks getBlockByNumber(int num) throws FileNotFoundException, IOException {
		
		if(blocks == null) {
			Blocks.readFile("ethereumP1data.txt");
		}
		
		for(int i = 0; i < blocks.size(); ++i) {
			if (blocks.get(i).getNumber() == num) {
				return blocks.get(i);
			}
		}

		return null;
	}

	public String toString() {
		return returnString.toString();
	}

	// reads a file of given filename and fills an ArrayList of Blocks
	public static ArrayList<Blocks> readFile(String filename) throws FileNotFoundException, IOException {
		// construct a file object for the file with the given name.
		File file = new File(filename);

		// construct a scanner to read the file.
		Scanner fileScanner = new Scanner(file);

		// blocks ArrayList to store Blocks objects
		ArrayList<Blocks> b = new ArrayList<Blocks>();

		// create the Array that will store each lines data so we can grab the required fields
		String[] fileData = null;

		// Store each line of the file into the ArrayList.
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			// split each line along the commas
			fileData = line.trim().split(",");

			// fileData[0] corresponds to block number, fileData[9] to miner address
			// fileData[16] corresponds to unix timestamp, fileData[17] corresponds to transaction count
			b.add(new Blocks(Integer.parseInt(fileData[0]), fileData[9], Integer.parseInt(fileData[16]), Integer.parseInt(fileData[17])));
		}

		fileScanner.close();

		blocks = new ArrayList<>(b);

		return b;
	}
	
	// sort the blocks ArrayList in ascending order based on block number
	public static void sortBlocksByNumber() throws FileNotFoundException, IOException {
		if (blocks==null) {
			readFile("ethereumP1.txt");
		}
		
		Collections.sort(blocks);
	}

	@Override
	public int compareTo(Blocks b) {
		Integer x = number;
		Integer y = b.getNumber();
		return x.compareTo(y);
	}
	
	// print the date with the correct format
	public String getDate() {
		// initialize date in milliseconds
		date = new Date(timestamp * 1000);
		dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
		return dateFormat.format(date);
	}
	
	// find the difference in time between two given Blocks in hours, minutes, and seconds
	public static void timeDiff(Blocks first, Blocks second) {
		//make sure given Blocks aren't null
		if ((first == null) || (second == null)) {
			System.out.println("A given Block is null.");
		}
		else {
			String hours = " hours, ";
			String minutes = " minutes, and ";
			String seconds = " seconds.";
			// use timestamps to find hours, minutes, seconds
			int diffInSeconds = (int) Math.abs(first.timestamp - second.timestamp);
			int diffInMinutes = diffInSeconds / 60;
			int diffInHours = diffInMinutes / 60;
			diffInSeconds = diffInSeconds % 60;
			diffInMinutes = diffInMinutes % 60;
			
			if (diffInHours == 1) {
				hours = " hour, ";
			}
			if (diffInMinutes == 1) {
				minutes = " minute, and ";
			}
			if (diffInSeconds == 1) {
				seconds = " second.";
			}
			

			System.out.println("The difference in time between Block " + first.getNumber() + " and Block " + second.getNumber() + " is "
					+ diffInHours + hours + diffInMinutes + minutes + diffInSeconds + seconds);
		}
	}
	
	// return the number of transactions between two Blocks not inclusive
	// return -1 if the Blocks are null/not in the blocks ArrayList
	// or if second is before first in the ArrayList
	public static int transactionDiff(Blocks first, Blocks second) throws FileNotFoundException, IOException {
		
		// if blocks ArrayList has not been read, do so now and sort it
		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
			sortBlocksByNumber();
		}
		
		// make sure given Blocks aren't null
		if ((first == null) || (second == null)) {
			return -1;
		}
		
		int indexA = -1;		// index of first in blocks ArrayList
		int indexB = -1;		// index of second in blocks ArrayList
		int count = 0;			// number of transactions between the two Blocks
		
		
		// for loop to find indexA and indexB
		for (int i = 0; i < blocks.size(); ++i) {
			if (first.getNumber() == blocks.get(i).getNumber()) {
				indexA = i;
			}
			if (second.getNumber() == blocks.get(i).getNumber()) {
				indexB = i;
			}
		}
		
		// make sure first and second are elements of blocks
		if ((indexA < 0) || (indexB < 0)) {
			return -1;
		}
		// make sure first comes before second
		if (indexA >= indexB) {
			return -1;
		}
		
		// for loop to count the transactions
		for (int i = indexA+1; i < indexB; ++i) {
			count += blocks.get(i).getTransactionCount();
		}
		
		return count;
	}
	public void readTransactions(String filename) throws NumberFormatException, IOException
	{
	BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
	while ((line = br.readLine()) != null)
	{ 
		String[] lines = filename.split(",");
		int number = Integer.parseInt(lines[3]);
		int index = Integer.parseInt(lines[4]);
		int gasLimit = Integer.parseInt(lines[8]);
		long gasPrice = Integer.parseInt(lines[9]);
		String fromAdr = lines[5];
		String toAdr = lines[6];
		temp.add(new Transaction(number, index, gasLimit, gasPrice, fromAdr, toAdr));
	}
	  for (Transaction x : temp)
	  { 
		  transactions.add(x);
	  }
	}
	   public double avgTransactionCost()
	{ 
		
	}
        public void uniqFromTo()
	{ 
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
