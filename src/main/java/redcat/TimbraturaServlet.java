package net.javaguides.servlet.tutorial.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.format.DateTimeFormatter.*;
import static java.time.format.FormatStyle.*;
import org.json.JSONObject;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.jdbc.Driver;

@WebServlet("/timbratura")
public class TimbraturaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{


	    Class.forName("com.mysql.cj.jdbc.Driver");
	    }
	    catch(ClassNotFoundException e){

	    e.printStackTrace();
    
	    }

        JSONObject jsonResp = new JSONObject();
        try{
        String id = req.getParameter("id");
        String action = req.getParameter("action");
        String oggi = LocalDate.now().toString();
        String orario = LocalTime.now().format(ofLocalizedTime(MEDIUM));
        resp.setContentType("text/plain");  
        resp.setCharacterEncoding("UTF-8");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controllo_presenze_dipendenti","root","");
        Statement stmt = connection.createStatement();
        String checkDuplicateQuery = "SELECT * FROM presenza WHERE id_dipendente = '"+id+"' AND data = '"+oggi+"'";
        ResultSet rs = stmt.executeQuery(checkDuplicateQuery);

        
        if (rs.next()){ //se esiste già una riga
         
         jsonResp.put("message","LA TUA PRESENZA D'INGRESSO E' GIA STATA REGISTRATA " + rs.getString("ingresso"));
         jsonResp.put("result","feilure");
         }else{
         
         String sql = "INSERT INTO presenza (data, id_dipendente, ingresso) VALUES ('"+oggi+"', '"+id+"', '"+orario+"')";
         int numrows = stmt.executeUpdate(sql);
         
         if (numrows == 1){ 
         jsonResp.put("message","PRESENZA REGISTRATA CORRETTAMENTE ALLE ORE: " + orario); 
         jsonResp.put("result","success");

         }else{
         jsonResp.put("message","LA PRESENZA NON È STATA INSERITA");
         jsonResp.put("result","feilure");
 
         }
         
        
        
        }
        resp.getWriter().write(jsonResp.toString());
        rs.close();
        stmt.close();
        connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            try{
            jsonResp.put("message"," NON E' STATA POSSIBILE REGISTRARE LA TUA TIMBRATURA ");
            jsonResp.put("result","feilure");
            }catch(JSONException ex){
            ex.printStackTrace();
        }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
     private void gestisciEntrata(){

    }
     private void gestisciUscita(){
        
    }
}
