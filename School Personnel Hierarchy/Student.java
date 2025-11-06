public class Student extends Person {
     private int yearLvl;
     private String course;

        Student(String id, String name, int age, int yearLvl, String course){
            super(id, name, age);
            this.yearLvl = yearLvl;
            this.course = course;
        }

        public int GetYearlvl(){
            return yearLvl;
        }
        
        public String GetCourse(){
            return course;
        }
       

    @Override
    public void Introduce(){
        System.out.println("ID: " + GetId() + " - " + "I am " + GetName() + " a " + GetYearlvl() + " student of " + GetCourse());
    }

    public void SubmitAssignment(){
        System.out.println( GetName() + " has submitted an assignment.");
    }
}
