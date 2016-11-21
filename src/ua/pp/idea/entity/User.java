package ua.pp.idea.entity;

import java.io.Serializable;
import java.time.LocalDate;




/**
 * Created by Dark on 07.11.2016.
 */
public class User implements Serializable{
    private Long id;
    private String username;
    private String useremail;
    private String userpwd;
    private String userkpwd;
    private LocalDate datereg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUserkpwd() { return userkpwd; }

    public void setUserkpwd(String userkpwd) {
        this.userkpwd = userkpwd;
    }

    public LocalDate getDatereg() {
        return datereg;
    }

    public void setDatereg(LocalDate datereg) {
        this.datereg = datereg;
    }
}
