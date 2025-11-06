public abstract class Person {
      private  String id;
      private  String name;
      private int age;


      Person(String id, String name, int age){
        this.id  = id;
        this.name = name;
        this.age = age;
      }


      ///getters and setters
      public String GetId(){
        return id;
      }


      public String GetName(){
        return name;
      }

      public int GetAge(){
        return age;
      }


      public abstract void Introduce();
}
