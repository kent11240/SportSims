package uuu.sportsims.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.Member;
import uuu.sportsims.domain.SportSimsException;

public class RDBMembersDAO implements DAOInterface<String, Member> {

    private static final String TABLE = "members";

    private static final String INSERT_SQL = "INSERT INTO " + TABLE
            + " (id,password,nickname,email,birthday,money,status)"
            + " VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE " + TABLE
            + " SET password=?,nickname=?,email=?,birthday=?,money=?"
            + " WHERE id=?";
    private static final String INVALID_SQL = "UPDATE " + TABLE + " SET status=0 WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT id,password,nickname,email,birthday,money,status"
            + " FROM " + TABLE;
    private static final String SQL = SELECT_ALL_SQL + " WHERE id=?";

    @Override
    public void insert(Member data) throws SportSimsException {
        if (data == null) {
            throw new SportSimsException("無法新增會員(null)");
        }
        try (
                Connection connection = RDBConnection.getConnection(); //建立連線
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL); //準備指令
                ) {
            //傳入參數
            pstmt.setString(1, data.getId());
            pstmt.setString(2, data.getPassword());
            pstmt.setString(3, data.getNickname());
            pstmt.setString(4, data.getEmail());
            pstmt.setDate(5, data.getBirthday() != null ? new java.sql.Date(data.getBirthday().getTime()) : null);
            pstmt.setInt(6, data.getMoney());
            pstmt.setInt(7, data.getStatus());
            //執行SQL指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "新增會員失敗: " + data, ex);
            throw new SportSimsException("新增會員失敗: " + data, ex);
        }
    }

    @Override
    public void update(Member data) throws SportSimsException {
        if (data == null) {
            throw new SportSimsException("無法修改會員(null)");
        }
        try (
                Connection connection = RDBConnection.getConnection(); //建立連線)
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL); //準備指令
                ) {
            //傳入參數  
            pstmt.setString(1, data.getPassword());
            pstmt.setString(2, data.getNickname());
            pstmt.setString(3, data.getEmail());
            pstmt.setDate(4, data.getBirthday() != null ? new java.sql.Date(data.getBirthday().getTime()) : null);
            pstmt.setInt(5, data.getMoney());
            pstmt.setString(6, data.getId());
            //執行SQL指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "修改會員失敗: " + data, ex);
            throw new SportSimsException("修改會員失敗: " + data, ex);
        }
    }

    @Override
    public void delete(Member data) throws SportSimsException {
        if (data == null) {
            throw new SportSimsException("無法停用客戶(null)");
        }
        try (
                Connection connection = RDBConnection.getConnection(); //建立連線)
                PreparedStatement pstmt = connection.prepareStatement(INVALID_SQL); //準備指令
                ) {
            //傳入參數  
            pstmt.setString(1, data.getId());
            //執行SQL指令
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "停用客戶失敗: " + data, ex);
            throw new SportSimsException("停用客戶失敗: " + data, ex);
        }
    }

    @Override
    public Member get(String key) throws SportSimsException {
        Member member = new Member();
        try (
                Connection connection = RDBConnection.getConnection(); //2.建立連線
                PreparedStatement pstmt = connection.prepareStatement(SQL); //3.建立指令物件
                ) {
            pstmt.setString(1, key); //3.2 傳入?參數的值
            try (ResultSet rs = pstmt.executeQuery();) { //4.執行指令
                //5.處理Resultset
                while (rs.next()) {
                    try {
                        member.setId(rs.getString("id"));
                        member.setPassword(rs.getString("password"));
                        member.setNickname(rs.getString("nickname"));
                        member.setEmail(rs.getString("email"));
                        member.setBirthday(rs.getDate("birthday"));
                        member.setMoney(rs.getInt("money"));
                        member.setStatus(rs.getInt("status"));
                    } catch (SportSimsException ex) {
                        Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "查詢會員建立指派會員物件失敗", ex);
                        throw new SportSimsException("查詢會員建立指派客戶物件失敗", ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "查詢會員失敗", ex);
            throw new SportSimsException("查詢會員失敗", ex);
        }
        return member;
    }

    @Override
    public List<Member> getAll() throws SportSimsException {
        List<Member> members = new ArrayList<>();
        //JDBC 查詢customers的所有資料
        try (
                Connection connection = RDBConnection.getConnection(); //2.建立連線
                PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SQL); //3.建立指令物件
                ) {
            try (ResultSet rs = pstmt.executeQuery();) { //4.執行指令
                //5.處理Resultset
                while (rs.next()) {
                    try {
                        Member member = new Member();
                        member.setId(rs.getString("id"));
                        member.setPassword(rs.getString("password"));
                        member.setNickname(rs.getString("nickname"));
                        member.setEmail(rs.getString("email"));
                        member.setBirthday(rs.getDate("birthday"));
                        member.setMoney(rs.getInt("money"));
                        member.setStatus(rs.getInt("status"));
                        members.add(member);//加入集合
                    } catch (SportSimsException ex) {
                        Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "查詢會員建立指派會員物件失敗", ex);
                        throw new SportSimsException("查詢會員建立指派會員物件失敗", ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDBMembersDAO.class.getName()).log(Level.SEVERE, "查詢會員失敗", ex);
            throw new SportSimsException("查詢會員失敗", ex);
        }
        return members;
    }
}
