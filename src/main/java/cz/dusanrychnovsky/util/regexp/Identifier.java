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
}
