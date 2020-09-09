package uuu.sportsims.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Member {

    public static final int INVALID = 0;
    public static final int VALID = 1;
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //Attribute
    /**
     * 英數1~50碼, 必要欄位
     */
    private String id;
    /**
     * 6~20碼, 必要欄位
     */
    private String password;
    /**
     * 必要欄位
     */
    private String nickname;
    /**
     * 必要欄位
     */
    private String email;
    /**
     * >18y, 必要欄位
     */
    private Date birthday; //import java.util.Date
    /**
     * 預設10000
     */
    private int money = 10000;
    /**
     * 會員狀態: 0:停用 1:啟用
     */
    private int status = 1;

    //Constructors
    public Member() {
    }

    public Member(String id, String password, String nickname, String email, Date birthday, int money) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.money = money;
    }

    //Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) throws SportSimsException {
        if (id != null
                && (id = id.trim()).length() > 0 && id.length() <= 50
                && id.matches("^[a-zA-Z0-9]\\w+$")) {
            this.id = id;
        } else {
            throw new SportSimsException("帳號請為大小寫英數字底線！");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws SportSimsException {
        if (password != null
                && (password = password.trim()).length() >= 6 && password.length() <= 20) {
            this.password = password;
        } else {
            throw new SportSimsException("密碼長度必須為6~20碼！");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) throws SportSimsException {
        if (nickname != null && (nickname = nickname.trim()).length() > 0 && nickname.length() <= 20) {
            this.nickname = nickname;
        } else {
            throw new SportSimsException("暱稱不可為空白，最多20字！");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws SportSimsException {
        if (email != null
                && (email = email.trim()).matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        } else {
            throw new SportSimsException("E-mail格式錯誤！");
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBirthdayString() {
        if (this.birthday != null) {
            return dateFormat.format(this.birthday);
        } else {
            return "";
        }
    }

    public void setBirthday(Date birthday) throws SportSimsException {
        if (birthday != null && birthday.before(new Date())
                && getAge(birthday) >= 18) {
            this.birthday = birthday;
        } else {
            throw new SportSimsException("生日不合法且必須年滿十八歲！");
        }
    }

    public void setBirthday(int year, int month, int day) throws SportSimsException {
        Date d = new GregorianCalendar(year, month - 1, day).getTime();
        this.setBirthday(d);
    }

    public void setBirthday(String date) throws SportSimsException {
        if (date != null || (date = date.trim()).length() != 0) {
            try {
                date = date.replace('/', '-');
                Date d = dateFormat.parse(date);
                this.setBirthday(d);
            } catch (ParseException ex) {
                Logger.getLogger(Member.class.getName()).log(Level.SEVERE, "日期格式錯誤", ex);
                throw new SportSimsException("日期格式錯誤", ex);
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) throws SportSimsException {
        if (money >= 0) {
            this.money = money;
        } else {
            throw new SportSimsException("金額錯誤！");
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //Business method
    public int getAge(Date birthday) {
        if (birthday == null) {
            return -1;
        }

        Calendar now = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);

        int yearNow = now.get(Calendar.YEAR);
        int yearBirth = birth.get(Calendar.YEAR);

        int age = yearNow - yearBirth;

        int monthNow = now.get(Calendar.MONTH);
        int monthBirth = birth.get(Calendar.MONTH);

        if (monthBirth > monthNow) {
            age--;
        } else if (monthNow == monthBirth) {
            int dayNow = now.get(Calendar.DAY_OF_MONTH);
            int dayBirth = birth.get(Calendar.DAY_OF_MONTH);
            if (dayBirth > dayNow) {
                age--;
            }
        }

        return age;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", password=" + password + ", nickname=" + nickname
                + ", email=" + email + ", birthday=" + birthday + ", money=" + money + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
