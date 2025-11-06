import java.util.*;


public class PersonelManager{

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Person> persons =  new ArrayList<>();
    public static void main(String[] args){


            while (true) {
                System.out.println("1. Add Teacher / Student / Admin");
                System.out.println("2. View All Personnel");
                System.out.println("3. Call role-specific actions");
                System.out.println("4. Exit");
                String input = scanner.nextLine();


                switch (input) {
                    case "1":
                        AddPersonel();
                        break;

                    case "2":
                        ViewAllPersonel();
                        break;
                    case "3":  
                        RoleSpecificActions();
                        break; 
                    case "4":
                        scanner.close();
                        return;
                    default:
                        break;
                }

                
            }


    }

    static boolean UniqueId(String id){
        for(Person person: persons){
          if (person.GetId().equals(id)) {
             return false;
            } 
            
          }

        return true;
    }

    static boolean ValidateAge(int age){
        if(age <= 0){
            return false;
        }
        return true;
    }
    //Add Personel
    static void AddPersonel(){
                System.out.println("1. Teacher");
                System.out.println("2. Student");
                System.out.println("3. Admin");
                System.out.print("Enter Choice: ");
                String input = scanner.nextLine();

                 System.out.print("Id: ");
                 String id = scanner.nextLine();

                 if(!UniqueId(id)){
                   System.out.println("This Id is already existed.");
                    return;
                 }
                 
                 System.out.print("Name: ");
                 String name = scanner.nextLine();

                 System.out.print("Age: ");
                 int age = scanner.nextInt();

                 if(ValidateAge(age)){
                    System.out.println("Invalid Age.");
                    return;
                 }

                 scanner.nextLine();

        


                switch (input) {
                    case "1":
                         System.out.print("Subject: ");
                         String subject = scanner.nextLine();
                         Teacher teacher =  new  Teacher (id, name, age, subject);
                         persons.add(teacher);
                         teacher.Introduce();
                        break;
                    case "2":
                         System.out.print("Year level: ");
                         int yearLvl = scanner.nextInt();
                         scanner.nextLine();
                         System.out.print("Course: ");
                         String course = scanner.nextLine();
                         Student student = new Student(id, name, age, yearLvl, course);
                         student.Introduce();
                         persons.add(student);
                         break;
                    case "3":
                         System.out.print("Department: ");
                         String department = scanner.nextLine();
                         AdminStaff admin = new AdminStaff(id, name, age, department);
                         admin.Introduce();
                         persons.add(admin);
                         break;
                    default:
                    System.out.println("Invalid Input");
                        break;
                }


    }


    static void ViewAllPersonel(){
        if(persons.isEmpty()){
            System.out.println("There is no Personel");
        }

        for(Person person: persons){
            person.Introduce();
        }
    }


    static void RoleSpecificActions(){
        System.out.print("Choose Personnel ID: ");
    String id = scanner.nextLine();

    for (Person person : persons) {
        if (person.GetId().equals(id)) {
            if (person instanceof Teacher teacher) {
                System.out.println("Action: Assign Grade");
                teacher.AssignGrade();
            } else if (person instanceof Student student) {
                System.out.println("Action: Submit Assignment");
                student.SubmitAssignment();
            } else if (person instanceof AdminStaff admin) {
                System.out.println("Action: Process Document");
                admin.ProcesssDocument();
            }
            return;
        }
    }
    System.out.println("Personnel not found.");
            
        }
            

    }
    
