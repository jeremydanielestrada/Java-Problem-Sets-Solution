import java.util.*;
public class Main{


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[]args){


        while(true){
            System.out.println("1.Base Conversion (Single Number).");
            System.out.println("2.Arithmetic Operation (Two Numbers, Any Base).");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    baseConversion();
                    break;
                case 2:
                  arithmeticOperations();
                break;
                default:
                    break;
            }

            
        }

    }

    static void baseConversion(){
        System.out.print("Enter number: ");
        String number = scanner.nextLine();

        System.out.print("Enter Base: ");
        String baseStr = scanner.nextLine();

        

    int base = switch (baseStr.toLowerCase()) {
        case "binary" -> 2;
        case "octal" -> 8;
        case "decimal" -> 10;
        case "hexadecimal" -> 16;
        default -> { System.out.println("Invalid base");
            yield -1;
         }
    };
    if (base == -1) return;
    int decimal = Integer.parseInt(number, base);


        System.out.println("Decimal: " + decimal);
        System.out.println("Binary: " + Integer.toBinaryString(decimal));   
        System.out.println("Octal: " + Integer.toOctalString(decimal));
        System.out.println("Hexadecimal: " + Integer.toHexString(decimal).toUpperCase());

    }


    static void arithmeticOperations(){
        System.out.print("Enter first number: ");
        String num1 = scanner.nextLine();

        System.out.print("Enter base 1: ");
        String baseStr1 = scanner.nextLine();

        System.out.print("Enter second number: ");
        String num2 = scanner.nextLine();


        System.out.print("Enter base 2:");
        String baseStr2 = scanner.nextLine();

        System.out.println("Enter Operation(+/-): ");
        String operation = scanner.nextLine();



    int base1 = switch (baseStr1.toLowerCase()) {
        case "binary" -> 2;
        case "octal" -> 8;
        case "decimal" -> 10;
        case "hexadecimal" -> 16;
        default -> { System.out.println("Invalid base");
            yield -1;
         }
    };  
    if (base1 == -1) return;


    int base2 = switch (baseStr2.toLowerCase()) {
        case "binary" -> 2;
        case "octal" -> 8;
        case "decimal" -> 10;
        case "hexadecimal" -> 16;
        default -> { System.out.println("Invalid base");
            yield -1;
         }
    };  
    if (base2 == -1) return;


     int num1Dec = Integer.parseInt(num1,base1);
     int num2Dec = Integer.parseInt(num2,base2);

     int result = operation.equals("+") ? num1Dec + num2Dec : num1Dec - num2Dec;

    System.out.println("Output");
    System.out.println("Decimal: " + result);
    System.out.println("Binary: " + Integer.toBinaryString(result));
    System.out.println("Octal: " + Integer.toOctalString(result));
    System.out.println("Hexadecimal: " + Integer.toHexString(result).toUpperCase());

    }


    

   static boolean isValid(String num, int base){
    return switch(base){
        case 2 -> num.matches("[0-1]+");
        case 8 -> num.matches("[0-7]+");
        case 10 -> num.matches("[0-9]+");
        case 16 -> num.matches("[0-9A-Fa-f]+");
        default -> {
            System.out.println("Invalid base number");
            yield false;
        }
    };
}



}