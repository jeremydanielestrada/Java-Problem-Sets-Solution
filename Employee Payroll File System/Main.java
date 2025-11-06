import java.util.*;
import java.io.*;
 class Employee{
    int id;
    String fullName;
    String position;
    int basicSalary;
    int allowances;
    int deductions;
    double grossSalary;
    double tax;
    double netSalary;


    public Employee(int id, String fullName, String position, int basicSalary, int allowances, int deductions, float netSalary){
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
        this.grossSalary = basicSalary + allowances  - deductions;
        this.tax =  grossSalary *  0.10;
        this.netSalary = grossSalary - tax;
    }



}



public class Main{
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args){
            while (true) {
                System.out.println("Choose Operation");
                System.out.println("1. Add Record");
                System.out.println("2. View Records");
                System.out.println("3. Update Record");
                System.out.println("4. Delete Record");
                System.out.println("5. Search Record");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addRecord();
                        break;
                    case 2:
                        displayRecords();
                        break;
                    case 3:
                        editEmployee();
                        break;
                    case 4:
                        deleteRecord();
                        break;
                    case 5:
                        generatePaySlip();
                        break;
                    default:
                        System.out.println("None of the choices");
                        break;
                }
            }


    }

    static void addRecord(){
        System.out.print("Enter Employee id: ");
        int id = scanner.nextInt();

        if(!uniqueId(id)){
            System.out.println("Id is already existed");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter Full Name: ");
        String fname = scanner.nextLine();

        System.out.print("Enter Position: ");
        String position = scanner.nextLine();

        System.out.print("Enter Basic Salary: ");
        int bsalary = scanner.nextInt();

        if(!validateSallary(bsalary)){
            System.out.println("Invalid Salary");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter Allowances: ");
        int allowances = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Deduction: ");
        int deductions = scanner.nextInt();

        scanner.nextLine();

        try{
            Employee emp = new Employee(id, fname, position, bsalary, allowances, deductions, bsalary);
            employees.add(emp);
            addToFile();
            System.out.println("Succesfully added Employee");
        }catch(Exception e){
            System.out.println("Error occurred: " + e.getMessage());
            return;
        }
        


    }


    static void displayRecords(){

        if(employees.isEmpty()){
            System.out.println("No Records Found");
            return;
        }
            System.out.println("--------------------------------------------------------");
            System.out.printf("%-5s %-15s %-20s %-10s%n", "ID", "Name", "Position", "Net Salary");
            System.out.println("--------------------------------------------------------");
        for(Employee emp: employees){
           
            
              // Display formatted row
            System.out.printf("%-5d %-15s %-20s %-10.2f%n", emp.id,emp.fullName, emp.position, emp.netSalary);
            System.out.println("--------------------------------------------------------");
            
        }
    }

    static void editEmployee(){
        System.out.print("Enter employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine();

         System.out.print("Enter Basic Salary: ");
         int bsalary = scanner.nextInt();

        boolean found = false;

        for(Employee emp: employees){

            if(empId == emp.id){
                
                if(!validateSallary(bsalary)){
                    System.out.println("Invalid Salary");
                    return;
                }
                found = true;
                emp.basicSalary = bsalary;
                emp.grossSalary = emp.basicSalary + emp.allowances - emp.deductions;
                emp.tax = emp.grossSalary * 0.10;
                emp.netSalary = emp.grossSalary - emp.tax;
                displayRecords();
                System.out.println("Succesfully Updated employee");
                break;
            }
            
        }

        if(!found){
            System.out.println("Id not found");
        }
    }

    static void deleteRecord(){
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        var it = employees.iterator();

        while(it.hasNext()){
        Employee emp = it.next();
        if(emp.id == id){
             employees.remove(emp);
             found = true;
             break;
        }
    }

    if(!found)System.out.println("Id not found");

}


    static void generatePaySlip(){
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        var it = employees.iterator();

        while(it.hasNext()){
        Employee emp = it.next();
        if(emp.id == id){
            System.out.println("Payslip for " + emp.fullName);
             System.out.println("--------------------------------------------------");
             System.out.println("Basic Salary: " + emp.basicSalary);
             System.out.println("Allowances: " + emp.allowances);
             System.out.println("Deductions: " + emp.deductions);
             System.out.println("Gross Salary: " + emp.grossSalary);
             System.out.println("Tax (10%) : " + emp.tax);
             System.out.println("Net Salary : " + emp.netSalary);
             System.out.println("--------------------------------------------------");
             found = true;
             break;
        }
    }

         if(!found)System.out.println("Id not found");
    }



    ///Helpers
    static boolean uniqueId(int id){
        for(Employee emp : employees){
            if(emp.id == id){
                return false;
            }
        }
        return true;
    }

    static boolean validateSallary(int sallary){
        return sallary > 1 && sallary != 0;
    }


    static void addToFile() throws IOException {
        FileWriter writer =  new FileWriter("Employee.txt");

        for(Employee emp: employees){
            writer.write(emp.id + " | " + emp.fullName + " | " + emp.position + " | " + emp.netSalary);
        }

        writer.close();
    }

}