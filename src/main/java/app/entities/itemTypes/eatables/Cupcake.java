package app.entities.itemTypes.eatables;

public class Cupcake extends Eatable {
    private int id;
    private CupcakeBottom cupcakeBottom;
    private CupcakeTop cupcakeTop;

    public Cupcake(CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop) {
        super(cupcakeBottom.getCostPrice() + cupcakeTop.getCostPrice(),
                cupcakeBottom.getSalesPrice() + cupcakeTop.getSalesPrice(),
                cupcakeBottom.getCalories() + cupcakeTop.getCalories(),
                cupcakeBottom.getDescription() + cupcakeTop.getDescription(),
                cupcakeBottom.isGlutenFree() && cupcakeTop.isGlutenFree());
        this.id = id;
        this.cupcakeBottom = cupcakeBottom;
        this.cupcakeTop = cupcakeTop;
    }

    public CupcakeBottom getCupcakeBottom() {
        return cupcakeBottom;
    }

    public CupcakeTop getCupcakeTop() {
        return cupcakeTop;
    }

    @Override
    public String toString() {
        return "Cupcake{" +
                "cupcakeBottom=" + cupcakeBottom +
                ", cupcakeTop=" + cupcakeTop +
                '}';
    }
}
