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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import me.uni.sushilkumar.geodine.db.DBConnection;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Sushil Kumar <0120sushil@gmail.com>
 */
public class YummlyLookup extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        DBConnection con = null;
        try {

            String cuisine = request.getParameter("cuisine");
            con = new DBConnection();
            CuisineObject obj = con.getCuisineData(cuisine);
            int id = obj.getId();
            String cuisineId = obj.getCuisineId();
            String country = obj.getCountry();
            String ytid = obj.getYtid();
            
            ArrayList<String> directions = obj.getDirections();
            ArrayList<String> ingredients=obj.getIngredients();
            HttpSession session = request.getSession(true);
            String email = (String) session.getAttribute("userName");
            boolean isFav=con.isFavourite(cuisineId, email);
            String fav="";
            if(isFav)
                fav="true";
            else
                fav="false";
            DocumentBuilderFactory docFactory = null;
            DocumentBuilder docBuilder = null;
            Document xmlDoc = null;
            Element root = null;
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            xmlDoc = docBuilder.newDocument();
            root = xmlDoc.createElement("item");
            xmlDoc.appendChild(root);

            Element eid = xmlDoc.createElement("id");
            eid.appendChild(xmlDoc.createTextNode(Integer.toString(id)));
            root.appendChild(eid);
            
            Element eisfav = xmlDoc.createElement("favourite");
            eisfav.appendChild(xmlDoc.createTextNode(fav));
            root.appendChild(eisfav);

            Element ecuisineId = xmlDoc.createElement("cuisine-id");
            ecuisineId.appendChild(xmlDoc.createTextNode(cuisineId));
            root.appendChild(ecuisineId);

            Element ecountry = xmlDoc.createElement("country");
            ecountry.appendChild(xmlDoc.createTextNode(country));
            root.appendChild(ecountry);

            Element eytid = xmlDoc.createElement("ytid");
            eytid.appendChild(xmlDoc.createTextNode(ytid));
            root.appendChild(eytid);

            Element edirections = xmlDoc.createElement("steps");
            for (int i = 0; i < directions.size(); i++) {
                Element temp = xmlDoc.createElement("step");
                temp.appendChild(xmlDoc.createTextNode(directions.get(i)));
                edirections.appendChild(temp);
            }
            root.appendChild(edirections);
            
            Element eingredients = xmlDoc.createElement("ingredients");
            for (int i = 0; i < ingredients.size(); i++) {
                Element temp = xmlDoc.createElement("ingredient");
                temp.appendChild(xmlDoc.createTextNode(ingredients.get(i)));
                eingredients.appendChild(temp);
            }
            root.appendChild(eingredients);
            
            StringWriter sw = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
            out.println(sw.toString());
            if (email != null) {
                con.addRecentCuisine(cuisineId, email, new Date().toString());
            }


        } catch (TransformerException ex) {
            Logger.getLogger(YummlyLookup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(YummlyLookup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
            if (con != null) {
                con.close();
            }
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
            Logger.getLogger(YummlyLookup.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(YummlyLookup.class.getName()).log(Level.SEVERE, null, ex);
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
