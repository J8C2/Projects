
public class Triangle 
{
private double sideA, sideB, sideC; 



public static final String POLYGONSHAPE = "Triangle";
public static final double DEFAULT_SIDE = 1.0;

public Triangle()
{ 

	this.sideA = DEFAULT_SIDE;
	this.sideB = DEFAULT_SIDE;
	this.sideC = DEFAULT_SIDE;
}

public Triangle(double sideA, double sideB, double sideC)
{ 
	if (sideA > 0 && sideB > 0 && sideC > 0 && isTriangle(sideA, sideB, sideC))
	{ 
		this.sideA = sideA; 
		this.sideB = sideB; 
		this.sideC = sideC;
	}
	else 
	{ 
		this.sideA = DEFAULT_SIDE;
		this.sideB = DEFAULT_SIDE;
		this.sideC = DEFAULT_SIDE;
	}
}
public Triangle (double[] sides)
{ 
	if (isTriangle(sides))
	{ 
	   sideA = sides[0]; 
	   sideB = sides[1]; 
	   sideC = sides[2];
	}
	else 
	{ 
	   sideA = DEFAULT_SIDE;
	   sideB = DEFAULT_SIDE; 
	   sideC = DEFAULT_SIDE;
	}
}
public Triangle (Triangle triangle)
{ 
	if (triangle != null)
	{ 
	this.sideA = triangle.sideA;
	this.sideB = triangle.sideB; 
	this.sideC = triangle.sideC;
	}
	else 
	{ 
	this.sideA = DEFAULT_SIDE;
	this.sideB = DEFAULT_SIDE;
	this.sideC = DEFAULT_SIDE;
	}
}
//Getters
public double getSideA()
{
	return sideA;
}
public double getSideB()
{ 
	return sideB;
}
public double getSideC()
{ 
	return sideC;
}
public double[] getSides()
{ 
	return new double[] {getSideA(), getSideB(), getSideC()};
}
public double getAngleA()
{ 
	return lawOfCosines(sideB, sideC, sideA);
}
public double getAngleB()
{ 
	return lawOfCosines(sideC, sideA, sideB);
}
public double getAngleC() 
{ 
	return lawOfCosines(sideA, sideB, sideC);
}
public double[] getAngles()
{ 
	return new double[] {getAngleA(), getAngleB(), getAngleC()};
}
// Setters 
public boolean setSideA(double a)
{ 
	if (isTriangle(a, this.sideB, this.sideC))
	{ 
		this.sideA = a; 
		return true;
	}
	return false;
}
public boolean setSideB(double b)
{ 
	if (isTriangle(this.sideA, b, this.sideC))
	{ 
		this.sideB = b; 
		return true;
	}
	return false;
}
public boolean setSideC(double c)
{ 
	if (isTriangle(this.sideA, this.sideB, c))
	{ 
		this.sideC = c; 
		return true;
	}
	return false;
}
public boolean setSides(double[] sides)
{ 
	if(isTriangle(sides))
	{ 
		this.sideA = sides[0];
		this.sideB = sides[1];
		this.sideC = sides[2];
		return true;
	}
	return false;
}

public String toString() 
{ 
	return String.format("%s(%.4f, %.4f, %.4f)", POLYGONSHAPE, sideA, sideB, sideC);
}

// Helpers
public static boolean isTriangle(double a, double b, double c) 
{ 
 if (a >= b + c || b >= c + a || c >= a + b || a <= 0 || b <= 0 || c <= 0)
 { 
	 return false;
 }
 else 
 { 
	 return true;
 }
}
 public static boolean isTriangle(double[] sides)
 { 
	 if (sides == null || sides.length != 3 || sides[0] <= 0 || sides[1] <= 0 || sides[2] <= 0)
	 { 
		 return false;
	 }
	 else 
	 {
	 return isTriangle(sides[0], sides[1], sides[2]);
	 }
 }
 
 public static double lawOfCosines(double a, double b, double c)
 { 
	 c = ((Math.pow(b, 2.0) + Math.pow(a, 2.0) - Math.pow(c, 2.0))/(2 * a * b)); 
	 c = Math.acos(c); 
	 c = (c * 180) /  Math.PI;
	 return c;
 }





}