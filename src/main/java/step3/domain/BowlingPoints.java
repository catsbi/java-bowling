/*
package step3.domain;

import step3.domain.dto.PointDTO;
import step3.type.PitchesOrderType;
import step3.type.ResultPitchesType;

import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static step3.domain.BowlingPoint.EMPTY_BOWLING_POINT;
import static step3.type.PitchesOrderType.*;

public class BowlingPoints {
    public static final String ERROR_NOT_PITCHES = "더 이상 투구할 수 없습니다.";
    public static final String ERROR_ALREADY_EXISTS_VALUE = "이미 존재하는 값을 추가할 수 없습니다.";
    public static final String ERROR_NOT_CREATE_OBJECT = "BowlingPoint 를 생성할 수 없습니다.";
    public static final int ZERO_SCORE = 0;
    public static final int STRIKE_VALUE = 10;

    private final Map<PitchesOrderType, BowlingPoint> bowlingPoints;
    private final int maxPitches;

    public BowlingPoints(Map<PitchesOrderType, BowlingPoint> bowlingPoints, int maxPitches) {
        this.bowlingPoints = bowlingPoints;
        this.maxPitches = maxPitches;
    }

    public static BowlingPoints of(int maxPitches) {
        return new BowlingPoints(new HashMap<>(), maxPitches);
    }

    public static BowlingPoints of(BowlingPoint bowlingPoint, int maxPitches) {
        return new BowlingPoints(new HashMap<PitchesOrderType, BowlingPoint>() {{
            put(FIRST, bowlingPoint);
        }}, maxPitches);
    }

    public BowlingPoints push(int pitchesCount) throws IllegalArgumentException {
        BowlingPoint bowlingPoint = makeBowlingPoint(pitchesCount);
        PitchesOrderType type = PitchesOrderType.nextType(size());

        return push(type, bowlingPoint);
    }

    private BowlingPoint makeBowlingPoint(int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());

        if (isNormalType()) {
            return makePoint((current) -> current.equals(EMPTY_BOWLING_POINT), pitchesCount);
        }

        if (isFinalFirst(type)) {
            return BowlingPoint.of(pitchesCount);
        }

        if (isAllowFinalCreate(type)) {
            return makePoint((current) -> current.getPoint() == STRIKE_VALUE || getScore() >= 10, pitchesCount);
        }

        throw new IllegalArgumentException(ERROR_NOT_CREATE_OBJECT);
    }

    private boolean isNormalType() {
        return maxPitches == NormalFrame.MAX_PITCHES;
    }

    private boolean isFinalFirst(PitchesOrderType type) {
        return isFinalType() && type.equals(NONE);
    }

    private boolean isFinalType() {
        return maxPitches == FinalFrame.MAX_PITCHES;
    }

    private boolean isAllowFinalCreate(PitchesOrderType type) {
        return isFinalType() && (type.equals(FIRST) || type.equals(SECOND) && getScore() >= STRIKE_VALUE);
    }

    private BowlingPoint makePoint(Function<BowlingPoint, Boolean> function, int pitchesCount) {
        PitchesOrderType type = PitchesOrderType.getType(size());
        BowlingPoint current = get(type);
        Boolean result = function.apply(current);

        if (result) {
            return BowlingPoint.of(pitchesCount);
        }

        return BowlingPoint.of(pitchesCount, current.getPoint());
    }

    public BowlingPoints push(PitchesOrderType type, BowlingPoint point) throws IllegalArgumentException {
        isAllowType(type);
        isValid();
        bowlingPoints.put(type, point);

        return this;
    }

    private boolean isMaximumSize() {
        return size() == maxPitches;
    }

    private void isValid() {
        if (isMaximumSize()) {
            throw new IllegalArgumentException(ERROR_NOT_PITCHES);
        }
    }

    private void isAllowType(PitchesOrderType type) {
        if (Objects.nonNull(bowlingPoints.get(type))) {
            throw new IllegalArgumentException(ERROR_ALREADY_EXISTS_VALUE);
        }
    }

    public int size() {
        return bowlingPoints.size();
    }

    public ResultPitchesType getType() {
        return ResultPitchesType.getType(getPointDTO());
    }

    private BowlingPoint get(PitchesOrderType type) {
        return Optional.ofNullable(bowlingPoints.get(type))
                .orElse(EMPTY_BOWLING_POINT);
    }

    public int getScore() {
        return bowlingPoints.values()
                .stream()
                .map(BowlingPoint::getPoint)
                .reduce(Integer::sum)
                .orElse(ZERO_SCORE);
    }

    public int getScore(PitchesOrderType type) {
        return get(type).getPoint();
    }

    public int getScore(PitchesOrderType... types) {
        List<PitchesOrderType> pitchesOrderTypes = asList(types);

        return bowlingPoints.entrySet()
                .stream()
                .filter(entry -> pitchesOrderTypes.contains(entry.getKey()))
                .map(entry -> entry.getValue().getPoint())
                .reduce(Integer::sum)
                .orElse(ZERO_SCORE);
    }

    public PointDTO getPointDTO() {
        int currentSize = size();
        PointDTO.Builder builder = PointDTO.Builder(currentSize, maxPitches);
        if (currentSize >= 1) {
            builder = builder.first(getScore(FIRST));
        }
        if (currentSize >= 2) {
            builder = builder.second(getScore(SECOND));
        }
        if (currentSize == 3) {
            builder = builder.third(getScore(THIRD));
        }
        return builder.build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPoints that = (BowlingPoints) o;
        return maxPitches == that.maxPitches &&
                Objects.equals(bowlingPoints, that.bowlingPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlingPoints, maxPitches);
    }
}
*/
