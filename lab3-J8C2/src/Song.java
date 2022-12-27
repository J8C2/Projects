import java.util.Arrays;

public class Song 
{
private String title, artist;
private int[] time;

public Song(String title, String artist, int[] time)
{ 
	
	this.title = title;
	this.artist = artist;
	int[] time2 = Arrays.copyOf(time, time.length);
	if (time.length == 3)
	{
		if (time[0] >= 0 && time[0] <= 59 && time[1] >= 0 && time[1] <= 59 && time[2] >= 0)
		{
	    this.time = time2;
		}
	}
	if (time.length == 2)
	{ 
		if (time[0] >= 0 && time[0] <= 59 && time[1] >= 0 && time[1] <= 59)
		{
		this.time = time2;
		}
	}
	if (time.length == 1)
	{ 
		if (time[0] >= 0 && time[0] <= 59)
		{
		this.time = time2;
		}
	}
}

public String getTitle() 
{ 
	return title;
}
public String getArtist()
{ 
	return artist;
}
public int[] getTime()
{ 

	int[] time2 = Arrays.copyOf(time, time.length);
	return time2;
}
}
