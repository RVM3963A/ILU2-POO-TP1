package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private Marche marche;
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbetals) {
		this.marche = new Marche(nbetals);
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder txt = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		if (indiceEtal == -1) {
			txt.append( "Tout les étals sont occupés, le vendeur " + vendeur.getNom() + " devra revenir demain.\n");
			return txt.toString();
		}
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		txt.append("Le vendeur " + vendeur.getNom() + "vend des " + produit + " à l'étal n°" + indiceEtal ""
				+ ".\n");
		return txt.toString();
	}
	
	
	
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbetals) {
			this.etals = new Etal[nbetals];
		}
		
		private void utiliserEtal( int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i = 0; i<etals.length; i++) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbprod = 0;
			int j= 0;
			for(int i = 0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbprod+=1;
				}
			}
			Etal[] etalproduit = new Etal[nbprod];
			for(int i = 0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalproduit[j] = etals[i];
					j+=1;
				}
			}
			
			return etalproduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i<etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			
			return null;
		}
		
		
		private String afficherMarche() {
			int nbetallibre = 0;
			StringBuilder marche = new StringBuilder();
			for(int i = 0; i<etals.length; i++) {
				if(!(etals[i].isEtalOccupe())) {
					nbetallibre +=1;
				}
			}
			for(int i = 0; i<etals.length; i++) {
				marche.append(etals[i].afficherEtal());
			}
			
			marche.append("Il reste " + nbetallibre + " étals non utilisés dans le marché\n");
			return marche.toString();
		}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}