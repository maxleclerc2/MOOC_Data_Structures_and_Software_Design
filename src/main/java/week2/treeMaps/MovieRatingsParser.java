package week2.treeMaps;

/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.*;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> map = new TreeMap<>();

		if (allUsersRatings == null || allUsersRatings.isEmpty()) return map;

		for (UserMovieRating rating: allUsersRatings) {
			if (rating == null) continue;

			String title = rating.getMovie();
			if (title == null || title.isEmpty()) continue;

			int rate = rating.getUserRating();
			if (rate < 0) continue;

			title = title.toLowerCase();

			PriorityQueue<Integer> pq;
			if (!map.containsKey(title)) {
				pq = new PriorityQueue<>();
			} else {
				pq = map.get(title);
			}
			pq.add(rate);
			map.put(title, pq);
		}

		return map;
	}
}
