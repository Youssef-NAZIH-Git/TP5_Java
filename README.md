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

---

## `CompteBancaire`

Classe de base représentant un compte bancaire classique avec des méthodes pour dépôt, retrait, transfert et affichage de solde.

### `depotArgent(float argent)`
Ajoute un montant au solde du compte.

```java
cpt.depotArgent(100);
```

---

### `retirerArgent(float argent)`
Retire un montant si le solde est suffisant.  
Lève `FondsInsuffisantsException` sinon.

```java
try {
    cpt.retirerArgent(200);
} catch (FondsInsuffisantsException e) {
    System.out.println(e.getMessage());
}
```

**Sortie attendue :**
```
Solde insuffisant, vous avez: 100.0
Vous avez essayer de retirer: 200.0
```

---

### `transferArgents(CompteBancaire cpt, float argent)`
Transfère de l'argent vers un autre compte.  
Lève `FondsInsuffisantsException` ou `CompteInexistantException`.

```java
try {
    cptAhmed.transferArgents(cptMohammed, 150);
} catch (FondsInsuffisantsException | CompteInexistantException e) {
    System.out.println(e.getMessage());
}
```

**Sortie attendue en cas d’erreur :**
```
Vous ne pouverz pas effectuer la transfert a cause des fonds insuffisants!
```

---

### `afficherSolde()`
Affiche simplement le solde.

```java
cpt.afficherSolde();
```

---

## `CompteCourant`

Hérite de `CompteBancaire`. Permet un **découvert autorisé** grâce à un attribut `limit`.

### Redéfinition de `retirerArgent`
Prend en compte la limite du découvert.

```java
try {
    cptAhmed.retirerArgent(250); // solde 100 + limite 100 = 200 < 250 → exception
} catch (FondsInsuffisantsException e) {
    System.out.println(e.getMessage());
}
```

**Sortie attendue :**
```
Solde insuffisant, vous avez: 100.0 Avec un limite supplementaire de: 100.0
Vous avez essayer de retirer: 250.0
```

---

### Redéfinition de `transferArgents`
Le transfert respecte aussi la limite du découvert.

```java
try {
    cptAhmed.transferArgents(cptMohammed, 180);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

## `CompteEpargne`

Hérite de `CompteBancaire`. Ajoute la capacité à générer des **intérêts**.

### `genererInteret()`
Ajoute un pourcentage du solde au compte.  
Lève une exception si le solde est nul ou négatif.

```java
try {
    cptEpargne.genererInteret();
} catch (FondsInsuffisantsException e) {
    System.out.println(e.getMessage() + " " + e.getFonds() + "DH");
}
```

**Sortie attendue (si solde est 0) :**
```
Solde insuffisant pour generer l'interet! 0.0DH
```

---

## `FondsInsuffisantsException`

Exception personnalisée levée lorsqu'un compte n’a pas assez de fonds.

```java
public class FondsInsuffisantsException extends Exception {
    float fonds;
    public FondsInsuffisantsException(float fonds, String msg){
        super(msg);
        this.fonds = fonds;
    }
    public float getFonds() {
        return this.fonds;
    }
}
```

---

## `CompteInexistantException`

Exception levée quand on tente un transfert vers un compte `null`.

```java
public class CompteInexistantException extends Exception {
    public CompteInexistantException(String msg){
        super(msg);
    }
}
```

---

## `Main`

Classe de test qui démontre les fonctionnalités des comptes et la gestion des exceptions.  
Utilise une `ArrayList<CompteBancaire>` pour stocker les comptes et effectuer des opérations.

```java
List<CompteBancaire> comptes = new ArrayList<>();
comptes.add(new CompteCourant(100, 1, 100, "Ahmed"));
comptes.add(new CompteEpargne(10, 2, 1000, "Mohammed"));
```

---

**Exemple de sortie globale (partielle) après exécution de `Main` :**
```
Solde insuffisant, vous avez: 100.0 Avec un limite supplementaire de: 100.0
Vous avez essayer de retirer: 201.0
Solde insuffisant pour generer l'interet! 0.0DH

Solde de Mohammed avant l'envoi: 0.0
Solde de Ahmed avant l'envoi: 100.0

Solde de Mohammed apres l'envoi: 200.0
Solde de Ahmed apres l'envoi: -100.0
Ahmed peut avoir -100DH car c'est le limite normal, mais apres 100 ca va afficher une exception!
```

---
