import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<>();
    private List<Item> basket = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getName() {
        return name;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws ItemNotFoundException{
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        throw new ItemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws ItemNotFoundException {
        Item itemToBeRemoved = findItemByName(itemName);
        menu.remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());
    }

    public int getBasketTotal() {
        int total = 0;
        for (Item item : basket) {
            total += item.getPrice();
        }
        return total;
    }

    public void addToBasket(String ... itemNames) throws ItemNotFoundException {
        for (String itemName : itemNames) {
            Item itemToBeAdded = findItemByName(itemName);
            basket.add(itemToBeAdded);
        }
    }

    public void removeFromBasket(String ... itemNames) throws ItemNotFoundException{
        for (String itemName : itemNames) {
            Item itemToBeRemoved = findItemByName(itemName);
            basket.remove(itemToBeRemoved);
        }
    }
}