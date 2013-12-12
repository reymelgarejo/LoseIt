package com.example.loseit;

public class Exercise {
	
	public int icon;
	public String name;
	
	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	/*public Exercise(){
		super();
	}*/
	
	public Exercise(int icon, String name){
		super();
		this.icon = icon;
		this.name = name;
	}

}
