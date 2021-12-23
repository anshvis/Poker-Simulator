package poker;
/**
 * This class evaluates the potential hands that one may get while playing
 * Poker. Each one of the methods analyzes a different hand that one may get
 * while playing poker (Pair, Two Pair, Three of a Kind, Straight, Flush, Full
 * House, Four of a Kind, and Straight Flush).
 * 
 * @author Ansh Viswanathan
 *
 */
public class PokerHandEvaluator {
	
	/**
	 * Evaluates if a player's "hand" (cards array) has a pair (two cards of 
	 * the same value).
	 * @param cards is the array that represents the players hand.
	 * @return true if the player does have a pair of cards, false if not.
	 */
	public static boolean hasPair(Card[] cards) {
		/*
		 * Traverses through the cards array, with the index variable 
		 * (firstCard) in this array being used as the firstCard which will be 
		 * compared with another card to see if there is a pair.
		 */
		for (int firstCard = 0; firstCard<cards.length; firstCard++) {
			/*
			 * Traverses through the cards array starting one index after the 
			 * outer loop, with the index variable (compCard) being used to 
			 * compare every Card in the array with the Card at the firstCard 
			 * index. Returns true if the Card at the firstCard index and the 
			 * Card at the compCard index are the same value.
			 */
			for (int compCard = firstCard+1; compCard<cards.length; compCard++) 
			{
				if (cards[firstCard].getValue() == cards[compCard].getValue()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Evaluates if a player's hand has as Two Pair (two pairs of 
	 * cards).
	 * @param cards is the array that represents the players hand.
	 * @return true if the player has a Two Pair, false if not.
	 */
	public static boolean hasTwoPair(Card[] cards) {
		boolean pairOne = false;
		int pairNum = 10000;
		boolean pairTwo = false;
		
		/*
		 * Traverses through the cards array, with the index variable 
		 * (firstCard) in this array being used as the firstCard which will be 
		 * compared with another card to see if there is a Two Pair.
		 */
		for (int firstCard = 0; firstCard<cards.length; firstCard++) {
			/*
			 * Traverses through the cards array starting one index after the 
			 * outer loop, with the index variable (compCard) being used to 
			 * compare every Card in the array with the Card at the firstCard 
			 * index. If it is the first time that the Card at the index 
			 * firstCard and compCard have the same value, then the boolean
			 * pairOne is set to true and the value of the firstCard is stored 
			 * as an int pairNum. If it is the second time, and the value
			 * of the firstCard is not the same as pairNum, then the boolean
			 * pairTwo is set to true. Only if pairOne and pairTwo are true for
			 * the method to return true.
			 */
			for (int compCard = firstCard+1; compCard<cards.length; compCard++) 
			{
				if (cards[firstCard].getValue() == cards[compCard].getValue()) {
					if (pairOne && cards[firstCard].getValue() != pairNum) {
						pairTwo = true;
					} else {
						pairOne = true;
						pairNum = cards[firstCard].getValue();
					}
				}
			}
		}
		if (pairOne && pairTwo) {
			return true;
		} else {
			return false;
		}	
	}
	
	/**
	 * Evaluates if a player's hand has as Three of a Kind (three
	 * cards of the same value). 
	 * @param cards is the array that represents the players hand.
	 * @return true if the hand has a Three of a Kind, false if not.
	 */
	public static boolean hasThreeOfAKind(Card[] cards) {
		int numCounter = 0;
		/*
		 * Traverses through the cards array, with the index variable 
		 * (firstCard) in this array being used as the firstCard which will be 
		 * compared with another card to see if there is a Three of a Kind.
		 */
		for (int firstCard = 0; firstCard<cards.length; firstCard++) {
			/*
			 * Traverses through the cards array starting one index after the 
			 * outer loop, with the index variable (compCard) being used to 
			 * compare every Card in the array with the Card at the firstCard 
			 * index. For every time that the Card at the firstCard and 
			 * compCard indices have the same value, the int numCounter 
			 * increments. If the Card at the firstCard index and the compCard 
			 * index have the same value twice, then the method will return
			 * true. Otherwise, the int numCounter will set to zero and the 
			 * next iteration of the outer loop will occur.
			 */
			for (int compCard = firstCard+1; compCard<cards.length; compCard++) 
			{
				if (cards[firstCard].getValue() == cards[compCard].getValue()) {
					numCounter++;
				}
			}
			if (numCounter == 2) {
				return true;
			} else {
				numCounter = 0;
			}
		}
		return false;
	}
	
	/**
	 * Evaluates if a player's hand has as a Straight (the value 
	 * of all the cards are in consecutive order).
	 * @param cards is the array that represents the players hand.
	 * @return true if the hand has a Straight, false if it not.
	 */
	public static boolean hasStraight(Card [] cards) {
		Card shortest;
		/*
		 * Traverses through the cards array, with the index variable 
		 * (firstCard) in this array being used as the firstCard which will be 
		 * compared with another card to see which one is larger.
		 */
		for (int firstCard = 0; firstCard<cards.length; firstCard++) {
			/*
			 * Traverses through the cards array starting one index after the 
			 * outer loop, with the index variable (compCard) being used to 
			 * compare every Card in the array with the Card at the firstCard 
			 * index. If they have the same value, it returns false, since in a 
			 * Straight you need consecutive numbers. If the Card at the 
			 * firstCard index has a value that is greater than that of the Card
			 * at the compCard index, then it will make the two Cards switch 
			 * indices. This will continue until all the Card are in ascending
			 * order, and then the ordered array will be used to test if the 
			 * hand has a Straight.
			 */
			for (int compCard = firstCard+1; compCard<cards.length; compCard++) 
			{
				if (cards[firstCard].getValue() == cards[compCard].getValue()) {
					return false;
				}
				if (cards[firstCard].getValue() > cards[compCard].getValue()) {
					shortest = cards[compCard];
					cards[compCard] = cards[firstCard];
					cards[firstCard] = shortest;
				}
			}
		}
		if (cards[1].getValue() == cards[0].getValue() + 1 && 
				cards[2].getValue() == cards[1].getValue() + 1 &&
				cards[3].getValue() == cards[2].getValue() + 1 &&
				cards[4].getValue() == cards[3].getValue() + 1) {
			return true;
		} else if (cards[0].getValue() == 1 && cards[1].getValue() == 10 &&
				cards[2].getValue() == 11 && cards[3].getValue() == 12 && 
				cards[4].getValue() == 13) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Evaluates if a player's hand has as a Flush (all the cards
	 * in the hand are part of the same suit).
	 * @param cards is the array that represents the players hand.
	 * @return true if the hand has a Flush, and false if it does not.
	 */
	public static boolean hasFlush(Card[] cards) {
		if (cards[0].getSuit() == cards[1].getSuit() &&
				cards[1].getSuit() == cards[2].getSuit() &&
				cards[2].getSuit() == cards[3].getSuit() &&
				cards[3].getSuit() == cards[4].getSuit()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Evaluates if the player's hand has a Full House.
	 * @param cards is the array that represents the players hand.
	 * @return true if the hand has a Full House, false if not.
	 */
	public static boolean hasFullHouse(Card[] cards) {
		if (PokerHandEvaluator.hasTwoPair(cards) && 
				PokerHandEvaluator.hasThreeOfAKind(cards)) {
			return true;
		} else {
			return false;
		}
		
		
		
	}
	
	/**
	 * Evaluates if the player's hand has a Four of a Kind (four cards of the 
	 * same value).
	 * @param cards is the array that represents the players hand.
	 * @return true if the hand has a Four of a Kind, false if not.
	 */
	public static boolean hasFourOfAKind(Card[] cards) {
		int numCounter = 0;
		/*
		 * Traverses through the cards array, with the index variable 
		 * (firstCard) in this array being used as the firstCard which will be 
		 * compared with another card to see if they have the same value.
		 */
		for (int firstCard = 0; firstCard<cards.length; firstCard++) {
			/*
			 * Traverses through the cards array starting one index after the 
			 * outer loop, with the index variable (compCard) being used to 
			 * compare every Card in the array with the Card at the firstCard 
			 * index. If they have the same value, the int numCounter is 
			 * incremented. If there are three such instances (since the 
			 * firstCard is compared with three other cards that match it), then
			 * the method returns true. If not, then numCounter is set to zero,
			 * and the outer loop iterates again.
			 */
			for (int compCard = firstCard+1; compCard<cards.length; compCard++) 
			{
				if (cards[firstCard].getValue() == cards[compCard].getValue()) {
					numCounter++;
				}
			}
			if (numCounter == 3) {
				return true;
			} else {
				numCounter = 0;
			}
		}
		return false;
	}
	
	/**
	 * Evaluates if the player's hand has a Straight Flush (the hand has five
	 * consecutive cards all in the same suit).
	 * @param cards is the array that represents the players hand.
	 * @return true if the player has a Straight Flush, false if not.
	 */
	public static boolean hasStraightFlush(Card[] cards) {
		if (PokerHandEvaluator.hasStraight(cards) && 
				PokerHandEvaluator.hasFlush(cards)) {
			return true;
		} else {
			return false;
		}
	}
}

