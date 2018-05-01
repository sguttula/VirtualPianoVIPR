package Piano;


import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.sun.javafx.geom.Rectangle;
import com.sun.javafx.geom.Shape;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReadTest extends Application {
	private BorderPane bp;
	BorderPane sp=new BorderPane();

	//public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	ArrayList<Rectangle> rectanglesOG=new ArrayList<Rectangle>();
	ArrayList<Rectangle> rectanglesUser=new ArrayList<Rectangle>();
	ArrayList<ImageView> notesOG=new ArrayList<ImageView>();
	ArrayList<ImageView> notesUser=new ArrayList<ImageView>();
	public static void main(String[] args) throws Exception {

		launch(args);
	}
	/**
	 * 
	 */
	public void comparetracks(){

		for(int i=0;i<notesOG.size();i++){
			for(int j=0;j<notesUser.size();j++){
				//	overlaps should check if two rectangles overlap
				if(notesOG.get(i).getBoundsInParent().intersects(notesUser.get(j).getBoundsInParent())){
					//correct hit
					System.out.println("Good Hit"+i);
				}
				else{
					//missed hit
				}

			}


		}


	}




	public ArrayList<Rectangle> toRectangle(Track track){
		ArrayList<Rectangle> temp=new ArrayList<Rectangle>();
		while(track.size()>2){
			MidiEvent begin=track.get(0);
			MidiMessage message = begin.getMessage();

			int count=1;
			MidiEvent end=track.get(count);
			MidiMessage messageEnd = end.getMessage();


			if (message instanceof ShortMessage) {
				ShortMessage sm = (ShortMessage) message;



				ShortMessage smEnd = (ShortMessage) message;

				while(sm.getData1()!=smEnd.getData1()){
					count++;
				}
				int note=sm.getData1();
				//position is found by determining which note is played then multiplying by width
				int xPos=(note-53)*50;
				long length=end.getTick()-begin.getTick();
				System.out.println("start "+((int) (-begin.getTick()*.1))+" note "+sm.getData1()+" length"+length);
				temp.add(new Rectangle(xPos,(int) (-begin.getTick()*.1), 100, (int)(length*.1)));
			}
			track.remove(begin);
			track.remove(end);
		}

		return temp;
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Sequence sequenceOriginal = MidiSystem.getSequence(new File("mary.mid"));
		Sequence user = MidiSystem.getSequence(new File("6testWrite.mid"));



		Synthesizer midiSynth = MidiSystem.getSynthesizer(); 

		midiSynth.open();
		Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
		MidiChannel[] mChannels = midiSynth.getChannels();

		midiSynth.loadInstrument(instr[0]);//load an instrument
		System.out.println("OG:");

		rectanglesOG=toRectangle(sequenceOriginal.getTracks()[1]);
		System.out.println(rectanglesOG.size());

		rectanglesUser=toRectangle(user.getTracks()[1]);
		System.out.println(rectanglesUser.size());







		bp=new BorderPane();

		Image blackKey=new Image(new File("keyBlack.png").toURI().toString());
		Image greenKey=new Image(new File("keyGreen.png").toURI().toString());

		ImageView b=new ImageView(blackKey);
		b.setX(0);
		b.setY(520);
		b.setScaleX(50);
		b.setScaleY(0.1);
		bp.getChildren().add(b);

		for(int i=0;i<rectanglesOG.size();i++){
			notesOG.add(new ImageView(blackKey));
			notesOG.get(i).setX(rectanglesOG.get(i).x);
			notesOG.get(i).setY(rectanglesOG.get(i).y);

			//			notes.get(i).setScaleX(rectangles.get(i).width/900.0);
			notesOG.get(i).setScaleY(rectanglesOG.get(i).height/234.0);
			bp.getChildren().add(notesOG.get(i));
		}

		///user-------------
		for(int i=0;i<rectanglesUser.size();i++){
			//			rectanglesUser.get(i).y=(int)(rectanglesUser.get(i).y*1.88);
			//		rectanglesUser.get(i).height=(int)(rectanglesUser.get(i).height*1.88);
			//rectanglesUser.get(i).setBounds(rectanglesUser.get(i).x, (int)(rectanglesUser.get(i).y*1.88),rectanglesUser.get(i).width, (int)(rectanglesUser.get(i).height*1.88));
			notesUser.add(new ImageView(greenKey));
			notesUser.get(i).setX(rectanglesUser.get(i).x);
			notesUser.get(i).setY(rectanglesUser.get(i).y*1.88);

			notesUser.get(i).setOpacity(0.5);


			notesUser.get(i).setScaleY((rectanglesUser.get(i).height/234.0)*1.88);
			bp.getChildren().add(notesUser.get(i));
		}

		comparetracks();



		EventHandler evPress =new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {


				if(event.getCode()==KeyCode.UP){
					//clip.start();
					for(int i=0;i<rectanglesOG.size();i++){
						notesOG.get(i).setY(notesOG.get(i).getY()-50);

					}

					for(int i=0;i<rectanglesUser.size();i++){
						notesUser.get(i).setY(notesUser.get(i).getY()-50);
					}

				}
				if(event.getCode()==KeyCode.DOWN){
					//clip.start();
					for(int i=0;i<rectanglesOG.size();i++){
						notesOG.get(i).setY(notesOG.get(i).getY()+50);

					}

					for(int i=0;i<rectanglesUser.size();i++){
						notesUser.get(i).setY(notesUser.get(i).getY()+50);
					}

				}

			}};






			Scene scene=new Scene(bp,900,900);
			scene.setOnKeyPressed(evPress);

			primaryStage.setScene(scene);
			primaryStage.show();
	}
}
