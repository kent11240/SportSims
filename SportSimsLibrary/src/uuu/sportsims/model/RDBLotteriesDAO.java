package uuu.sportsims.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Lottery;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class RDBLotteriesDAO implements DAOInterface<Integer, Lottery> {

    private static final String TABLE = "lotteries";

    private static final String INSERT_SQL_AUTO_ID = "INSERT INTO " + TABLE
            + " (memberId,`dateTime`,selections,game1Id,game1Item,game1Spread,game1Odds,game2Id,game2Item,game2Spread,game2Odds,"
            + "game3Id,game3Item,game3Spread,game3Odds,game4Id,game4Item,game4Spread,game4Odds,game5Id,game5Item,game5Spread,game5Odds,"
            + "game6Id,game6Item,game6Spread,game6Odds,game7Id,game7Item,game7Spread,game7Odds,game8Id,game8Item,game8Spread,game8Odds,"
            + "pass1,pass2,pass3,pass4,pass5,pass6,pass7,pass8,stake,price,status)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE
            + " SET price=?,status=? WHERE lotteryId=?";

    private static final String SELECT_ALL_SQL = "SELECT lotteryId,memberId,`dateTime`,selections,game1Id,game1Item,game1Spread,game1Odds,"
            + "game2Id,game2Item,game2Spread,game2Odds,game3Id,game3Item,game3Spread,game3Odds,game4Id,game4Item,game4Spread,game4Odds,"
            + "game5Id,game5Item,game5Spread,game5Odds,game6Id,game6Item,game6Spread,game6Odds,game7Id,game7Item,game7Spread,game7Odds,"
            + "game8Id,game8Item,game8Spread,game8Odds,pass1,pass2,pass3,pass4,pass5,pass6,pass7,pass8,stake,price,status"
            + " FROM " + TABLE;

    private static final String SELECT_ALL_BY_MEMBER_SQL = SELECT_ALL_SQL + " WHERE memberId=? ORDER BY `dateTime` DESC";
    private static final String SELECT_BY_KEY_SQL = SELECT_ALL_SQL + " WHERE lotteryId=? AND memberId=?";

    @Override
    public void insert(Lottery data) throws SportSimsException {
        if (data == null) {
            throw new IllegalArgumentException("新增彩券為null");
        }
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt
                = //2.建立指令
                connection.prepareStatement(INSERT_SQL_AUTO_ID, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, data.getMember().getId());
            pstmt.setTimestamp(2, new java.sql.Timestamp(data.getDateTime().getTime()));
            pstmt.setInt(3, data.getSelections());

            //依總關數決定放gameId...數量
            int gameIndex = 0;
            for (int j = 4; j <= data.getSelections() * 4; j += 4) {
                pstmt.setInt(j, data.getGame()[gameIndex].getGameId());
                pstmt.setString(j + 1, data.getItem()[gameIndex]);

                //讓分盤存pointSpread, 大小盤存total, 其餘null
                switch (data.getItem()[gameIndex]) {
                    case "away":
                    case "home":
                        pstmt.setDouble(j + 2, data.getGame()[gameIndex].getPointSpread());
                        break;
                    case "over":
                    case "under":
                        pstmt.setDouble(j + 2, data.getGame()[gameIndex].getTotal());
                        break;
                    default:
                        pstmt.setNull(j + 2, java.sql.Types.DOUBLE);
                        break;
                }
                pstmt.setDouble(j + 3, data.getGame()[gameIndex].getOddsByItem(data.getItem()[gameIndex]));

                gameIndex++;
            }

            //剩下關數存入null
            for (int i = 4 * (data.getSelections() + 1); i <= 8 * 4; i += 4) {
                pstmt.setNull(i, java.sql.Types.INTEGER);
                pstmt.setNull(i + 1, java.sql.Types.VARCHAR);
                pstmt.setNull(i + 2, java.sql.Types.DOUBLE);
                pstmt.setNull(i + 3, java.sql.Types.DOUBLE);
            }

            //依總關數決定放pass數量
            int passIndex = 0;
            for (int j = 36; j < 36 + data.getSelections(); j++) {
                pstmt.setBoolean(j, data.getPass()[passIndex]);
                passIndex++;
            }

            //剩下過關數存入null
            for (int i = 36 + data.getSelections(); i <= 43; i++) {
                pstmt.setNull(i, java.sql.Types.BOOLEAN);
            }

            pstmt.setInt(44, data.getStake());
            pstmt.setInt(45, data.getPrice());
            pstmt.setInt(46, data.getStatus().ordinal());

            int rows = pstmt.executeUpdate();
            assert (rows == 1) : "新增彩券資料結構有誤:" + data;
            try (ResultSet rs = pstmt.getGeneratedKeys();) {
                if (rs.next()) {
                    data.setLotteryId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "新增彩券失敗: " + data, ex);
            throw new SportSimsException("新增彩券失敗", ex);
        }
    }

    @Override
    public void update(Lottery data) throws SportSimsException {
        if (data == null) {
            throw new IllegalArgumentException("修改賽事不得為null!");
        }
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);) {
            pstmt.setInt(1, data.getPrice());
            pstmt.setInt(2, data.getStatus().ordinal());
            pstmt.setInt(3, data.getLotteryId());
            int rows = pstmt.executeUpdate();
            assert (rows == 1) : "修改彩券資料結構有誤:" + data;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "修改彩券失敗: " + data, ex);
            throw new SportSimsException("修改彩券失敗!", ex);
        }
    }

    @Override
    public void delete(Lottery data) throws SportSimsException {
        throw new UnsupportedOperationException("不支援刪除彩券");
    }

    @Override
    public Lottery get(Integer key) throws SportSimsException {
        throw new UnsupportedOperationException("不支援查詢彩券OnlyByKey");
    }

    @Override
    public List<Lottery> getAll() throws SportSimsException {
        throw new UnsupportedOperationException("不支援全部彩券");
    }

    //getAll只取回部分資料用於彩券列表
    public List<Lottery> getAllByMember(Member member) throws SportSimsException {
        List<Lottery> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_BY_MEMBER_SQL);) { //2. 建立指令
            pstmt.setString(1, member.getId());
            try (ResultSet rs = pstmt.executeQuery();) {//3. 執行指令，取回結果
                while (rs.next()) {
                    Lottery lottery = new Lottery();
                    lottery.setLotteryId(rs.getInt("lotteryId"));
                    lottery.setMember(member);
                    lottery.setDateTime(rs.getTimestamp("dateTime"));
                    lottery.setSelections(rs.getInt("selections"));

                    boolean pass[] = new boolean[lottery.getSelections()];
                    for (int i = 0; i < lottery.getSelections(); i++) {
                        pass[i] = rs.getBoolean("pass" + (i + 1));
                    }
                    lottery.setPass(pass);

                    lottery.setStake(rs.getInt("stake"));
                    lottery.setPrice(rs.getInt("price"));
                    lottery.setStatus(Lottery.Status.values()[rs.getInt("status")]);
                    list.add(lottery);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢彩券(by客戶)失敗!", ex);
            throw new SportSimsException("查詢彩券(by客戶)失敗!", ex);
        }
        return list;
    }

    public Lottery getByKeyAndMember(Integer key, Member member) throws SportSimsException {
        Lottery lottery = null;
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_KEY_SQL);) { //2. 建立指令
            pstmt.setInt(1, key);
            pstmt.setString(2, member.getId());
            //3. 執行指令，取回結果
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    lottery = new Lottery();
                    lottery.setLotteryId(rs.getInt("lotteryId"));
                    lottery.setMember(member);
                    lottery.setDateTime(rs.getTimestamp("dateTime"));
                    lottery.setSelections(rs.getInt("selections"));

                    String item[] = new String[lottery.getSelections()];
                    for (int i = 0; i < lottery.getSelections(); i++) {
                        item[i] = rs.getString("game" + (i + 1) + "Item");
                    }
                    lottery.setItem(item);

                    boolean pass[] = new boolean[lottery.getSelections()];
                    for (int i = 0; i < lottery.getSelections(); i++) {
                        pass[i] = rs.getBoolean("pass" + (i + 1));
                    }
                    lottery.setPass(pass);

                    lottery.setStake(rs.getInt("stake"));
                    lottery.setPrice(rs.getInt("price"));
                    lottery.setStatus(Lottery.Status.values()[rs.getInt("status")]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢彩券失敗: key-" + key, ex);
            throw new SportSimsException("查詢彩券失敗!", ex);
        }
        return lottery;
    }

    public double[][] getGameInfoByKeyAndMember(Integer key, Member member) throws SportSimsException {
        double gameInfo[][] = null;
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_KEY_SQL);) { //2. 建立指令
            pstmt.setInt(1, key);
            pstmt.setString(2, member.getId());
            //3. 執行指令，取回結果
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    gameInfo = new double[rs.getInt("selections")][3];
                    for (int i = 0; i < gameInfo.length; i++) {
                        gameInfo[i][0] = rs.getInt("game" + (i + 1) + "Id");
                        gameInfo[i][1] = rs.getDouble("game" + (i + 1) + "Spread");
                        gameInfo[i][2] = rs.getDouble("game" + (i + 1) + "Odds");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢彩券之賽事編號陣列失敗: key-" + key, ex);
            throw new SportSimsException("查詢彩券之賽事編號陣列失敗!", ex);
        }
        return gameInfo;
    }
}
