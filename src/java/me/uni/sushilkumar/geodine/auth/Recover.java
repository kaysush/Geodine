/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.uni.sushilkumar.geodine.db.DBConnection;
import me.uni.sushilkumar.geodine.util.Mailer;

/**
 *
 * @author sushil
 */
public class Recover extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DBConnection con = null;
        try {
            /* The DB LOGIN logic  */
            String dbHost = getServletContext().getInitParameter("dbHost");
            String dbUser = getServletContext().getInitParameter("dbUser");
            String dbPassword = getServletContext().getInitParameter("dbPassword");
            String dbName = getServletContext().getInitParameter("dbName");
            con = new DBConnection(dbUser, dbPassword, dbHost, dbName);

            String email = request.getParameter("email");
            String type = request.getParameter("type");
            String password = request.getParameter("password");
            if (type.equals("recovery")) {
                if (con.alreadyExists(email)) {
                    if (con.hasRequestedPasswordReset(email)) {
                        out.println("Already requested");
                    } else {
                        CaptchaGenerator captchaGen = new CaptchaGenerator();
                        String resetcode = captchaGen.getCode();
                        resetcode = encrypt(resetcode);

                        Mailer mailer = new Mailer("info.geodine@gmail.com", "Sarvesh0@");
                        String body = "Goto http://projects-sushilkumar.rhcloud.com/geodine/recover/" + resetcode;
                        System.out.println("http://projects-sushilkumar.rhcloud.com/geodine/recover/" + resetcode);
                        boolean send = mailer.send(email, "Geodine Password Reset Request", body);
                        if (!send) {
                            out.println("Unable to process the request");
                        } else {
                            con.requestForgotPassword(email, resetcode);
                            out.println("Instructions for Pasword recovery has been mailed to you.");
                        }
                    }
                } else {
                    out.println("Email not found");
                }
            } else {
                try {
                    con.requestPasswordUpdate(email, password);
                    out.println("Reset Password Successful");
                } catch (SQLException ex) {
                    Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
                    out.println("Reset Password Failed!!!!");
                }

            }
        } finally {
            out.close();
            con.close();
        }

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error at geodine :(</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Use <strong>POST</strong> instead of <strong>GET</strong>.");
            out.println("</body>");
            out.println("</html>");


        } finally {
            out.close();
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
