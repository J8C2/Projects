
public class IsoscelesTrapezoid extends Polygon
{
	private double top;
	private double base;
	private double leg;
	private double area;
	private double triangleBase;
	private double height;
	
	public IsoscelesTrapezoid(double top, double base, double leg)
	{ 
	    super(top, base, leg, leg);
		this.top = top;
		this.base = base;
		this.leg = leg;
		double number = (base - top) / 2.0;
		this.height = Math.sqrt((leg * leg) - (number * number));
		this.area = (top + base) * (height / 2.0);
		this.triangleBase = base;
	}
	public double getTop()
	{ 
		return top;
	}
	public double getBase()
	{ 
		return base;
	}
	public double getLeg()
	{ 
		return leg;
	}
	public double getArea()
	{ 
		return area;
	}
	public Rectangle getCenterRectangle()
	{ 
		Rectangle x;
		if (top < base)
		{
		 x = new Rectangle(top, height);
		}
		else
		{
		x = new Rectangle(base, height);
		}
		
		return x;
	}
	
}
