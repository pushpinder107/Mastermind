package mastermind;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	private static final int EASY = 1;
	private static final int MEDIUM = 2;
	private static final int HARD = 3;
	private static ArrayList<String> list;

	public static void main(String args[]) {
		while (true) {
			play();
		}
	}

	public static void play() {
		int wordLength = getDifficulty();
		String hiddenWord = getRandomWord(wordLength);
		list = allPossibleWords(wordLength);
		boolean userWon = false;
		boolean computerWon = false;
		while (!(userWon || computerWon)) {
			System.out.println("Size" + list.size());
			userWon = userGuesses(hiddenWord);
			if (userWon) {
				System.out.println("User is victorious");
				return;
			}
			computerWon = computerGuesses();
			if (computerWon) {
				System.out.println("Computer won");
				System.out.println("User needed to guess : " + hiddenWord);
				return;
			}
		}
	}

	public static boolean userGuesses(String hiddenWord) {
		System.out.println("USER'S TURN ");
		// System.out.println("Secret:" + hiddenWord);
		String guess = getStringFromUser();
		if (guess.equals(hiddenWord)) {
			System.out.println("Your guess is correct!");
			return true;
		}
		int matchCount = getMatchCount(guess, hiddenWord);
		System.out.println("You got " + matchCount + " letters correct!");
		return false;
	}

	private static boolean computerGuesses() {
		System.out.println("COMPUTERS TURN");

		Scanner input = new Scanner(System.in);
		String randomGuess = getRandomFromList(list);
		System.out.println("I guess : " + randomGuess);
		System.out.println("Is my guess correct?(y/n) :");
		String isCorrect = input.next();
		if (isCorrect.equalsIgnoreCase("Y")) {
			return true;
		}
		System.out.println("How many matches : ");
		int matchCount = input.nextInt();
		list = (ArrayList<String>) trimList(randomGuess, matchCount, list);
		randomGuess = getRandomFromList(list);
		return false;
	}

	private static int getDifficulty() {
		System.out.println("Please choose a difficulty -> \n1 for easy \n2 for medium\n3 for hard \n : ");
		Scanner input = new Scanner(System.in);
		int difficultyChoice = input.nextInt();
		int wordLength = getWordLength(difficultyChoice);
		return wordLength;
	}

	private static int getWordLength(int choice) {
		if (choice == EASY) {
			return 4;
		} else if (choice == MEDIUM) {
			return 5;
		}
		return 6;
	}

	private static String getStringFromUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string :");
		return input.next();
	}

	private static int getRandomNumber(int limit) {
		Random rand = new Random();
		return rand.nextInt(limit);
	}

	private static int getMatchCount(String guess, String hidden) {
		char[] array = guess.toCharArray();
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (hidden.contains(String.valueOf(array[i]))) {
				count++;
				hidden = hidden.replaceAll(String.valueOf(array[i]), "");
			}
		}
		return count;
	}

	private static String getRandomWord(int size) {
		List<String> words = allPossibleWords(size);
		int random = getRandomNumber(words.size());
		return words.get(random);

	}

	private static List<String> trimList(String word, int matchCount, ArrayList<String> previousList) {
		List<String> newList = new ArrayList<>();
		if (matchCount == 0) {
			for (String str : previousList) {
				if (!(getMatchCount(word, str) == 0)) {
					newList.add(str);
				}
			}
			return newList;
		}

		for (String str : previousList) {
			if (getMatchCount(str, word) == matchCount) {
				newList.add(str);
			}
		}

		return newList;
	}

	private static ArrayList<String> allPossibleWords(int length) {
		String path = "C:\\Users\\pushpisingh\\Desktop\\sowpods.txt";
		ArrayList<String> words = new ArrayList<String>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(path);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.length() == length && !containsRepeatingLetters(line)) {
					words.add(line);
				}
			}
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}

		return words;
	}

	private static String getRandomFromList(List<String> list) {
		int random = getRandomNumber(list.size());
		return list.get(random);
	}

	private static boolean containsRepeatingLetters(String word) {
		char[] array = word.toUpperCase().toCharArray();
		int[] countArray = new int[26];
		for (int i = 0; i < 26; i++) {
			countArray[i] = 0;
		}
		for (int i = 0; i < array.length; i++) {
			countArray[array[i] - 'A']++;
		}
		for (int i = 0; i < 26; i++) {
			if (countArray[i] > 1) {
				return true;
			}
		}
		return false;
	}

}
