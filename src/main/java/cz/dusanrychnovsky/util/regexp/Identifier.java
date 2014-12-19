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
}
