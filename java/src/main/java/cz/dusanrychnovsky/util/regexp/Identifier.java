package cz.dusanrychnovsky.util.regexp;

public class Identifier extends Expression {

    private final String symbol;

    /**
     *
     * @param symbol
     */
    public Identifier(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return
     */
	public String getSymbol() {
		return symbol;
	}

    @Override
    public <T> T accept(Visitor<T> visitor) {
    	return visitor.visit(this);
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if (!(obj instanceof Identifier)) {
    		return false;
    	}
    	
    	Identifier other = (Identifier) obj;
    	return symbol.equals(other.symbol);
    }
    
    @Override
    public int hashCode() {
    	return symbol.hashCode();
    }
}
