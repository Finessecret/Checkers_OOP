package basic.pack.models;

public class Checker extends Figure {
    private static String SYMBOL = "C";

    public Checker(Node currentNodeModel) {
        super(currentNodeModel);
        setSymbol(SYMBOL);
    }
}
