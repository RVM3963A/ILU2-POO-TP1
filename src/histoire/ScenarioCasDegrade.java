package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois Asterox = new Gaulois("Asterox", 99);
		etal.acheterProduit(1, Asterox);

		
		try {
		etal.libererEtal();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}



}


