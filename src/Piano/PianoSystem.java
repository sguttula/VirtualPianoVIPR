package Piano;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;

public class PianoSystem {
	private File f;
	
	MidiTrack tempoTrack = new MidiTrack();
	MidiTrack noteTrack = new MidiTrack();
	Instant momentZero;
	
	private boolean recording;
	
	private ArrayList<Scale> scales=new ArrayList<Scale>();
	
	private int[] noteValue;
		
	private ArrayList<PianoKey> pianoKeys=new ArrayList<>();
	
	private final String[] KEYBOARD_KEYS={"TAB","ONE","Q","TWO","W","E","FOUR","R","FIVE","T","SIX","Y","U","EIGHT","I","NINE","O","P","MINUS","RBRACKET","EQUALS","LBRACKET","BACKSPACE","SLASH","HOME"};
	public PianoSystem(){
		TimeSignature ts = new TimeSignature();
		ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);

		Tempo t = new Tempo();
		t.setBpm(120);

		tempoTrack.insertEvent(ts);
		tempoTrack.insertEvent(t);
	
		noteValue=new int[25];
		int temp=53;
		for(int i=0;i<25;i++){
			pianoKeys.add(new PianoKey(temp,KEYBOARD_KEYS[i], false));
			
			noteValue[i]=temp;
			temp++;
		}
		
		this.f=new File("scales.txt");
		if(!f.isFile()){
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new BufferedOutputStream(
						new FileOutputStream(new File("scales.txt"))));
				out.writeObject("");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("scales.txt created");
		}
			
	}
	public void update(){
		try {
			Scanner input=new Scanner(f);
			
				
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}
	public void addScale(Scale s) {
		scales.add(s);
		try {
			writeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void writeFile() throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(new FileOutputStream("scales.txt"));
		for(int i=0;i<scales.size();i++){
			pw.println(scales.get(i).toString());
			
			
		}
		pw.close();
	}
	public int getNoteValue(String val){		
		for(int i=0;i<pianoKeys.size();i++){
			if(pianoKeys.get(i).getKeyBoardVal().equals(val))
				return pianoKeys.get(i).getMidiValue();	
		}
		return -1;
	}
	public void setKeyClicked(String val,boolean b){	
		for(int i=0;i<pianoKeys.size();i++){
			if(pianoKeys.get(i).getKeyBoardVal().equals(val)){
				pianoKeys.get(i).setClicked(b);
				if(recording){
					//if recording and IS PRESSED, begin the timer
					if(b){
						pianoKeys.get(i).setBegin();
						
					}
					//if recording and IS RELEASED, end the timer and add to track
					else{
						noteTrack.insertNote(0, pianoKeys.get(i).getMidiValue() , 100, Duration.between(momentZero,pianoKeys.get(i).getBegin()).toMillis(), Duration.between(pianoKeys.get(i).getBegin(), Instant.now()).toMillis());
						
					}
					
					
				}
				
				
			}
		}
		
		
		
	}
	
	/**
	 * checks to see if the requested key is clicked
	 * 
	 * @param val
	 * @param b
	 */
	public boolean isKeyClicked(String val){
		for(int i=0;i<pianoKeys.size();i++){
			if(pianoKeys.get(i).getKeyBoardVal().equals(val))
				return pianoKeys.get(i).isClicked();
		}
		 return false;
		
	}
	
	
	
	/**
	 * Generates a random melody based on a given scale
	 * 
	 * @return int[]
	 */
	public int[] generateMelody(){
		int [] melody=new int[10];
		int [] sweep={55,58,62,67,70,74};
		//Scale scale1=new Scale("sweep",sweep);
		//System.out.println(scale1);
	
		int currentPos=0;
		int note=60;
		for(int i=0;i<10;i++){

			int rand=(int)(Math.random()*5);

			
			if(rand==0)
				note+=-1;
			if(rand==1)
				note+=-2;
			if(rand==3)
				note+=1;
			if(rand==4)
				note+=2;
			

			note=currentPos+note;
			if(currentPos<0||currentPos>4)
				currentPos=(int)(Math.random()*5);
			
			
			//melody[i]=sweep[currentPos];
			melody[i]=note;
		}



		return melody;
	}
	public void setRecording(boolean rec){
		this.recording=rec;
		momentZero=Instant.now();
		
	}
	public boolean isRecording(){
		return this.recording;
		
	}
	
	public void renderRecording(){
		ArrayList<MidiTrack> tracks = new ArrayList<MidiTrack>();
		tracks.add(tempoTrack);
		tracks.add(noteTrack);

		MidiFile midi = new MidiFile(960, tracks);

		// 4. Write the MIDI data to a file
		
		int date=LocalDateTime.now().getSecond();
		File output = new File(date+"testWrite.mid");
		try
		{
			midi.writeToFile(output);
		}
		catch(IOException e)
		{
			System.err.println(e);
		}

	}
	
	
	
	
	
	
}
