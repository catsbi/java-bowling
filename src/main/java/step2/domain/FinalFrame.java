package step2.domain;

import step2.exception.NotExistsNextFrameException;
import step2.type.ResultPitchesType;

import static step2.type.PitchesOrderType.FIRST;
import static step2.type.PitchesOrderType.SECOND;
import static step2.type.ResultPitchesType.STRIKE;

public class FinalFrame implements Frame {
    public static final String ERROR_CURRENT_FRAME_IS_FINAL = "해당 프레임이 마지막 프레임입니다.";
    public static final int MAX_PITCHES = 3;

    private final int frameNo;
    private final BowlingPoints bowlingPoints;
    private final BowlingSymbols bowlingSymbols;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.bowlingPoints = BowlingPoints.of(MAX_PITCHES);
        this.bowlingSymbols =BowlingSymbols.of(MAX_PITCHES);
    }

    @Override
    public int pitches(int pitchesCount) {
        if (!bowlingPoints.isCompleted()) {
            bowlingPoints.push(pitchesCount);
            bowlingSymbols.push(pitchesCount);
            return pitchesCount;
        }

        throw new IllegalArgumentException("더 이상 던질 수 없습니다.");
    }


    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        return getCurrentScore();
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        if (STRIKE.equals(prevType)) {
            return bowlingPoints.getScore(FIRST, SECOND);
        }
        return bowlingPoints.getScore(FIRST);
    }


    @Override
    public int getCurrentScore() {
        return bowlingPoints.getScore();
    }

    @Override
    public Frame next() {
        throw new NotExistsNextFrameException(ERROR_CURRENT_FRAME_IS_FINAL);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String getResultString() {
        return bowlingSymbols.getSymbol();
    }

    @Override
    public boolean isFinished() {
        return bowlingPoints.isCompleted();
    }

}
