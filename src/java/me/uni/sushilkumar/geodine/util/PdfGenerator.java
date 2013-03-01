/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import me.uni.sushilkumar.geodine.db.DBConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author shiva
 */
public class PdfGenerator extends HttpServlet {

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
        
        DBConnection con = null;
        HttpSession session = request.getSession(true);
        String email = (String) session.getAttribute("userName");
        try {
            con = new DBConnection();
            ArrayList<CuisineObject> cookbook = con.getCookBook(email);
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            Paragraph paragraph = new Paragraph();
            paragraph.add(new Chunk("Geodine CookBook", new Font(Font.getFamily("Jokerman"), 25, Font.BOLD)));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(new Paragraph(paragraph));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            Image image1 = Image.getInstance(new URL("http://localhost:8084/geodine/img/glogo.png"));
            image1.setAlignment(Element.ALIGN_CENTER);
            document.add(image1);

            for (int i = 0; i < cookbook.size(); i++) {
                document.add(Chunk.NEXTPAGE);

                CuisineObject obj = cookbook.get(i);
                Image image = Image.getInstance(new URL(obj.getImageUrl()));
                image.setAlignment(Element.ALIGN_CENTER);
                
                ArrayList<String> directions = obj.getDirections();
                ArrayList<String> ingredients=obj.getIngredients();
                String name = obj.getName();
                
                
       
                
                paragraph = new Paragraph();
                paragraph.add(new Chunk(name, new Font(Font.getFamily("Jokerman"), 20, Font.ITALIC)));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(new Paragraph(paragraph));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(image);
                 document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                
                paragraph = new Paragraph();
                paragraph.add(new Chunk("Ingredients", new Font(Font.getFamily("Jokerman"), 20, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_LEFT);
                document.add(new Paragraph(paragraph));
                List list = new List(false, 20);
                for (int j = 0; j < ingredients.size(); j++) {
                    list.add(new ListItem(ingredients.get(j)));

                }
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(list);
                
                
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                
                
                paragraph = new Paragraph();
                paragraph.add(new Chunk("Directions", new Font(Font.getFamily("Jokerman"), 20, Font.BOLD)));
                paragraph.setAlignment(Element.ALIGN_LEFT);
                document.add(new Paragraph(paragraph));
                
                
                list = new List(false, 20);
                for (int j = 0; j < directions.size(); j++) {
                    list.add(new ListItem(directions.get(j)));

                }
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(list);
            }

            document.close();
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException ex) {
            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
     public String getImageUrl(String id) throws IOException
    {
        String url="";
        try {
            JSONParser parser=new JSONParser();
                URL u = new URL("http://api.yummly.com/v1/api/recipe/" + id + "?_app_id=bad04ef2&_app_key=f2df74726bb514d9390abf1e6c9ad4f0");
                Object json = parser.parse(new BufferedReader(new InputStreamReader(u.openStream())));
                JSONObject obj = (JSONObject) json;
                JSONArray images=(JSONArray) obj.get("images");
                Iterator<JSONObject> it=images.iterator();
                JSONObject temp=it.next();
                url=(String) temp.get("hostedLargeUrl");
        } catch (ParseException ex) {
            Logger.getLogger(GetCountry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GetCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return url;
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
