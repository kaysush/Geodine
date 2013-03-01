/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.uni.sushilkumar.geodine.db.DBConnection;

/**
 *
 * @author sushil
 */
public class RecoverEngine extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DBConnection con=null;
        try {
            /* The DB LOGIN logic  */
            String dbHost=getServletContext().getInitParameter("dbHost");
            String dbUser=getServletContext().getInitParameter("dbUser");
            String dbPassword=getServletContext().getInitParameter("dbPassword");
            String dbName=getServletContext().getInitParameter("dbName");
            con=new DBConnection(dbUser,dbPassword,dbHost,dbName);
            
            String resetcode=request.getRequestURI();
            String email="email";
            int index=resetcode.lastIndexOf("/");
            resetcode=resetcode.substring(index+1);
            email=con.resetcodeToEmail(resetcode);
            //out.println(resetcode);
            //out.println("Recovery Email : "+email);
            request.setAttribute("email",email);
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/RecoveryDispatcher.jsp");
            rd.forward(request, response);
             
        } finally {            
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
