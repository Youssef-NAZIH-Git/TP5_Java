public class CompteCourant extends CompteBancaire{
    float limit;
    public CompteCourant(float limit, int numcpt, float solde, String titulaire){
        super(numcpt, solde, titulaire);
        this.limit = limit;
    }

    @Override
    public void retirerArgent(float argent) throws FondsInsuffisantsException{
        if (this.solde + limit < argent){
            throw new FondsInsuffisantsException(argent, "Solde insuffisant, vous avez: " + this.solde + " Avec un limite supplementaire de: " + this.limit + "\nVous avez essayer de retirer: " + argent);
        }
        this.solde -= argent;
    }

    @Override
    public void transferArgents(CompteBancaire cpt, float argent) throws CompteInexistantException, FondsInsuffisantsException{
        if (cpt == null){
            throw new CompteInexistantException("Compte n'existe pas");
        }
        if (this.solde + limit < cpt.getSolde() || this.solde + limit - argent < 0){
            throw new FondsInsuffisantsException(argent, "Vous ne pouverz pas effectuer la transfert a cause des fonds insuffisants!");
        }
        this.solde -= argent;
        cpt.setSolde(cpt.getSolde() + argent);
    }
}