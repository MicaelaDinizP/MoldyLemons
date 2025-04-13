package devandroid.micaela.moldylemons;

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
        this.partnerOneName = partnerOneName;
        this.partnerTwoName = partnerTwoName;
        this.anniversaryDate = anniversaryDate;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerOneName() {
        return this.partnerOneName;
    }

    public void setPartnerOneName(String personOneName) {
        this.partnerOneName = personOneName;
    }

    public String getPartnerTwoName() {
        return this.partnerTwoName;
    }

    public void setPartnerTwoName(String personTwoName) {
        this.partnerTwoName = personTwoName;
    }

    public Date getAnniversaryDate() {
        return this.anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}