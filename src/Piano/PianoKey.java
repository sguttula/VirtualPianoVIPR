package Piano;

import java.time.Instant;

public class PianoKey {
	private int midiValue;
	private String keyBoardVal;
	private boolean isClicked;
	 private Instant begin, end;
	public PianoKey(int midiValue, String keyBoardVal, boolean isClicked) {
		super();
		this.midiValue = midiValue;
		this.keyBoardVal = keyBoardVal;
		this.isClicked = isClicked;
	}
	public int getMidiValue() {
		return midiValue;
	}
	public void setMidiValue(int keyValue) {
		this.midiValue = keyValue;
	}
	public String getKeyBoardVal() {
		return keyBoardVal;
	}
	public void setKeyBoardVal(String keyBoardVal) {
		this.keyBoardVal = keyBoardVal;
	}
	public boolean isClicked() {
		return isClicked;
	}
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	public Instant getBegin() {
		return begin;
	}
	public void setBegin() {
		this.begin = Instant.now();
	}
	public Instant getEnd() {
		return end;
	}
	/**
	 * Sets the instant when the key was released
	 * @param end
	 */
	public void setEnd(Instant end) {
		this.end = end;
	}
	
	
}
