package Piano;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PianoSystem {
	private File f;
	private ArrayList<Scale> scales=new ArrayList<Scale>();
	
	//contains the int values that correspond to the notes
	private int[] noteValue;
	
	//contains one boolean per key to keep track of which is clicked
	private boolean[] clickedValue;
	
	//Contains the String constants that correspond to the keyboard
	private String[] keyBoard={"TAB","ONE","Q","TWO","W","E","FOUR","R","FIVE","T","SIX","Y","U","EIGHT","I","NINE","O","P","MINUS","RBRACKET","EQUALS","LBRACKET","BACKSPACE","SLASH","HOME"};
	/*
	 * Constructor checks if the file exists, if not then creates one
	 */
	public PianoSystem(){
		clickedValue=new boolean[25];
		
		
		noteValue=new int[25];
		int temp=53;
		for(int i=0;i<25;i++){
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
	/**
	 * Reads the contents of the file and re populates them into an ArrayList 
	 * 
	 */
	public void update(){
		try {
			Scanner input=new Scanner(f);
			
				
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}
	/*
	 * Adds a scale to the database of scales
	 */
	public void addScale(Scale s) {
		scales.add(s);
		try {
			writeFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Saves the current ArrayList of scales to a file
	 * @throws FileNotFoundException 
	 */
	private void writeFile() throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(new FileOutputStream("scales.txt"));
		for(int i=0;i<scales.size();i++){
			pw.println(scales.get(i).toString());
			
			
		}
		pw.close();
	}
	/**
	 * 
	 * 
	 * @param val takes in a String value and returns an int corresponding to the noteValue
	 * @return
	 */
	public int getNoteValue(String val){
		for(int i=0;i<keyBoard.length;i++){
			if(keyBoard[i].equals(val))
				return noteValue[i];
			
		}
		
		
		return 60;
		
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

	
	
	
	
	
	
}
