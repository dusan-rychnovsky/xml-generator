package cz.dusanrychnovsky.util.regexp;

public class Identifier extends Expression {

    private final String token;

    /**
     *
     * @param token
     */
    public Identifier(String token) {
        this.token = token;
    }
    
    /**
     * 
     * @return
     */
	public String getToken() {
		return token;
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
    	return token.equals(other.token);
    }
    
    @Override
    public int hashCode() {
    	return token.hashCode();
    }
}
