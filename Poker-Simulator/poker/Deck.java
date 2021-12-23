package poker;

/**
 * This class creates a Deck of cards which will be used to play Poker. Each
 * deck can shuffle, cut, and deal cards. It also can return the number of
 * cards in the Deck as well as find a card at a given index. 
 * 
 * @author Ansh Viswanathan
 *
 */

public class Deck {
	
	private Card[] cards; //array that contains all the Card objects in the deck
	
	/**
	 * Standard constructor.
	 * Declares and instantiates a standard deck of 52 cards, with Ace through 
	 * King for all suits (Spades, Diamonds, Clubs, and Hearts). 
	 */
	public Deck() {
		cards = new Card[52];
		
		/*
		 * Traverses through each one of suits, with each index.
		 * representing a different suit (0 = spades, 1 = diamonds, 2 = clubs, 
		 * and 3 = hearts). 
		 */
		for (int suit = 0; suit<=3; suit++) {
			/*
			 * Traverses through each one of the cards, starting with Ace
			 * (value = 1) through King (value = 13). The int position 
			 * represents the index in the card array (from 0 to 52) that each 
			 * card should go to. At each position, the Card instantiated 
			 * goes in order from Ace to King (1 to 13), in the order of the
			 * suits (Spades, Diamonds, Clubs, and Hearts).
			 */
			for (int value = 1; value<=13; value++) {
				int position = 13*suit+value-1;
				cards[position] = new Card(value, suit);
			}
		}
	}

	/**
	 * Copy constructor.
	 * @param other represents the Deck object that is being copied.
	 */
	public Deck(Deck other) {
		cards = new Card[other.getNumCards()];
		
		/*
		 * Traverses through each position of the other Deck, instantiating 
		 * every card in the copied cards array from the card at the same 
		 * position in the cards array from the other Deck.
		 */
		for (int position = 0; position<other.getNumCards(); position++) {
			this.cards[position] = other.cards[position];
		}
	}
	
	/**
	 * "Getter" for Card in Deck.
	 * @param position is the index of the Card that is being called for
	 * @return the Card in the cards array at the index of the int position.
	 */
	public Card getCardAt(int position) {
		return cards[position];
	}

	/**
	 * Finds size of the Deck.
	 * @return length of the cards array of the object calling the method.
	 */
	public int getNumCards() {
		return cards.length;
	}

	/**
	 * Shuffles cards array
	 */
	public void shuffle() {
		Card[] bottomPack = new Card[cards.length/2]; 
		Card[] topPack = new Card[cards.length-bottomPack.length];
		
		/*
		 * Traverses the cards array from the start to one less than the index
		 * representing the length of the topPack array, instantiating every 
		 * Card from the cards array into the topPack array at the same index.
		 */
		for(int index = 0; index<topPack.length; index++) {
			topPack[index] = cards[index];
		}
		
		/*
		 * Traverse the cards array from the the index representing the length
		 * of the topPack to the end of the array. Each of these values gets
		 * instantiated as the Cards of the bottomPack, starting at index 0 
		 * till one less than the index representing the lenght of the 
		 * bottomPack.
		 */
		for (int index = 0; index<bottomPack.length; index++) {
			bottomPack[index] = cards[topPack.length+index];
		}
		
		int topIndex = 0;
		int botIndex = 0;
		/*
		 * This array traverses through the cards array. For every even index, 
		 * starting with the first card, it replaces the card at that index 
		 * in the cards array with a card from the topPack array in order. 
		 * Similarly, every odd card in the cards array is replaced with a card
		 * from the bottomPack array in order. 
		 */
		for (int index = 0; index<cards.length; index++) {
			if (index%2 == 0) {
				cards[index] = topPack[topIndex];
				topIndex++;
			} else {
				cards[index] = bottomPack[botIndex];
				botIndex++;
			}
		}
	}

	/**
	 * "Cuts" the deck. Takes a set amount of cards from the top of the deck and 
	 * puts it at the bottom of the Deck.
	 * @param position is the index at which the "cut" happens, where every card
	 * before that gets put at the bottom of the Deck.
	 */
	public void cut(int position) {
		Card[] cutCards = new Card[position];
		
		/*
		 * Traverses through the cards array from index 0 to one less than the 
		 * int position. Every card from these indices become the cards in the 
		 * cutCards array. 
		 */
		for (int index = 0; index<position; index++) {
			cutCards[index] = cards[index];
		}
		
		/*
		 * Traverses through the cards array from index position to the end of  
		 * the array. Every card from these indices get put back at the 
		 * beginning of the cards array (from indices 0 to one less than 
		 * cards.length-position, which represents all the cards are not cut 
		 * that get put at the top of the Deck).
		 */
		for (int index = 0; index<cards.length-position; index++) {
			cards[index] = cards[position+index];
		}

		/*
		 * Traverses through the cards array from index cards.length-position
		 * (where the the cards that weren't cut end) till the end of the array,
		 * instantiating values in the cards array from the cutCards array until
		 * all the values from the cutCards array are instantiated as the last 
		 * values of the cards array.
		 */
		for(int index = 0; index<position; index++) {
			cards[cards.length-position+index] = cutCards[index];
		}
		
	}

	/**
	 * "Deals" cards, creating a new Deck of the cards dealt and making the 
	 * original cards array respectively smaller.
	 * @param numCards is the number of cards that are dealt.
	 * @return the new Deck object with all the cards that are dealt.
	 */
	public Card[] deal(int numCards) {
		Card[] cardsDealt = new Card[numCards];
		Card[] smaller = new Card[cards.length-numCards];
		
		/*
		 * Traverses from the beginning of the cards array till one less than
		 * the index numCards, instantiating that all the cards between those
		 * two indices in the cards array into the new array cardsDealt.
		 */
		for (int index = 0; index<numCards; index++) {
			cardsDealt[index] = cards[index];
		}
		
		/*
		 * Traverses through the cards array from the index numCards till the 
		 * end, instantiating all the values into the array smaller, which will
		 * then be used to represent the new cards array.
		 */
		for (int index = 0; index<cards.length-numCards; index++) {
			smaller[index] = cards[numCards+index];
		}
		cards = smaller;
		
		return cardsDealt;
	}
	
	
}
