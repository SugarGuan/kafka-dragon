package Producer;

public class User {
    private String username;
    private int age;

    public User(String name, int age) {
        setUsername(name);
        setAge(age);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }




}