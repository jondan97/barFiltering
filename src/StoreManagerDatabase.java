import java.util.ArrayList;

import ketai.data.*;

public class StoreManagerDatabase {
    KetaiSQLite storesDB;

    StoreManagerDatabase(KetaiSQLite db) {
        storesDB = db;
    }

    public void deleteAll() { //if something goes wrong for full reset
        storesDB.execute("DROP TABLE stores");
        System.out.println("Deleted");
        storesDB.execute("DROP TABLE products");
        System.out.println("Deleted");
        storesDB.execute("DROP TABLE storeProducts");
        System.out.println("Deleted");
    }

    public void createDB() { //first time use
        String CREATE_DB_SQL1 = "CREATE TABLE stores (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, music TEXT, owner TEXT, coordinateX REAL, coordinateY REAL)";
        String CREATE_DB_SQL2 = "CREATE TABLE products (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, description TEXT)";
        String CREATE_DB_SQL3 = "CREATE TABLE storeProducts (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "storeID INTEGER REFERENCES stores(id), productID INTEGER REFERENCES products(id))";

        if (storesDB.connect()) {
            if (!storesDB.tableExists("stores")) {
                storesDB.execute(CREATE_DB_SQL1);
                System.out.println("Done");
            }
            if (!storesDB.tableExists("products")) {
                storesDB.execute(CREATE_DB_SQL2);
                System.out.println("Done");
            }
            if (!storesDB.tableExists("storeProducts")) {
                storesDB.execute(CREATE_DB_SQL3);
                System.out.println("Done");
            }
        }
    }

    public void addProducts() { //sample data (brutally entered because the user is not supposed to enter products, only choose for his store)
        storesDB.execute("INSERT into products (`name`,description) " + "VALUES (" + "'Sprite', 'Tastes like soda with lemon')");
        storesDB.execute("INSERT into products (`name`,description) " + "VALUES (" + "'Coca', 'Not to be confused with coke')");
        storesDB.execute("INSERT into products (`name`,description) " + "VALUES (" + "'Healing Potion', 'Makes you feel better')");
        storesDB.execute("INSERT into products (`name`,description) " + "VALUES (" + "'Water', 'Are you not supposed to drink this anyways?')");
        storesDB.execute("INSERT into products (`name`,description) " + "VALUES (" + "'Air', 'Sure why not')");
    }

    public void addStores() { //sample data
        this.addStore("Robasi", "Latin", "Saltsas", 10.5, 11.5);
        this.addStore("Pegasus", "Greek", "Ntomatas", 20.5, 21.5);
        this.addStore("Mikonos", "Jazz", "Mamakias", 30.5, 31.5);
        this.addStore("Amari", "Rock", "Malakas", 40.5, 41.5);
        this.addStore("Steki", "Jazz", "Madolinos", 50.5, 51.5);
    }

    public void addProductsToStore() {
        this.addProductToStore(1, 1);
        this.addProductToStore(1, 2);
        this.addProductToStore(1, 4);
        this.addProductToStore(2, 3);
        this.addProductToStore(3, 3);
        this.addProductToStore(4, 5);
        this.addProductToStore(2, 5);
        this.addProductToStore(3, 2);
    }

    public void addStore(String storeName, String storeMusic, String storeOwner, double coordinateX, double coordinateY) { //store added, data included: name,music,owner
        if (storesDB.connect()) {
            storesDB.query("SELECT * FROM stores WHERE name='" + storeName + "' AND owner='" + storeOwner + "' LIMIT 1");
            if (!storesDB.next()) {
                storesDB.execute("INSERT into stores (`name`,`music`,`owner`,`coordinateX`,`coordinateY`) " + "VALUES (" + "'" + storeName + "','" + storeMusic + "','" + storeOwner + "','" + coordinateX + "','" + coordinateY + "')");
            } else {
                System.out.println("entry already exists");
            }
            System.out.println("data count for stores table after insert: " +
                    storesDB.getRecordCount("stores"));
        }
    }

    public void addProductToStore(int productID, int storeID) {
        if (storesDB.connect()) {
            storesDB.query("SELECT * FROM storeProducts WHERE storeID=" + storeID + " AND + productID=" + productID + " LIMIT 1");
            if (!storesDB.next()) {
                storesDB.execute("INSERT into storeProducts(`storeID`,`productID`)" + "VALUES (" + "'" + storeID + "','" + productID + "')");
            }
        }
    }

    public void removeProductFromStore(int productID, int storeID) {
        if (storesDB.connect()) {
            storesDB.query("SELECT * FROM storeProducts WHERE storeID=" + storeID + " AND + productID=" + productID + " LIMIT 1");
            if (storesDB.next()) {
                storesDB.execute("DELETE FROM storeProducts WHERE storeID=" + storeID + " AND productID=" + productID);
            }
        }
    }

    public void deleteStore(int id) {
        if (storesDB.connect()) {
            storesDB.query("SELECT * FROM stores WHERE id=" + id + " LIMIT 1");
            if (storesDB.next()) {
                storesDB.execute("DELETE FROM stores WHERE id=" + id);
                storesDB.execute("DELETE FROM storeProducts WHERE storeID=" + id);
            }
        }
    }

    public void updateStore(int rowID, String column, String newInfo) { //rowID will be known beforehand, for example first entry in the database may be ID=1, column is column name (name/music/owner), newInfo the updated data
        if (storesDB.connect()) {
            storesDB.query("SELECT * FROM stores WHERE id=" + rowID + " LIMIT 1");
            if (storesDB.next()) {
                storesDB.execute("Update stores SET " + column + "=" + "'" + newInfo + "' WHERE id=" + rowID);
            }
        }
    }

    public ArrayList getStores() { //returns all stores in a string array, with this format: Id=1,Name=Freeway,Music=Rock,Owner=Freddy Mercury,Products=1|2
        if (storesDB.connect()) {
            int storeIDs[] = new int[(int) storesDB.getRecordCount("stores")]; // example row: 1
            int counter = 0;
            storesDB.query("SELECT id FROM stores"); //select all IDs from all stores, and stores them into a classic array
            while (storesDB.next()) {
                storeIDs[counter] = storesDB.getInt("id");
                counter++;
            }

            ArrayList<ReferenceBasedList> productsInStoreAr = new ArrayList(); // example row: productIDs=1->2->4
            ReferenceBasedList productsInStore;
            for (int x = 0; x < storeIDs.length; x++) { // traverses through the storeIDs array, and queries all productIDs that are associated with the storeID
                productsInStore = new ReferenceBasedList();
                storesDB.query("SELECT products.id,products.name,products.description FROM products,storeProducts WHERE storeProducts.storeID=" + storeIDs[x] + " AND storeProducts.productID=products.id");
                while (storesDB.next()) {
                    Product p = new Product(storesDB.getInt("id"), storesDB.getString("name"), storesDB.getString("description"));
                    try {
                        System.out.println(p);
                        productsInStore.addSortedAlphabetically(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                productsInStoreAr.add(productsInStore);
            }
            ArrayList stores = new ArrayList();
            counter = 0;
            storesDB.query("SELECT * FROM stores"); //will now fetch all from stores, and will add all of them to a stores string array that will later be returned to main, each entry includes all the product ids that were gathered on the previous DB call,
            //no change in how the IDs will be fetched this time, so no concerns about mismatches between storeID and productIDs
            while (storesDB.next()) {
                Store s = new Store(storesDB.getInt("id"), storesDB.getString("name"), storesDB.getString("music"), productsInStoreAr.get(counter), storesDB.getString("owner"), storesDB.getDouble("coordinateX"), storesDB.getDouble("coordinateY"));
                stores.add(s);
                //storeID = storesDB.getInt(id) + ""; i tried doing some sort of a subquery here with the hopes of being more efficient, but it doesn't seem to work unfortunately
                //storesDBDummy.query("SELECT productID FROM storeProducts WHERE storeID=" + storeID );
                //while(storesDBDummy.next()){
                // productsInStore += storesDBDummy.getInt("productID") + ", ";
                //}
                //stores.add("Id=" + storesDB.getInt("id") + ", Name=" + storesDB.getString("name") + ", Music=" + storesDB.getString("music") + ", Owner=" + storesDB.getString("owner") + ", Products=" + ((productsInStoreAr.get(counter)).substring(0,(productsInStoreAr.get(counter).length())-1)) + "");
                counter++;
            }
            return stores;
        }
        return null;
    }

    public ArrayList getProducts() { //returns all products in a string array, with this format: Id=1,Name=Sprite,Description=Tastes like soda with lemon
        if (storesDB.connect()) {
            ArrayList products = new ArrayList();
            storesDB.query("SELECT * FROM products");
            while (storesDB.next()) {
                Product p = new Product(storesDB.getInt("id"), storesDB.getString("name"), storesDB.getString("description"));
                products.add(p);
            }
            return products;
        }
        return null;
    }
}
