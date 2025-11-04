package bigdatatfg;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.bson.Document;

public class ConsultaWhere {

public static ArrayList<String> resultado(String basedatos, String tabla, String campo, String campo2,String q1, String q2, BigDataTFG parent, ResourceBundle rb  ) {

        ArrayList<String> resultado = new ArrayList<>(); 
    try (MongoClient client = new MongoClient("localhost", 27017)) {
            
            MongoDatabase database = client.getDatabase(basedatos);
            MongoCollection<Document> collection = database.getCollection(tabla);           
            Document query = new Document();  
            query.append(q1, q2);
            Document projection = new Document();
            projection.append(campo, campo2);           
            projection.append("_id", 0);
            Document sort = new Document();
            sort.append(campo, 1);  
            
                            
            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {                   
                    resultado.add(document.get(campo).toString());  
                    System.out.println(document.get(campo));
                }
            };            
            collection.find(query).projection(projection).sort(sort).forEach(processBlock);
            client.close();
        } catch (MongoException exp) {
            JOptionPane.showMessageDialog(parent, exp.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);
              }

return resultado;
 }// fin método

public static ArrayList<String> resultado(String basedatos, String tabla, String campo, String campo2,String q1, String q2, String q3, String q4, String q5, String q6, BigDataTFG parent, ResourceBundle rb) {

        ArrayList<String> resultado = new ArrayList<>(); 
    try (MongoClient client = new MongoClient("localhost", 27017)) {
            
            MongoDatabase database = client.getDatabase(basedatos);
            MongoCollection<Document> collection = database.getCollection(tabla);           
            Document query = new Document();  
            query.append(q1, q2);
            query.append(q3, q4);
            query.append(q5, q6);
            Document projection = new Document();
            projection.append(campo, campo2);           
            projection.append("_id", 0);
            Document sort = new Document();
            sort.append(campo, 1);  
            
                            
            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {                   
                    resultado.add(document.get(campo).toString());  
                    System.out.println(document.get(campo));
                }
            };            
            collection.find(query).projection(projection).sort(sort).forEach(processBlock);
            client.close();
        } catch (MongoException exp) {
            JOptionPane.showMessageDialog(parent, exp.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);
              }
return resultado;
 }// fin método

}//fin clase
