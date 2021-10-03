package simple;

import org.junit.jupiter.api.Test;

import java.util.Optional;

//TODO:KMD Put max range stuff in here in a base class
public class TestConstructor {

    @Test
    public void testConstructor() {
        new Type(
            1d,
            1f,
            0,
            1,
            false,
            "Test",
            new InnerType(1f),
            Optional.of(1d),
            Optional.of(1f),
            Optional.of(1),
            Optional.of(1L),
            Optional.of(false),
            Optional.of("Test"),
            Optional.of(new InnerType(1f))
        );
    }

}