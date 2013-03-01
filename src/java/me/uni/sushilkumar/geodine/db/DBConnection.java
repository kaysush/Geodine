/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.db;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import me.uni.sushilkumar.geodine.util.CuisineObject;

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
    private DataSource ds;

    public DBConnection() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sushil");

            con = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void requestPasswordUpdate(String email, String password) throws SQLException {
        try {
            String query = "Update usercredentials set pass=? where email=?";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(query);
            stmt.setString(1, encrypt(password));
            stmt.setString(2, email);
            stmt.executeUpdate();
            query = "delete from forgotpassword where email=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            stmt.executeUpdate();
            con.commit();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getFeed(int rand) throws SQLException {
        String feed = "";
        PreparedStatement stmt = null;
        String query = "SELECT feed_content from datafeeds where id=?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, rand);
        ResultSet rs = stmt.executeQuery();
        boolean first = rs.first();
        if (first) {
            feed = rs.getString("feed_content");
        } else {
            feed = "Unable to fetch facts";
        }
        return feed;

    }

    public ArrayList<String> getCuisines(String country) throws SQLException {
        ArrayList<String> cuisines = new ArrayList<String>();
        String query = "Select * from cuisine_list where country=? order by name";
        PreparedStatement stmt = null;
        stmt = con.prepareStatement(query);
        stmt.setString(1, country.trim().toLowerCase());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            cuisines.add(rs.getString("name") + "+" + rs.getString("image_url"));
        }
        return cuisines;
    }

    public ArrayList<String> getCountries(String continent) throws SQLException {
        ArrayList<String> countries = new ArrayList<String>();
        String query = "Select * from countries where continent_code=(select code from continents where name=?)";
        PreparedStatement stmt = null;
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            countries.add(rs.getString("name"));
        }
        return countries;
    }

    public ArrayList<String> getCuisineList() throws SQLException {
        ArrayList<String> cuisineList = new ArrayList<String>();
        String query = "SELECT * from cuisine_list";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            cuisineList.add(rs.getString("name"));
        }
        return cuisineList;
    }

    public boolean alreadyVisited(String cuisineId, String email) {
        String query = "SELECT * from recent_cuisines where cuisine_id=? and email=?";
        boolean alreadyRecent = false;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, cuisineId);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                alreadyRecent = false;
            } else {
                alreadyRecent = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            alreadyRecent = false;
        }
        return alreadyRecent;
    }

    public void addRecentCuisine(String cuisineId, String email, String time) {
        if (!alreadyVisited(cuisineId, email)) {
            String query = "INSERT INTO recent_cuisines (cuisine_id,email,time) VALUES(?,?,?)";
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(query);
                stmt.setString(1, cuisineId);
                stmt.setString(2, email);
                stmt.setString(3, time);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String query = "UPDATE recent_cuisines set time=? where cuisine_id=? and email=?";
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(query);
                stmt.setString(1, time);
                stmt.setString(2, cuisineId);
                stmt.setString(3, email);
                stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<CuisineObject> populateCuisineList(String country) {
        ArrayList<CuisineObject> cuisineList = new ArrayList<CuisineObject>();
        ArrayList<String> directions = new ArrayList<String>();
        ArrayList<String> ingredients = new ArrayList<String>();
        String query = "SELECT * from cuisine_list where country=? order by name";
        PreparedStatement stmt = null;
        {
            ObjectInputStream o = null;
            try {
                stmt = con.prepareStatement(query);
                stmt.setString(1, country);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String cuisineId = rs.getString("cuisine_id");
                    String cntry = rs.getString("country");
                    String ytid = rs.getString("ytid");
                    String name = rs.getString("name");
                    byte[] steps = rs.getBytes("steps");
                    byte[] ingr=rs.getBytes("ingredients");
                String imageUrl=rs.getString("image_url");
                directions=(ArrayList<String>) deserialize(steps);
                ingredients=(ArrayList<String>) deserialize(ingr);
                    cuisineList.add(new CuisineObject(id, cntry, cuisineId, ytid, directions, name,ingredients,imageUrl));
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (o != null) {
                        o.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cuisineList;
    }

    public ArrayList<String> getRecentCuisine(String email) {
        ArrayList<String> cuisines = new ArrayList<String>();
        try {

            String query = "Select distinct(name) from cuisine_list where cuisine_id in (select cuisine_id from recent_cuisines where email=?) order by name";
            PreparedStatement stmt = null;
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                cuisines.add(rs.getString("name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cuisines;
    }

    public CuisineObject getCuisineData(String cuisine) {
        CuisineObject obj = null;
        ArrayList<String> directions = new ArrayList<String>();
         ArrayList<String> ingredients=new ArrayList<String>();
        String query = "SELECT * from cuisine_list where name=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, cuisine);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String cuisineId = rs.getString("cuisine_id");
                String cntry = rs.getString("country");
                String ytid = rs.getString("ytid");
                String name = rs.getString("name");
                byte[] steps = rs.getBytes("steps");
                byte[] ingr=rs.getBytes("ingredients");
                String imageUrl=rs.getString("image_url");
                directions=(ArrayList<String>) deserialize(steps);
                ingredients=(ArrayList<String>) deserialize(ingr);
            obj = new CuisineObject(id, cntry, cuisineId, ytid, directions, name,ingredients,imageUrl);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public Object deserialize(byte[] array) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream b = new ByteArrayInputStream(array);
            ObjectInputStream o = new ObjectInputStream(b);
            return o.readObject();
    }

    public boolean isFavourite(String cuisineId, String email) {
        boolean isFav = false;
        String query = "SELECT * from favourite_cuisines where cuisine_id=? and email=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, cuisineId);
            stmt.setString(2, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.isBeforeFirst()) {
                isFav = false;
            } else {
                isFav = true;
            }


        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isFav;
    }

    public String getCuisineId(int id) {
        String query = "SELECT cuisine_id from cuisine_list where id=?", name = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            name = rs.getString("cuisine_id");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public void addFavourite(String cuisineId, String email) {
        String query = "INSERT INTO favourite_cuisines (cuisine_id,email) VALUES(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, cuisineId);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getFavourites(String email) {
        ArrayList<String> favourite = new ArrayList<String>();
        String query = "SELECT distinct(name) from cuisine_list where cuisine_id in (SELECT cuisine_id from favourite_cuisines where email=?) order by name";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                favourite.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favourite;
    }

    public void post(String title, String body, String date, String email) {
        String query = "INSERT INTO tb_posts (post_title,post_content,post_date,post_author) VALUES(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, body);
            stmt.setString(3, date);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<CuisineObject> getCookBook(String email) {
        ArrayList<String> fav = getFavourites(email);
        ArrayList<CuisineObject> cookbook = new ArrayList<CuisineObject>();
        for (int i = 0; i < fav.size(); i++) {
            cookbook.add(getCuisineData(fav.get(i)));
        }
        return cookbook;
    }
    
    public ArrayList<String> getFeeds()
    {
        ArrayList<String> favourite = new ArrayList<String>();
        String query = "SELECT * from tb_posts order by post_id desc limit 3";
        PreparedStatement stmt=null;
        
        try {
            stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                favourite.add(rs.getString("post_title"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favourite;
        
    }

    public static void main(String[] args) {
        //DBConnection obj = new DBConnection("0120sushil", "Sarvesh0@", "www.freesql.org:3306", "geodine");
        DBConnection obj = new DBConnection("root", "", "localhost:3306", "geodine");
        //DBConnection obj=new DBConnection();
        //obj.signup("Sushil","Kumar","0120sushil@gmail.com","pass");
        //obj.signup("Sushil","Kumar","kumar.sushil1993@outlook.com","pass");
        obj.getRecentCuisine("sushil@gmail.com");

        //String email = obj.resetcodeToEmail("ee899bd991275d875e7893f59d117dd87dc829764217ae971e7efaa64bde8");
        //System.out.println("Recovery Email : " + email);

        obj.close();

    }
}
