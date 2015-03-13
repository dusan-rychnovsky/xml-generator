package cz.dusanrychnovsky.util.regexp;

import lombok.Data;

@Data
public class Identifier extends Expression {

    private final String symbol;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
