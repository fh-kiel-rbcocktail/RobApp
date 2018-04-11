package de.fh_kiel.cimtt.robotik;

import com.kuka.roboticsAPI.geometricModel.Frame;

public class Bildpunkt {
	private Frame position;
	private int color;
	private boolean isSet;
	
	public Bildpunkt(){
		this.isSet = false;
	}
	public Bildpunkt(Frame Position, int Color){
		this.color = Color;
		this.position = Position;
		this.isSet = false;
	}
	
	private void setPlace(){
		this.isSet = true;
	}

	public void setPunkt(Frame Position, int Color){
		this.position = Position.copy();
		this.color = Color;
		this.isSet = false;
		if(this.color == 0){
			this.isSet = true;
		}
	}
	
	public void setPosition(Frame Position){
		this.position = Position;
	}
	
	public Frame getPosition(){
		if(this.isSet == false){
			setPlace();
			return this.position;
		}
		return null;
	}
	
	public boolean getIsPlace(){
		return this.isSet;
	}
	
	public Frame getNextPoint(int Color){
		if(this.isSet == false && this.color == Color){
			this.isSet = true;
			return this.position;
		}
		return null;
	}
}
