package interfaces;

import com.kdsc.protogen.runtime.ProtoGenType;
import java.util.Optional;

public interface TypeInterface extends interfaces.BaseInterface1, interfaces.BaseInterface2, ProtoGenType {

	double getTestDouble();
	float getTestFloat();
	int getTestInt32();
	long getTestInt64();
	boolean getTestBool();
	String getTestString();
	com.kdsc.protogen.runtime.types.Bytes getTestBytes();
	com.kdsc.protogen.runtime.types.Decimal getTestDecimal();
	com.kdsc.protogen.runtime.types.Date getTestDate();
	com.kdsc.protogen.runtime.types.DateTime getTestDateTime();
	com.kdsc.protogen.runtime.types.LocalDateTime getTestLocalDateTime();
	com.kdsc.protogen.runtime.types.Map<Integer, Integer> getTestMap();
	com.kdsc.protogen.runtime.types.Set<Integer> getTestSet();
	com.kdsc.protogen.runtime.types.ValueOrError<Integer> getTestValueOrError();
	interfaces.InnerType getTestType();
	com.kdsc.protogen.runtime.types.Array<Integer> getTestArray();
	Optional<Double> getTestOptionalDouble();
	Optional<Float> getTestOptionalFloat();
	Optional<Integer> getTestOptionalInt32();
	Optional<Long> getTestOptionalInt64();
	Optional<Boolean> getTestOptionalBool();
	Optional<String> getTestOptionalString();
	Optional<com.kdsc.protogen.runtime.types.Bytes> getTestOptionalBytes();
	Optional<com.kdsc.protogen.runtime.types.Decimal> getTestOptionalDecimal();
	Optional<com.kdsc.protogen.runtime.types.Date> getTestOptionalDate();
	Optional<com.kdsc.protogen.runtime.types.DateTime> getTestOptionalDatetime();
	Optional<com.kdsc.protogen.runtime.types.LocalDateTime> getTestOptionalLocalDateTime();
	Optional<com.kdsc.protogen.runtime.types.Map<Integer, Integer>> getTestOptionalMap();
	Optional<com.kdsc.protogen.runtime.types.Set<Integer>> getTestOptionalSet();
	Optional<com.kdsc.protogen.runtime.types.ValueOrError<Integer>> getTestOptionalValueOrError();
	Optional<interfaces.InnerType> getTestOptionalType();
	Optional<com.kdsc.protogen.runtime.types.Array<Integer>> getTestOptionalArray();

}