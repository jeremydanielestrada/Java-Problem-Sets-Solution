 import java.util.ArrayList;
 import java.util.Scanner;
 import java.io.FileWriter;
 import java.io.IOException;

      class Book{
            //Attributes
            String  bookId;
            String  title;
            String  author;
            int     yearPublished;
            String  status;

            //Constructor
                 Book(String bookId, String title, String author, int yearPublished, String status){
                this.bookId = bookId;
                this.title = title;
                this.author = author;
                this.yearPublished  = yearPublished;
                this.status = status;
            }


        }


 public class LibraryClass{
            static Scanner scanner = new Scanner(System.in);
            static ArrayList<Book> books  = new ArrayList<>();


        public static void main(String[] args){
                
                     while(true){
                        System.out.println("===== Library Book Records Manager =====");
                        System.out.println("1. Add Book Record");
                        System.out.println("2. View All Books");
                        System.out.println("3. Update Book Record");
                        System.out.println("4. Delete Book Record");
                        System.out.println("5. Search Book");
                        System.out.println("6. Exit Program");
                        String input = scanner.nextLine();


                        switch (input) {
                            case "1":
                                AddBookRecord();
                                break;
                            case "2":
                            ViewAllBooks();
                                break;   
                            case "3":
                            UpdateBook();
                                break;   
                            case "4":
                            DeleteBook();
                                break;
                            case "5":
                            SearchBook();
                                break;
                            case "6":
                                 System.out.println("6. Exiting Program");
                                 scanner.close();
                                 return;
                            default:
                                 System.out.println("None of the choices");
                                  break;
                        }




                     }

        }
        //Add Book Record
        static void AddBookRecord(){
           System.out.println("Enter Book ID");
           String id = scanner.nextLine(); 

            if(UniqueId(id)){
                System.out.println("This Id is alread exits");
                return;
            }

           System.out.println("Enter Title");
           String title = scanner.nextLine(); 

           System.out.println("Enter Author");
           String author = scanner.nextLine(); 

           System.out.println("Enter Year");
           String year = scanner.nextLine(); 

           System.out.println("Enter Status");
           String status = scanner.nextLine(); 


           Book book = new Book(id,title,author,Integer.parseInt(year),status); ///Call the constructor name to insert the datas

           books.add(book);

           System.out.println("Added Book Successfully");

           InsertToFile();
        }

            //Function to check for unique Book Id
        static boolean UniqueId(String inputId){

            for(Book book: books){
                if(inputId.equals(book.bookId)){
                    return true;

                }
            }

            return false;

        }


        static void InsertToFile(){

            try{
             FileWriter w =  new FileWriter("book.txt");

                for(Book b: books ){
                    w.write(b.bookId + "|" + b.title + "|" + b.author + "|" + b.yearPublished + "|" + b.status + " \n");
                }
                     w.close();
                

            }catch(IOException e){
             System.out.println("An error occurred: " + e.getMessage());
            }
        }

        //View  All Books
        static void ViewAllBooks(){
                System.out.printf("%-8s %-20s %-15s %-6s %-10s%n", 
                  "Book Id", "Title", "Author", "Year", "Status");
                System.out.println("=".repeat(70));

            for(Book book: books){
                System.out.printf("%-8s %-20s %-15s %-6d %-10s%n",
                      book.bookId,
                      book.title,
                      book.author,
                      book.yearPublished,
                      book.status);
}
        
}
        //Update Book Status
    static void UpdateBook(){
        System.out.println("Enter BookID to update: ");
        String inputId = scanner.nextLine();

        System.out.println("Enter new Status: ");
        String newStatus = scanner.nextLine();


        var it = books.iterator();
        boolean  found = false;

        while(it.hasNext()){
             Book book = it.next();
             if(book.bookId.equals(inputId)){
                book.status = newStatus;
                found = true;
                InsertToFile();
                System.out.println("Update Status Successfully");
                break;
             }
        }

        if(!found){
            System.out.println("Book Id not found");
        }

        // for(Book book: books){
        //     if(inputId.equals(book.bookId)){
        //         book.status = newStatus;
        //         System.out.println("Update Status Successfully");
        //         break;
        //     }
        // }
    }

    static void DeleteBook(){
        System.out.println("Enter Book Id to delete");
        String inputId = scanner.nextLine();


        //can use var for short term instead of  -Iterator<Books>-
        var it = books.iterator();
        boolean found = false;



            while(it.hasNext()){
                 Book book = it.next();
                 if(book.bookId.equals(inputId)){
                    it.remove();
                    found = true;
                    InsertToFile(); //Insert the new clean file
                    System.out.println("Deleted Book Successfully");
                    break;  
                }

             }


             if(!found){
                System.out.println("Book Id not found");
             }
    } 

    static void SearchBook(){
        System.out.println("Enter Book Id to search ");
        String bookId = scanner.nextLine();

        var it = books.iterator();
        boolean found = false;


        while(it.hasNext()){
            Book book = it.next();
            if(book.bookId.equals(bookId)){
            found = true;
              System.out.printf("%-8s %-20s %-15s %-6d %-10s%n",
                book.bookId,
                book.title,
                book.author,
                book.yearPublished,
                book.status);
                break;
            }
        }

        if(!found){
            System.out.println("Book Id not found");
        }


    }


}