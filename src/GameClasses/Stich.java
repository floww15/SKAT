package GameClasses;

import java.io.Serializable;

public class Stich implements Serializable{
	Karte k1;
	Karte k2;
	Karte k3;
	int punkte;
	public Stich(Karte k1,Karte k2,Karte k3) {
		this.k1=k1;
		this.k2=k2;
		this.k3=k3;
		punkte=k1.getPoints()+k2.getPoints()+k3.getPoints();
	}
	public int getPoints() {
		return punkte;
	}
	public String toString() {
		return k1+", "+k2+", "+k3;
	}
}
