package GameClasses;

import java.io.Serializable;
import java.util.Comparator;

public class Karte implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public int getWertAsInt() {
		return Integer.parseInt(wert);
	}

	public String toString() {
		return farbe + " " + wert;
	}

	public int getPoints() {
		return punkte;
	}

	public int getFarbeAsInt() {
		if (farbe.equals("Kreuz"))
			return 3;
		else if (farbe.equals("Pik"))
			return 2;
		else if (farbe.equals("Herz"))
			return 1;
		else
			return 0;
	}

	class KartenComparator implements Comparator<Karte> {
		String trumpf;
		String farbe;

		KartenComparator(String trumpf, String farbe) {
			this.trumpf = trumpf; // Trumpf bzw. was gespielt wird
			this.farbe = farbe; // Farbe die beantwortet werden musste
		}

		@Override
		public int compare(Karte k1, Karte k2) {
			// TODO Auto-generated method stub
			if (trumpf.equals("Grand")) { /** Wenn Grand gespielt wird **/
				if (k1.getWert().equals("Bube")) { /** Falls k1 ein Bube ist **/
					if (!k2.getWert().equals("Bube")) {
						return -1; /** Falls k2 kein Bube ist **/
					} else if (k1.getFarbeAsInt() > k2.getFarbeAsInt()) { /** Falls k2 auch ein Bube ist **/
						return -1;
					} else
						return 1;
				} else {
					if (k2.getWert().equals("Bube")) {
						return 1; /** Falls k2 ein Bube ist, aber k1 nicht **/
					} else {
						if (k1.getPoints() > k2.getPoints())
							return -1; /** Falls die erste Karte der höhere Trumpf ist **/
						else if (k1.getPoints() < k2.getPoints())
							return 1; /** Falls die zweite Karte der höhere Trumpf ist **/
						else {
							if (k1.getWertAsInt() > k2.getWertAsInt()) {
								return -1; /** Wenn beide Zahlen als Karten haben und k1 höher ist **/
							} else
								return 1; /** Wenn beide Zahlen als Karten haben und k2 höher ist **/
						}
					}
				}
			} else if (trumpf.equals("Null")) { /** Wenn Null gespielt wird **/

				if (!k1.getFarbe().equals(farbe)) { /** Wenn k1 nicht die Farbe ist **/
					if (k2.getFarbe().equals(farbe))
						return 1;
					else
						return -1;
				} else { /** Wenn k1 die Farbe ist **/

					if (!k2.getFarbe().equals(farbe)) { /** Falls k2 nicht die Farbe beantwortet **/
						return -1; /** Falls nur die erste Karte richtige Farbe ist **/
					} else { /** Falls k2 die Farbe auch beantwortet **/
						if (k1.getWert().equals("10")) { /** 10er werden eingereiht **/
							if (k2.getPoints() > 1)
								return 1; /** Falls k2 Bube oder Höher **/
							else
								return -1;
						} else if (k2.getWert().equals("10")) {
							if (k1.getPoints() > 1)
								return -1;
							else
								return 1;
						} else if (k1.getPoints() > k2.getPoints())
							return -1; /** Falls die erste Karte die höhere Karte ist **/
						else if (k1.getPoints() < k2.getPoints())
							return 1; /** Falls die zweite Karte die höhere Karte ist **/
						else {
							if (k1.getWertAsInt() > k2.getWertAsInt()) {
								return -1; /** Wenn beide Zahlen als Karten haben und k1 höher ist **/
							} else
								return 1; /** Wenn beide Zahlen als Karten haben und k2 höher ist **/
						}
					}
				}
			} else {
				if (k1.getWert().equals("Bube")) { /** Falls k1 ein Bube ist **/
					if (!k2.getWert().equals("Bube")) {
						return -1; /** Falls k2 kein Bube ist **/
					} else if (k1.getFarbeAsInt() > k2.getFarbeAsInt()) { /** Falls k2 auch ein Bube ist **/
						return -1;
					} else
						return 1;

				} else {
					if (k2.getWert().equals("Bube"))
						return 1; /** Falls k2 ein Bube ist, aber k1 nicht **/
					else { /** Falls beide keine Buben sind **/
						if (!k1.getFarbe().equals(trumpf)
								&& !k1.getFarbe().equals(farbe)) { /** Falls k1 Abwerfkarte ist **/
							if (!k2.getFarbe().equals(trumpf) && !k2.getFarbe().equals(farbe)) {
								return -1; /** Falls beide Karten abwerfkarten sind **/
							} else {
								return 1; /** Falls nur die erste Karte eine Abwerfkarte ist **/
							}
						} else { /** Falls k1 keine Abwerfkarte ist **/
							if (!k2.getFarbe().equals(trumpf) && !k2.getFarbe().equals(farbe)) {
								return -1; /** Falls nur die zweite Karte eine Abwerfkarte ist **/
							} else {
								if (k1.getFarbe().equals(trumpf)) { /** Falls k1 Trumpf ist **/
									if (!k2.getFarbe().equals(trumpf)) {
										return -1; /** Falls nur die erste Karte trumpf ist **/
									} else {
										if (k1.getPoints() > k2.getPoints())
											return -1; /** Falls die erste Karte der höhere Trumpf ist **/
										else if (k1.getPoints() < k2.getPoints())
											return 1; /** Falls die zweite Karte der höhere Trumpf ist **/
										else {
											if (k1.getWertAsInt() > k2.getWertAsInt()) {
												return -1; /** Wenn beide Zahlen als Karten haben und k1 höher ist **/
											} else
												return 1; /** Wenn beide Zahlen als Karten haben und k2 höher ist **/
										}
									}
								} else { /** Falls k1 die Farbe beantwortet **/
									if (!k2.getFarbe().equals(farbe)) { /** Falls k2 nicht die Farbe beantwortet **/
										return -1; /** Falls nur die erste Karte richtige Farbe ist **/
									} else { /** Falls k2 die Farbe auch beantwortet **/
										if (k1.getPoints() > k2.getPoints())
											return -1; /** Falls die erste Karte die höhere Karte ist **/
										else if (k1.getPoints() < k2.getPoints())
											return 1; /** Falls die zweite Karte die höhere Karte ist **/
										else {
											if (k1.getWertAsInt() > k2.getWertAsInt()) {
												return -1; /** Wenn beide Zahlen als Karten haben und k1 höher ist **/
											} else
												return 1; /** Wenn beide Zahlen als Karten haben und k2 höher ist **/
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
