package step2.type;

import step2.domain.BowlingPoints;

import java.util.Arrays;

public enum PitchesOrderType {
    NONE(0),
    FIRST(1),
    SECOND(2),
    THIRD(3);

    public static final String ERROR_NO_SUCH_MATCH_TYPE = "적절한 타입을 찾을 수 없습니다.";

    private final int orderNumber;

    PitchesOrderType(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static PitchesOrderType currentType(BowlingPoints points) {
        return getType(points.size());
    }

    public static PitchesOrderType nextType(BowlingPoints points) {
        return getType(points.size() + 1);
    }

    public static PitchesOrderType getType(int source) {
        return Arrays.stream(PitchesOrderType.values())
                .filter(type -> type.orderNumber == source)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException(ERROR_NO_SUCH_MATCH_TYPE));
    }
}
