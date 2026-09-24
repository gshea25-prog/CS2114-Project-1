public class Website {
    private String name;
    private Password password;

    public Website(String name, Password password){
        this.name = name;
        this.password = password;
    }

    public Website(){

    }

    public String getName(){
        return name;
    }

    public Password getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(Password password){
        this.password = password;
    }
}
