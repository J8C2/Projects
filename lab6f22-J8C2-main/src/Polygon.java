
public abstract class Polygon extends Shape
{
private double perimeter;


protected Polygon(double... sides) throws IllegalArgumentException
{
    double z = 0.0;
 
	
		if (sides == null)
		{ 
			throw new IllegalArgumentException("null sides");
		}
		
		if (sides.length < 3)
		{ 
			throw new IllegalArgumentException("Invalid number of sides: " + sides.length);
		}
		for (double x : sides)	
		{
			if (x <= 0)
			{ 
				throw new IllegalArgumentException("Nonpositive side: " + x);
			}
		}
		for (double x : sides)
		{
			for (double y : sides)
			{
				z = z + y;
			}
			z = z - x;
			
			if (x >= z)
			{ 
				throw new IllegalArgumentException("Polygon inequality violated: " + x + " >= " + z);
			}
		z = 0.0;
		
		this.perimeter += x;
		}
	
}

public double getPerimeter()
{ 
	return perimeter;
}
	
	
}
