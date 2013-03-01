/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.uni.sushilkumar.geodine.db.DBConnection;

/**
 *
 * @author shiva
 */
public class RecentCuisines extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);
            String email = (String) session.getAttribute("userName");
            if (email != null) {
                DBConnection con = new DBConnection();
                ArrayList<String> recent = con.getRecentCuisine(email);
                out.println("<h6>Recent Cuisines</h6><br/>");
                out.println("<ul>");
                for (int i = 0; i < recent.size(); i++) {
                    String cuisine = recent.get(i);
                    String href = cuisine.replaceAll("\\s+", "-");
                    out.println("<li><a href=/geodine/cuisine/" + href + ">" + cuisine + "</a></li>");
                }
                out.println("</ul>");
            }
            else
            {
                out.println("<span>Not Logged In</span>");
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
