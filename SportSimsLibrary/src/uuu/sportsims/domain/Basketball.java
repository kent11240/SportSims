package uuu.sportsims.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Basketball implements java.io.Serializable { //可序列化 防止server發生 java.io.NotSerializableException,  Cannot serialize session attribute.
//可序列化需檢查"屬性的型別"皆可序列化

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Attribute
    /**
     * 賽事編號
     */
    private int gameId;
    /**
     * 賽事時間
     */
    private Date dateTime;
    /**
     * 客隊名稱
     */
    private String awayName;
    /**
     * 主隊名稱
     */
    private String homeName;
    /**
     * 讓分盤口(主隊)
     */
    private double pointSpread;
    /**
     * 大小盤口
     */
    private double total;

    //賠率--------------------
    /**
     * 客隊讓分
     */
    private double away = 1;
    /**
     * 主隊讓分
     */
    private double home = 1;
    /**
     * 大分
     */
    private double over = 1;
    /**
     * 小分
     */
    private double under = 1;
    /**
     * 客隊不讓分
     */
    private double awayPk = 1;
    /**
     * 主隊不讓分
     */
    private double homePk = 1;
    /**
     * 客隊第一節
     */
    private double awayQ1 = 1;
    /**
     * 第一節和局
     */
    private double drawQ1 = 1;
    /**
     * 主隊第一節
     */
    private double homeQ1 = 1;
    /**
     * 單
     */
    private double odd = 1;
    /**
     * 雙
     */
    private double even = 1;
    /**
     * 客隊1-5
     */
    private double away1to5 = 1;
    /**
     * 客隊6-10
     */
    private double away6to10 = 1;
    /**
     * 客隊11-15
     */
    private double away11to15 = 1;
    /**
     * 客隊16-20
     */
    private double away16to20 = 1;
    /**
     * 客隊21-25
     */
    private double away21to25 = 1;
    /**
     * 客隊26+
     */
    private double away26 = 1;
    /**
     * 主隊1-5
     */
    private double home1to5 = 1;
    /**
     * 主隊6-10
     */
    private double home6to10 = 1;
    /**
     * 主隊11-15
     */
    private double home11to15 = 1;
    /**
     * 主隊16-20
     */
    private double home16to20 = 1;
    /**
     * 主隊21-25
     */
    private double home21to25 = 1;
    /**
     * 主隊26+
     */
    private double home26 = 1;
    //------------------------

    /**
     * 客隊結果
     */
    private int awayResult = 0;
    /**
     * 主隊結果
     */
    private int homeResult = 0;
    /**
     * 客隊第一節結果
     */
    private int awayQ1Result = 0;
    /**
     * 主隊第一節結果
     */
    private int homeQ1Result = 0;
    /**
     * 賽事狀態: UNSTARTED("未開賽"), STARTED("已開賽"), PAYOUT("已派彩"), INVALID("已關閉")
     */
    private Status status = Status.UNSTARTED;

    //Getter & Setter
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) throws SportSimsException {
        if (gameId >= 0) {
            this.gameId = gameId;
        } else {
            throw new SportSimsException("賽事編號錯誤！");
        }
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getDateTimeString() {
        if (this.dateTime != null) {
            return dateFormat.format(this.dateTime);
        } else {
            return "";
        }
    }

    public void setDateTime(Date dateTime) throws SportSimsException {
        if (dateTime != null) {
            this.dateTime = dateTime;
        } else {
            throw new SportSimsException("賽事時間錯誤！");
        }
    }

    public void setDateTime(int year, int month, int day, int hour, int minute) throws SportSimsException {
        Date d = new GregorianCalendar(year, month - 1, day, hour, minute).getTime();
        this.setDateTime(d);
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) throws SportSimsException {
        if (awayName != null && (awayName = awayName.trim()).length() > 0) {
            this.awayName = awayName;
        } else {
            throw new SportSimsException("客隊名稱不可為空！");
        }
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) throws SportSimsException {
        if (homeName != null && (homeName = homeName.trim()).length() > 0) {
            this.homeName = homeName;
        } else {
            throw new SportSimsException("主隊名稱不可為空！");
        }
    }

    public double getPointSpread() {
        return pointSpread;
    }

    public void setPointSpread(double pointSpread) throws SportSimsException {
        this.pointSpread = pointSpread;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) throws SportSimsException {
        if (total >= 0) {
            this.total = total;
        } else {
            throw new SportSimsException("大小盤分不可為負！");
        }
    }

    public double getAway() {
        return away;
    }

    public void setAway(double away) throws SportSimsException {
        if (away >= 1) {
            this.away = away;
        } else {
            throw new SportSimsException("客隊讓分賠率最低為1.0！");
        }
    }

    public double getHome() {
        return home;
    }

    public void setHome(double home) throws SportSimsException {
        if (home >= 1) {
            this.home = home;
        } else {
            throw new SportSimsException("主隊讓分賠率最低為1.0！");
        }
    }

    public double getOver() {
        return over;
    }

    public void setOver(double over) throws SportSimsException {
        if (over >= 1) {
            this.over = over;
        } else {
            throw new SportSimsException("大分賠率最低為1.0！");
        }
    }

    public double getUnder() {
        return under;
    }

    public void setUnder(double under) throws SportSimsException {
        if (under >= 1) {
            this.under = under;
        } else {
            throw new SportSimsException("小分賠率最低為1.0！");
        }
    }

    public double getAwayPk() {
        return awayPk;
    }

    public void setAwayPk(double awayPk) throws SportSimsException {
        if (awayPk >= 1) {
            this.awayPk = awayPk;
        } else {
            throw new SportSimsException("客隊不讓分賠率最低為1.0！");
        }
    }

    public double getHomePk() {
        return homePk;
    }

    public void setHomePk(double homePk) throws SportSimsException {
        if (homePk >= 1) {
            this.homePk = homePk;
        } else {
            throw new SportSimsException("主隊不讓分賠率最低為1.0！");
        }
    }

    public double getAwayQ1() {
        return awayQ1;
    }

    public void setAwayQ1(double awayQ1) throws SportSimsException {
        if (awayQ1 >= 1) {
            this.awayQ1 = awayQ1;
        } else {
            throw new SportSimsException("客隊第一節賠率最低為1.0！");
        }
    }

    public double getDrawQ1() {
        return drawQ1;
    }

    public void setDrawQ1(double drawQ1) throws SportSimsException {
        if (drawQ1 >= 1) {
            this.drawQ1 = drawQ1;
        } else {
            throw new SportSimsException("第一節和局賠率最低為1.0！");
        }
    }

    public double getHomeQ1() {
        return homeQ1;
    }

    public void setHomeQ1(double homeQ1) throws SportSimsException {
        if (homeQ1 >= 1) {
            this.homeQ1 = homeQ1;
        } else {
            throw new SportSimsException("主隊第一節賠率最低為1.0！");
        }
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) throws SportSimsException {
        if (odd >= 1) {
            this.odd = odd;
        } else {
            throw new SportSimsException("單賠率最低為1.0！");
        }
    }

    public double getEven() {
        return even;
    }

    public void setEven(double even) throws SportSimsException {
        if (even >= 1) {
            this.even = even;
        } else {
            throw new SportSimsException("雙賠率最低為1.0！");
        }
    }

    public double getAway1to5() {
        return away1to5;
    }

    public void setAway1to5(double away1to5) throws SportSimsException {
        if (away1to5 >= 1) {
            this.away1to5 = away1to5;
        } else {
            throw new SportSimsException("客隊1-5賠率最低為1.0！");
        }
    }

    public double getAway6to10() {
        return away6to10;
    }

    public void setAway6to10(double away6to10) throws SportSimsException {
        if (away6to10 >= 1) {
            this.away6to10 = away6to10;
        } else {
            throw new SportSimsException("客隊6-10賠率最低為1.0！");
        }
    }

    public double getAway11to15() {
        return away11to15;
    }

    public void setAway11to15(double away11to15) throws SportSimsException {
        if (away11to15 >= 1) {
            this.away11to15 = away11to15;
        } else {
            throw new SportSimsException("客隊11-15賠率最低為1.0！");
        }
    }

    public double getAway16to20() {
        return away16to20;
    }

    public void setAway16to20(double away16to20) throws SportSimsException {
        if (away16to20 >= 1) {
            this.away16to20 = away16to20;
        } else {
            throw new SportSimsException("客隊16-20賠率最低為1.0！");
        }
    }

    public double getAway21to25() {
        return away21to25;
    }

    public void setAway21to25(double away21to25) throws SportSimsException {
        if (away21to25 >= 1) {
            this.away21to25 = away21to25;
        } else {
            throw new SportSimsException("客隊21-25賠率最低為1.0！");
        }
    }

    public double getAway26() {
        return away26;
    }

    public void setAway26(double away26) throws SportSimsException {
        if (away26 >= 1) {
            this.away26 = away26;
        } else {
            throw new SportSimsException("客隊26+賠率最低為1.0！");
        }
    }

    public double getHome1to5() {
        return home1to5;
    }

    public void setHome1to5(double home1to5) throws SportSimsException {
        if (home1to5 >= 1) {
            this.home1to5 = home1to5;
        } else {
            throw new SportSimsException("主隊1-5賠率最低為1.0！");
        }
    }

    public double getHome6to10() {
        return home6to10;
    }

    public void setHome6to10(double home6to10) throws SportSimsException {
        if (home6to10 >= 1) {
            this.home6to10 = home6to10;
        } else {
            throw new SportSimsException("主隊6-10賠率最低為1.0！");
        }
    }

    public double getHome11to15() {
        return home11to15;
    }

    public void setHome11to15(double home11to15) throws SportSimsException {
        if (home11to15 >= 1) {
            this.home11to15 = home11to15;
        } else {
            throw new SportSimsException("主隊11-15賠率最低為1.0！");
        }
    }

    public double getHome16to20() {
        return home16to20;
    }

    public void setHome16to20(double home16to20) throws SportSimsException {
        if (home16to20 >= 1) {
            this.home16to20 = home16to20;
        } else {
            throw new SportSimsException("主隊16-20賠率最低為1.0！");
        }
    }

    public double getHome21to25() {
        return home21to25;
    }

    public void setHome21to25(double home21to25) throws SportSimsException {
        if (home21to25 >= 1) {
            this.home21to25 = home21to25;
        } else {
            throw new SportSimsException("主隊21-25賠率最低為1.0！");
        }
    }

    public double getHome26() {
        return home26;
    }

    public void setHome26(double home26) throws SportSimsException {
        if (home26 >= 1) {
            this.home26 = home26;
        } else {
            throw new SportSimsException("主隊26+賠率最低為1.0！");
        }
    }

    public int getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(int awayResult) throws SportSimsException {
        if (awayResult >= 0) {
            this.awayResult = awayResult;
        } else {
            throw new SportSimsException("客隊結果錯誤！");
        }
    }

    public int getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(int homeResult) throws SportSimsException {
        if (homeResult >= 0) {
            this.homeResult = homeResult;
        } else {
            throw new SportSimsException("主隊結果錯誤！");
        }
    }

    public int getAwayQ1Result() {
        return awayQ1Result;
    }

    public void setAwayQ1Result(int awayQ1Result) throws SportSimsException {
        if (awayQ1Result >= 0) {
            this.awayQ1Result = awayQ1Result;
        } else {
            throw new SportSimsException("客隊第一節結果錯誤！");
        }
    }

    public int getHomeQ1Result() {
        return homeQ1Result;
    }

    public void setHomeQ1Result(int homeQ1Result) throws SportSimsException {
        if (homeQ1Result >= 0) {
            this.homeQ1Result = homeQ1Result;
        } else {
            throw new SportSimsException("主隊第一節結果錯誤！");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getOddsByItem(String item) throws SportSimsException {
        switch (item) {
            case "away":
                return getAway();
            case "home":
                return getHome();
            case "over":
                return getOver();
            case "under":
                return getUnder();
            case "awayPk":
                return getAwayPk();
            case "homePk":
                return getHomePk();
            case "awayQ1":
                return getAwayQ1();
            case "drawQ1":
                return getDrawQ1();
            case "homeQ1":
                return getHomeQ1();
            case "odd":
                return getOdd();
            case "even":
                return getEven();
            case "away1to5":
                return getAway1to5();
            case "away6to10":
                return getAway6to10();
            case "away11to15":
                return getAway11to15();
            case "away16to20":
                return getAway16to20();
            case "away21to25":
                return getAway21to25();
            case "away26":
                return getAway26();
            case "home1to5":
                return getHome1to5();
            case "home6to10":
                return getHome6to10();
            case "home11to15":
                return getHome11to15();
            case "home16to20":
                return getHome16to20();
            case "home21to25":
                return getHome21to25();
            case "home26":
                return getHome26();
            default:
                throw new SportSimsException("賠率項目名稱錯誤！");
        }
    }

    public void setOddsByItem(String item, double value) throws SportSimsException {
        switch (item) {
            case "away":
                setAway(value);
                break;
            case "home":
                setHome(value);
                break;
            case "over":
                setOver(value);
                break;
            case "under":
                setUnder(value);
                break;
            case "awayPk":
                setAwayPk(value);
                break;
            case "homePk":
                setHomePk(value);
                break;
            case "awayQ1":
                setAwayQ1(value);
                break;
            case "drawQ1":
                setDrawQ1(value);
                break;
            case "homeQ1":
                setHomeQ1(value);
                break;
            case "odd":
                setOdd(value);
                break;
            case "even":
                setEven(value);
                break;
            case "away1to5":
                setAway1to5(value);
                break;
            case "away6to10":
                setAway6to10(value);
                break;
            case "away11to15":
                setAway11to15(value);
                break;
            case "away16to20":
                setAway16to20(value);
                break;
            case "away21to25":
                setAway21to25(value);
                break;
            case "away26":
                setAway26(value);
                break;
            case "home1to5":
                setHome1to5(value);
                break;
            case "home6to10":
                setHome6to10(value);
                break;
            case "home11to15":
                setHome11to15(value);
                break;
            case "home16to20":
                setHome16to20(value);
                break;
            case "home21to25":
                setHome21to25(value);
                break;
            case "home26":
                setHome26(value);
                break;
            default:
                throw new SportSimsException("賠率項目名稱錯誤！");
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.gameId;
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
        final Basketball other = (Basketball) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Basketball{" + "gameId=" + gameId + ", dateTime=" + dateTime
                + ", awayName=" + awayName + ", homeName=" + homeName
                + ", pointSpread=" + pointSpread + ", total=" + total
                + ", away=" + away + ", home=" + home + ", over=" + over
                + ", under=" + under + ", awayPk=" + awayPk + ", homePk=" + homePk
                + ", awayQ1=" + awayQ1 + ", drawQ1=" + drawQ1
                + ", homeQ1=" + homeQ1 + ", odd=" + odd + ", even=" + even
                + ", away1to5=" + away1to5 + ", away6to10=" + away6to10
                + ", away11to15=" + away11to15 + ", away16to20=" + away16to20
                + ", away21to25=" + away21to25 + ", away26=" + away26
                + ", home1to5=" + home1to5 + ", home6to10=" + home6to10
                + ", home11to15=" + home11to15 + ", home16to20=" + home16to20
                + ", home21to25=" + home21to25 + ", home26=" + home26
                + ", awayResult=" + awayResult + ", homeResult=" + homeResult
                + ", awayQ1Result=" + awayQ1Result + ", homeQ1Result=" + homeQ1Result
                + ", status=" + status + '}';
    }

    public enum Status implements java.io.Serializable {
        UNSTARTED("未開賽"), STARTED("已開賽"), PAYOUT("已派彩"), INVALID("已關閉");
        private final String description;

        private Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

}
