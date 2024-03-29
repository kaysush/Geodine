/*
 * Copyright Sushil Kumar <0120sushil@gmail.com>.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * under the License.
 */
package me.uni.sushilkumar.geodine.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.uni.sushilkumar.geodine.db.DBConnection;

/**
 *
 * @author Sushil Kumar <0120sushil@gmail.com>
 */
public class FeedHandler extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        PrintWriter out = null;
        DBConnection con = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            try {
                /* The DB LOGIN logic  */
                String dbHost = getServletContext().getInitParameter("dbHost");
                String dbUser = getServletContext().getInitParameter("dbUser");
                String dbPassword = getServletContext().getInitParameter("dbPassword");
                String dbName = getServletContext().getInitParameter("dbName");
                String email = request.getParameter("user-name");
                //con = new DBConnection(dbUser, dbPassword, dbHost, dbName);
                con = new DBConnection();
                Random rand = new Random();
                String feed = con.getFeed(rand.nextInt(44));
                out.println(feed);


            } catch (SQLException ex) {
                Logger.getLogger(FeedHandler.class.getName()).log(Level.SEVERE, null, ex);
                out.println("Unable to fetch facts");
            }
        } catch (IOException ex) {
            Logger.getLogger(FeedHandler.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Unable to fetch facts");
        } finally {
            out.close();
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
