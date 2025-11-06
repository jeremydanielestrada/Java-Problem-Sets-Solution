public class Teacher extends Person {
        private String subject;

        Teacher(String id, String name, int age, String subject){
            super(id, name, age);
            this.subject = subject;
        }

        public String GetSubject(){
        return subject;
         }

        
    
    @Override
    public  void Introduce(){
        System.out.println("ID: " + GetId() + " - " +"I am " + GetName() + ", a Teacher of " + GetSubject());
    }; 


    public void AssignGrade(){
        System.out.println( GetName() + " Assigned a grade to student");
    }
}

