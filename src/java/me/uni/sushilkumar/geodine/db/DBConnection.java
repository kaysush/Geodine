/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sushil
 */
public class DBConnection {

    private String userName;
    private String userPass;
    private String dbHost;
    private String dbName;
    private Connection con;

    public DBConnection(String userName, String userPass, String dbHost, String dbName) {
        this.userName = userName;
        this.userPass = userPass;
        this.dbHost = dbHost;
        this.dbName = dbName;
        initConnection();

    }

    public final void initConnection() {

        try {
            String url = "jdbc:mysql://" + dbHost + "/" + dbName;
            String dbClass = "org.gjt.mm.mysql.Driver";
            Class.forName(dbClass);
            con = DriverManager.getConnection(url, userName, userPass);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void signup(String fname, String lname, String email, String pass) {
        try {
            boolean exists = alreadyExists(email);
            if (!exists) {
                userDetails(fname, lname, email);
                userCredentials(email, pass);

            } else {
                System.out.println("Email already exists");
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void userDetails(String fname, String lname, String email) throws SQLException {
        String query = "INSERT INTO userdetails(fname,lname,email) VALUES(?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, fname);
            stmt.setString(2, lname);
            stmt.setString(3, email);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void userCredentials(String email, String pass) throws SQLException {
        String query = "INSERT INTO usercredentials(email,pass) VALUES(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, encrypt(pass));
            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public boolean close() {
        try {
            if (con != null) {
                con.close();
            }
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alreadyExists(String email) throws SQLException {
        String query = "SELECT * from userdetails where email=?";
        PreparedStatement stmt = null;
        boolean exists = true;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                exists = false;
            } else {
                exists = true;
            }
        } catch (SQLException e) {
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return exists;
    }

    public String encrypt(String pass) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte[] passData = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passData.length; i++) {
            sb.append(Integer.toHexString(0xFF & passData[i]));
        }
        return sb.toString();
    }

    public boolean login(String email, String pass) throws SQLException {
        String query = "SELECT * from usercredentials where email=? and pass=?";
        PreparedStatement stmt = null;
        boolean correct = false;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, encrypt(pass));
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                correct = false;
            } else {
                correct = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return correct;
    }

    public void requestForgotPassword(String email, String resetcode) {
        try {
            String query = "INSERT INTO forgotpassword(email,resetcode) VALUES(?,?)";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, resetcode);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean hasRequestedPasswordReset(String email) {
        boolean already = true;
        String query = "SELECT * from forgotpassword where email=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                already = false;
            } else {
                already = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return already;
    }

    public String resetcodeToEmail(String resetcode) {
        String query = "Select * from forgotpassword where resetcode=?";
        String recoveryEmail = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, resetcode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                recoveryEmail = rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recoveryEmail;
    }
    public void requestPasswordUpdate(String email,String password) throws SQLException 
    {
        try {
            String query="Update usercredentials set pass=? where email=?";
            PreparedStatement stmt=null;
            stmt=con.prepareStatement(query);
            stmt.setString(1,encrypt(password));
            stmt.setString(2, email);
            stmt.executeUpdate();
            query="delete from forgotpassword where email=?";
            stmt=con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();
            con.commit();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void main(String[] args) {
        DBConnection obj = new DBConnection("0120sushil", "Sarvesh0@", "www.freesql.org:3306", "geodine");
        //obj.signup("Sushil","Kumar","0120sushil@gmail.com","pass");
        //obj.signup("Sushil","Kumar","kumar.sushil1993@outlook.com","pass");


        String email = obj.resetcodeToEmail("ee899bd991275d875e7893f59d117dd87dc829764217ae971e7efaa64bde8");
        System.out.println("Recovery Email : " + email);



        obj.close();

    }
}
