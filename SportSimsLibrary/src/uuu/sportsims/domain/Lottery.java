package uuu.sportsims.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import static uuu.sportsims.domain.Basketball.dateFormat;

public class Lottery {

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Attribute
    /**
     * 彩券編號
     */
    private int lotteryId;
    /**
     * 該彩券屬於member
     */
    private Member member;
    /**
     * 購買彩券時間, 年月日時分秒
     */
    private Date dateTime = new Date();
    /**
     * 總關數1~8關
     */
    private int selections;
    /**
     * 賽事陣列, length==selections, 相同賽事不得串關
     */
    private Basketball game[];
    /**
     * 賽事項目, length==selections
     */
    private String item[];
    /**
     * 過關數陣列, length==selections
     */
    private boolean pass[];
    /**
     * 每組合金額, 單張彩券最低面額為100
     */
    private int stake;
    /**
     * 派彩後彩金
     */
    private int price = 0;

    /**
     * 彩券狀態, 0:尚未派彩 1:未中獎 2:已中獎 3:作廢
     */
    private Status status = Status.NOTPAYOUT;

    //Getter & Setter
    public int getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(int lotteryId) throws SportSimsException {
        if (lotteryId >= 0) {
            this.lotteryId = lotteryId;
        } else {
            throw new SportSimsException("彩券編號錯誤！");
        }
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) throws SportSimsException {
        if (member != null) {
            this.member = member;
        } else {
            throw new SportSimsException("彩券擁有者錯誤！");
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
            throw new SportSimsException("彩券購買時間錯誤！");
        }
    }

    public int getSelections() {
        return selections;
    }

    public void setSelections(int selections) throws SportSimsException {
        if (selections >= 1 && selections <= 8) {
            this.selections = selections;
        } else {
            throw new SportSimsException("彩券總關數為1~8關！");
        }
    }

    public Basketball[] getGame() {
        return game;
    }

    public void setGame(Basketball[] game) throws SportSimsException {
        if (checkGame(game)) {
            this.game = game;
        } else {
            throw new SportSimsException("彩券賽事錯誤，賽事數須與總關數相同，且相同賽事不得串關！");
        }
    }

    public String[] getItem() {
        return item;
    }

    public void setItem(String[] item) throws SportSimsException {
        if (checkItem(item)[0].equals("Error")) {
            throw new SportSimsException("彩券賽事項目錯誤，賽事項目數須與總關數相同！");
        } else {
            this.item = checkItem(item);
        }
    }

    public boolean[] getPass() {
        return pass;
    }

    public void setPass(boolean[] pass) throws SportSimsException {
        if (checkPass(pass)) {
            this.pass = pass;
        } else {
            throw new SportSimsException("至少選擇一種過關組合！");
        }
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) throws SportSimsException {
        if (stake > 0 && getCombination(pass) * stake >= 100) {
            this.stake = stake;
        } else {
            throw new SportSimsException("單張彩券最低面額為100元！");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws SportSimsException {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new SportSimsException("彩金金額錯誤！");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) throws SportSimsException {
        this.status = status;
    }

    //Business method
    public int getCombination(boolean pass[]) {
        int comb = 0;
        int len = pass.length;
        for (int i = 0; i < len; i++) {
            if (pass[i]) {
                comb += combCal(len, i + 1);
            }
        }
        return comb;
    }

    //C(m, n) = C(m - 1, n - 1) + C(m - 1, n)
    public static int combCal(int m, int n) {
        int result;
        if (n > m) {
            result = 0;
        } else if (n == 1) {
            result = m;
        } else {
            result = combCal(m - 1, n) + combCal(m - 1, n - 1);
        }
        return result;
    }

    public boolean checkGame(Basketball[] game) {
        //檢查陣列length==總關數
        if (game.length != selections) {
            return false;
        }
        //檢查NULL
        for (int i = 0; i < game.length; i++) {
            if (game[i] == null) {
                return false;
            }
        }
        //檢查重複賽事
        for (int i = 0; i < game.length; i++) {
            for (int j = i + 1; j < game.length; j++) {
                if (game[i].equals(game[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] checkItem(String[] item) {
        //檢查失敗時傳回此陣列
        String[] error = {"Error"};
        //檢查陣列length==總關數
        if (item.length != selections) {
            return error;
        }
        //檢查NULL, 空字串
        for (int i = 0; i < item.length; i++) {
            if (item[i] == null || (item[i] = item[i].trim()).length() <= 0) {
                return error;
            }
        }
        //回傳trim過的item[]
        return item;
    }

    public boolean checkPass(boolean[] pass) {
        //檢查陣列length==總關數
        if (pass.length != selections) {
            return false;
        }
        //檢查至少選擇一種過關組合
        for (int i = 0; i < pass.length; i++) {
            if (pass[i] || false) { //如果有True就離開迴圈
                break;
            }
            if (i == pass.length - 1) { //到最後一項還沒被前面break就回傳false
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lottery{" + "lotteryId=" + lotteryId + ", member=" + member
                + ", dateTime=" + dateTime + ", selections=" + selections + ", game=" + Arrays.toString(game)
                + ", item=" + Arrays.toString(item) + ", pass=" + Arrays.toString(pass)
                + ", stake=" + stake + ", price=" + price + ", status=" + status + '}';
    }

    public enum Status {
        NOTPAYOUT("尚未派彩"), LOSE("未中獎"), WIN("已中獎"), INVALID("作廢");
        private final String description;

        private Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
