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

	public String afficherVillageois() throws VillageSansChefException{
		StringBuilder chaine = new StringBuilder();
		if(chef == null) {
			throw new VillageSansChefException("Le village n'a pas de chef !");
		}
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	public String afficherMarche() {
		return marche.afficherMarcheM();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder txt = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		if (indiceEtal == -1) {
			txt.append( "Tout les �tals sont occup�s, le vendeur " + vendeur.getNom() + " devra revenir demain.\n");
			return txt.toString();
		}
		marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
		txt.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " � l'�tal n�" + indiceEtal + ".\n");
		return txt.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] trouveretale = marche.trouverEtals(produit);
		StringBuilder txt = new StringBuilder();
		if(trouveretale.length == 0) {
			txt.append("Il n'y a aucun vendeur qui propose des " + produit + " sur le march�.\n");
			return txt.toString();
		}
		txt.append("Les vendeurs qui proposent des " + produit + " sont :\n");
		for (int i=0; i<trouveretale.length; i++ ) {
			txt.append(" - " + trouveretale[i].getVendeur().getNom() + "\n");
		}
		return txt.toString();
		
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etale = rechercherEtal(vendeur);
		return marche.LibererEtaleMarcher(etale);
	}
	
	
	public String Acheter(Etal etal, int quantite, Gaulois acheteur) {
		StringBuilder chaine = new StringBuilder();
		
		chaine.append(etal.acheterProduit(quantite, acheteur));
		return chaine.toString();
	}
	
	
	
	
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbetals) {
			
			etals = new Etal[nbetals];
			
			for (int i = 0; i<nbetals;i++) {
				etals [i] = new Etal();
			}
		}
		
		private void utiliserEtal( int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i = 0; i<etals.length; i++) {
				if ((etals[i] == null || !(etals[i].isEtalOccupe()))){
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
		
		
		private String afficherMarcheM() {
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
			
			marche.append("Il reste " + nbetallibre + " �tals non utilis�s dans le march�\n");
			return marche.toString();
		}
		
		
		private String LibererEtaleMarcher(Etal etale) {
			return etale.libererEtal();
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
}