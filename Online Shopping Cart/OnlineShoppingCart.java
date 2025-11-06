import java.util.*;

//Base Class
abstract class Product{
    private String productId;
    private String productName;
    protected double price;


    public Product(String productId, String productName, double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId(){
        return productId;
    }

    public String getProductName(){
        return productName;
    }


    public double getProductPrice(){
        return price;
    }


    public abstract double getFinalPrice(double price);

    public void getPrice(){


    }

    public void DisplayProductInfo(){
         System.out.println("Product ID: " + productId);
         System.out.println("Product Name: " + productName);
         System.out.println("Price: " + price);
         System.out.println("--------------------------");

    }



}

///Electornics - subclass
class Electronic extends  Product{
    private final  double DISCOUNT = 10.00;

    public Electronic(String productId, String productName, double price){
        super(productId, productName,price);
    } 

    @Override
    public  double getFinalPrice(double price){
        double discountAmount = price * (DISCOUNT/100);
        double discountedPrice = price - discountAmount;

        return discountedPrice;
    }

}

class Clothing extends Product{
    private final double DISCOUNT = 20;

    public Clothing(String productId, String productName, double price){
        super(productId, productName,price);
    } 

    @Override
    public double getFinalPrice(double price){
        double discountAmount = price * (DISCOUNT/100);
        double discountedPrice = price - discountAmount;

        if(price > 1000){
         return price; ///return price if morethan 1000
        }else{
          return discountedPrice;
        }

    }
}

class Grocery extends Product{

    public Grocery(String productId, String productName, double price){
        super(productId, productName,price);
    } 

    @Override
    public double getFinalPrice(double price){
    return price;
    }
}
//Main Program
public class OnlineShoppingCart {
   private static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        while (true) {
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Remove Product");
            System.out.println("3. Checkout(showtotal)");
            System.out.println("4. Display Cart");
            System.out.println("5. Exit");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    ShoppingCart.addProduct();
                    break;
                
                case "2":
                    ShoppingCart.removeProduct();
                    break;
                case "3":
                    ShoppingCart.calculateTotal();
                    break;
                case "4":
                    ShoppingCart.displayCart();
                    break;
                case "5":
                    System.out.println("Exiting Program");
                    scanner.close();
                    return;
                default:
                    break;
            }
            
        }

    }
}

//Shopping Cart Class
 class ShoppingCart{
  private static  Scanner scanner = new Scanner(System.in);
  private  static  ArrayList<Product> products = new ArrayList<>();
    
    //Add Product
  public static void addProduct(){
        System.out.println("Enter Product Type (1-Electronics, 2-Clothing, 3-Grocery): ");
        String type = scanner.nextLine();

        System.out.print("Enter ProductId: ");
        String productId = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Product product = null; // initialize product contstruct into null to avoid errors
        switch (type) {
            case "1":
                product = new Electronic( productId, productName, price);
                System.out.println("Added Electronic Products Succesfully");
                break;
            case "2":
                product = new Clothing( productId, productName, price);
                System.out.println("Added Clothing Products Succesfully");
                break;
           case "3":
                product = new Grocery(productId, productName, price);
                System.out.println("Added Grocery Products Succesfully");
                break;
        
            default:
                break;
        }

        products.add(product);
  }
    //Remove Product
 public static void removeProduct(){
        System.out.println("Enter Product ID");
        String prodId = scanner.nextLine();

        boolean found = false;
        for(Product product: products){
            if(product.getProductId().equals(prodId)){
                products.remove(product);
                System.out.println("Succesfully remove pruduct" + prodId);
                found = true;
                break;
            }
        }

      if(!found){
            System.out.println("Product not found.");
        }
    
 }
 //Calculate Total Product
 public static void calculateTotal(){
      double totalAmount = 0.0;

      for(Product product: products){
        double finalPrice = product.getFinalPrice(product.getProductPrice());
        totalAmount += finalPrice;
      }

    System.out.println("===================");
    System.out.println("TOTAL CART AMOUNT: $" + totalAmount);
    

 }

 //Display Cart
static void displayCart(){
     if(products.isEmpty()){
            System.out.println("Cart is empty.");
            return;
      }


    for(Product product: products){
        System.out.println("Cart Contents");
        System.out.println(product.getProductId() +  " - " + product.getProductName() + " | " + "Final Price: " + product.getFinalPrice(product.getProductPrice()));
    }

}

}