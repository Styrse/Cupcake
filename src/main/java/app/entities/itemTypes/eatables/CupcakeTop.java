package app.entities.itemTypes.eatables;

public class CupcakeTop extends Eatable {
    private int id;
    private String flavor;
    private String path;

    public CupcakeTop(float costPrice, float salesPrice, int calories, String description, boolean glutenFree, int id, String flavor, String path) {
        super(costPrice, salesPrice, calories, description, glutenFree);
        this.id = id;
        this.flavor = flavor;
        this.path = path;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CupcakeTop that = (CupcakeTop) obj;
        return id == that.id;
    }

}
