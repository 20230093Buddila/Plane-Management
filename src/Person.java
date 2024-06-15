public class Person {
    private String name;
    private String surname;
    private String email;

    //constructors
    public Person(String name, String surname,String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        //personInfo();
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //getters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }

    //Method to access information of the person
    public void personInfo() {
        System.out.println("Name: " + name );
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}
