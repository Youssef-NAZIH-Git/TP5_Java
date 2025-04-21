public class CompteEpargne extends CompteBancaire {
    float interet;

    public CompteEpargne(float interet, int numcpt, float solde, String titulaire){
        super(numcpt, solde, titulaire);
        this.interet = interet;
    }

    public void genererInteret() throws FondsInsuffisantsException{
        if (this.solde <= 0){
            throw new FondsInsuffisantsException(this.solde, "Solde insuffisant pour generer l'interet!");
        }
        this.solde += this.solde*interet/100;
    }
}
