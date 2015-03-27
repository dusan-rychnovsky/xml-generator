package cz.dusanrychnovsky.xmlgenerator.schema.graph;

import java.util.HashSet;
import java.util.Set;

import lombok.Value;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

@Value
public class EnumContent extends ContentType {

    private final Set<String> values = new HashSet<>();

    public EnumContent(Set<String> values) {
        values.forEach(value -> this.values.add(value));
    }

    public EnumContent(String... values) {
        this(new HashSet<>(asList(values)));
    }

    public Set<String> getValues() {
        return unmodifiableSet(values);
    }
}
