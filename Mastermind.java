package mastermind;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

	private static final int EASY = 1;
		private static final int MEDIUM = 2;
			private static final int HARD = 3;

				public static void main(String args[]) {

						// System.out.println(allPossibleWords(5));

								startPlaying();
										// System.out.println(getMatchCount("hhelp", "lpeht"));
											}

												public static void startPlaying() {
														System.out.println("Enter 1 to guess\nEnter 2 to let me guess \n: ");
																Scanner input = new Scanner(System.in);
																		int choice = input.nextInt();
																				if (choice == 1) {
																							userGuesses();
																									} else {
																												computerGuesses();
																														}
																															}

																																public static void userGuesses() {
																																		int wordLength = getDifficulty();
																																				String hiddenWord = getRandomWord(wordLength);
																																						System.out.println(hiddenWord);
																																								boolean isGameOver = false;
																																										while (!isGameOver) {
																																													String guess = getStringFromUser();
																																																if (guess.equals(hiddenWord)) {
																																																				System.out.println("Your guess is correct!");
																																																								isGameOver = true;
																																																											}
																																																														int matchCount = getMatchCount(guess, hiddenWord);
																																																																	System.out.println("You got " + matchCount + " letters correct!");
																																																																			}
																																																																				}

																																																																					private static int getDifficulty() {
																																																																							System.out.println("Please choose a difficulty -> \n1 for easy \n2 for medium\n3 for hard \n : ");
																																																																									Scanner input = new Scanner(System.in);
																																																																											int difficultyChoice = input.nextInt();
																																																																													int wordLength = getWordLength(difficultyChoice);
																																																																															return wordLength;
																																																																																}

																																																																																	private static void computerGuesses() {
																																																																																			int wordLength = getDifficulty();
																																																																																					boolean isGuessCorrect = false;
																																																																																							String randomGuess = getRandomWord(wordLength);
																																																																																									List<String> list = allPossibleWords(wordLength);
																																																																																											while (!isGuessCorrect) {
																																																																																														Scanner input = new Scanner(System.in);
																																																																																																	System.out.println("I guess : " + randomGuess);
																																																																																																				System.out.println("Is my guess correct?(y/n) :");
																																																																																																							String isCorrect = input.next();
																																																																																																										if (isCorrect.equalsIgnoreCase("Y")) {
																																																																																																														System.out.println("Victory!!!");
																																																																																																																		isGuessCorrect = true;
																																																																																																																					} else {
																																																																																																																									System.out.println("How many matches : ");
																																																																																																																													int matchCount = input.nextInt();
																																																																																																																																	if (matchCount == wordLength) {
																																																																																																																																						System.out.println("I have guessed an anagram!");
																																																																																																																																										}
																																																																																																																																														list = trimList(randomGuess, matchCount, list);
																																																																																																																																																		randomGuess = getRandomFromList(list);
																																																																																																																																																					}
																																																																																																																																																							}

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
																																																																																																																																																																																					return rand.nextInt(limit) + 1;
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

																																																																																																																																																																																																																									private static List<String> trimList(String word, int matchCount, List<String> previousList) {
																																																																																																																																																																																																																											List<String> newList = new ArrayList<>();
																																																																																																																																																																																																																													for (String str : previousList) {
																																																																																																																																																																																																																																if (getMatchCount(str, word) == matchCount) {
																																																																																																																																																																																																																																				newList.add(str);
																																																																																																																																																																																																																																							}
																																																																																																																																																																																																																																									}
																																																																																																																																																																																																																																											return newList;
																																																																																																																																																																																																																																												}

																																																																																																																																																																																																																																													private static List<String> allPossibleWords(int length) {
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

