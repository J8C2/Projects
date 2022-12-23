import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck 
{
private List<Card> cards;

public Deck()
{ 
	cards = new ArrayList<>();
	Rank[] ranks = Rank.values();
	Suit[] suits = Suit.values();
	for (Suit s : suits)
	{ 
		for (Rank r : ranks)
		{ 
			Card c = new Card(r, s);
			cards.add(c);
		}
	} 
}

public int size()
{ 
	return cards.size();
}

public String toString()
{ 
	return cards.toString();
}

public Card draw()
{ 
	if (cards.size() == 0)
	{ 
		return null;
	}
	Card c = cards.get(0);
	cards.remove(0);
	return c;
}

public List<Card> draw(int count)
{
	List<Card> c;
	if (count < 0)
	{ 
		c = new ArrayList<>();
		return c;
	}
	if (count > cards.size())
	{ 
		c = new ArrayList<>(cards);
		cards.clear();
		return c;
	}
	c = new ArrayList<>();
	for (int i = 0; i < count; ++i)
	{ 
		c.add(cards.get(i));
	}
	cards.removeAll(c);
	return c;
}

public void shuffle()
{ 
	Collections.shuffle(cards);
}

public List<Card> getCardsByRank(Rank rank)
{
	List<Card> c = new ArrayList<>();
	for (int i = 0; i < cards.size(); ++i)
	{ 
		if (rank == cards.get(i).getRank())
		{ 
			c.add(cards.get(i));
		}
	}
	return c;
}
}
