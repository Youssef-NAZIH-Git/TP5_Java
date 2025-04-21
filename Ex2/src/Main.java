import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<CompteBancaire> comptes = new ArrayList<>();

        CompteCourant cptAhmed = new CompteCourant(100, 1, 100, "Ahmed");
        CompteEpargne cptMohammed = new CompteEpargne(10, 2, 1000, "Mohammed");
        comptes.add(cptAhmed);
        comptes.add(cptMohammed);


        // Ahmed retirer 201 et son solde est 100 avec un limite de 100 c'est 200 qu'il peut retirer
        try {
            cptAhmed.retirerArgent(201);
        } catch (FondsInsuffisantsException e) {
            System.out.println(e.getMessage());
        }

        // Mohammed veut generer l'interet mais il n'as pas de fonds
        cptMohammed.setSolde(0);
        try { 
            cptMohammed.genererInteret();
        } catch (FondsInsuffisantsException e){
            System.out.println(e.getMessage() + " " + e.getFonds() + "DH");
        }


        // Ahmed va envoyer a Mohammed 200 DH
        System.out.println("\n\n\nSolde de Mohammed avant l'envoi: " + cptMohammed.getSolde());
        System.out.println("Solde de Ahmed avant l'envoi: " + cptAhmed.getSolde() + "\n");

        try {
            cptAhmed.transferArgents(cptMohammed, 200);
        } catch (CompteInexistantException c){
            System.err.println(c.getMessage());
        }
        catch (FondsInsuffisantsException f){
            System.err.println(f.getMessage());
        }
        finally {
            System.out.println("\nSolde de Mohammed apres l'envoi: " + cptMohammed.getSolde());
            System.out.println("Solde de Ahmed apres l'envoi: " + cptAhmed.getSolde());
        }

        System.out.println("Ahmed peut avoir -100DH car c'est le limite normal, mais apres 100 ca va afficher une exception!");
    }
}
