import java.util.Arrays;

public class Playlist 
{
private Song[] songs;
private int numSongs; 
private static final int MIN_CAPACITY = 3;

public Playlist()
{
	songs = new Song[MIN_CAPACITY];
	
}
public Playlist(int capacity)
{ 
	if (capacity < MIN_CAPACITY)
	{ 
	songs = new Song[MIN_CAPACITY];
	}
	else
	{
	songs = new Song[capacity];
	}
}
public int getCapacity()
{ 
	return songs.length;
}
public int getNumSongs() 
{ 
	return numSongs;
}
public Song getSong(int index)
{ 
	if (index < 0 || index > numSongs-1)
	{
	return null;
	}
	return songs[index];
}
public Song[] getSongs()
{ 
	return Arrays.copyOf(songs, numSongs);
}
public boolean addSong(Song song)
{ 
	return addSong(numSongs, song);
}
public boolean addSong(int index, Song song)
{ 
	if (numSongs > songs.length-1 || song == null || index < 0 || index >= numSongs+1)
	{ 
		return false;
	}
	else
	{
    for (int i = index; i < songs.length-1; ++i)
    { 
    	songs[i+1] = songs[i];
    }
    songs[index] = song;
	++numSongs; 
	
	return true;
	}
	
	
}
public int addSongs(Playlist playlist)
{ 
	int count = 0;
	if (playlist == null || numSongs >= songs.length || numSongs == 0)
	{ 
		return 0;
	}
	for (int i = numSongs, j = 0; songs.length > i && playlist.numSongs > j; ++i)
	{ 
	songs[i] = playlist.songs[j];
	++count;
	++j;
	}
	numSongs = numSongs + count;
	return count;
}
public Song removeSong()
{ 
	return removeSong(numSongs-1);
}
public Song removeSong(int index)
{ 
	if (numSongs == 0 || songs[index] == null || index > numSongs || numSongs > songs.length || index >= songs.length || index < 0)
	{ 
		return null;
	}
	Song answer = songs[index]; 
	songs[index] = null;
	for (int i = index; i < songs.length-1 && numSongs < songs.length; ++i)
	{ 
		songs[i] = songs[i+1];
	}
    --numSongs;
    
	return answer;
} 



}

