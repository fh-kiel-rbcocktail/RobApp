package de.fh_kiel.cimtt.robotik;

import com.kuka.roboticsAPI.geometricModel.Frame;

public class Stone {

	private int color;
	private Frame position;
	
	public Stone(int Color, Frame Position){
		this.color = Color;
		this.position = Position;
	}
	public Stone(){
		this.color = -1;
		this.position = null;
	}
	
	public void setPosition(Frame Position){
		if(Position != null){
			this.position = Position;
		}
	}
	
	public Frame getPosition(){
		return this.position;
	}
	
	public void setColor(int Color){
		if(Color >= 0){
			this.color = Color;
		}
	}
	
	public int getColor(){
		return this.color;
	}
	
	/**
	 * Die Methode prüft, ob der aktuelle Stein dunkler als
	 * der übergebene Stein ist.
	 * @param stone	- Stein mit dem Verglichen wird
	 * @return	
	 */
	public boolean isDarker(Stone stone){
		if(this.getColor() < stone.getColor()){
			return true;
		}
		return false;
	}
}
