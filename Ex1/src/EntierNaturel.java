public class EntierNaturel {
    int val;

    public EntierNaturel(int val) throws NombreNegatifException {
        if (val < 0){
            throw new NombreNegatifException("Nombre negatif!", val);
        }
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) throws NombreNegatifException{
        if (val < 0){
            throw new NombreNegatifException("Nombre negatif! Erreur de set: ", val);
        }
        this.val = val;
    }

    public void decrementer() throws NombreNegatifException{
        if (val - 1 < 0){
            throw new NombreNegatifException("Decrementer va donner: ", val - 1);
        }
        this.val -= 1;
    }

    public static void main(String[] args){
        try {
            EntierNaturel var1 = new EntierNaturel(2);
            var1.decrementer();
            var1.decrementer();
            var1.decrementer();
        } catch (NombreNegatifException e) {
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }

        try{
            EntierNaturel var2 = new EntierNaturel(-2);
        } catch (NombreNegatifException e){
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }

        try{
            EntierNaturel var3 = new EntierNaturel(5);
            var3.setVal(-3);
        } catch (NombreNegatifException e){
            System.out.println(e.getMessage() + " " + e.getValerreur());
        }
    }
}
