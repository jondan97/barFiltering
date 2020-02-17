package objects;

import referenceBasedList.ReferenceBasedList;

public class Store {

    private int id; //we will see how we are going to implement this so that it's unique
    private String name;
    private String music;
    private ReferenceBasedList products;
    private String owner;
    private double coordinateX;
    private double coordinateY;

    public Store(String name, String music, String owner) {
        this.id = (name+owner).hashCode(); //music is not included because if one decides to change the music, does the whole store change?
        this.name = name;
        this.music = music;
        this.products = new ReferenceBasedList();
        this.owner = owner;
    }

    public Store(int id, String name, String music, ReferenceBasedList products, String owner, double coordinateX, double coordinateY) {
        this.id = id;
        this.name = name;
        this.music = music;
        this.products = products;
        this.owner = owner;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Store(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public ReferenceBasedList getProducts() {
        return products;
    }

    public void setProducts(ReferenceBasedList products) {
        this.products = products;
    }

    public void addProduct(Product p) throws Exception {
        if(!products.exists(p)) {
            products.addSortedAlphabetically(p);
        }
        else throw new Exception("Product already exists.");
    }

    public void removeProduct(Product p) throws Exception {
        if(products.exists(p)) {
            products.removeByItem(p);
        }
        else throw new Exception("Product does not exist.");
    }

    @Override //overrides object's equals and we use our custom one
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (o != null && o instanceof Store){
            isEqual = this.id == ((Store) o).getId();
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", music='" + music + '\'' +
                ", owner='" + owner + '\'' +
                ", coordinateX='" + coordinateX + '\'' +
                ", coordinateY='" + coordinateY + '\'' +
                ", products=" + products +
                '}';
    }
}
