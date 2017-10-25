import java.util.Arrays;

public class Zufallszahlen {
	static int[] deck = new int[52]; //Hallo Max!

	private static void fillDeck() {
		for (int i = 0; i < 52; i++) {
			deck[i] = i + 1;
		}
	}

	public static int[] drawHand(int[] deck) {
		int deckIndex = 0;
		int[] hand = new int[5];
		int laenge = deck.length - 1;
		for (int i = 0; i < hand.length; i++) {
			deckIndex = (int) (Math.random() * (laenge - i));
			hand[i] = deck[deckIndex];
			deck[deckIndex] = deck[laenge - i];
		}
		return hand;
	}

	private static int checkPair(int[] hand) {
		int zaehler = 0;
		int paircounter = 0;
		int[] sortedHand = new int[5];
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] % 13 == 0) {
				sortedHand[i] = 13;
			} else {
				sortedHand[i] = hand[i] % 13;
			}
		}
		Arrays.sort(sortedHand);
		for (int i = 0; i < (sortedHand.length - 2); i++) {
			if (sortedHand[i] == sortedHand[i + 1] && sortedHand[i] != sortedHand[i + 2]) {
					zaehler++;
			}
			if (zaehler > 0) {
				paircounter++;
				zaehler = 0;
			}
		}
		if (paircounter == 1) {
			return 1;
		}
		if (paircounter == 2) {
			return 2;
		} else {
			return 0;
		}
	}

	private static boolean checkThreeOfAKind(int[] hand) {
		int zaehler = 0;
		int[] sortedHand = new int[5];
		for (int i = 0; i < hand.length; i++) {
			sortedHand[i] = hand[i] % 13;
		}
		Arrays.sort(sortedHand);
		for (int i = 0; i < (sortedHand.length - 2); i++) {
			if (sortedHand[i] == sortedHand[i + 1] && sortedHand[i + 1] == sortedHand[i + 2]) {
				zaehler = zaehler + 2;
			}
		}
		if (zaehler == 2) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkPoker(int[] hand) {
		int zaehler = 0;
		int[] sortedHand = new int[5];
		for (int i = 0; i < hand.length; i++) {
			sortedHand[i] = hand[i] % 13;
		}
		Arrays.sort(sortedHand);
		for (int i = 0; i < (sortedHand.length - 3); i++) {
			if (sortedHand[i] == sortedHand[i + 1] && sortedHand[i + 1] == sortedHand[i + 2]
					&& sortedHand[i + 2] == sortedHand[i + 3]) {
				return true;
				// break;
			}
		}
		return false;
	}

	private static boolean checkFullHouse(int[] hand) {
		if (checkPair(hand) == 1 && checkThreeOfAKind(hand) == true) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean checkFlush(int[] hand) {
		int zaehler1 = 0;
		int zaehler2 = 0;
		int zaehler3 = 0;
		int zaehler4 = 0;
		for (int i = 0; i < (hand.length); i++) {
			if (hand[i] <= 13) {
				zaehler1++;
			}
			if (hand[i] >= 14 && hand[i] <= 26) {
				zaehler2++;
			}
			if (hand[i] >= 27 && hand[i] <= 39) {
				zaehler3++;
			}
			if (hand[i] >= 40 && hand[i] <= 52) {
				zaehler4++;
			}
		}
		if (zaehler1 == 5 || zaehler2 == 5 || zaehler3 == 5 || zaehler4 == 5) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkStraight(int[] hand) {
		int zaehler = 0;
		int[] sortedHand = new int[5];
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] % 13 == 0) {
				sortedHand[i] = 13;
			} else {
				sortedHand[i] = hand[i] % 13;
			}
		}
		Arrays.sort(sortedHand);
		for (int i = 0; i < (sortedHand.length - 1); i++) {
			if (sortedHand[i] == (sortedHand[i + 1] - 1)) {
				zaehler++;
			}
		}
		if (zaehler == 4) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkStraightFlush(int[] hand) {
		if (checkFlush(hand) == true && checkStraight(hand) == true) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkRoyalFlush(int[] hand) {
		int[] sortedHand = new int[5];
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] % 13 == 0) {
				sortedHand[i] = 13;
			} else {
				sortedHand[i] = hand[i] % 13;
			}
		}
		Arrays.sort(sortedHand);
		if (checkStraightFlush(sortedHand) == true && sortedHand[0] == 9) {
			return true;
		} else {
			return false;
		}
	}

	private static void ausgabe() {
		int pairCounter = 0;
		int twoPairCounter = 0;
		int threeCounter = 0;
		int straightCounter = 0;
		int flushCounter = 0;
		int fullHouseCounter = 0;
		int pokerCounter = 0;
		int straightFlushCounter = 0;
		int royalFlushCounter = 0;
		for (int i = 1; i <= 1000000; i++) {
			 fillDeck();
			 int[] hand = drawHand(deck);
//			 int[] hand = {9,10,11,12,8};
			if (checkPair(hand) == 1) {
				pairCounter++;
			}
			if (checkPair(hand) == 2) {
				twoPairCounter++;
			}
			if (checkThreeOfAKind(hand) == true) {
				threeCounter++;
			}
			if (checkPoker(hand) == true) {
				pokerCounter++;
			}
			if (checkFullHouse(hand) == true) {
				fullHouseCounter++;
			}
			if (checkFlush(hand) == true) {
				flushCounter++;
			}
			if (checkStraight(hand) == true) {
				straightCounter++;
			}
			if (checkStraightFlush(hand) == true) {
				straightFlushCounter++;
			}
			if (checkRoyalFlush(hand) == true) {
				royalFlushCounter++;
			}
		}
		System.out.println("Anzahl der Simulatonen: 1 000 000");
		System.out.println("Paar:          " + pairCounter);
		System.out.println("2 Paare:       " + twoPairCounter);
		System.out.println("Drilling:      " + threeCounter);
		System.out.println("Poker:         " + pokerCounter);
		System.out.println("FullHouse:     " + fullHouseCounter);
		System.out.println("Flush:         " + flushCounter);
		System.out.println("Straight:      " + straightCounter);
		System.out.println("StraightFlush: " + straightFlushCounter);
		System.out.println("RoyalFlush:    " + (int) (royalFlushCounter / 100));
	}

	public static void main(String[] args) {
		ausgabe();
	}

}
