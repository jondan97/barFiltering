import objects.Product;
import objects.Store;
import referenceBasedList.ReferenceBasedList;

import java.util.ArrayList;

public class StoreManager {

    private ArrayList<Store> stores;

    StoreManager(){
        this.stores = new ArrayList<>();
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void addStore (int id, String name, String music, ReferenceBasedList products, String owner) throws Exception{
        Store s = new Store(name, music, products, owner);
        if (!stores.contains(s))
            stores.add(s);
        else
            throw new Exception("Store already exists.");
    }

    public void addProductToStore(String productName, String storeName, String owner) throws Exception {
        Store store = new Store(storeName, "", owner);
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            Product p = new Product(productName);
            store.addProduct(p);
        }
        else
            throw new Exception("Store does not exist.");
    }

    public void removeProductFromStore(String productName, String storeName, String owner) throws Exception {
        Store store = new Store(storeName, "", owner);
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            Product p = new Product(productName);
            store.removeProduct(p);
        }
        else
            throw new Exception("Store does not exist.");
    }

    public void addStore (String name, String music, String owner) throws Exception{
        Store s = new Store( name, music,owner);
        if (!stores.contains(s))
            stores.add(s);
        else
            throw new Exception("Store already exists.");
    }

    public void updateStoreName(String Sname, String owner, String name) throws Exception {
        Store store = new Store(Sname,"",owner);
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setName(name);
        }
        else throw new Exception("Store does not exist.");
    }

    public void updateStoreOwner(String Sname, String owner, String nOwner) throws Exception {
        Store store = new Store(Sname,"",owner);
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setOwner(nOwner);
        }
        else throw new Exception("Store does not exist.");
    }

    public void updateStoreMusic(String Sname, String owner, String music) throws Exception {
        Store store = new Store(Sname,"",owner);
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setMusic(music);
        }
        else throw new Exception("Store does not exist.");
    }

    public String getStoreToString(String name, String owner) throws Exception{
        Store s = new Store(name, "", owner);
        if (stores.contains(s)){
            s = stores.get(stores.indexOf(s));
            return s.toString();
        }
        else throw new Exception("Store does not exist");
    }

    public ArrayList getStoresToString(){
        ArrayList storesToStrings = new ArrayList();
        for(Store store: stores){
            storesToStrings.add(store.toString());
        }
        return storesToStrings;
    }

    //*****************************************************
    //METHODS THAT MIGHT OR MIGHT NOT BE USED IN THE FUTURE
    //*****************************************************

    public void updateStoreMusic(Store store, String name)throws Exception{
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setMusic(name);
        }
        else throw new Exception("Store does not exist.");
    }

    public void updateStoreName(Store store, String name)throws Exception{
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setName(name);
        }
        else throw new Exception("Store does not exist.");
    }

    public void updateStoreProducts(Store store, ReferenceBasedList products) throws Exception{
        if (stores.contains(store)){
            store = stores.get(stores.indexOf(store));
            store.setProducts(products);
        }
        else throw new Exception("Store does not exist.");
    }

    public ArrayList getStoresWithName(String name){
        ArrayList<Store> filteredStores = new ArrayList<>();
        for(Store s: stores){
            if(s.getName().equals(name)){
                filteredStores.add(s);
            }
        }
        return filteredStores;
    }

    public Store getUniqueStore(String name, String owner)throws Exception{
        int storeId = (name+owner).hashCode();
        Store store = null;
        for(Store s: stores){
            if(s.equals(storeId)){
                store = s;
                break;
            }
        }
        if (store == null) throw new Exception("Store with name: "+ name + "and owner: " + owner +" does not exist.");
        return store;
    }

    public Store getFirstStoreWithName(String name) throws Exception{
        Store store = null;
        for(Store s: stores){
            if(s.getName().equals(name)){
                store = s;
                break;
            }
        }
        if (store == null) throw new Exception("Store with name: "+ name +" does not exist.");
        return store;
    }

    public ArrayList getStoresWithMusic(String music){
        ArrayList<Store> filteredStores = new ArrayList<>();
        for(Store s: stores){
            if(s.getMusic().equals(music)){
                filteredStores.add(s);
            }
        }
        return filteredStores;
    }

    public Store getFirstStoreWithMusic(String music) throws Exception{
        Store store = null;
        for(Store s: stores){
            if(s.getMusic().equals(music)){
                store = s;
                break;
            }
        }
        if (store == null) throw new Exception("Store with name:"+ music +"does not exist.");
        return store;
    }

    public ArrayList getStoresWithProduct(Product product){
        ArrayList<Store> filteredStores = new ArrayList<>();
        for(Store s: stores){
            if (s.getProducts().exists(product)){
                filteredStores.add(s);
            }
        }
        return filteredStores;
    }

    public Store getFirstStoreWithProduct(Product product){
        Store store = null;
        for(Store s: stores){
            if (s.getProducts().exists(product)){
                store = s;
                break;
            }
        }
        return store;
    }

    public Store getStore (Store s) throws Exception{
        if (stores.contains(s)){
            s = stores.get(stores.indexOf(s));
            return s;
        }
        else throw new Exception("Store does not exist");
    }

}

