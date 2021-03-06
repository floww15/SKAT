package SpielAblauf;

import java.rmi.RemoteException;

import Server_Client.RemoteSkatClient;

public class Reizen {
	private int accWert = 0;
	private boolean[] weg = new boolean[3];
	private int anzWeg = 0;
	private boolean ersteRunde = true;
	private boolean sagen = true;
	private RemoteSkatClient[] clients;
	private int[] werte = new int[] { 0, 18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54, 55, 59, 60,
			63, 66, 70, 72, 77, 80, 81, 84, 88, 90, 96, 99, 100, 108, 110, 117, 120, 121, 126, 130, 132, 135, 140, 143,
			144, 153, 156, 160, 162, 165, 168, 170, 176, 180, 187, 192, 198, 204, 216, 240, 264, Integer.MAX_VALUE };
//	boolean change = false;

	public Reizen(RemoteSkatClient[] clients) {
		this.clients = clients;
		try {
			clients[0].startReizen();
			clients[1].startReizen();
			clients[2].startReizen();
			clients[0].reizenStartStats();
			clients[1].reizenStartStats();
			clients[2].reizenStartStats();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void weg(int pos) {
		try {
			if (weg[pos])
				return;
			if (pos == 0 && ersteRunde) {
				System.out.println("nicht in Runde 1 weg " + pos);
				return;
			}
			if (pos == 1 && werte[accWert] == 0) {
				System.out.println("nicht in Runde 1 weg ohne angesagt " + pos);
				return;
			}
			if ((ersteRunde && pos == 2 && !sagen) || (!ersteRunde && pos != 0 && sagen)
					|| (!ersteRunde && pos == 0 && !sagen) || (ersteRunde && pos != 2 && sagen)) {
				System.out.println("nicht dran " + pos);
				return;
			}

//			if (!ersteRunde && (weg[(pos + 1) % 3] || weg[(pos + 2) % 3])) {
//				if (weg[(pos + 1) % 3]) {
//					clients[(pos + 0) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//					clients[(pos + 1) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//					clients[(pos + 2) % 3].startDruecken();
//				} else {
//					clients[(pos + 0) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//					clients[(pos + 1) % 3].startDruecken();
//					clients[(pos + 2) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//				}
//				return;
//				
//			}
			if (ersteRunde && pos == 2 && sagen) {

				clients[0].changesReizen(null, null, null, "gereizt bis "+werte[accWert], "" + werte[accWert + 1], "sagen");
				clients[1].changesReizen(null, "Weg", "", null, "JA", "h�ren");
				clients[2].changesReizen("Weg", "", null, "Bitte warten", "", "");
				weg[2]=true;
				System.out.println("weg von 2");
				ersteRunde=false;
				return;
			}
			if (ersteRunde && pos == 1 && !sagen) {

				clients[0].changesReizen(null, null, null, null, "" + werte[accWert + 1], "sagen");
				clients[1].changesReizen("Weg", "", null, "Bitte warten", "", "");
				clients[2].changesReizen(null, "Weg", "", null, "JA", "h�ren");
				weg[1]=true;
				System.out.println("weg von 1");
				ersteRunde=false;
				return;
			}

			if (!ersteRunde && pos == 0) {
				if (weg[2]) {
					clients[0].changesReizen("Weg", null, null, "Bitte warten", null, null);
					clients[1].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
					clients[2].changesReizen("Weg", null, null, "Bitte warten", null, null);
				
				} else {
					clients[0].changesReizen("Weg", null, null, "Bitte warten", null, null);
					clients[1].changesReizen("Weg", null, null, "Bitte warten", null, null);
					clients[2].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
					
				}
				weg[0]=true;
				System.out.println("weg von 0");
				return;
			}
			if (!ersteRunde && pos != 0 ) {
				if (werte[accWert] == 0) {
					System.out.println("nicht in Runde 2 weg ohne ansage " + pos);
					return;
				}
				clients[0].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
				clients[1].changesReizen("Weg", null, null, "Bitte warten", null, null);
				clients[2].changesReizen("Weg", null, null, "Bitte warten", null, null);
				if(pos==2) {
					weg[2]=true;
					System.out.println("weg von 2");
				}else {
					weg[1]=true;
					System.out.println("weg von 1");
				}
				return;
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// alte schreibweise
//		if (ersteRunde && pos == 1) {
//			try {
//				clients[0].changesReizen(null, null, null, null, "" + werte[accWert + 1], "sagen");
//				clients[1].changesReizen(null, "", "raus von Reizen", null, "", "raus");
//				clients[2].changesReizen(null, null, null, null, "", "h�ren");
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if (ersteRunde && pos == 2) {
//			try {
//				clients[0].changesReizen(null, null, null, null, "" + werte[accWert + 1], "sagen");
//				clients[1].changesReizen(null, null, null, null, "", "h�ren");
//				clients[2].changesReizen(null, "", "raus von Reizen", null, "", "raus");
//			} catch (RemoteException e) {
//
//				e.printStackTrace();
//			}
//		}
//
//		if (!ersteRunde && weg[1] && weg[2] && werte[accWert] == 0) {
//			try {
//				clients[0].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
//			} catch (RemoteException e) {
//
//				e.printStackTrace();
//			}
//
//		}
//		if (!ersteRunde && weg[pos] && (weg[(pos + 1) % 3] || weg[(pos + 2) % 3])) {
//			if (werte[accWert] > 0) {
//				// starten von DrueckenAcvitivy
//				if (weg[(pos + 1) % 3]) {
//					System.out.println("druecken " + (pos + 2) % 3);
//					try {
//						clients[(pos + 2) % 3].startDruecken();
//						clients[(pos + 1) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//						clients[(pos) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//					} catch (RemoteException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				if (weg[(pos + 2) % 3]) {
//					System.out.println("druecken " + (pos + 1) % 3);
//					try {
//						clients[(pos + 1) % 3].startDruecken();
//						clients[(pos + 2) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//						clients[(pos) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
//
//					} catch (RemoteException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			} else {
//				if (weg[(pos + 1) % 3]) {
//					try {
//						clients[(pos + 2) % 3].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
//						System.out.println("why1");
//					} catch (RemoteException e) {
//
//						e.printStackTrace();
//					}
//					return;
//				}
//				if (weg[(pos + 2) % 3]) {
//					try {
//						clients[(pos + 1) % 3].changesReizen(null, null, "alle raus", "18 sagen?", "18", null);
//						System.out.println("why2");
//					} catch (RemoteException e) {
//
//						e.printStackTrace();
//					}
//					return;
//				}
//			}
//		}
//		System.out.println("weg");
//		System.out.println(weg[0]);
//		System.out.println(weg[1]);
//		System.out.println(weg[2]);
//		if (anzWeg == 3) {
//			// NeuStart
//			// kein Ramsch Spielmodus
//		}
//		weg[pos] = true;
//		System.out.println(pos + " weg");
//		anzWeg++;
//		if (anzWeg == 3) {
//			System.out.println("alle weg");
//			return;
//		}
//
//		if (pos == 2 || pos == 1) {
//			ersteRunde = false;
//		}

	}

	public synchronized void nextClick(int pos) {
		if (weg[pos]) {
			System.out.println("schon weg, kein next "+pos);
			return;
		}
			
//		if (!change)
//			return;
		try {
			if (weg[(pos + 1) % 3] && weg[(pos + 2) % 3]) {
				System.out.println("druecken startet " + pos);
				clients[pos].startDruecken();
				clients[(pos + 1) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
				clients[(pos + 2) % 3].changesReizen("es wird gedr�ckt", "", "", "bitte warten", "", "");
				return;
			}

			if (sagen) {
				if (ersteRunde && pos == 2) {
					accWert++;
					clients[0].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
					clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
					clients[2].changesReizen(null, null, "", "gereizt bis " + werte[accWert], "" + werte[accWert + 1],
							null);
					sagen = false;
					return;
				}
				if (!ersteRunde && pos == 0) {
					accWert++;
					clients[0].changesReizen(null, null, "", "gereizt bis " + werte[accWert], "" + werte[accWert + 1],
							null);
					clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
					clients[2].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
					sagen = false;
					return;
				}
				System.out.println("nicht dran mit sagen " + pos);
				return;
			}
			if (!sagen) {
				if (ersteRunde && pos == 1) {
					clients[2].changesReizen(null, null, "Ja von Sp2", null, null, null);
					sagen = true;
					return;
				}
				if (!ersteRunde) {
					if (weg[1]) {
						clients[0].changesReizen(null, null, "Ja von Sp3", null, null, null);
					} else {
						clients[0].changesReizen(null, null, "Ja von Sp2", null, null, null);
					}
					sagen = true;
					return;
				}
				System.out.println("nicht dran mit JA " + pos);
				return;
			}

		} catch (

		RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (sagen && ersteRunde && pos == 2) {
//			accWert++;
//			// Anzeige bei allen Spielern �ndern.
//			try {
////				clients[0].changeLGereizt(""+werte[accWert]);
////				clients[1].changeLGereizt(""+werte[accWert]);
////				clients[2].changeLGereizt(""+werte[accWert]);
////				clients[2].changeBtnNext(""+werte[accWert+1]);
//				clients[0].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
//				clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
//				clients[2].changesReizen(null, null, "", "gereizt bis " + werte[accWert], "" + werte[accWert + 1],
//						null);
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			sagen = false;
//			return;
//		}
//		if (!sagen && ersteRunde && pos == 1) {
//			try {
////				clients[2].changeLEmpty("Ja von Sp2");
//				clients[2].changesReizen(null, null, "Ja von Sp2", null, null, null);
//
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// pos2 uebermitteln, dass weiter gereizt werden soll
//			sagen = true;
//			return;
//		}
//		if (sagen && !ersteRunde && pos == 0) {
//			accWert++;
//			try {
//				clients[0].changesReizen(null, null, "", "gereizt bis " + werte[accWert], "" + werte[accWert + 1],
//						null);
////				clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
////				clients[2].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
//				if (weg[1]) {
//					clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
//					clients[2].changesReizen(null, null, null, "gereizt bis " + werte[accWert], "JA", null);
//				}
//				if (weg[2]) {
//					clients[1].changesReizen(null, null, null, "gereizt bis " + werte[accWert], "JA", null);
//					clients[2].changesReizen(null, null, null, "gereizt bis " + werte[accWert], null, null);
//				}
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			sagen = false;
//			return;
//		}
//		if (!sagen && !ersteRunde) {
//			if (weg[1] && pos == 2) {
//				try {
////					clients[2].changeLEmpty("Ja von Sp2");
//					clients[0].changesReizen(null, null, "Ja von Sp3", null, null, null);
//
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// pos2 uebermitteln, dass weiter gereizt werden soll
//				sagen = true;
//				return;
//			}
//			if (weg[2] && pos == 1) {
//				try {
////					clients[2].changeLEmpty("Ja von Sp2");
//					clients[0].changesReizen(null, null, "Ja von Sp2", null, null, null);
//
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// pos2 uebermitteln, dass weiter gereizt werden soll
//				sagen = true;
//				return;
//			}
//
//		}

	}

	public int gereiztBis() {
		return werte[accWert];
	}

}
