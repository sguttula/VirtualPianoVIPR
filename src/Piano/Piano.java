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
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
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

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class Piano extends Application{

	BorderPane bp;
	ImageView[] whitekeys=new ImageView[15];
	ImageView[] blackkeys=new ImageView[10];
	ImageView[] whiteKeyPressed=new ImageView[15];
	PianoSystem s=new PianoSystem();
	Sequence sequence;
	InputStream is;

	Image keyPressedImage=new Image(new File("keyPressed.png").toURI().toString());
	Image whiteKey=new Image(new File("key.png").toURI().toString());
	Image blackKey=new Image(new File("keyBlack.png").toURI().toString());
	Image overLay=new Image(new File("board.png").toURI().toString());
	Image greenKey=new Image(new File("keyGreen.png").toURI().toString());


	ArrayList<Rectangle> rectangles=new ArrayList<Rectangle>();
	ArrayList<ImageView> notes=new ArrayList<ImageView>();


	//int[] melody=generateMelody();
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * draws keys on screen
	 */
	public void setupKeys(){

		int posX=70;
		for(int i=0;i<15;i++){ 


			whiteKeyPressed[i]=new ImageView(keyPressedImage);
			whiteKeyPressed[i].setX(posX);
			whiteKeyPressed[i].setY(640);

			whitekeys[i]=new ImageView(whiteKey);
			whitekeys[i].setX(posX);
			whitekeys[i].setY(640);

			posX+=110;
			bp.getChildren().add(whiteKeyPressed[i]);
			bp.getChildren().add(whitekeys[i]);




		}

		setupBlackKeys();
	}

	/**
	 * A method for setting black keys since they are not linear
	 * 
	 */
	public void setupBlackKeys(){
		blackkeys[0]=new ImageView(blackKey);
		blackkeys[0].setX(150);
		blackkeys[0].setY(630);

		blackkeys[1]=new ImageView(blackKey);
		blackkeys[1].setX(270);
		blackkeys[1].setY(630);



		blackkeys[2]=new ImageView(blackKey);
		blackkeys[2].setX(490);
		blackkeys[2].setY(630);

		blackkeys[3]=new ImageView(blackKey);
		blackkeys[3].setX(590);
		blackkeys[3].setY(630);

		blackkeys[4]=new ImageView(blackKey);
		blackkeys[4].setX(700);
		blackkeys[4].setY(630);




		blackkeys[5]=new ImageView(blackKey);
		blackkeys[5].setX(920);
		blackkeys[5].setY(630);

		blackkeys[6]=new ImageView(blackKey);
		blackkeys[6].setX(1040);
		blackkeys[6].setY(630);





		blackkeys[7]=new ImageView(blackKey);
		blackkeys[7].setX(1250);
		blackkeys[7].setY(630);

		blackkeys[8]=new ImageView(blackKey);
		blackkeys[8].setX(1370);
		blackkeys[8].setY(630);

		blackkeys[9]=new ImageView(blackKey);
		blackkeys[9].setX(1490);
		blackkeys[9].setY(630);




		bp.getChildren().add(blackkeys[0]);
		bp.getChildren().add(blackkeys[1]);
		bp.getChildren().add(blackkeys[2]);
		bp.getChildren().add(blackkeys[3]);
		bp.getChildren().add(blackkeys[4]);
		bp.getChildren().add(blackkeys[5]);
		bp.getChildren().add(blackkeys[6]);
		bp.getChildren().add(blackkeys[7]);
		bp.getChildren().add(blackkeys[8]);
		bp.getChildren().add(blackkeys[9]);
	}

	/*
	 * 
	 */
	public void addNotes(){
		for(int i=0;i<rectangles.size();i++){
			notes.add(new ImageView(blackKey));

			if(rectangles.get(i).x==0)
				notes.get(i).setX(whitekeys[0].getX()+40);
			if(rectangles.get(i).x==2)
				notes.get(i).setX(whitekeys[1].getX()+40);
			if(rectangles.get(i).x==4)
				notes.get(i).setX(whitekeys[2].getX()+40);
			if(rectangles.get(i).x==5)
				notes.get(i).setX(whitekeys[3].getX()+40);
			if(rectangles.get(i).x==7)
				notes.get(i).setX(whitekeys[4].getX()+40);
			if(rectangles.get(i).x==9)
				notes.get(i).setX(whitekeys[5].getX()+40);
			if(rectangles.get(i).x==11)
				notes.get(i).setX(whitekeys[6].getX()+40);
			if(rectangles.get(i).x==12)
				notes.get(i).setX(whitekeys[7].getX()+40);
			if(rectangles.get(i).x==14)
				notes.get(i).setX(whitekeys[8].getX()+40);
			if(rectangles.get(i).x==16)
				notes.get(i).setX(whitekeys[9].getX()+40);
			if(rectangles.get(i).x==17)
				notes.get(i).setX(whitekeys[10].getX()+40);
			if(rectangles.get(i).x==19)
				notes.get(i).setX(whitekeys[10].getX()+40);
			if(rectangles.get(i).x==21)
				notes.get(i).setX(whitekeys[11].getX()+40);
			if(rectangles.get(i).x==23)
				notes.get(i).setX(whitekeys[12].getX()+40);
			if(rectangles.get(i).x==24)
				notes.get(i).setX(whitekeys[13].getX()+40);



			if(rectangles.get(i).x==1)
				notes.get(i).setX(blackkeys[0].getX());
			if(rectangles.get(i).x==3)
				notes.get(i).setX(blackkeys[1].getX());
			if(rectangles.get(i).x==6)
				notes.get(i).setX(blackkeys[2].getX());
			if(rectangles.get(i).x==8)
				notes.get(i).setX(blackkeys[3].getX());
			if(rectangles.get(i).x==10)
				notes.get(i).setX(blackkeys[4].getX());
			if(rectangles.get(i).x==13)
				notes.get(i).setX(blackkeys[5].getX());
			if(rectangles.get(i).x==15)
				notes.get(i).setX(blackkeys[6].getX());
			if(rectangles.get(i).x==18)
				notes.get(i).setX(blackkeys[7].getX());
			if(rectangles.get(i).x==20)
				notes.get(i).setX(blackkeys[8].getX());
			if(rectangles.get(i).x==22)
				notes.get(i).setX(blackkeys[9].getX());


			notes.get(i).setY(rectangles.get(i).y);
			//			notes.get(i).setScaleX(rectangles.get(i).width/900.0);
			//set y scale sets the length of the drawing, .2 is less accurate lengths but more accurate spacings
			notes.get(i).setScaleY(rectangles.get(i).height/234.0);
			bp.getChildren().add(notes.get(i));
		}

	}

	/**
	 * Removes the old notes from border pane when loading new files
	 */
	public void removeNotes(){
		for(int i=0;i<notes.size();i++){
			bp.getChildren().remove(notes.get(i));
		}
	}




	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//primaryStage.setOnCloseRequest(e -> Platform.exit());


		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});



		s.update();
		//loads a midi file and starts converting to rectangle []
		//basically like recursion
		//	Sequence sequence = MidiSystem.getSequence(new File("mary.mid"));


		//rectangles=toRectangle(sequence.getTracks()[1]);
		System.out.println(rectangles.size());
		System.out.println(notes.size());


		bp=new BorderPane();




		setupKeys();

		//black bar where the notes start playing
		ImageView b=new ImageView(blackKey);
		b.setX(0);
		b.setY(520);
		b.setScaleX(80);
		b.setScaleY(0.1);
		bp.getChildren().add(b);

		//adds the converted notes to the screen



		ImageView keyOverLay=new ImageView(overLay);
		keyOverLay.setX(25);
		keyOverLay.setY(585);
		bp.getChildren().add(keyOverLay);



		HBox area=new HBox();
		Button play=new Button("PLAY");
		Button save=new Button("RECORD");
		Button load=new Button("LOAD A SONG");


		play.setMinSize(200, 100);
		save.setMinSize(200, 100);
		load.setMinSize(200,100);


		area.setLayoutX(0);
		area.setLayoutY(930);
		area.getChildren().add(load);
		area.getChildren().add(play);
		area.getChildren().add(save);
		//key.setLayoutX(300);
		//bp.setTop(area);
		bp.getChildren().add(area);
		//bp.scaleXProperty().bind(maxScale);

		StackPane root = new StackPane(bp);
		// root.setAlignment(Pos.TOP_LEFT);

		// use gridPane size to determine the factor to scale by
		NumberBinding maxScale = Bindings.min(root.widthProperty().divide(1900),
				root.heightProperty().divide(1080));
		bp.scaleXProperty().bind(maxScale);
		bp.scaleYProperty().bind(maxScale);


		Sequencer player=MidiSystem.getSequencer();

		player.open();


		Synthesizer midiSynth = MidiSystem.getSynthesizer(); 

		midiSynth.open();
		Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
		MidiChannel[] mChannels = midiSynth.getChannels();
		System.out.println(mChannels.length);
		midiSynth.loadInstrument(instr[0]);//load an instrument






		EventHandler evPress =new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if(event.getCode()==KeyCode.TAB&&!s.isKeyClicked("TAB")){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 100);

					s.setKeyClicked("TAB",true);
					System.out.println("TAB");


					whitekeys[0].setOpacity(0.2);



				}
				if(event.getCode()==KeyCode.DIGIT1&&!s.isKeyClicked("ONE")){
					mChannels[0].noteOn(s.getNoteValue("ONE"), 100);
					s.setKeyClicked("ONE",true);
					blackkeys[0].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.Q&&!s.isKeyClicked("Q")){
					mChannels[0].noteOn(s.getNoteValue("Q"), 100);
					s.setKeyClicked("Q", true);
					whitekeys[1].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.W&&!s.isKeyClicked("W")){
					mChannels[0].noteOn(s.getNoteValue("W"), 100);
					s.setKeyClicked("W", true);
					whitekeys[2].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.E&&!s.isKeyClicked("E")){
					mChannels[0].noteOn(s.getNoteValue("E"), 100);
					s.setKeyClicked("E", true);
					whitekeys[3].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.R&&!s.isKeyClicked("R")){
					mChannels[0].noteOn(s.getNoteValue("R"), 100);
					s.setKeyClicked("R", true);
					whitekeys[4].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.T&&!s.isKeyClicked("T")){
					mChannels[0].noteOn(s.getNoteValue("T"), 100);
					s.setKeyClicked("T", true);
					whitekeys[5].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.Y&&!s.isKeyClicked("Y")){
					mChannels[0].noteOn(s.getNoteValue("Y"), 100);
					s.setKeyClicked("Y", true);
					whitekeys[6].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.U&&!s.isKeyClicked("U")){
					mChannels[0].noteOn(s.getNoteValue("U"), 100);
					s.setKeyClicked("U", true);
					whitekeys[7].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.I&&!s.isKeyClicked("I")){
					mChannels[0].noteOn(s.getNoteValue("I"), 100);
					s.setKeyClicked("I", true);
					whitekeys[8].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.O&&!s.isKeyClicked("O")){
					mChannels[0].noteOn(s.getNoteValue("O"), 100);
					s.setKeyClicked("O", true);
					whitekeys[9].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.P&&!s.isKeyClicked("P")){
					mChannels[0].noteOn(s.getNoteValue("P"), 100);
					s.setKeyClicked("P", true);
					whitekeys[10].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.OPEN_BRACKET&&!s.isKeyClicked("RBRACKET")){
					mChannels[0].noteOn(s.getNoteValue("RBRACKET"), 100);
					s.setKeyClicked("RBRACKET", true);
					whitekeys[11].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.CLOSE_BRACKET&&!s.isKeyClicked("LBRACKET")){
					mChannels[0].noteOn(s.getNoteValue("LBRACKET"), 100);
					s.setKeyClicked("LBRACKET", true);
					whitekeys[12].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.BACK_SLASH&&!s.isKeyClicked("SLASH")){
					mChannels[0].noteOn(s.getNoteValue("SLASH"), 100);
					s.setKeyClicked("SLASH", true);
					whitekeys[13].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.HOME&&!s.isKeyClicked("HOME")){
					mChannels[0].noteOn(s.getNoteValue("HOME"), 100);
					s.setKeyClicked("HOME", true);
					whitekeys[14].setOpacity(0.2);
				}


				//BLACK KEYS
				if(event.getCode()==KeyCode.DIGIT2&&!s.isKeyClicked("TWO")){
					mChannels[0].noteOn(s.getNoteValue("TWO"), 100);
					s.setKeyClicked("TWO", true);
					System.out.println("TWO");
					blackkeys[1].setOpacity(0.2);
				}


				if(event.getCode()==KeyCode.DIGIT4&&!s.isKeyClicked("FOUR")){
					mChannels[0].noteOn(s.getNoteValue("FOUR"), 100);
					s.setKeyClicked("FOUR", true);
					blackkeys[2].setOpacity(0.2);

				}
				if(event.getCode()==KeyCode.DIGIT5&&!s.isKeyClicked("FIVE")){
					mChannels[0].noteOn(s.getNoteValue("FIVE"), 100);
					s.setKeyClicked("FIVE", true);
					blackkeys[3].setOpacity(0.2);

				}
				if(event.getCode()==KeyCode.DIGIT6&&!s.isKeyClicked("SIX")){
					mChannels[0].noteOn(s.getNoteValue("SIX"), 100);
					s.setKeyClicked("SIX", true);
					blackkeys[4].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.DIGIT8&&!s.isKeyClicked("EIGHT")){
					mChannels[0].noteOn(s.getNoteValue("EIGHT"), 100);
					s.setKeyClicked("EIGHT", true);
					blackkeys[5].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.DIGIT9&&!s.isKeyClicked("NINE")){
					mChannels[0].noteOn(s.getNoteValue("NINE"), 100);
					s.setKeyClicked("NINE", true);
					blackkeys[6].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.MINUS&&!s.isKeyClicked("MINUS")){
					mChannels[0].noteOn(s.getNoteValue("MINUS"), 100);
					s.setKeyClicked("MINUS", true);
					blackkeys[7].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.EQUALS&&!s.isKeyClicked("EQUALS")){
					mChannels[0].noteOn(s.getNoteValue("EQUALS"), 100);
					s.setKeyClicked("EQUALS", true);
					blackkeys[8].setOpacity(0.2);
				}
				if(event.getCode()==KeyCode.BACK_SPACE&&!s.isKeyClicked("BACKSPACE")){
					mChannels[0].noteOn(s.getNoteValue("BACKSPACE"), 100);
					s.setKeyClicked("BACKSPACE", true);
					System.out.println("BACK");
					blackkeys[9].setOpacity(0.2);
				}

			}

		};





		//----------Used for setting values after release-------
		EventHandler evRelease =new EventHandler<KeyEvent>() {

			@Override
			public void handle(final KeyEvent event) {

				if(event.getCode()==KeyCode.TAB){
					mChannels[0].noteOn(s.getNoteValue("TAB"), 0);

					s.setKeyClicked("TAB",false);
					whitekeys[0].setOpacity(1);



				}

				if(event.getCode()==KeyCode.Q){
					mChannels[0].noteOn(s.getNoteValue("Q"), 0);
					s.setKeyClicked("Q",false);
					whitekeys[1].setOpacity(1);
				}


				if(event.getCode()==KeyCode.W){
					mChannels[0].noteOn(s.getNoteValue("W"), 0);
					s.setKeyClicked("W",false);
					whitekeys[2].setOpacity(1);
				}
				if(event.getCode()==KeyCode.E){
					mChannels[0].noteOn(s.getNoteValue("E"), 0);
					s.setKeyClicked("E",false);
					whitekeys[3].setOpacity(1);
				}
				if(event.getCode()==KeyCode.R){
					mChannels[0].noteOn(s.getNoteValue("R"), 0);
					s.setKeyClicked("R",false);
					whitekeys[4].setOpacity(1);
				}
				if(event.getCode()==KeyCode.T){
					mChannels[0].noteOn(s.getNoteValue("T"), 0);
					s.setKeyClicked("T",false);
					whitekeys[5].setOpacity(1);
				}
				if(event.getCode()==KeyCode.Y){
					mChannels[0].noteOn(s.getNoteValue("Y"), 0);
					s.setKeyClicked("Y",false);
					whitekeys[6].setOpacity(1);
				}
				if(event.getCode()==KeyCode.U){
					mChannels[0].noteOn(s.getNoteValue("U"), 0);
					s.setKeyClicked("U",false);
					whitekeys[7].setOpacity(1);
				}
				if(event.getCode()==KeyCode.I){
					mChannels[0].noteOn(s.getNoteValue("I"), 0);
					s.setKeyClicked("I",false);
					whitekeys[8].setOpacity(1);
				}
				if(event.getCode()==KeyCode.O){
					mChannels[0].noteOn(s.getNoteValue("O"), 0);
					s.setKeyClicked("O",false);
					whitekeys[9].setOpacity(1);
				}
				if(event.getCode()==KeyCode.P){
					mChannels[0].noteOn(s.getNoteValue("P"), 0);
					s.setKeyClicked("P",false);
					whitekeys[10].setOpacity(1);
				}
				if(event.getCode()==KeyCode.OPEN_BRACKET){
					mChannels[0].noteOn(s.getNoteValue("RBRACKET"), 0);
					s.setKeyClicked("RBRACKET",false);
					whitekeys[11].setOpacity(1);
				}
				if(event.getCode()==KeyCode.CLOSE_BRACKET){
					mChannels[0].noteOn(s.getNoteValue("LBRACKET"), 0);
					s.setKeyClicked("LBRACKET",false);
					whitekeys[12].setOpacity(1);
				}
				if(event.getCode()==KeyCode.BACK_SLASH){
					mChannels[0].noteOn(s.getNoteValue("SLASH"), 0);
					s.setKeyClicked("SLASH",false);
					whitekeys[13].setOpacity(1);
				}
				if(event.getCode()==KeyCode.HOME){
					mChannels[0].noteOn(s.getNoteValue("HOME"), 0);
					s.setKeyClicked("HOME",false);
					whitekeys[14].setOpacity(1);
				}
				//black keys 
				if(event.getCode()==KeyCode.DIGIT1){
					mChannels[0].noteOn(s.getNoteValue("ONE"),0);
					s.setKeyClicked("ONE",false);
					blackkeys[0].setOpacity(1);

				}

				if(event.getCode()==KeyCode.DIGIT2){
					mChannels[0].noteOn(s.getNoteValue("TWO"), 0);
					s.setKeyClicked("TWO",false);
					blackkeys[1].setOpacity(1);
				}

				if(event.getCode()==KeyCode.DIGIT4){
					mChannels[0].noteOn(s.getNoteValue("FOUR"), 0);
					s.setKeyClicked("FOUR",false);
					blackkeys[2].setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT5){
					mChannels[0].noteOn(s.getNoteValue("FIVE"), 0);
					s.setKeyClicked("FIVE",false);
					blackkeys[3].setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT6){
					mChannels[0].noteOn(s.getNoteValue("SIX"), 0);
					s.setKeyClicked("SIX",false);
					blackkeys[4].setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT8){
					mChannels[0].noteOn(s.getNoteValue("EIGHT"), 0);
					s.setKeyClicked("EIGHT",false);
					blackkeys[5].setOpacity(1);
				}
				if(event.getCode()==KeyCode.DIGIT9){
					mChannels[0].noteOn(s.getNoteValue("NINE"), 0);
					s.setKeyClicked("NINE",false);
					blackkeys[6].setOpacity(1);
				}
				if(event.getCode()==KeyCode.MINUS){
					mChannels[0].noteOn(s.getNoteValue("MINUS"), 0);
					s.setKeyClicked("MINUS",false);
					blackkeys[7].setOpacity(1);
				}

				if(event.getCode()==KeyCode.EQUALS){
					mChannels[0].noteOn(s.getNoteValue("EQUALS"), 0);
					s.setKeyClicked("EQUALS",false);
					blackkeys[8].setOpacity(1);
				}
				if(event.getCode()==KeyCode.BACK_SPACE){
					mChannels[0].noteOn(s.getNoteValue("BACKSPACE"), 0);
					s.setKeyClicked("BACKSPACE",false);
					blackkeys[9].setOpacity(1);
				}



			}

		};



		//controls the playback of the notes scrolling through
		AnimationTimer at=new AnimationTimer(){

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				double speed=3.2;
				for(int i=0;i<rectangles.size();i++){


					notes.get(i).setY(notes.get(i).getY()+speed);
					//					notes.get(i).setScaleX(rectangles.get(i).width/900.0);
					//					notes.get(i).setScaleY(.1);
					if (notes.get(i).getY()>520&&notes.get(i).getY()<530){

					}
				}
			}

		};

		EventHandler playButton =new EventHandler() {
			boolean playing=false;
			long pos=0;
			@Override
			public void handle(Event event) {
				//	mChannels[0].noteOn(53, 100);
				if(playing){
					playing=false;
					pos=player.getTickPosition();
					player.stop();
					play.setText("Pause");
					at.stop();

				}else{
					play.setText("Play");
					playing=true;
					player.setTickPosition(pos);

					player.start();
					at.start();

				}

			}

		};
		EventHandler recordButton =new EventHandler() {

			@Override
			public void handle(Event event) {
				//	mChannels[0].noteOn(53, 100);
				if(s.isRecording()){
					s.setRecording(false);
					System.out.println("STOPPED");
					save.setText("Stopped");

					s.renderRecording();
					player.stop();
					at.stop();

				}else{
					save.setText("Recording");
					System.out.println("RECORDING");

					s.setRecording(true);
					player.start();
					at.start();
				}

			}

		};
		FileChooser fileChooser = new FileChooser();

		EventHandler fileLoad=  new EventHandler() {



			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file!=null){
					try {
						player.stop();
						at.stop();
						removeNotes();
						Sequence sequence = MidiSystem.getSequence(file);
						rectangles=toRectangle(sequence.getTracks()[1]);
						addNotes();

						InputStream is = new BufferedInputStream(new FileInputStream(file));

						player.setSequence(is);

						player.setTickPosition(0);


					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}


			}
		};





		Scene scene=new Scene(root,1280,720);
		//scene.getStylesheets().add("style.css");
		load.setOnMouseClicked(fileLoad);
		scene.setOnKeyPressed(evPress);
		scene.setOnKeyReleased(evRelease);
		play.setOnMouseClicked(playButton);
		save.setOnMouseClicked(recordButton);
		// scene.setOnKeyTyped(ev);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * Recursively call this method to convert midi tracks to rectangles[] ready to be drawn
	 * 
	 * @param track
	 * @return
	 */
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
				//position will be a value from 0-25 
				int xPos=(note-53);
				long length=end.getTick()-begin.getTick();
				System.out.println("start "+(-begin.getTick())+" note "+sm.getData1()+" length"+length);
				//setting note as the x value so the other method knows what note to play
				temp.add(new Rectangle(xPos,(int) (-begin.getTick()*.1)+500, 100, (int)(0.1*length)));
			}
			track.remove(begin);
			track.remove(end);


		}
		return temp;
	}



}



