
public class Transaction 
{
private int blockNumber;
private int index;
private int gasLimit;
private long gasPrice;
private String fromAdr;
private String toAdr;

public Transaction(int number, int index, int gasLimit, long gasPrice, String fromAdr, String toAdr)
{ 
	this.blockNumber = number;
	this.index = index;
	this.gasLimit = gasLimit;
	this.gasPrice = gasPrice;
	this.fromAdr = fromAdr;
	this.toAdr = toAdr;
}
public int getBlockNumber()
{ 
	return blockNumber;
}
public int getIndex()
{ 
	return index;	
}
public int getGasLimit()
{ 
	return gasLimit;
}
public long getGasPrice()
{ 
	return gasPrice;
}
public String getFromAddress()
{ 
	return fromAdr;
}
public String getToAddress()
{ 
	return toAdr;
}
public double transactionCost()
{ 
	double totalCost = gasLimit * gasPrice;
	return totalCost;
}
public String toString()
{ 
	return "Transaction " + index + " for Block " + blockNumber;
}
public int compareTo(Transaction t)
{
	return Integer.compare(this.index, t.index);
}
	
}
