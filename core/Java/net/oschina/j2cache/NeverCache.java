package net.oschina.j2cache;

public class NeverCache {
	private static final NeverCache instance = new NeverCache();

	private NeverCache() {

	}

	public static NeverCache getInstance() {
		return instance;
	}

}
