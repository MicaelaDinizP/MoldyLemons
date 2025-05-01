package devandroid.micaela.moldylemons.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "couples",
        indices = {@Index(value = "login", unique = true)})
public class Couple {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "partner_one_name")
    private String partnerOneName;

    @ColumnInfo(name = "partner_two_name")
    private String partnerTwoName;

    @ColumnInfo(name = "anniversary_date")
    private Date anniversaryDate;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "password")
    private String password;

    public Couple(String partnerOneName, String partnerTwoName, Date anniversaryDate, String login, String password) {
        this.setPartnerOneName(partnerOneName);
        this.setPartnerTwoName(partnerTwoName);
        this.setAnniversaryDate(anniversaryDate);
        this.setLogin(login);
        this.setPassword(password);
    }

    private boolean isInvalidLogin( String login ) {
        if (!login.matches("^[a-zA-Z][a-zA-Z0-9._]{3,19}$")) {
            return true;
        }
        return false;
    }

    private boolean isInvalidPassword( String password ) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=-]{8,30}$")) {
            return true;
        }
        return false;
    }

    private String validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty.");
        }
        return name;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than zero.");
        }
        this.id = id;
    }

    public String getPartnerOneName() {
        return this.partnerOneName;
    }
    public void setPartnerOneName(String name) {
        this.partnerOneName = validateName(name, "Partner one's name");
    }
    public String getPartnerTwoName() {
        return this.partnerTwoName;
    }
    public void setPartnerTwoName(String name) {
        this.partnerTwoName = validateName(name, "Partner two's name");
    }

    public Date getAnniversaryDate() {
        return this.anniversaryDate;
    }
    public void setAnniversaryDate(Date date) {
        if (date == null || date.after(new Date())) {
            throw new IllegalArgumentException("Anniversary date cannot be null or in the future.");
        }
        this.anniversaryDate = date;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be null or empty.");
        }
        if(isInvalidLogin(login)) {
            throw new IllegalArgumentException("Login must start with a letter and be 4-20 characters long, using letters, digits, '.' or '_'.");
        }
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if(isInvalidPassword(password)) {
            throw new IllegalArgumentException("Password must be 8-30 characters long, with at least one letter and one number.");
        }
        this.password = password;
    }

}