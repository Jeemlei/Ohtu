package statistics;


import statistics.matcher.*;

public class QueryBuilder {
    
    private Matcher m;

    public QueryBuilder() {
        this.m = new All();
    }
    
    public Matcher build() {
        Matcher matcher = this.m;
        this.m = new All();
        return matcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.m = new PlaysIn(team);
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.m = new And(m, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.m = new And(m, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.m = new And(m, new Or(m1, m2));
        return this;
    }
}
