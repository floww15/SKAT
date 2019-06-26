package GameClasses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class KartenStapel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Karte> stapel=new ArrayList<Karte>();
	public KartenStapel() {
		stapel.add(new Karte("Kreuz","7"));
		stapel.add(new Karte("Kreuz","8"));
		stapel.add(new Karte("Kreuz","9"));
		stapel.add(new Karte("Kreuz","10"));
		stapel.add(new Karte("Kreuz","Bube"));
		stapel.add(new Karte("Kreuz","Dame"));
		stapel.add(new Karte("Kreuz","Koenig"));
		stapel.add(new Karte("Kreuz","Ass"));
		
		stapel.add(new Karte("Pik","7"));
		stapel.add(new Karte("Pik","8"));
		stapel.add(new Karte("Pik","9"));
		stapel.add(new Karte("Pik","10"));
		stapel.add(new Karte("Pik","Bube"));
		stapel.add(new Karte("Pik","Dame"));
		stapel.add(new Karte("Pik","Koenig"));
		stapel.add(new Karte("Pik","Ass"));
		
		stapel.add(new Karte("Herz","7"));
		stapel.add(new Karte("Herz","8"));
		stapel.add(new Karte("Herz","9"));
		stapel.add(new Karte("Herz","10"));
		stapel.add(new Karte("Herz","Bube"));
		stapel.add(new Karte("Herz","Dame"));
		stapel.add(new Karte("Herz","Koenig"));
		stapel.add(new Karte("Herz","Ass"));
		
		stapel.add(new Karte("Karo","7"));
		stapel.add(new Karte("Karo","8"));
		stapel.add(new Karte("Karo","9"));
		stapel.add(new Karte("Karo","10"));
		stapel.add(new Karte("Karo","Bube"));
		stapel.add(new Karte("Karo","Dame"));
		stapel.add(new Karte("Karo","Koenig"));
		stapel.add(new Karte("Karo","Ass"));
		
	
		Collections.shuffle(stapel);
	}
	public Karte getKarte() {
		
		return stapel.remove(0);
		
	}
}
