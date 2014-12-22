package cz.dusanrychnovsky.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

	// ========================================================================
	// FOLD
	// ========================================================================
	
	/**
	 * 
	 * @param lists
	 * @param initial
	 * @param reduce
	 * @return
	 */
	public static <T> T fold(List<T> list, T initial, Reduce<T> reduce) {
		
		T result = initial;
		for (T current : list) {
			result = reduce.eval(result, current);
		}
		return result;
	}
	
	/**
	 * 
	 * @author Dušan Rychnovský
	 *
	 * @param <T>
	 */
	public static interface Reduce<T> {
		
		/**
		 * 
		 * @param first
		 * @param second
		 * @return
		 */
		public T eval(T first, T second);
	}

	// ========================================================================
	// CONCAT
	// ========================================================================
	
	/**
	 * 
	 * @param lists
	 * @return
	 */
	public static <T> List<T> concat(List<List<T>> lists) {
		return fold(lists, new ArrayList<T>(), new Reduce<List<T>>() {
			public List<T> eval(List<T> first, List<T> second) {
				first.addAll(second);
				return first;
			}
		});
	}
}
