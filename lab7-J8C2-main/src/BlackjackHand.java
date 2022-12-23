import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BlackjackHand 
{
	
private static final int MAX_VALUE = 21;
private List<Card> cards = new ArrayList<>();
private int value = 0;
private int numAcesAs11 = 0;
private static Map<Rank, Integer> CARD_VALUES = new HashMap<>();

static 
{
 CARD_VALUES.put(Rank.TWO, 2);
 CARD_VALUES.put(Rank.THREE, 3);
 CARD_VALUES.put(Rank.FOUR, 4);
 CARD_VALUES.put(Rank.FIVE, 5);
 CARD_VALUES.put(Rank.SIX, 6);
 CARD_VALUES.put(Rank.SEVEN, 7);
 CARD_VALUES.put(Rank.EIGHT, 8);
 CARD_VALUES.put(Rank.NINE, 9);
 CARD_VALUES.put(Rank.TEN, 10);
 CARD_VALUES.put(Rank.QUEEN, 10);
 CARD_VALUES.put(Rank.KING, 10);
 CARD_VALUES.put(Rank.JACK, 10);
 CARD_VALUES.put(Rank.ACE, 11);
}

public BlackjackHand(Card c1, Card c2)
{ 
	    if (c1.getRank().toString() == "K" || c1.getRank().toString() == "Q" || c1.getRank().toString() == "J")
	    { 
	    	CARD_VALUES.put(c1.getRank(), 10);
	    }
	    else if (c1.getRank().toString() == "A")
	    { 
	    	numAcesAs11++;
	    	CARD_VALUES.put(c1.getRank(), 11);
	    }
	    else 
	    { 
	    	CARD_VALUES.put(c1.getRank(), Integer.parseInt(c1.getRank().toString()));
	    }
		
	    if (c2.getRank().toString() == "K" || c2.getRank().toString() == "Q" || c2.getRank().toString() == "J")
	    { 
	    	CARD_VALUES.put(c2.getRank(), 10);
	    }
	    else if (c2.getRank().toString() == "A")
	    { 
	    	numAcesAs11++;
	    	CARD_VALUES.put(c2.getRank(), 11);
	    }
	    else 
	    { 
	    	CARD_VALUES.put(c2.getRank(), Integer.parseInt(c2.getRank().toString()));
	    }
		
	    cards.add(c1);
        cards.add(c2);
        value = CARD_VALUES.get(c1.getRank()) + CARD_VALUES.get(c2.getRank());
	

}

public void addCard(Card card)throws NullPointerException
{ 
	if (card == null)
	{ 
		throw new NullPointerException();
	}
	if (value < MAX_VALUE)
	{ 
		cards.add(card);
		if (card.getRank().toString() == "K" || card.getRank().toString() == "Q" || card.getRank().toString() == "J")
		{ 
			value += 10;
		}
		else if (card.getRank().toString() == "A")
		{ 
			value += 11;
			numAcesAs11 += 1;
		}
		else 
		{ 
			value += Integer.parseInt(card.getRank().toString());
		}
	}
}

public int size()
{ 
	return cards.size();
}

public static Map<Rank, Integer> getCardValues()
{
	Map<Rank, Integer> copy = new HashMap<>();
	for (Map.Entry<Rank, Integer> entry : CARD_VALUES.entrySet())
	{ 
		copy.put(entry.getKey(), entry.getValue());
	}
	return copy;
}

public List<Card> getCards()
{ 
	List<Card> copy = new ArrayList<>(cards);
	return copy;
}
public int getValue()
{ 
	while (value > MAX_VALUE && numAcesAs11 > 0)
	{ 
		numAcesAs11 -= 1;
		value -= 10;
	}
	return value;
}
public String toString()
{ 
	return cards.toString();
}
}
