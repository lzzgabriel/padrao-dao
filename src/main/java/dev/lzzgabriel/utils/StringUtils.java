package dev.lzzgabriel.utils;

public class StringUtils {

	public static boolean isNullOrEmpty(String x) {
		if (x == null)
			return true;
		if (x.trim().isEmpty())
			return true;
		return false;
	}
}
