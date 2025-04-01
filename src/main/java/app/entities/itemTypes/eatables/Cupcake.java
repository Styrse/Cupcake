package app.entities.itemTypes.eatables;

public class Cupcake extends Eatable {
    private CupcakeBottom cupcakeBottom;
    private CupcakeTop cupcakeTop;

    public Cupcake(CupcakeBottom cupcakeBottom, CupcakeTop cupcakeTop) {
        super(cupcakeBottom.getCostPrice() + cupcakeTop.getCostPrice(),
                cupcakeBottom.getSalesPrice() + cupcakeTop.getSalesPrice(),
                cupcakeBottom.getCalories() + cupcakeTop.getCalories(),
                cupcakeBottom.getDescription() + cupcakeTop.getDescription(),
                cupcakeBottom.isGlutenFree() && cupcakeTop.isGlutenFree());
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
        return cupcakeBottom.getFlavor() + " base with " + cupcakeTop.getFlavor().toLowerCase() + " top";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cupcake cupcake = (Cupcake) obj;
        return cupcakeBottom.equals(cupcake.cupcakeBottom) && cupcakeTop.equals(cupcake.cupcakeTop);
    }
}
