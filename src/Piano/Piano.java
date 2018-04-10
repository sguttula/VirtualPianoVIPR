package Piano;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;
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

	BorderPane bp;
	
	PianoSystem s=new PianoSystem();
	Instant pointZero,begin,end;

	MidiTrack tempoTrack = new MidiTrack();
	MidiTrack noteTrack = new MidiTrack();

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
		temp.setX(10);
		temp.setY(140);

		Image tmp2=new Image(new File("key.png").toURI().toString());
		ImageView temp2=new ImageView(tmp2);
		temp2.setX(10);
		temp2.setY(140);



		//bp.getChildren().add(l);
		//gp.add(key, 2, 2);
		bp=new BorderPane();
		HBox area=new HBox();
		Button key=new Button("KEY");
		Button save=new Button("SAVE");
		key.setMinSize(200, 100);
		save.setMinSize(200, 100);
		area.getChildren().add(key);
		area.getChildren().add(save);
		//key.setLayoutX(300);
		bp.setTop(area);
		//bp.getChildren().add(key);
		bp.getChildren().add(temp);
		bp.getChildren().add(temp2);

		Sequencer player=MidiSystem.getSequencer();

		player.open();
		InputStream is = new BufferedInputStream(new FileInputStream(new File("HappyBirthday.mid")));
		//		InputStream is=new BufferedInputStream(new FileInputStream(new File("GHOST.mid")));
		//		sequencer.setSequence(is);

		//		track.add(noteOn);
		//		track.add(noteOff);
		player.setSequence(is);

		player.setTickPosition(0);
		//sequencer.setTrackSolo(2, true);
		//sequencer.start();

		Synthesizer midiSynth = MidiSystem.getSynthesizer(); 

		midiSynth.open();
		Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
		MidiChannel[] mChannels = midiSynth.getChannels();

		midiSynth.loadInstrument(instr[0]);//load an instrument
		GridPane gp=new GridPane();





		EventHandler evPress =new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if(event.getCode()==KeyCode.TAB&&!s.isKeyClicked("TAB")){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);

					s.setKeyClicked("TAB",true);
					System.out.println("TAB");
					temp2.setOpacity(0.2);
					



				}
				if(event.getCode()==KeyCode.DIGIT1&&!s.isKeyClicked("ONE")){
					mChannels[0].noteOn(s.getNoteValue("ONE"), 100);
					s.setKeyClicked("ONE",true);
				}
				if(event.getCode()==KeyCode.Q&&!s.isKeyClicked("Q")){
					mChannels[0].noteOn(s.getNoteValue("Q"), 100);
					s.setKeyClicked("Q", true);
				}
				if(event.getCode()==KeyCode.DIGIT2&&!s.isKeyClicked("TWO")){
					mChannels[0].noteOn(s.getNoteValue("TWO"), 100);
					s.setKeyClicked("TWO", true);
					System.out.println("TWO");
				}


				if(event.getCode()==KeyCode.BACK_SPACE&&!s.isKeyClicked("BACKSPACE")){
					mChannels[0].noteOn(s.getNoteValue("BACKSPACE"), 100);
					s.setKeyClicked("BACKSPACE", true);
					System.out.println("BACK");
				}


			}

		};

		EventHandler mousePress =new EventHandler() {
			boolean play=false;
			long pos=0;
			@Override
			public void handle(Event event) {
				//	mChannels[0].noteOn(53, 100);
				if(play){
					play=false;
					pos=player.getTickPosition();
					player.stop();
					key.setText("Pause");


				}else{
					key.setText("Play");
					play=true;
					player.setTickPosition(pos);
					player.start();


				}

			}

		};

		EventHandler mousePress2 =new EventHandler() {



			@Override
			public void handle(Event event) {
				//	mChannels[0].noteOn(53, 100);
				if(s.isRecording()){
					s.setRecording(false);
					System.out.println("STOPPED");
					save.setText("Stopped");
					
					s.renderRecording();


				}else{
					save.setText("Recording");
					System.out.println("RECORDING");
					
					s.setRecording(true);
					

				}

			}

		};


		EventHandler evRelease =new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if(event.getCode()==KeyCode.TAB){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 0);

					s.setKeyClicked("TAB",false);
					temp2.setOpacity(1);
					


				}
				if(event.getCode()==KeyCode.DIGIT1){
					mChannels[0].noteOn(s.getNoteValue("ONE"),0);
					s.setKeyClicked("ONE",false);
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.Q){
					mChannels[0].noteOn(s.getNoteValue("Q"), 0);
					s.setKeyClicked("Q",false);
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT2){
					mChannels[0].noteOn(s.getNoteValue("TWO"), 0);
					s.setKeyClicked("TWO",false);
					temp2.setOpacity(1);
				}
				if(event.getCode()==KeyCode.BACK_SPACE){
					mChannels[0].noteOn(s.getNoteValue("BACKSPACE"), 0);
					s.setKeyClicked("BACKSPACE",false);
					temp2.setOpacity(1);
				}








			}

		};


		Scene scene=new Scene(bp,900,900);
		scene.getStylesheets().add("style.css");
		scene.setOnKeyPressed(evPress);
		scene.setOnKeyReleased(evRelease);
		key.setOnMouseClicked(mousePress);
		save.setOnMouseClicked(mousePress2);
		// scene.setOnKeyTyped(ev);
		primaryStage.setScene(scene);
		primaryStage.show();
	}




}



