/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.uni.sushilkumar.geodine.db.DBConnection;

/**
 *
 * @author sushil
 */
public class LoginAuthentication extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DBConnection con = null;
        try {

            /* The DB LOGIN logic  */
            String dbHost = getServletContext().getInitParameter("dbHost");
            String dbUser = getServletContext().getInitParameter("dbUser");
            String dbPassword = getServletContext().getInitParameter("dbPassword");
            String dbName = getServletContext().getInitParameter("dbName");
            String email = request.getParameter("user-name");
            con = new DBConnection(dbUser, dbPassword, dbHost, dbName);

            //boolean requestedReset = con.hasRequestedPasswordReset(email);
            boolean requestedReset=false;
            if (requestedReset) {
                //response.sendRedirect("/geodine/login.jsp?request_reset=true");
                out.println("Error!!! Password Recovery is Requested");

            } else {
                boolean correctCredentials = con.login(email, request.getParameter("user-pass"));
                if (correctCredentials) {

                    session.setAttribute("userName", email);

                    //response.sendRedirect("/geodine/app.jsp");
                    out.println("loggedin");
                } else {
                    //response.sendRedirect("/geodine/login.jsp?login_attempt=true");
                    out.println("Error!!! Wrong login credentials");

                }
            }

        } finally {

            con.close();
        }
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAuthentication.class.getName()).log(Level.SEVERE, null, ex);
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
