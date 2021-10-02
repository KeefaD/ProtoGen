package simple;

import org.junit.jupiter.api.Test;

import java.util.Optional;

//TODO:KMD Put max range stuff in here in a base class
public class TestConstructor {

    @Test
    public void testConstructor() {
        var type = new Type(
            0,
            1,
            Optional.of(1),
            Optional.of(1L)
        );
    }

}