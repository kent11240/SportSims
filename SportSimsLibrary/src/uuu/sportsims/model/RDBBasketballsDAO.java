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
import uuu.sportsims.domain.Basketball;
import uuu.sportsims.domain.SportSimsException;

public class RDBBasketballsDAO implements DAOInterface<Integer, Basketball> {

    private static final String TABLE = "basketballs";

    private static final String INSERT_SQL_AUTO_ID = "INSERT INTO " + TABLE
            + " (`dateTime`,awayName,homeName,pointSpread,total,away,home,`over`,`under`,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,"
            + "away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,"
            + "awayResult,homeResult,awayQ1Result,homeQ1Result,status)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE
            + " (`dateTime`,awayName,homeName,pointSpread,total,away,home,`over`,`under`,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,"
            + "away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,"
            + "awayResult,homeResult,awayQ1Result,homeQ1Result,status,gameId)"
            + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE_SQL = "UPDATE " + TABLE
            + " SET `dateTime`=?,awayName=?,homeName=?,pointSpread=?,total=?,away=?,home=?,`over`=?,`under`=?,awayPk=?,homePk=?,awayQ1=?,drawQ1=?,homeQ1=?,odd=?,even=?,"
            + "away1to5=?,away6to10=?,away11to15=?,away16to20=?,away21to25=?,away26=?,home1to5=?,home6to10=?,home11to15=?,home16to20=?,home21to25=?,home26=?,"
            + "awayResult=?,homeResult=?,awayQ1Result=?,homeQ1Result=?,status=?"
            + " WHERE gameId=?";

    private static final String SELECT_ALL_SQL = "SELECT gameId,`dateTime`,awayName,homeName,pointSpread,total,away,home,`over`,`under`,awayPk,homePk,awayQ1,drawQ1,homeQ1,odd,even,"
            + "away1to5,away6to10,away11to15,away16to20,away21to25,away26,home1to5,home6to10,home11to15,home16to20,home21to25,home26,"
            + "awayResult,homeResult,awayQ1Result,homeQ1Result,status"
            + " FROM " + TABLE;

    private static final String SELECT_ALL_SQL_BY_STATUS = SELECT_ALL_SQL + " WHERE status=?";

    private static final String SELECT_SQL = SELECT_ALL_SQL + " WHERE gameId=?";

    @Override
    public void insert(Basketball data) throws SportSimsException {
        if (data == null) {
            throw new IllegalArgumentException("新增賽事為null");
        }
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt
                = //2.建立指令
                connection.prepareStatement(data.getGameId() > 0 ? INSERT_SQL : INSERT_SQL_AUTO_ID,
                        data.getGameId() > 0 ? Statement.NO_GENERATED_KEYS : Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, data.getDateTimeString());
            pstmt.setString(2, data.getAwayName());
            pstmt.setString(3, data.getHomeName());
            pstmt.setDouble(4, data.getPointSpread());
            pstmt.setDouble(5, data.getTotal());
            pstmt.setDouble(6, data.getAway());
            pstmt.setDouble(7, data.getHome());
            pstmt.setDouble(8, data.getOver());
            pstmt.setDouble(9, data.getUnder());
            pstmt.setDouble(10, data.getAwayPk());
            pstmt.setDouble(11, data.getHomePk());
            pstmt.setDouble(12, data.getAwayQ1());
            pstmt.setDouble(13, data.getDrawQ1());
            pstmt.setDouble(14, data.getHomeQ1());
            pstmt.setDouble(15, data.getOdd());
            pstmt.setDouble(16, data.getEven());
            pstmt.setDouble(17, data.getAway1to5());
            pstmt.setDouble(18, data.getAway6to10());
            pstmt.setDouble(19, data.getAway11to15());
            pstmt.setDouble(20, data.getAway16to20());
            pstmt.setDouble(21, data.getAway21to25());
            pstmt.setDouble(22, data.getAway26());
            pstmt.setDouble(23, data.getHome1to5());
            pstmt.setDouble(24, data.getHome6to10());
            pstmt.setDouble(25, data.getHome11to15());
            pstmt.setDouble(26, data.getHome16to20());
            pstmt.setDouble(27, data.getHome21to25());
            pstmt.setDouble(28, data.getHome26());
            pstmt.setInt(29, data.getAwayResult());
            pstmt.setInt(30, data.getHomeResult());
            pstmt.setInt(31, data.getAwayQ1Result());
            pstmt.setInt(32, data.getHomeQ1Result());
            pstmt.setInt(33, data.getStatus().ordinal());
            if (data.getGameId() > 0) {
                pstmt.setInt(34, data.getGameId());
            }
            int rows = pstmt.executeUpdate();
            assert (rows == 1) : "新增賽事資料結構有誤:" + data;
            if (data.getGameId() <= 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys();) {
                    if (rs.next()) {
                        data.setGameId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "新增賽事失敗: " + data, ex);
            throw new SportSimsException("新增賽事失敗", ex);
        }
    }

    @Override
    public void update(Basketball data) throws SportSimsException {
        if (data == null) {
            throw new IllegalArgumentException("修改賽事不得為null!");
        }

        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);) {
            pstmt.setString(1, data.getDateTimeString());
            pstmt.setString(2, data.getAwayName());
            pstmt.setString(3, data.getHomeName());
            pstmt.setDouble(4, data.getPointSpread());
            pstmt.setDouble(5, data.getTotal());
            pstmt.setDouble(6, data.getAway());
            pstmt.setDouble(7, data.getHome());
            pstmt.setDouble(8, data.getOver());
            pstmt.setDouble(9, data.getUnder());
            pstmt.setDouble(10, data.getAwayPk());
            pstmt.setDouble(11, data.getHomePk());
            pstmt.setDouble(12, data.getAwayQ1());
            pstmt.setDouble(13, data.getDrawQ1());
            pstmt.setDouble(14, data.getHomeQ1());
            pstmt.setDouble(15, data.getOdd());
            pstmt.setDouble(16, data.getEven());
            pstmt.setDouble(17, data.getAway1to5());
            pstmt.setDouble(18, data.getAway6to10());
            pstmt.setDouble(19, data.getAway11to15());
            pstmt.setDouble(20, data.getAway16to20());
            pstmt.setDouble(21, data.getAway21to25());
            pstmt.setDouble(22, data.getAway26());
            pstmt.setDouble(23, data.getHome1to5());
            pstmt.setDouble(24, data.getHome6to10());
            pstmt.setDouble(25, data.getHome11to15());
            pstmt.setDouble(26, data.getHome16to20());
            pstmt.setDouble(27, data.getHome21to25());
            pstmt.setDouble(28, data.getHome26());
            pstmt.setInt(29, data.getAwayResult());
            pstmt.setInt(30, data.getHomeResult());
            pstmt.setInt(31, data.getAwayQ1Result());
            pstmt.setInt(32, data.getHomeQ1Result());
            pstmt.setInt(33, data.getStatus().ordinal());
            pstmt.setInt(34, data.getGameId());
            int rows = pstmt.executeUpdate();
            assert (rows == 1) : "修改賽事資料結構有誤:" + data;
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "修改賽事失敗: " + data, ex);
            throw new SportSimsException("修改賽事失敗!", ex);
        }
    }

    @Override
    public void delete(Basketball data) throws SportSimsException {
        throw new UnsupportedOperationException("不支援刪除賽事");
    }

    @Override
    public Basketball get(Integer key) throws SportSimsException {
        Basketball game = null;
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);) { //2. 建立指令

            pstmt.setInt(1, key);
            //3. 執行指令，取回結果
            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    game = new Basketball();
                    game.setGameId(rs.getInt("gameId"));
                    game.setDateTime(rs.getTimestamp("dateTime"));
                    game.setAwayName(rs.getString("awayName"));
                    game.setHomeName(rs.getString("homeName"));
                    game.setPointSpread(rs.getDouble("pointSpread"));
                    game.setTotal(rs.getDouble("total"));
                    game.setAway(rs.getDouble("away"));
                    game.setHome(rs.getDouble("home"));
                    game.setOver(rs.getDouble("over"));
                    game.setUnder(rs.getDouble("under"));
                    game.setAwayPk(rs.getDouble("awayPk"));
                    game.setHomePk(rs.getDouble("homePk"));
                    game.setAwayQ1(rs.getDouble("awayQ1"));
                    game.setDrawQ1(rs.getDouble("drawQ1"));
                    game.setHomeQ1(rs.getDouble("homeQ1"));
                    game.setOdd(rs.getDouble("odd"));
                    game.setEven(rs.getDouble("even"));
                    game.setAway1to5(rs.getDouble("away1to5"));
                    game.setAway6to10(rs.getDouble("away6to10"));
                    game.setAway11to15(rs.getDouble("away11to15"));
                    game.setAway16to20(rs.getDouble("away16to20"));
                    game.setAway21to25(rs.getDouble("away21to25"));
                    game.setAway26(rs.getDouble("away26"));
                    game.setHome1to5(rs.getDouble("home1to5"));
                    game.setHome6to10(rs.getDouble("home6to10"));
                    game.setHome11to15(rs.getDouble("home11to15"));
                    game.setHome16to20(rs.getDouble("home16to20"));
                    game.setHome21to25(rs.getDouble("home21to25"));
                    game.setHome26(rs.getDouble("home26"));
                    game.setAwayResult(rs.getInt("awayResult"));
                    game.setHomeResult(rs.getInt("homeResult"));
                    game.setAwayQ1Result(rs.getInt("awayQ1Result"));
                    game.setHomeQ1Result(rs.getInt("homeQ1Result"));
                    game.setStatus(Basketball.Status.values()[rs.getInt("status")]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢賽事失敗: key-" + key, ex);
            throw new SportSimsException("查詢賽事失敗!", ex);
        }
        return game;
    }

    @Override
    public List<Basketball> getAll() throws SportSimsException {
        List<Basketball> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SQL); //2. 建立指令
                ResultSet rs = pstmt.executeQuery();) {//3. 執行指令，取回結果
            while (rs.next()) {
                Basketball game = new Basketball();
                game.setGameId(rs.getInt("gameId"));
                game.setDateTime(rs.getTimestamp("dateTime"));
                game.setAwayName(rs.getString("awayName"));
                game.setHomeName(rs.getString("homeName"));
                game.setPointSpread(rs.getDouble("pointSpread"));
                game.setTotal(rs.getDouble("total"));
                game.setAway(rs.getDouble("away"));
                game.setHome(rs.getDouble("home"));
                game.setOver(rs.getDouble("over"));
                game.setUnder(rs.getDouble("under"));
                game.setAwayPk(rs.getDouble("awayPk"));
                game.setHomePk(rs.getDouble("homePk"));
                game.setAwayQ1(rs.getDouble("awayQ1"));
                game.setDrawQ1(rs.getDouble("drawQ1"));
                game.setHomeQ1(rs.getDouble("homeQ1"));
                game.setOdd(rs.getDouble("odd"));
                game.setEven(rs.getDouble("even"));
                game.setAway1to5(rs.getDouble("away1to5"));
                game.setAway6to10(rs.getDouble("away6to10"));
                game.setAway11to15(rs.getDouble("away11to15"));
                game.setAway16to20(rs.getDouble("away16to20"));
                game.setAway21to25(rs.getDouble("away21to25"));
                game.setAway26(rs.getDouble("away26"));
                game.setHome1to5(rs.getDouble("home1to5"));
                game.setHome6to10(rs.getDouble("home6to10"));
                game.setHome11to15(rs.getDouble("home11to15"));
                game.setHome16to20(rs.getDouble("home16to20"));
                game.setHome21to25(rs.getDouble("home21to25"));
                game.setHome26(rs.getDouble("home26"));
                game.setAwayResult(rs.getInt("awayResult"));
                game.setHomeResult(rs.getInt("homeResult"));
                game.setAwayQ1Result(rs.getInt("awayQ1Result"));
                game.setHomeQ1Result(rs.getInt("homeQ1Result"));
                game.setStatus(Basketball.Status.values()[rs.getInt("status")]);
                list.add(game);
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢全部賽事失敗!", ex);
            throw new SportSimsException("查詢全部賽事失敗!", ex);
        }
        return list;
    }

    public List<Basketball> getAllByStatus(Basketball.Status status) throws SportSimsException {
        List<Basketball> list = new ArrayList<>();
        try (Connection connection = RDBConnection.getConnection(); //1. 取得連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SQL_BY_STATUS);) { //2. 建立指令

            pstmt.setInt(1, status.ordinal());
            try (ResultSet rs = pstmt.executeQuery();) {//3. 執行指令，取回結果
                while (rs.next()) {
                    Basketball game = new Basketball();
                    game.setGameId(rs.getInt("gameId"));
                    game.setDateTime(rs.getTimestamp("dateTime"));
                    game.setAwayName(rs.getString("awayName"));
                    game.setHomeName(rs.getString("homeName"));
                    game.setPointSpread(rs.getDouble("pointSpread"));
                    game.setTotal(rs.getDouble("total"));
                    game.setAway(rs.getDouble("away"));
                    game.setHome(rs.getDouble("home"));
                    game.setOver(rs.getDouble("over"));
                    game.setUnder(rs.getDouble("under"));
                    game.setAwayPk(rs.getDouble("awayPk"));
                    game.setHomePk(rs.getDouble("homePk"));
                    game.setAwayQ1(rs.getDouble("awayQ1"));
                    game.setDrawQ1(rs.getDouble("drawQ1"));
                    game.setHomeQ1(rs.getDouble("homeQ1"));
                    game.setOdd(rs.getDouble("odd"));
                    game.setEven(rs.getDouble("even"));
                    game.setAway1to5(rs.getDouble("away1to5"));
                    game.setAway6to10(rs.getDouble("away6to10"));
                    game.setAway11to15(rs.getDouble("away11to15"));
                    game.setAway16to20(rs.getDouble("away16to20"));
                    game.setAway21to25(rs.getDouble("away21to25"));
                    game.setAway26(rs.getDouble("away26"));
                    game.setHome1to5(rs.getDouble("home1to5"));
                    game.setHome6to10(rs.getDouble("home6to10"));
                    game.setHome11to15(rs.getDouble("home11to15"));
                    game.setHome16to20(rs.getDouble("home16to20"));
                    game.setHome21to25(rs.getDouble("home21to25"));
                    game.setHome26(rs.getDouble("home26"));
                    game.setAwayResult(rs.getInt("awayResult"));
                    game.setHomeResult(rs.getInt("homeResult"));
                    game.setAwayQ1Result(rs.getInt("awayQ1Result"));
                    game.setHomeQ1Result(rs.getInt("homeQ1Result"));
                    game.setStatus(Basketball.Status.values()[rs.getInt("status")]);
                    list.add(game);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "查詢全部賽事失敗!", ex);
            throw new SportSimsException("查詢全部賽事失敗!", ex);
        }
        return list;
    }

}
