/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sushil
 */
@WebServlet(name = "GetCountry", urlPatterns = {"/GetCountry"})
public class GetCountry extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String country="";
        boolean found=false;
        try {
            JSONParser parser=new JSONParser();
try
{
URL u=new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="+request.getParameter("latlng")+"&sensor=true");
HttpURLConnection conn=(HttpURLConnection)u.openConnection();
BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
JSONObject obj=(JSONObject)parser.parse(br);
String status=(String)obj.get("status");
if(status.equals("OK"))
{
JSONArray results=(JSONArray)obj.get("results");
Iterator<JSONObject> resultIt=results.iterator();
while(resultIt.hasNext())
{
JSONObject temp=resultIt.next();
JSONArray address=(JSONArray)temp.get("address_components");
Iterator<JSONObject> addressIt=address.iterator();
while(addressIt.hasNext())
{
JSONObject temptype=addressIt.next();
JSONArray type=(JSONArray)temptype.get("types");
Iterator<String> typeIt=type.iterator();
while(typeIt.hasNext())
{
String tempcountry=typeIt.next();
if(tempcountry.equals("country"))
{
country=(String)temptype.get("long_name");
found=true;
break;
}
}
if(found)
break;
}
if(found)
break;
}
out.println(country);
}
else
out.println("No Country Selected");
}

catch(IOException e){
e.printStackTrace();
}
catch(ParseException e){
e.printStackTrace();
}
        
           
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(GetCountry.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(GetCountry.class.getName()).log(Level.SEVERE, null, ex);
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