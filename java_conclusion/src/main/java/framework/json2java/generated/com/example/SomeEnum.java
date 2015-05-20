
package framework.json2java.generated.com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Generated("org.jsonschema2pojo")
public enum SomeEnum {

    ONE("one"),
    TWO("two"),
    THREE("three");
    private final String value;
    private static Map<String, SomeEnum> constants = new HashMap<String, SomeEnum>();

    static {
        for (SomeEnum c: values()) {
            constants.put(c.value, c);
        }
    }

    private SomeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static SomeEnum fromValue(String value) {
        SomeEnum constant = constants.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
