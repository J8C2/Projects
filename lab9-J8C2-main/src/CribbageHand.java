import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class CribbageHand 
{

public final static  Map<Rank, Integer> CARD_VALUES = Map.ofEntries(
		Map.entry(Rank.ACE, 1),
        Map.entry(Rank.TWO, 2),
        Map.entry(Rank.THREE, 3),
        Map.entry(Rank.FOUR, 4),
        Map.entry(Rank.FIVE, 5),
        Map.entry(Rank.SIX, 6),
        Map.entry(Rank.SEVEN, 7),
        Map.entry(Rank.EIGHT, 8),
        Map.entry(Rank.NINE, 9),
        Map.entry(Rank.TEN, 10),
        Map.entry(Rank.JACK, 10),
        Map.entry(Rank.QUEEN, 10),
        Map.entry(Rank.KING, 10));
        
public final List<Card> cards;

public CribbageHand(Card c1, Card c2, Card c3, Card c4)
{ 
	
	if (c1 == null || c2 == null || c3 == null || c4 == null)
	{ 
		throw new NullPointerException();
	}
	cards = List.of(c1, c2, c3, c4);
}

public static Set<Set<Card>> powerSet(List<Card> cards)
{ 
	Set<Set<Card>> powerCards = new HashSet<>();
	if (cards.isEmpty())
	{ 
		powerCards.add(new HashSet<Card>());
		return powerCards;
	}
	List<Card> list = new ArrayList<>(cards);
	Card head = list.get(0);
	Set<Card> rest = new HashSet<>(list.subList(1, list.size()));
	List<Card> restList = new ArrayList<>(rest);
	for (Set<Card> set : powerSet(restList))
	{ 
		Set<Card> newSet = new HashSet<>();
		newSet.add(head);
		newSet.addAll(set);
		powerCards.add(newSet);
		powerCards.add(set);
	}
	return powerCards;
}

public Set<Set<Card>> fifteens(Card starter)
{ 
	Set<Set<Card>> fifteens = new HashSet<>();
	int sum = 0;
	Set<Set<Card>> cardlol = powerSet(cards);
	
	for (Set<Card> subset : cardlol)
	{ 
		for (Card card : subset)
		{ 
			sum += CARD_VALUES.get(card.getRank());
		}
		if (sum + CARD_VALUES.get(starter.getRank()) == 15)
		{ 
			subset.add(starter);
			fifteens.add(subset);
		}
		else if (sum == 15)
		fifteens.add(subset);
		sum = 0;
	}
	return fifteens;		
	
}
}
