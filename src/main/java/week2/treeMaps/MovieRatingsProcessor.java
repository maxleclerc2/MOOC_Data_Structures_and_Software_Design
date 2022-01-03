package week2.treeMaps;

/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.*;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		LinkedList<String> list = new LinkedList<>();

		if (movieRatings == null || movieRatings.isEmpty()) return list;

		list.addAll(movieRatings.descendingKeySet());
		Collections.reverse(list);

		return list;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		LinkedList<String> list = new LinkedList<>();

		if (movieRatings == null || movieRatings.isEmpty() || rating < 0) return list;

		for (String title: movieRatings.keySet()) {
			PriorityQueue<Integer> pq = movieRatings.get(title);

			if (pq.peek() > rating) {
				list.add(title);
			}
		}

		return list;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> map = new TreeMap<>();

		if (movieRatings == null || movieRatings.isEmpty() || rating < 0) return map;

		TreeMap<String, PriorityQueue<Integer>> tmp = new TreeMap<>(movieRatings);

		for (String title: tmp.keySet()) {
			PriorityQueue<Integer> pq = new PriorityQueue<>(movieRatings.get(title));

			int removed = 0;
			for (Integer rate: tmp.get(title)) {
				if (rate < rating) {
					pq.remove(rate);
					removed++;
				}
			}

			if (removed != 0) {
				movieRatings.put(title, pq);
				map.put(title, removed);
			}

			if (pq.isEmpty()) {
				movieRatings.remove(title);
			}

		}

		return map;
	}
}
