package org.com.Servlet.login.web.Login;


public
class JavaObject{
    private String  id;
    private String name;
    private String email;
    private String password;
    private String city;

    public
    void setCity(String city) {
        this.city = city;
    }

    public JavaObject(String id, String name, String email, String city){
        this.id=id;
        this.name=name;
        this.password=password;
        this.email=email;
       this.city=city;


    }
    public
    String getName() {
        return name;
    }

    public
    String getPassword() {
        return password;
    }

    public
    void setPassword(String password) {
        this.password = password;
    }

    public
    JavaObject(String id) {
        this.id = id;
    }

    public
    String getEmail() {
        return email;
    }

    public
    void setEmail(String email) {
        this.email = email;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    String getId() {
        return id;
    }

    public
    void setId(String id) {
        this.id = id;
    }

    public
    String getCity() {
return city;

}}
