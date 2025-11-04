package bigdatatfg;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.bson.Document;

public class VerTabla {
ArrayList<String> colecionCombo;

public static void listaComboBox(JComboBox jComboBox1, String basedatos, String tabla, String campo, String campo2, BigDataTFG parent, ResourceBundle rb ) {
ArrayList<String> colecionCombo = new ArrayList<>(); 
jComboBox1.removeAllItems();//se elimina para no repetir
try (MongoClient client = new MongoClient("localhost", 27017)) { 
            MongoDatabase database = client.getDatabase(basedatos);
            MongoCollection<Document> collection = database.getCollection(tabla);                              
            Document query = new Document();//creación de consulta            
            Document projection = new Document();
            projection.append(campo, campo2);            
            projection.append("_id", 0);
            Document sort = new Document();
            sort.append(campo, 1);        //ordenación    
            
            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {                    
                    colecionCombo.add(document.get(campo).toString());
                }
            };            

            collection.find(query).projection(projection).sort(sort).forEach(processBlock);
                    //.forEach(processBlock);
            //collection.find(query).projection(projection).sort(sort).forEach(processBlock);         
           
        } catch (MongoException exp) {
            JOptionPane.showMessageDialog(parent, exp.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);
              }
int x=colecionCombo.size();
//va añadiendo todas las colecciones en jComboBox
for(int y=0;y<x;y++){
    jComboBox1.addItem(colecionCombo.get(y));
}    
 }// fin método

public static void listaComboBox(JComboBox jComboBox1, String basedatos, String tabla, String campo, String campo2, String q1, String q2, BigDataTFG parent, ResourceBundle rb ) {
ArrayList<String> colecionCombo = new ArrayList<>(); 
jComboBox1.removeAllItems();
try (MongoClient client = new MongoClient("localhost", 27017)) {
            
            MongoDatabase database = client.getDatabase(basedatos);
            MongoCollection<Document> collection = database.getCollection(tabla);                                
            Document query = new Document();   
            query.append(q1, q2);//las condiciones del filtro, el campo por el que se filtra y su valor
            Document projection = new Document();
            projection.append(campo, campo2);        
            projection.append("_id", 0);
            Document sort = new Document();
            sort.append(campo, 1);            
                            
            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {                    
                    colecionCombo.add(document.get(campo).toString());
                }
            };            
            collection.find(query).projection(projection).sort(sort).forEach(processBlock);
            
        } catch (MongoException exp) {
            JOptionPane.showMessageDialog(parent, exp.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);
              }
int x=colecionCombo.size();
//va añadiendo todas las colecciones en jComboBox
for(int y=0;y<x;y++){
    jComboBox1.addItem(colecionCombo.get(y));}    
 }// fin método
}//fin clase











