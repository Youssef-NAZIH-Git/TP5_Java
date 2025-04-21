public class CompteBancaire{
    int numcpt;
    float solde;
    String titulaire;

    // Num compte
    public int getNumcpt() {
        return numcpt;
    }
    
    // Solde
    public float getSolde() {
        return solde;
    }
    public void setSolde(float solde) {
        this.solde = solde;
    }

    // Titulaire
    public String getTitulaire() {
        return titulaire;
    }
    public void setTitulaire(String titulaire) {
        this.titulaire = titulaire;
    }


    // Constructeur
    public CompteBancaire(int numcpt, float solde, String titulaire) {
        this.numcpt = numcpt;
        this.solde = solde;
        this.titulaire = titulaire;
    }

    // Methods
    public void depotArgent(float argent){
        this.solde += argent;
    }

    public void retirerArgent(float argent) throws FondsInsuffisantsException{
        if (this.solde < argent){
            throw new FondsInsuffisantsException(argent, "Solde insuffisant, vous avez: " + this.solde + "\nVous avez essayer de retirer: " + argent);
        }
        this.solde -= argent;
    }

    public void afficherSolde(){
        System.out.println("Votre solde est: " + this.solde);
    }

    public void transferArgents(CompteBancaire cpt, float argent) throws CompteInexistantException, FondsInsuffisantsException{
        if (cpt == null){
            throw new CompteInexistantException("Compte n'existe pas");
        }
        if (this.solde < cpt.getSolde() || this.solde - argent < 0){
            throw new FondsInsuffisantsException(argent, "Vous ne pouverz pas effectuer la transfert a cause des fonds insuffisants!");
        }
        this.solde -= argent;
        cpt.setSolde(cpt.getSolde() + argent);
    }
}