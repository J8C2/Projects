import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Playlist 
{
	
	private ArrayList<Song> songs;
	
	public Playlist() 
	{
		songs = new ArrayList<>();
	}
	public Playlist(String filename) throws IOException 
	{ 
		this();
		addSongs(filename);
	}
	
	public int getNumSongs() 
	{
		return songs.size();
	}
	
	public Song getSong(int index) 
	{
		if (index < 0 || index >= getNumSongs()) 
		{
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() 
	{
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) 
	{
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) 
	{
		if (song == null || index < 0 || index > songs.size())
		{
			return false; 
		}
		songs.add(index, song);
		return true;
	}
	public int addSongs(String filename) throws IOException
	{ 
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		int numSongs = 0;

		while ((line = br.readLine()) != null)
		{ 
			Song lines = new Song(line);
			songs.add(lines);
			++numSongs;
		}
		br.close();
		return numSongs;
	}
	public int addSongs(Playlist playlist) 
	{
		int count = 0;
		if (playlist == null || songs.size() == 0)
		{ 
			return 0;
		}
	    for (Song s : playlist.getSongs())
		{ 
			this.songs.add(s);
			++count;
		}
		return count;
	}
	
	public Song removeSong() 
	{
		return removeSong(getNumSongs()-1);
	}
	
	public Song removeSong(int index) 
	{
		if (index < 0 || index > songs.size()-1)
		{ 
			return null;
		}
		Song item = songs.get(index);
		songs.remove(index);
		return item;
	}
	public void saveSongs(String filename) throws IOException
	{ 
		
		FileWriter fr = new FileWriter(filename);
		BufferedWriter br = new BufferedWriter(fr);
	    
		for (Song s : songs)
		{ 
			br.write(s.toString() + "\n");
		}
		br.close();
		
	}
	
	public String toString()
	{ 
		if (songs.size() == 0)
		{ 
			return "";
		}
		if (songs.size() == 1)
		{ 
			String builder = songs.get(0).toString();
			return builder;
		}
		
		StringJoiner sj = new StringJoiner("");
		for (int i = 0; i < songs.size(); ++i)
		{ 
			String builder = songs.get(i).toString();
			sj.add(builder);
			if (i != songs.size()-1)
			{ 
				sj.add(System.lineSeparator());
			}
		}
		String result = sj.toString();
		return result;
	}
	
	public int[] getTotalTime()
	{ 
		int seconds = 0;
		int minutes = 0;
		int hours = 0;
	
		for (Song i : songs)
		{ 
			
			int[] times = i.getTime();
			
			if (times.length == 3)
			{ 
				seconds += times[0];
				minutes += times[1];
				hours += times[2];
			}
			if (times.length == 2)
			{ 
				seconds += times[0]; 
				minutes += times[1];
			}
			if (times.length == 1)
			{ 
				seconds += times[0];
			}
			
			if (seconds > 59)
			{ 
				minutes += (seconds / 60);
				seconds = seconds % 60;	
			}
			
			if (minutes > 59)
			{ 
				hours += (minutes / 60);
				minutes = minutes % 60;
			}
		}
		int totalTime[];
		if (hours == 0 && minutes == 0)
		{ 
			int[] time = {seconds};
			totalTime = time;
		}
		else if (hours == 0)
		{ 
			int time[] = {seconds, minutes};
			totalTime = time;
		}
		else 
		{ 
			int time[] = {seconds, minutes, hours};
			totalTime = time;
		}
			return totalTime;
	}
}
