
public abstract class Shape implements Comparable<Shape> 
{
private int id;
private static int nextID = 0;

protected Shape() 
{
this.id = nextID;
nextID++;
}
public int getID()
{ 
	return id;
}


public abstract double getPerimeter();

public abstract double getArea();

@Override
public int compareTo(Shape other)
{ 
	
	int number = getClass().getName().compareTo(other.getClass().getName());
	if (number == 0)
	{ 
		number = Double.compare(getPerimeter(), other.getPerimeter());
		if (number == 0)
		{ 
			number = Double.compare(getArea(), other.getArea());
		}
	}
	return number;
}

	@Override
	public String toString() 
	{
		return "<"
				+ getClass().getName()
				+ ", ID: " + id
				+ ", PERIMETER: " + String.format("%.1f", getPerimeter())
				+ ", AREA: " + String.format("%.1f", getArea())
				+ ">";
	}
}