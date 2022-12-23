import java.util.Objects;

public class Card 
{
private Rank rank;
private Suit suit;

public Card(Rank rank, Suit suit) throws NullPointerException
{ 
	if (rank == null || suit == null)
	{ 
		throw new NullPointerException();
	}
	this.rank = rank;
	this.suit = suit;
}

public int compareTo(Card card)
{
	int number = suit.compareTo(card.suit);
	if (number == 0)
	{ 
		 number = rank.compareTo(card.rank);
	}
	return number; 
}

@Override
public boolean equals(Object obj)
{ 
	if (obj instanceof Card != true)
	{ 
		return false;
	}
	Card cards = (Card) obj;
	int number = compareTo(cards);
	if (number == 0)
	{
		return true;
	}
	else
	{ 
	return false;
	}
	
}
public Rank getRank()
{ 
	return rank;
}
public Suit getSuit()
{ 
	return suit;
}

@Override
public int hashCode()
{ 
	return Objects.hash(rank, suit);
}

public String toString()
{ 
	return rank.toString() + suit.toString();
}
}
