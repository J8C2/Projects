import java.util.Comparator;

public class ShapeIDComparator implements Comparator<Shape>
{
	
public int compare(Shape s1, Shape s2)
{ 
	int number = Integer.compare(s1.getID(), s2.getID());
	return number;
}



}
