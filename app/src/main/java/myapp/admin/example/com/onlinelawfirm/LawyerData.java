package myapp.admin.example.com.onlinelawfirm;

public class LawyerData  {

    String id, name , email , contact , lawyer_type , designation , description ;
    public LawyerData( String id , String name ,  String email , String contact, String lawyer_type , String designation , String description )
    {

        this.name = name;
        this.email = email;
        this.contact = contact;
        this.lawyer_type = lawyer_type;
        this.designation = designation;
        this.description = description;
        this.id = id;


    }



    public String getContact() {
        return contact;
    }



    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDesignation() {
        return designation;
    }

    public String getLawyer_type() {
        return lawyer_type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setLawyer_type(String lawyer_type) {
        this.lawyer_type = lawyer_type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
