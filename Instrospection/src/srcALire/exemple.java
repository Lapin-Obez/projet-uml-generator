package srcALire;

public class exemple {
    public int attribut1;
    private String attribut2;
    protected char attribut3;

    public exemple(int attribut1, String attribut2, char attribut3) {
        this.attribut1 = attribut1;
        this.attribut2 = attribut2;
        this.attribut3 = attribut3;
    }
    public String method(String parameter){
        return attribut2+" "+parameter;
    }
}
