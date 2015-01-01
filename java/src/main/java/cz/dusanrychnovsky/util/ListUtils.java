package cz.dusanrychnovsky.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ListUtils {
	
	/**
	 * 
	 * @param list
	 * @param f
	 * @return
	 */
	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		
		List<R> result = new ArrayList<R>();
		for (T item : list) {
			result.add(f.apply(item));
		}
		return result;
	}
	
	/**
	 * 
	 * @param list
	 * @param init
	 * @param f
	 * @return
	 */
	public static <T, R> R fold(List<T> list, R init, BiFunction<R, T, R> f) {
		
		R result = init;
		for (T item : list) {
			result = f.apply(result, item);
		}
		return result;
	}
}
