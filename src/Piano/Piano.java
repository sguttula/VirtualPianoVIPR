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
	BorderPane bp;
	int move=5;
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
		temp.setX(10+move);
		temp.setY(10);

		Image tmp2=new Image(new File("key.png").toURI().toString());
		ImageView temp2=new ImageView(tmp2);
		temp2.setX(10);
		temp2.setY(10);
		temp2.getStyleClass().add("key");

		System.out.println(temp2.getStyle());

		//bp.getChildren().add(l);
		//gp.add(key, 2, 2);
		bp=new BorderPane();
		bp.getChildren().add(temp);
		bp.getChildren().add(temp2);

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

				if(event.getCode()==KeyCode.TAB&&click){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);
					
					click=false;
					
					temp2.setOpacity(0.2);
					
				}
				if(event.getCode()==KeyCode.DIGIT1)
					mChannels[0].noteOn(s.getNoteValue("1"), 100);
				if(event.getCode()==KeyCode.Q)
					mChannels[0].noteOn(s.getNoteValue("Q"), 100);
				if(event.getCode()==KeyCode.DIGIT2)
					mChannels[0].noteOn(s.getNoteValue("2"), 100);
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

				if(event.getCode()==KeyCode.TAB){
					mChannels[0].noteOff(s.getNoteValue("TAB"), 100);
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



