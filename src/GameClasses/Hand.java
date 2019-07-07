package GameClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Hand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Karte> handkarten;

	public Hand() {	/** standard constructor**/
		handkarten = new ArrayList<Karte>();
	}

	public Hand(ArrayList<Karte> list) {/**custom constructor, dem eine arraylist aus karten übergeben wird**/
		handkarten = list;
	}

	public int getSize() {/** gibt die anzahl an karten auf der hand an**/
		return handkarten.size();
	}

	public ArrayList<Karte> getHandkarten() {/** gibt die arraylist an karten auf der hand aus**/
		return handkarten;
	}

	public void add(Karte k) {/** fügt der hand eine Karte hinzu   **/
		handkarten.add(k);
	}

	public Karte get(int i) {/**  gibt die karte auf position i der hand zurück  **/
		return handkarten.get(i);
	}

	public void removeD(Karte k) {/**  löscht die karte k von der hand  **/
		handkarten.remove(k);
	}

	public boolean remove(Karte k) {	/** setzt die karte k auf der hand auf den wert null  **/
		for (Karte t : handkarten) {
			if (t != null) {
				if (t.getFarbe().equals(k.getFarbe())) {
					if (t.getWert().equals(k.getWert())) {
						handkarten.set(handkarten.indexOf(t), null);
						System.out.println("DONE");
						return true;
					}
				}
			}
		}
//		return handkarten.remove(k);
		return false;
	}

	public Karte remove(int i) {/**  löscht die karte an position i   **/
		System.out.println(handkarten);
		Karte k = handkarten.get(i);
		this.remove(handkarten.get(i));
		System.out.println(handkarten); // zum testen
		return k;
	}

	public String toString() {/**  gibt eine string repräsentation der Hand wieder  **/
		String res = "";
		for (int i = 0; i < handkarten.size(); i++)
			res += handkarten.get(i) + "\n";
		return res;

	}

	public boolean contains(String s) {	/** contains methode, falls eine farbe angespielt wird, die nicht trumpf ist**/
		for (int i = 0; i < handkarten.size(); i++) {
			System.out.println(handkarten);
			if (handkarten.get(i) != null) {
				if (handkarten.get(i).getFarbe().equals(s)  && !handkarten.get(i).getWert().equals("Bube") )
					return true;
			}
		}
		return false;
	}

	public boolean containsTrumpf(String s) {	/** contains methode, falls die farbe angespielt wird, die auch trumpf ist**/
		for (int i = 0; i < handkarten.size(); i++) {
			System.out.println(handkarten);
			if (handkarten.get(i) != null) {
				if (handkarten.get(i).getFarbe().equals(s) || handkarten.get(i).getWert().equals("Bube"))
					return true;
			}
		}
		return false;
		// CONTAINS MUESSEN AUCH NOCH UEBERPRUEFEN, OB DER WERT BEIM FARBEN UEBERPRUEFEN
		// BUBE IST
	}

	public boolean containsNull(String s) {	/**contains für spielmodus null**/
		for (int i = 0; i < handkarten.size(); i++) {
			if (handkarten.get(i) != null) {
				if (handkarten.get(i).getFarbe().equals(s))
					return true;
			}
		}
		return false;
	}

	public boolean containsBube() {/** containsmethode, die bei grand schaut, ob ein trumpf vorhanden ist**/
		for (int i = 0; i < handkarten.size(); i++) {
			if (handkarten.get(i) != null) {
				if (handkarten.get(i).getWert().equals("Bube"))
					return true;
			}
		}
		return false;
	}

	public boolean containsTrumpfGrand(String farbe) {	/** contains methode, für grand, wenn kein trumpf gespielt wurde**/
		for (int i = 0; i < handkarten.size(); i++) {
			if (handkarten.get(i) != null) {
				if (handkarten.get(i).getFarbe().equals(farbe) && !handkarten.get(i).getWert().equals("Bube"))
					return true;
			}
		}
		return false;
	}

	public static class HandComparator implements Comparator<Karte> {/**   comparator, der benutzt wird, um die handkarten zu sortieren   **/

		@Override
		public int compare(Karte k1, Karte k2) {
			// TODO Auto-generated method stub
			if (k1.getWert().equals("Bube")) { /** wenn k1 ein Bube ist **/
				if (!k2.getWert().equals("Bube"))
					return 1; /** wenn k1 bube und k2 kein bube **/
				else if (k1.getFarbeAsInt() > k2.getFarbeAsInt())
					return 1; /** wenn k1 bube und k2 auch bube und k1 bube höher **/
				else
					return -1; /** wenn k1 bube und k2 auch bube und k2 bube drunter **/
			} else {
				if (k2.getWert().equals("Bube"))
					return -1; /** wenn k2 ein bube ist und k1 nicht **/
				else if (k1.getFarbeAsInt() > k2.getFarbeAsInt())
					return 1; /** Niedrigeste karte zuerst **/
				else if (k1.getFarbeAsInt() < k2.getFarbeAsInt())
					return -1; /** sortieren **/
				else {
					if (k1.getPoints() > k2.getPoints()) {
						return 1;
					} else if (k1.getPoints() < k2.getPoints())
						return -1;
					else {
						if (k1.getWertAsInt() > k2.getWertAsInt()) {
							return 1;
						} else if (k1.getWertAsInt() < k2.getWertAsInt())
							return -1;
					}

				}
				return 0;
			}
		}

	}
}
