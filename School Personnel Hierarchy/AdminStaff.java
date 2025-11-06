public class AdminStaff  extends Person{
    private String department;

        AdminStaff(String id, String name, int age, String department){
            super(id, name, age);
            this.department = department;
        }

        public String GetDepartment(){
         return department;
        }

        @Override
        public void Introduce(){
            System.out.println("ID: " + GetId() + " - " +"I am " + GetName() + ", working in the " + GetDepartment());
        }


        public void ProcesssDocument(){
            System.out.println( GetName() + " has processed a document.");
        }

}
