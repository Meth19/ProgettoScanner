package net.javaguides.servlet.tutorial.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/controllo_presenze_dipendenti","root","");
        Statement stmt = connection.createStatement();
         String sql;
         sql = "SELECT * FROM dipendente";
         ResultSet rs = stmt.executeQuery(sql);
         String text = "Ciao Mondo";
         while (rs.next()){
            String id = rs.getString("id");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            text += id +" "+ nome +" "+ cognome;
        }
        resp.setContentType("text/plain");  
        resp.setCharacterEncoding("UTF-8"); 
        resp.getWriter().write(text);
        rs.close();
        stmt.close();
        connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        /*String id = req.getParameter("id");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>");
        printWriter.print("<body>");
        printWriter.print("<p> pid :: " + id + "</p>");
        printWriter.print("</body>");
        printWriter.print("</html>");
        printWriter.close();*/
    }
}
