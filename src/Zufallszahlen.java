import java.util.Arrays;

public class Zufallszahlen {
	static int[] deck = new int[52];
	// static int[] hand = new int[5];

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

	private static void checkHand(int[] hand) {
		int zaehler = 0;
		for (int i = 0; i < (hand.length - 1); i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i] % 13 == hand[j] % 13){
					System.out.println("zaehler++" + zaehler);
					zaehler++; 
					break;
				}
			}
		}
		switch (zaehler) {
		case 1:
			System.out.println("Paar!");
			break;
		case 2:
			System.out.println("Drilling!");
			break;
		case 3:
			System.out.println("Poker!");
			break;
		default:
			System.out.println("Es ist keine besondere Kombination in Ihrer Hand.");
		}
	}

	private static void ausgabe() {
		fillDeck();
		// int[] meineHand = drawHand(deck);
//		int[] meineHand = { 1, 14, 27, 40, 41 }; //PokerKombi!
		int[] meineHand = { 1, 14, 41, 42, 27};
//		Arrays.sort(meineHand);
		System.out.println("Ihre gezogene Hand besteht aus folgenden Karten: ");
		for (int i = 0; i < meineHand.length; i++) {
			System.out.println(meineHand[i] % 13);
		}
		checkHand(meineHand);
	}

	public static void main(String[] args) {
		ausgabe();
	}

}
