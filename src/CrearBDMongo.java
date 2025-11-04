package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CrearBDMongo {
public static void Crear (String nombreDB, JComboBox combo, JComboBox combo2,BigDataTFG parent, ResourceBundle rb)
   {
   try (MongoClient client = new MongoClient("localhost", 27017)) {
       String nombreValido=nombreDB.replace(" ", "_");
       System.out.println(nombreValido);      
       MongoDatabase database2 = client.getDatabase(nombreValido);         
       database2.createCollection("total_p"); 
       database2.createCollection("total_tmin"); 
       database2.createCollection("total_tmax");        
       combo.removeAllItems();// elimina todos item para no repetir las coleciones        
       VerBasesDeDatos.listaComboBox(combo); 
       combo2.removeAllItems();// elimina todos item para no repetir las coleciones        
       VerBasesDeDatos.listaComboBox(combo2);
       JOptionPane.showMessageDialog(parent,nombreDB + rb.getString("database_created"));
    }        
         catch (MongoException exp) 
            {JOptionPane.showMessageDialog(parent, exp.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);}
 }// fin método
}//fin clase
  

