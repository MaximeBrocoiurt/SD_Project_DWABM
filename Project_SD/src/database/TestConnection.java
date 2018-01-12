package database;
import java.sql.*;

public class TestConnection {



 //source : https://openclassrooms.com/forum/sujet/connexion-java-mysql-65111
 
public static void main(String[] args) {
    try{
         
        // 1...charger le driver mysql
        Class.forName("com.mysql.jdbc.Driver");
        // 2..creer la conection
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost/databaseprojectsd","root","0000");
        // 3..creer la requette
        Statement stm=con.createStatement();
        // 4..executer requette
        ResultSet res=stm.executeQuery("select * from objetenvente ;");
        // 5..parcour des resultat stoque dans res
        while (res.next()){
            System.out.println("nom : "+res.getString(1)+ "\nprix de depart : "+res.getFloat(2)+"\nprix courant : "+res.getFloat(3)+"\nproprietaire : "+res.getString(4)+"\nid du meneur : "+res.getString(5)+"\n-----------\n");
        }  
    //  6..fermer la connection
    con.close();
    // 7..traitement des exeption
        }catch (Exception e) {
            System.out.println("ERROR :"+e.getMessage());
        }
    }
}