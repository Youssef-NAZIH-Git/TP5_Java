public class FondsInsuffisantsException extends Exception {
    float fonds;
    public FondsInsuffisantsException(float fonds, String msg){
        super(msg);
    }
    public float getFonds() {
        return this.fonds;
    }
}
