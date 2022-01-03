package week1.collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<Sentence> list = new LinkedList<Sentence>();
		String line = null;
		FileReader fr;
		BufferedReader br;
		int start = 5;
		int max = 2;
		int min = -2;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				try {
					//String line = br.readLine();
					String[] str = line.split(" ");
					start = Integer.parseInt(str[0]);
					//System.out.println("start = " + start);

					if (start < min || start > max || str[1].equals(" ")) {
						//System.out.println("continue");
						continue;
					}

					String sent = line.replace(start + " ", "");
		/*
						System.out.println(line);
						System.out.println(start);
						System.out.println(sent);
		 */
					Sentence s = new Sentence(start, sent);
					list.add(s);
				} catch (Exception e) {
					//continue;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return list;
		}

		return list;

	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		HashSet<Word> words = new HashSet<Word>();

		if (sentences == null || sentences.isEmpty()) {
			return words;
		}

		for (Sentence s: sentences) {
			try {
				String[] sent = s.getText().split(" ");
				int score = s.getScore();

				for (String w: sent) {
					String[] w_punct = w.split("");

					if (Pattern.matches("\\p{Punct}", w_punct[0])) {
						continue;
					}

					int len = w_punct.length;
					if (Pattern.matches("\\p{Punct}", w_punct[len - 1])) {
						w = w.substring(0, len - 1);
					}

					w = w.toLowerCase();
					Word word = new Word(w);

					if (words.contains(word)) {
						word.increaseTotal(score);
					} else {
						words.add(word);
					}
				}
			} catch (Exception e) {
				// skip
			}
		}
		
		return words;
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		HashMap<String, Double> map = new HashMap<String, Double>();

		if (words == null || words.isEmpty()) {
			return map;
		}

		for (Word w: words) {
			try {
				String text = w.getText();
				double score = w.calculateScore();

				map.put(text, score);
			} catch (Exception e) {
				// skip
			}
		}

		return map;

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		if (wordScores == null || wordScores.isEmpty()) {
			return 0;
		}

		if (sentence == null || sentence.isEmpty()) {
			return 0;
		}

		double total = 0.0;
		int nb_words = 0;
		String[] words = sentence.split(" ");
		for (String w: words) {
			String[] w_punct = w.split("");

			if (Pattern.matches("\\p{Punct}", w_punct[0])) {
				continue;
			}

			int len = w_punct.length;
			if (Pattern.matches("\\p{Punct}", w_punct[len - 1])) {
				w = w.substring(0, len - 1);
			}

			w = w.toLowerCase();
			double score;

			score = wordScores.getOrDefault(w, 0.0);

			total += score;
			nb_words ++;
		}

		double average;
		if (nb_words == 0) {
			average = 0.0;
		} else {
			average = total / nb_words;
		}

		return average;
	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
