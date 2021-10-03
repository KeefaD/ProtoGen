package simple;

import com.kdsc.protogen.runtime.types.*;
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
            new Decimal(),
            new Date(),
            new DateTime(),
            new LocalDateTime(),
            new ValueOrError<>(1),
            new InnerType(1f),
            Optional.of(1d),
            Optional.of(1f),
            Optional.of(1),
            Optional.of(1L),
            Optional.of(false),
            Optional.of("Test"),
            Optional.of(new Decimal()),
            Optional.of(new Date()),
            Optional.of(new DateTime()),
            Optional.of(new LocalDateTime()),
            Optional.of(new ValueOrError<>(1)),
            Optional.of(new InnerType(1f))
        );
    }

}