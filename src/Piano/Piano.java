//package Piano;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//import javax.sound.midi.Instrument;
//import javax.sound.midi.MidiChannel;
//import javax.sound.midi.MidiEvent;
//import javax.sound.midi.MidiSystem;
//import javax.sound.midi.Sequence;
//import javax.sound.midi.Sequencer;
//import javax.sound.midi.ShortMessage;
//import javax.sound.midi.Synthesizer;
//import javax.sound.midi.Track;
//
//import com.sun.javafx.geom.Rectangle;
//
//import javafx.application.Application;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.shape.Line;
//import javafx.stage.Stage;
//public class Piano extends Application{
//	Boolean click=true;
//	BorderPane bp;
//	int move=5;
//	PianoSystem s=new PianoSystem();
//	//int[] melody=generateMelody();
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		
//		s.update();
//
//		
//		
//		
//		
//		
//		
//		Image tmp1=new Image(new File("keyPressed.png").toURI().toString());
//		ImageView temp=new ImageView(tmp1);
//		temp.setX(10+move);
//		temp.setY(10);
//
//		Image tmp2=new Image(new File("key.png").toURI().toString());
//		ImageView temp2=new ImageView(tmp2);
//		temp2.setX(10);
//		temp2.setY(10);
//		temp2.getStyleClass().add("key");
//
//		System.out.println(temp2.getStyle());
//
//		//bp.getChildren().add(l);
//		//gp.add(key, 2, 2);
//		bp=new BorderPane();
//		bp.getChildren().add(temp);
//		bp.getChildren().add(temp2);
//
//		Sequencer player=MidiSystem.getSequencer();
//		player.open();
//		Sequence sequence=new Sequence(Sequence.PPQ,4);
//	
////		InputStream is=new BufferedInputStream(new FileInputStream(new File("GHOST.mid")));
////		sequencer.setSequence(is);
//		 Track track = sequence.createTrack();
////		track.add(noteOn);
////		track.add(noteOff);
//		player.setSequence(sequence);
//		//sequencer.setTrackSolo(2, true);
//		//sequencer.start();
//		
//		Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
//		midiSynth.open();
//		Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//		MidiChannel[] mChannels = midiSynth.getChannels();
//
//		midiSynth.loadInstrument(instr[0]);//load an instrument
//		GridPane gp=new GridPane();
//
//		Button key=new Button("KEY");
//		key.setMinSize(100, 200);
//		player.start();
//
//
//		EventHandler evPress =new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(final KeyEvent event) {
//
//				if(event.getCode()==KeyCode.TAB&&click){
//					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);
//					
//					click=false;
//					
//					temp2.setOpacity(0.2);
//					
//				}
//				if(event.getCode()==KeyCode.DIGIT1)
//					mChannels[0].noteOn(s.getNoteValue("1"), 100);
//				if(event.getCode()==KeyCode.Q)
//					mChannels[0].noteOn(s.getNoteValue("Q"), 100);
//				if(event.getCode()==KeyCode.DIGIT2)
//					mChannels[0].noteOn(s.getNoteValue("2"), 100);
//				key.setText("PRESSED");
//				
//
//				
//
//			}
//
//		};
//
//		EventHandler mousePress =new EventHandler() {
//			@Override
//			public void handle(Event event) {
//				//	mChannels[0].noteOn(53, 100);
//				click=false;
//				System.out.println("CaT PRESSED");
//			}
//
//		};
//
//		EventHandler mousePress2 =new EventHandler() {
//			@Override
//			public void handle(Event event) {
//				System.out.println("KEYPRESSED");
//				
//			}
//
//		};
//
//		
//		EventHandler evRelease =new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(final KeyEvent event) {
//
//				if(event.getCode()==KeyCode.TAB){
//					mChannels[0].noteOff(s.getNoteValue("TAB"), 100);
//					click=true;
//					temp2.setOpacity(1);
//				}
//
//			}
//
//		};
//
//		
//		Scene scene=new Scene(bp,900,900);
//		scene.getStylesheets().add("style.css");
//		scene.setOnKeyPressed(evPress);
//		scene.setOnKeyReleased(evRelease);
//		temp.setOnMouseClicked(mousePress);
//		temp2.setOnMouseClicked(mousePress2);
//		// scene.setOnKeyTyped(ev);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//	
//
//
//
//}
//
//
//


package Piano;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.sun.javafx.geom.Rectangle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
public class Piano extends Application{
	Boolean click=true;
	BorderPane bp, bp2;
	int move=5;
	int posX = 5;
	PianoSystem s=new PianoSystem();
	//int[] melody=generateMelody();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		s.update();
		Image tmp1=new Image(new File("keyPressed.png").toURI().toString());
		ImageView temp=new ImageView(tmp1);
		temp.setX(1+move);
		temp.setY(1);
		Image tmp2=new Image(new File("key.png").toURI().toString());
		ImageView temp2=new ImageView(tmp2);
		temp2.setX(1);
		temp2.setY(1);
		temp2.getStyleClass().add("key");
		//System.out.println(temp2.getStyle());
		
//		
		//bp.getChildren().add(l);
		//gp.add(key, 2, 2);
		bp=new BorderPane();
		ImageView[] keys = new ImageView[15];
		ImageView[] keys2 = new ImageView[15];
//		bp.getChildren().add(temp);
//		bp.getChildren().add(temp2);
	
		for(int i = 0 ; i < 15 ; i++) {
			posX += 55;
			keys[i] = new ImageView(tmp2);
			keys[i].setX(posX);
			keys[i].setY(10);
			keys2[i] = new ImageView(tmp1);
			keys2[i].setX(posX);
			keys2[i].setY(10);
//			bp.getChildren().add(temp);
		
//			bp.getChildren().add(temp);
		bp.getChildren().add(keys[i]);
		
		//bp.getChildren().add(keys2[i]);
			//bp.getChildren().add(temp2);
		}
		//bp.getChildren().remove(keys[i]);
		Sequencer player=MidiSystem.getSequencer();
		player.open();
		Sequence sequence=new Sequence(Sequence.PPQ,4);
		//		InputStream is=new BufferedInputStream(new FileInputStream(new File("GHOST.mid")));
		//		sequencer.setSequence(is);
		Track track = sequence.createTrack();
		//		track.add(noteOn);
		//		track.add(noteOff);
		player.setSequence(sequence);
		//sequencer.setTrackSolo(2, true);
		//sequencer.start();
		Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
		midiSynth.open();
		Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
		MidiChannel[] mChannels = midiSynth.getChannels();
		midiSynth.loadInstrument(instr[0]);//load an instrument
		GridPane gp=new GridPane();
		Button key=new Button("KEY");
		key.setMinSize(100, 200);
		player.start();
		EventHandler evPress =new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent event) {
				JButton tab = new JButton("TAB");
				tab.setVerticalAlignment(SwingConstants.BOTTOM);
				//tab.setFont(font);
			
				if(event.getCode()==KeyCode.TAB){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);
//					for(int i = 0 ; i < 15 ; i++) {
//						posX += 55;
//						keys2[i] = temp;
//					keys2[i].setX(posX);
//					keys2[i].setY(10);
//					}
//					
					bp.getChildren().add(keys2[0]);
					click=false;
				}
				if(event.getCode()==KeyCode.DIGIT1)
					mChannels[0].noteOn(s.getNoteValue("1"), 100);
				click=false;
				
//				temp2.setOpacity(0.2);
				//bp.getChildren().add(temp);
				if(event.getCode()==KeyCode.Q)
					mChannels[0].noteOn(s.getNoteValue("Q"), 100);
				click=false;
				//bp.getChildren().add(keys2[1]);
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT2)
					mChannels[0].noteOn(s.getNoteValue("2"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.W)
					mChannels[0].noteOn(s.getNoteValue("W"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.E)
					mChannels[0].noteOn(s.getNoteValue("E"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT4)
					mChannels[0].noteOn(s.getNoteValue("4"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.R)
					mChannels[0].noteOn(s.getNoteValue("R"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT5)
					mChannels[0].noteOn(s.getNoteValue("5"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.T)
					mChannels[0].noteOn(s.getNoteValue("T"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT6)
					mChannels[0].noteOn(s.getNoteValue("6"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.Y)
					mChannels[0].noteOn(s.getNoteValue("Y"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.U)
					mChannels[0].noteOn(s.getNoteValue("U"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT8)
					mChannels[0].noteOn(s.getNoteValue("8"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.I)
					mChannels[0].noteOn(s.getNoteValue("I"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.DIGIT9)
					mChannels[0].noteOn(s.getNoteValue("9"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.O)
					mChannels[0].noteOn(s.getNoteValue("O"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.P)
					mChannels[0].noteOn(s.getNoteValue("P"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.MINUS)
					mChannels[0].noteOn(s.getNoteValue("-"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.OPEN_BRACKET)
					mChannels[0].noteOn(s.getNoteValue("["), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.EQUALS)
					mChannels[0].noteOn(s.getNoteValue("="), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.CLOSE_BRACKET)
					mChannels[0].noteOn(s.getNoteValue("]"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.BACK_SPACE)
					mChannels[0].noteOn(s.getNoteValue("B"), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.BACK_SLASH)
					mChannels[0].noteOn(s.getNoteValue("\""), 100);
				click=false;
				temp2.setOpacity(0.2);
				if(event.getCode()==KeyCode.NUMPAD7)
					mChannels[0].noteOn(s.getNoteValue("HOME"), 100);
				click=false;
				temp2.setOpacity(0.2);
				key.setText("PRESSED");
			}
		};
		EventHandler mousePress =new EventHandler() {
			@Override
			public void handle(Event event) {
				//	mChannels[0].noteOn(53, 100);
				click=false;
				System.out.println("CaT PRESSED");
			}
		};
		EventHandler mousePress2 =new EventHandler() {
			@Override
			public void handle(Event event) {
				System.out.println("KEYPRESSED");
			}
		};
		EventHandler evRelease =new EventHandler<KeyEvent>() {
			@Override
			public void handle(final KeyEvent event) {
//				if(event.getCode()==KeyCode.TAB){
//					mChannels[0].noteOff(s.getNoteValue("TAB"), 100);
//					click=true;
//					temp2.setOpacity(1);
//				}
				if(event.getCode()==KeyCode.TAB){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);
//					for(int i = 0 ; i < 15 ; i++) {
//						posX += 55;
//						keys[i] = temp2;
//					keys[i].setX(posX);
//					keys[i].setY(10);
//					}
					
					bp.getChildren().remove(keys2[0]);
					click=false;
				}
				if(event.getCode()==KeyCode.DIGIT1){
					mChannels[0].noteOff(s.getNoteValue("1"), 100);
					click=true;
					temp2.setOpacity(1);
					//keys[i].setOpacity(0);
				}
				if(event.getCode()==KeyCode.Q){
					mChannels[0].noteOff(s.getNoteValue("Q"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT2){
					mChannels[0].noteOff(s.getNoteValue("2"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.W){
					mChannels[0].noteOff(s.getNoteValue("W"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.E){
					mChannels[0].noteOff(s.getNoteValue("E"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT4){
					mChannels[0].noteOff(s.getNoteValue("4"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.R){
					mChannels[0].noteOff(s.getNoteValue("R"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT5){
					mChannels[0].noteOff(s.getNoteValue("5"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.T){
					mChannels[0].noteOff(s.getNoteValue("T"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT6){
					mChannels[0].noteOff(s.getNoteValue("6"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.Y){
					mChannels[0].noteOff(s.getNoteValue("Y"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.U){
					mChannels[0].noteOff(s.getNoteValue("U"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT8){
					mChannels[0].noteOff(s.getNoteValue("8"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.I){
					mChannels[0].noteOff(s.getNoteValue("I"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT9){
					mChannels[0].noteOff(s.getNoteValue("9"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.O){
					mChannels[0].noteOff(s.getNoteValue("O"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.P){
					mChannels[0].noteOff(s.getNoteValue("P"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.MINUS){
					mChannels[0].noteOff(s.getNoteValue("-"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.OPEN_BRACKET){
					mChannels[0].noteOff(s.getNoteValue("["), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.EQUALS){
					mChannels[0].noteOff(s.getNoteValue("="), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.CLOSE_BRACKET){
					mChannels[0].noteOff(s.getNoteValue("]"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.BACK_SPACE){
					mChannels[0].noteOff(s.getNoteValue("B"), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.BACK_SLASH){
					mChannels[0].noteOff(s.getNoteValue("\""), 100);
					click=true;
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.NUMPAD7){
					mChannels[0].noteOff(s.getNoteValue("HOME"), 100);
					click=true;
					temp2.setOpacity(1);
				}
			}
		};
		Scene scene=new Scene(bp,900,900);
		scene.getStylesheets().add("style.css");
		scene.setOnKeyPressed(evPress);
		scene.setOnKeyReleased(evRelease);
		temp.setOnMouseClicked(mousePress);
		temp2.setOnMouseClicked(mousePress2);
		// scene.setOnKeyTyped(ev);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}