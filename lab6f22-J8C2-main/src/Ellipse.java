
public class Ellipse extends Shape
{
private double a;
private double b;

public Ellipse(double a, double b) throws IllegalArgumentException
{
		if (a <= 0.0 || b <= 0.0)
		{ 
			throw new IllegalArgumentException("Nonpositive value(s) provided for the constructor");
		}
		if (a < b)
		{ 
			throw new IllegalArgumentException("Semi-major axis length cannot be less than semi-minor axis length");
		}
	this.a = a;
	this.b = b;
}
public double getArea()
{ 
	return a * b * Math.PI;
}
public double getPerimeter()
{ 
	return 2 * Math.PI * (Math.sqrt((a * a + b * b) / 2));
}
public double getA()
{ 
	return a;
}
public double getB()
{ 
	return b;
}
}
