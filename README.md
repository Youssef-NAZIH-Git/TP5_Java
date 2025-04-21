# Exercice 1

Ce projet contient deux classes principales :

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
