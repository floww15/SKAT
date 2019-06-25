package GameClasses;

public class Karte {
	private String farbe;
	private String wert;
	private int punkte;
	public Karte(String f, String w) {
		farbe = f;
		wert = w;
		switch (wert) {
		case "10":
			punkte = 10;
			break;
		case "Bube":
			punkte = 2;
			break;
		case "Dame":
			punkte = 3;
			break;
		case "Koenig":
			punkte = 4;
			break;
		case "Ass":
			punkte = 11;
			break;
		default:
			punkte = 0;
			break;

		}
	}

	public String getFarbe() {
		return farbe;
	}

	public String getWert() {
		return wert;
	}

	public String toString() {
		return farbe + " " + wert;
	}

	public int getPoints() {
		return punkte;
	}
}
