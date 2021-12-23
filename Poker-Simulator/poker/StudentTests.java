package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	/* Use the @Test annotation for JUnit 4 
	 * [This is just an example, please erase
	 * it and write some real tests.]    */
	@Test
	public void testConstructor() {
		boolean rightCard = false;;
		Deck a = new Deck();
		int position = 0;
		
		for(int suit = 0; suit<=3; suit++) {
			for (int value = 1; value<=13; value++, position++) {
				if (a.getCardAt(position).getSuit() == suit && 
						a.getCardAt(position).getValue() == value) {
					rightCard = true;
				} else {
					rightCard = false;
				}
			}
		}
		
		assertTrue(rightCard);
	}
	
	@Test
	public void testCopyConstructor() {
		boolean sameCards = false;
		Deck a = new Deck();
		Deck b = new Deck(a);
		
		for(int index = 0; index<52; index++) {
			if (a.getCardAt(index).toString().equals
					(b.getCardAt(index).toString())) {
				sameCards = true;
			} else {
				sameCards = false;
			}
		}
		assertTrue(a.getNumCards() == b.getNumCards());
		assertTrue(sameCards);
		
		a.shuffle();
		a.cut(4);
		a.shuffle();
		boolean sameCardsTwo = false;
		for(int index = 0; index<52; index++) {
			if (a.getCardAt(index).toString().equals
					(b.getCardAt(index).toString())) {
				sameCardsTwo = true;
			} else {
				sameCardsTwo = false;
			}
		}
		assertFalse(sameCardsTwo);
	}
	
	@Test
	public void testGetCardAt() {
		Deck a = new Deck();
		
		assertTrue(a.getCardAt(20).getSuit() == 1);
		assertTrue(a.getCardAt(20).getValue() == 8);
	}
	
	@Test
	public void testGetNumCards() {
		Deck a = new Deck();
		
		assertTrue(a.getNumCards() == 52);
	}
	
	@Test
	public void testShuffle() {
		Deck a = new Deck();
		a.shuffle();

		boolean blackSuits = false;
		for (int index = 0; index<a.getNumCards()/2; index++) {
			int suit = a.getCardAt(index).getSuit();
			if (suit%2 == 0) {
				blackSuits = true;
			} else {
				blackSuits = false;
			}
		}
		
		boolean redSuits = false;
		for (int index = a.getNumCards()/2; index<a.getNumCards(); index++) {
			int suit = a.getCardAt(index).getSuit();
			if (suit%2 == 1) {
				redSuits = true;
			} else {
				redSuits = false;
			}
		}
		
		boolean numbers = false;
		for(int index = 0; index<a.getNumCards()-1; index+=2) {
			int first = a.getCardAt(index).getValue();
			int second = a.getCardAt(index+1).getValue();
			if(first==second) {
				numbers = true;
			} else {
				numbers = false;
			}
		}
		
		assertTrue(blackSuits);
		assertTrue(redSuits);
		assertTrue(numbers);
	}
	
	@Test
	public void testCut() {
		Deck a = new Deck();
		Deck b = new Deck();
		a.cut(5);
		boolean trueCut = false;
		
		for (int index = 0; index<a.getNumCards()-5; index++) {
			if (a.getCardAt(index).toString().equals
					(b.getCardAt(index+5).toString())) {
				trueCut = true;
			} else {
				trueCut = false;
			}
		}
		
		for (int index = 0; index<5; index++) {
			
			if (a.getCardAt(index+a.getNumCards()-5).toString().equals
					(b.getCardAt(index).toString())) {
				trueCut = true;
			} else {
				trueCut = false;
			}
		}
		
		assertTrue(trueCut);
	}
	
	@Test
	public void testDeal() {
		Deck a = new Deck();
		Card[] dealt = a.deal(10);
		
		assertTrue(a.getNumCards() == 42);
		assertTrue(dealt.length == 10);
	}
	
	@Test
	public void testHasPair() {
		Card[] hand = new Card[5];
		hand[0] = new Card(7, 1);
		hand[1] = new Card(5, 0);
		hand[2] = new Card(12, 3);
		hand[3] = new Card(7, 3);
		hand[4] = new Card(11, 0);
		assertTrue(PokerHandEvaluator.hasPair(hand));
	}
	
	@Test
	public void testHasTwoPair() {
		Card[] hand = new Card[5];
		hand[0] = new Card(13, 2);
		hand[1] = new Card(5, 1);
		hand[2] = new Card(5, 3);
		hand[3] = new Card(1, 0);
		hand[4] = new Card(13, 0);
		assertTrue(PokerHandEvaluator.hasTwoPair(hand));
	}
	
	@Test
	public void testHasThreeOfAKind() {
		Card[] hand = new Card[5];
		hand[0] = new Card(8, 3);
		hand[1] = new Card(11, 0);
		hand[2] = new Card(2, 0);
		hand[3] = new Card(8, 0);
		hand[4] = new Card(8, 1);
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(hand));
	}
	
	@Test
	public void testHasStraight() {
		Card[] handOne = new Card[5];
		handOne[0] = new Card(11, 1);
		handOne[1] = new Card(8, 1);
		handOne[2] = new Card(9, 3);
		handOne[3] = new Card(12, 2);
		handOne[4] = new Card(10, 2);
		
		Card[] handTwo = new Card[5];
		handTwo[0] = new Card(10, 2);
		handTwo[1] = new Card(11, 0);
		handTwo[2] = new Card(12, 3);
		handTwo[3] = new Card(13, 0);
		handTwo[4] = new Card(1, 3);
		
		Card[] handThree = new Card[5];
		handThree[0] = new Card(2, 1);
		handThree[1] = new Card(1, 2);
		handThree[2] = new Card(3, 3);
		handThree[3] = new Card(4, 1);
		handThree[4] = new Card(5, 2);
		
		Card[] handFour = new Card[5];
		handFour[0] = new Card(2, 1);
		handFour[1] = new Card(1, 2);
		handFour[2] = new Card(3, 3);
		handFour[3] = new Card(1, 1);
		handFour[4] = new Card(4, 2);
		
		Card[] handFive = new Card[5];
		handFive[0] = new Card(2, 1);
		handFive[1] = new Card(1, 2);
		handFive[2] = new Card(13, 3);
		handFive[3] = new Card(12, 1);
		handFive[4] = new Card(11, 2);
		
		assertTrue(PokerHandEvaluator.hasStraight(handOne));
		assertTrue(PokerHandEvaluator.hasStraight(handTwo));
		assertTrue(PokerHandEvaluator.hasStraight(handThree));
		assertFalse(PokerHandEvaluator.hasStraight(handFour));
		assertFalse(PokerHandEvaluator.hasStraight(handFive));
		
	}
	
	@Test
	public void testHasFlush() {
		Card[] hand = new Card[5];
		hand[0] = new Card(6, 2);
		hand[1] = new Card(1, 2);
		hand[2] = new Card(8, 2);
		hand[3] = new Card(12, 2);
		hand[4] = new Card(2, 2);
		assertTrue(PokerHandEvaluator.hasFlush(hand));		
	}
	
	@Test
	public void testHasFullHouse() {
		Card[] hand = new Card[5];
		hand[0] = new Card(7, 0);
		hand[1] = new Card(7, 3);
		hand[2] = new Card(2, 0);
		hand[3] = new Card(7, 1);
		hand[4] = new Card(7, 2);
		assertFalse(PokerHandEvaluator.hasFullHouse(hand));
		
		Card[] handTwo = new Card[5];
		handTwo[0] = new Card(8, 3);
		handTwo[1] = new Card(11, 0);
		handTwo[2] = new Card(11, 0);
		handTwo[3] = new Card(11, 0);
		handTwo[4] = new Card(8, 1);
		assertTrue(PokerHandEvaluator.hasFullHouse(handTwo));
		
		Card[] handThree = new Card[5];
		handThree[0] = new Card(8, 3);
		handThree[1] = new Card(11, 0);
		handThree[2] = new Card(11, 0);
		handThree[3] = new Card(8, 0);
		handThree[4] = new Card(8, 1);
		assertTrue(PokerHandEvaluator.hasFullHouse(handThree));
		
		Card[] handFour = new Card[5];
		handFour[0] = new Card(8, 3);
		handFour[1] = new Card(11, 0);
		handFour[2] = new Card(11, 0);
		handFour[3] = new Card(8, 0);
		handFour[4] = new Card(1, 1);
		assertFalse(PokerHandEvaluator.hasFullHouse(handFour));
	}
	
	@Test
	public void testStraightFlush() {
		Card[] hand = new Card[5];
		hand[0] = new Card(7, 1);
		hand[1] = new Card(4, 1);
		hand[2] = new Card(8, 1);
		hand[3] = new Card(6, 1);
		hand[4] = new Card(5, 1);
		assertTrue(PokerHandEvaluator.hasStraightFlush(hand));
		
		Card[] handTwo = new Card[5];
		handTwo[0] = new Card(13, 0);
		handTwo[1] = new Card(12, 0);
		handTwo[2] = new Card(11, 0);
		handTwo[3] = new Card(10, 0);
		handTwo[4] = new Card(1, 0);
		assertTrue(PokerHandEvaluator.hasStraightFlush(handTwo));
		
		Card[] handThree = new Card[5];
		handThree[0] = new Card(4, 0);
		handThree[1] = new Card(1, 0);
		handThree[2] = new Card(3, 0);
		handThree[3] = new Card(1, 0);
		handThree[4] = new Card(2, 0);
		assertFalse(PokerHandEvaluator.hasStraightFlush(handThree));

	}
	
	
}
