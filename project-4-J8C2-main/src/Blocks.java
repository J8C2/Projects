import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Jace Blackwell
 * @version JDK 17
 * Project 1
 *
 */

public class Blocks implements Comparable<Blocks> {
	private int number;				// Block number
	private String miner;			// Miner address
	private long timestamp; 		// Unix timestamp
	private int transactionCount;	// Transaction count
	private ArrayList<Transaction> transactions;
	private static ArrayList<Blocks> blocks = null;
	private StringBuilder returnString = new StringBuilder();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMMM yyyy HH:mm:ss z");
	private Date date;				// date in the format of "dateFormat


/**
 * 
 * Default constructors for this class
 * 
 */
	public Blocks() {
		returnString.append("Empty Block");
	}
/**
 * 
 * @param number
 */
	public Blocks(int number) {
		this.number = number;
		returnString.append("Block Number: " + number);
	}
 /**
  * 
  * @param number
  * @param miner
  */
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
		returnString.append("Block Number: " + number + " Miner Address: " + miner);
	}
	/**
	 * 
	 * @param number
	 * @param miner
	 * @param timestamp
	 * @param transactionCount
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public Blocks(int number, String miner, long timestamp, int transactionCount) throws NumberFormatException, IOException {
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactionCount = transactionCount;
		readTransactions("ethereumtransactions1.csv");
		returnString.append("Block Number: " + number + " Miner Address: " + miner);
	}
/** 
 * 
 * @return number
 */
	public int getNumber() {
		return this.number;
	}
/** 
 * 
 * @return miner
 */
	public String getMiner() {
		return this.miner;
	}
	/**
	 * 
	 * @return transactionCount
	 */
	public int getTransactionCount() {
		return this.transactionCount;
	}
	/**
	 * 
	 * @return arraylist of blocks
	 */
	public static ArrayList<Blocks> getBlocks() {
		return new ArrayList<>(blocks);
	}

	public ArrayList<Transaction> getTransactions()
	{ 
	ArrayList<Transaction> copy = new ArrayList<>();
	copy.addAll(transactions);
	return copy;
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 */
	public static void calUniqMiners() throws FileNotFoundException, IOException {	
		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
		}
		
	
		ArrayList<String> uniqMiners = new ArrayList<String>();
		ArrayList<Integer> uniqMinersFreq = new ArrayList<Integer>();
		String miner;

		for (int i = 0; i < blocks.size(); ++i)
		{
			miner = blocks.get(i).getMiner();

			if (!(uniqMiners.contains(miner)))
			{

				uniqMiners.add(miner);
				uniqMinersFreq.add(1);
			}
	
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


		System.out.println("Number of unique Miners: " + uniqMiners.size() + "\n");
		System.out.println("Each unique Miner and its frequency:");
		for (int i = 0; i < uniqMiners.size(); ++i)
		{
			System.out.println("Miner Address: " + uniqMiners.get(i) + "\nMiner Frequency: " + uniqMinersFreq.get(i) + "\n");
		}
	}

	public static int blockDiff(Blocks minuend, Blocks subtrahend) {
		int diff = minuend.getNumber() - subtrahend.getNumber();

		return diff;
	}


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
/**
 * @return string
 */
	public String toString() {
		return returnString.toString();
	}

    /**
     * 
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static ArrayList<Blocks> readFile(String filename) throws FileNotFoundException, IOException {
	
		File file = new File(filename);

		
		Scanner fileScanner = new Scanner(file);

	
		ArrayList<Blocks> b = new ArrayList<Blocks>();

		
		String[] fileData = null;


		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			fileData = line.trim().split(",");
			b.add(new Blocks(Integer.parseInt(fileData[0]), fileData[9], Integer.parseInt(fileData[16]), Integer.parseInt(fileData[17])));
		}

		fileScanner.close();

		blocks = new ArrayList<>(b);

		return b;
	}
	
	public static void sortBlocksByNumber() throws FileNotFoundException, IOException {
		if (blocks==null) {
			readFile("ethereumP1.txt");
		}
		
		Collections.sort(blocks);
	}
/**
 * @Override
 * @return compareTo int
 */
	@Override
	public int compareTo(Blocks b) {
		Integer x = number;
		Integer y = b.getNumber();
		return x.compareTo(y);
	}
	
/**
 * 
 * @return string of date
 */
	public String getDate() {

		date = new Date(timestamp * 1000);
		dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
		return dateFormat.format(date);
	}
	
	public static void timeDiff(Blocks first, Blocks second) {

		if ((first == null) || (second == null)) {
			System.out.println("A given Block is null.");
		}
		else {
			String hours = " hours, ";
			String minutes = " minutes, and ";
			String seconds = " seconds.";
	
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
	
 /**
  * 
  * @param first
  * @param second
  * @return
  * @throws FileNotFoundException
  * @throws IOException
  */
	public static int transactionDiff(Blocks first, Blocks second) throws FileNotFoundException, IOException {
		

		if (blocks == null)
		{
			readFile("ethereumP1data.txt");
			sortBlocksByNumber();
		}

		if ((first == null) || (second == null)) {
			return -1;
		}
		
		int indexA = -1;		
		int indexB = -1;		
		int count = 0;		
		
		

		for (int i = 0; i < blocks.size(); ++i) {
			if (first.getNumber() == blocks.get(i).getNumber()) {
				indexA = i;
			}
			if (second.getNumber() == blocks.get(i).getNumber()) {
				indexB = i;
			}
		}
		
	
		if ((indexA < 0) || (indexB < 0)) {
			return -1;
		}

		if (indexA >= indexB) {
			return -1;
		}
		
		
		for (int i = indexA+1; i < indexB; ++i) {
			count += blocks.get(i).getTransactionCount();
		}
		
		return count;
	}
	/**
	 * 
	 * @param filename
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public void readTransactions(String filename) throws NullPointerException, IOException
	{ 
	BufferedReader br = new BufferedReader(new FileReader(filename));
	String line;
	Set<Transaction> temp = new HashSet<>();
	while ((line = br.readLine()) != null)
	{ 
		String[] lines = line.split(",");
		int nums = Integer.parseInt(lines[3]);
		int index = Integer.parseInt(lines[4]);
		int gasLimit = Integer.parseInt(lines[8]);
		BigDecimal happy = new BigDecimal(lines[9]);
		long gasPrice = happy.longValue();
     	String fromAdr = lines[5];
		String toAdr = lines[6];
		if (nums == this.number)
		{
		temp.add(new Transaction(number, index, gasLimit, gasPrice, fromAdr, toAdr));
		}
	}
    ArrayList<Transaction> noDupes = new ArrayList<>(temp);
 	transactions = new ArrayList<>(noDupes);
 	Collections.sort(transactions);
	br.close();
	}
	public double avgTransactionCost()
	{ 
	double totalAmount = 0;
	for (int i = 0; i < transactions.size(); ++i)
	{ 
		totalAmount += transactions.get(i).transactionCost();
	}
	return totalAmount / transactions.size();	
	}
	/**
	 * find unique transactions
	 */
	public void uniqFromTo()
	{ 
		HashMap<String, ArrayList<String>> pairs = new HashMap<>();
		ArrayList<String> fromAdr = new ArrayList<>();
		ArrayList<String> toAdr = new ArrayList<>();
		HashMap<String, Double> sumMap = new HashMap<>();
		String from = "";
		double mapSum = 0.0;
		for (int i = 0; i < transactions.size(); ++i)
		{ 
			from = transactions.get(i).getFromAddress();
			if (!(fromAdr.contains(from)))
			{ 
				fromAdr.add(from);
			}
		}
		for (int j = 0; j < transactions.size(); ++j)
		{ 
			from = transactions.get(j).getFromAddress();
			if (sumMap.containsKey(from) == false)
			{ 
				mapSum += transactions.get(j).transactionCost();
				for (int k = j + 1; k < transactions.size(); ++k)
				{ 
					if (from.equals(transactions.get(k).getFromAddress()))
					{ 
						mapSum += transactions.get(k).transactionCost();
					}
				}
				sumMap.put(from, mapSum);
				mapSum = 0.0;
			}
		}
		for (int i = 0; i < transactions.size(); ++i)
		{ 
			from = transactions.get(i).getFromAddress();
			if (pairs.containsKey(from) == false)
			{ 
				toAdr.add(transactions.get(i).getToAddress());
				pairs.put(from, toAdr);
			}
			else if (pairs.containsKey(from))
			{ 
				pairs.get(from).add(transactions.get(i).getToAddress());
			}
			toAdr = new ArrayList<>();
		}
		System.out.println("Each transaction by from address for Block " + this.number + "\n");
		for (int i = 0; i < fromAdr.size(); ++i)
		{ 
			from = fromAdr.get(i);
			System.out.println("From " + from);
			for (int l = 0; l < pairs.get(from).size(); ++l)
			{ 
				System.out.println(" -> " + pairs.get(from).get(l));
			}
			System.out.printf("Total cost of transactions: %.8f ETH\n", + sumMap.get(from).doubleValue());
		    System.out.println();
		}
	}
}
