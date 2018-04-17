package Piano;

import java.time.Instant;

//class models a physical piano key
public class PianoKey {
	private int midiValue;
	private String keyBoardVal;
	private boolean isClicked;
	 private Instant begin, end;
	/**
	 * this class models a piano's keys
	 * 
	 * @param keyValue the integer value that java uses
	 * @param keyBoardVal the keyBoard name that is associated with the note
	 * @param isClicked boolean to check if the key is clicked
	 */
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
	/**
	 * 
	 * @return the name of the key associated with that note
	 */
	public String getKeyBoardVal() {
		return keyBoardVal;
	}
	public void setKeyBoardVal(String keyBoardVal) {
		this.keyBoardVal = keyBoardVal;
	}
	public boolean isClicked() {
		return isClicked;
	}
	/**
	 * Set if the key is being pressed
	 * @param isClicked
	 */
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	public Instant getBegin() {
		return begin;
	}
	/**
	 * sets the instant when the key was first pressed
	 * @param begin
	 */
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
