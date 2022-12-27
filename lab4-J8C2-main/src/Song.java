import java.util.Arrays;

public class Song 
{
	
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	public Song(String title, String artist, int[] time) 
	{
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) 
	{ 
	   String[] parts = info.split(INFO_DELIMITER);
	   String[] num = parts[IDX_TIME].split(TIME_DELIMITER);
	   time = new int[num.length];
	   
	   for (int i = 0; i < num.length; ++i)
	   { 
		   this.time[i] = Integer.parseInt(num[num.length-1-i]);
	   }
	   this.title = parts[IDX_TITLE];
	   this.artist = parts[IDX_ARTIST];  
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
		return Arrays.copyOf(time, time.length);
	}
	
	public String toString()
	{ 
		if (time.length == 1) 
		{
		return title + INFO_DELIMITER + artist + INFO_DELIMITER + time[0];
		}
		if (time.length == 2)
		{ 
		return title + INFO_DELIMITER + artist + INFO_DELIMITER + time[1] + TIME_DELIMITER + String.format("%02d", time[0]);
		}
		if (time.length == 3)
		{ 
		return title + INFO_DELIMITER + artist + INFO_DELIMITER + time[2] + TIME_DELIMITER + String.format("%02d", time[1]) + TIME_DELIMITER + String.format("%02d", time[0]);
		}
		return null;
	}
}
