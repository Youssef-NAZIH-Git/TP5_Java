# Exercice 1
## 1. NombreNegatifException.java

Exception personnalisée utilisée pour signaler toute tentative d’utilisation d’une valeur négative dans un contexte où cela n’est pas permis.

```java
public class NombreNegatifException extends Exception {
    int valerreur;

    public NombreNegatifException(String msg, int valerreur) {
        super(msg);
        this.valerreur = valerreur;
    }

    public int getValerreur() {
        return valerreur;
    }
}
```

## 2. EntierNaturel.java

Classe qui encapsule un entier naturel (valeur ≥ 0). Lève une exception si une opération produit une valeur négative.

Comportements notables :
- Constructeur qui refuse les valeurs négatives.
- Méthode `setVal(int)` pour changer la valeur.
- Méthode `decrementer()` qui décrémente `val` et peut lever une exception.

```java
public class EntierNaturel {
    int val;

    public EntierNaturel(int val) throws NombreNegatifException {
        if (val < 0) throw new NombreNegatifException("Nombre negatif!", val);
        this.val = val;
    }

    public void setVal(int val) throws NombreNegatifException {
        if (val < 0) throw new NombreNegatifException("Nombre negatif! Erreur de set: ", val);
        this.val = val;
    }

    public void decrementer() throws NombreNegatifException {
        if (val - 1 < 0) throw new NombreNegatifException("Decrementer va donner: ", val - 1);
        this.val -= 1;
    }

    public static void main(String[] args) {
        try {
            EntierNaturel var1 = new EntierNaturel(2);
            var1.decrementer();
            var1.decrementer();
            var1.decrementer(); // Provoque une exception
        } catch (NombreNegatifException e) {
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }

        try {
            EntierNaturel var2 = new EntierNaturel(-2); // Provoque une exception
        } catch (NombreNegatifException e) {
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }

        try {
            EntierNaturel var3 = new EntierNaturel(5);
            var3.setVal(-3); // Provoque une exception
        } catch (NombreNegatifException e) {
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }
    }
}
```

## Exécution attendue

```
Decrementer va donner:  -1
Nombre negatif! -2
Nombre negatif! Erreur de set:  -3
```


# Exercice 2
## Classes principales

### `CompteBancaire`
Classe de base représentant un compte bancaire.

#### Méthodes importantes :
- `depotArgent(float montant)` : Ajoute de l'argent au compte.
- `retirerArgent(float montant)` : Retire de l'argent du compte.
  - Lève `FondsInsuffisantsException` si le solde est insuffisant.
- `transferArgents(CompteBancaire cpt, float montant)` : Transfère des fonds vers un autre compte.
  - Lève `FondsInsuffisantsException` ou `CompteInexistantException`.

---

### `CompteCourant`
Hérite de `CompteBancaire`. Autorise un découvert jusqu'à une certaine limite.

- Le retrait et le transfert prennent en compte la limite de découvert.
- Les mêmes exceptions que `CompteBancaire` sont levées en cas de dépassement de cette limite.

---

### `CompteEpargne`
Hérite de `CompteBancaire`. Permet la génération d’intérêts.

- `genererInteret()` :
  - Lève `FondsInsuffisantsException` si le solde est nul ou négatif.

---

## Exceptions personnalisées

### `FondsInsuffisantsException`
Lancée lorsqu’une opération ne peut pas être effectuée à cause d’un solde insuffisant. Elle fournit :
- Le montant impliqué
- Un message détaillé

### `CompteInexistantException`
Lancée lors d’un transfert vers un compte non valide (null).

---

## Classe `Main`

La classe de test `Main` :
- Crée des comptes (`CompteCourant`, `CompteEpargne`)
- Effectue des dépôts, retraits, transferts
- Gère correctement les exceptions en affichant des messages explicites

### Exemples :
- Tentative de retrait supérieur à la limite de découvert déclenche `FondsInsuffisantsException`
- Génération d'intérêt avec solde nul déclenche aussi `FondsInsuffisantsException`
- Tentative de transfert vers un compte null déclenche `CompteInexistantException`

---

## Exécution

Compilation :
```bash
javac *.java
```

Exécution :
```bash
java Main
```

