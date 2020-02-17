
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
        StoreManager storesAthens = new StoreManager();
        Scanner input = new Scanner(System.in);
        int choice;
        String answer;
        try {
            storesAthens.addStore("Bald Crow", "Rock", "Sandberg Bytaer");
            storesAthens.addStore("Rouketa","Drum and Bass","Piraulos Rouketas");
            storesAthens.addStore("Sinatraaa","Jazz","Manolis Paksimadis");
            storesAthens.addStore("Pes to kai Egine!", "Greek Folk","Pantelis Pantelidis");

            storesAthens.addProductToStore("Jager","Bald Crow", "Sandberg Bytaer");
            storesAthens.addProductToStore("Gin","Bald Crow", "Sandberg Bytaer");
            storesAthens.addProductToStore("Jager","Rouketa","Piraulos Rouketas");
            storesAthens.addProductToStore("Sprite","Sinatraaa", "Manolis Paksimadis");
            storesAthens.addProductToStore("Gin","Sinatraaa", "Manolis Paksimadis");
            storesAthens.addProductToStore("Sprite","Pes to kai Egine!","Pantelis Pantelidis");


            System.out.println("Will now show all stores:");
            ArrayList<String> storesAthensAr = storesAthens.getStoresToString();
            for(String store: storesAthensAr){
                System.out.println(store);
            }

            System.out.println("Name of the store you would like to update?");
            String name = input.nextLine();
            System.out.println("Owner of the store you would like to update?");
            String owner = input.nextLine();
            System.out.println("name: "+name + ", owner: " + owner);


            System.out.println("What would you like to update?");
            System.out.println("1 = Update the Name");
            System.out.println("2 = Update the Music");
            System.out.println("3 = Update the Owner");
            System.out.println("4 = Update the Products");
            choice = input.nextInt();

            if (choice == 1){
                System.out.println("Please input the info");
                answer = input.nextLine();
                answer = input.nextLine();
                storesAthens.updateStoreName(name, owner, answer);
                System.out.println(storesAthens.getStoreToString(name,owner));
            }
            else if (choice == 2){
                System.out.println("Please input the info");
                answer = input.nextLine();
                answer = input.nextLine();
                storesAthens.updateStoreMusic(name, owner, answer);
                System.out.println(storesAthens.getStoreToString(name,owner));
            }
            else if (choice == 3){
                System.out.println("Please input the info");
                answer = input.nextLine();
                answer = input.nextLine();
                storesAthens.updateStoreOwner(name, owner, answer);
                System.out.println(storesAthens.getStoreToString(name,owner));
            }
            else if (choice == 4){
                System.out.println("What would you like to do?");
                System.out.println("1 = Add a Product");
                System.out.println("2 = Remove a product");
                choice = input.nextInt();

                if (choice == 1){
                    System.out.println("What is the name of the product?");
                    answer = input.nextLine();
                    answer = input.nextLine();
                    storesAthens.addProductToStore(answer, name, owner);
                    System.out.println(storesAthens.getStoreToString(name,owner));
                }
                else if (choice == 2){
                    System.out.println("What is the name of the product?");
                    answer = input.nextLine();
                    answer = input.nextLine();
                    storesAthens.removeProductFromStore(answer,name,owner);
                    System.out.println(storesAthens.getStoreToString(name,owner));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
