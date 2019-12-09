package statistics;

import statistics.matcher.*;

public class QueryBuilder {

	private Matcher m;

	public QueryBuilder() {
		m = new All();
	}

	public QueryBuilder playsIn(String team) {
		m = new And(m, new PlaysIn(team));
		return this;
	}

	public QueryBuilder hasAtLeast(int value, String category) {
		m = new And(m, new HasAtLeast(value, category));
		return this;
	}

	public QueryBuilder hasFewerThan(int value, String category) {
		m = new And(m, new HasFewerThan(value, category));
		return this;
	}

	public QueryBuilder oneOf(Matcher... matchers) {
		m = new And(m, new Or(matchers));
		return this;
	}

	public Matcher build() {
		return m;
	}
}
