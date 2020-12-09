package myapp.admin.example.com.onlinelawfirm;

public class LawData  {
    String title , description, pdf;
    public LawData(  String title , String description,String pdf)
    {
        this.title = title;
        this.description= description;
        this.pdf = pdf;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPdf() {
        return pdf;
    }
}
