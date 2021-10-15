import java.util.Map;
import java.util.Set;

public class TemporaryGenericsJavaTest<T1 extends Integer, T2 extends Map<T1, Map<T1, T2>>, T3 extends Set<T4>, T4> {

    public interface InnerInterface1<T> {}

    public interface InnerInterface2<T> extends InnerInterface1<T> {}

    public class InnerClass1<T> {}

    public class InnerClass2<T> {}

    public class TestInnerClass1<T extends InnerInterface1<T> & InnerInterface2<T>>{}

    public class TestInnerClass2<T extends InnerClass1<T> & InnerInterface2<T>>{}

}
