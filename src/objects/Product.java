package objects;

public class Product {

    private int id; //we will see how we are going to implement this
    private String name;
    private String description;

    public Product( String name, String description) {
        this.id = name.hashCode();
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override //overrides object's equals and we use our custom one
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (o != null && o instanceof Product){
            isEqual = this.name.equals(((Product) o).getName());
        }
        return isEqual;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                "name=" + name +
                ", description='" + description + '\'' +
                '}';
    }
}
