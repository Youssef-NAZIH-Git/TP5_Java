 public class NombreNegatifException extends Exception{
    int valerreur;
    public NombreNegatifException(String msg, int valerreur){
        super(msg);
        this.valerreur = valerreur;
    }
    public int getValerreur() {
        return valerreur;
    }
 }