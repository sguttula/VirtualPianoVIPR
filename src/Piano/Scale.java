package Piano;

import java.io.Serializable;

public class Scale implements Serializable{
	private String name;
	private int[] notes;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getNotes() {
		return notes;
	}
	public void setNotes(int[] notes) {
		this.notes = notes;
	}
	public Scale(String name,int[] notes){
		this.name=name;
		this.notes=notes;
	}
	public String toString(){
		String temp="";
		temp=name+" ";
		for(int i=0;i<notes.length;i++){
			temp=temp+" "+notes[i];
		}
		return temp;
	}
}
