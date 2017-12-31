package Login;

import org.mongodb.morphia.annotations.Entity;

import static Application.Main.morphia;

@Entity("employees")
public class Employee{

    private String employeeId;
    private String name;
    private String password;
    private String role;

    private Employee() {

    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    static Employee login(String id, String password){
        return morphia.getDatastore().createQuery(Employee.class).
                field("employeeId").equal(id).
                field("password").equal(password).
                fetch().next();
    }

}
