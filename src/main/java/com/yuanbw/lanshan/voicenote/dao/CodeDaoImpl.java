package com.yuanbw.lanshan.voicenote.dao;

import com.yuanbw.lanshan.voicenote.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeDaoImpl implements CodeDao {
    @Override
    public int findMaxCodeID() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        con = JDBCUtils.getConnection();
        int result = 0;
        try {
            pstm = con.prepareStatement("SELECT MAX(voiceid) AS voiceid FROM voice");
            rs = pstm.executeQuery();
            rs.next();
            result = rs.getInt("voiceid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertCodeInfo(int codeID) {
        Connection con = null;
        PreparedStatement pstm = null;
        con = JDBCUtils.getConnection();
        int result = 0;
        try {
            pstm = con.prepareStatement("INSERT INTO voice VALUES (?,null,null,null)");
            pstm.setInt(1, codeID);
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
