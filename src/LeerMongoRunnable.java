package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.bson.Document;

public class LeerMongoRunnable implements Runnable{
private final String baseDatos; 
private final String colecion; 
private final BigDataTFG parent;  
String documento;
private final JComboBox combo;
private final ResourceBundle rb;

public LeerMongoRunnable (String baseDatos,String colecion,BigDataTFG parent, JComboBox combo, ResourceBundle rb){
this.parent = parent;
this.baseDatos = baseDatos;
this.colecion = colecion;
this.combo=combo;
this.rb=rb;};

@Override
public void run(){
    synchronized(this) {
//Conexión a la base de datos
    try {// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
        String documentoTemp="0"; 
        parent.getPgrAvance2().setString("");
        parent.getPgrAvance2().setMinimum(0);
        parent.getPgrAvance2().setMaximum(45000);
        parent.getPgrAvance2().setValue(0);
        MongoClient mongoClient = new MongoClient("localhost", 27017);
         // PASO 2: Conexión a la base de datos 
         MongoDatabase db = mongoClient.getDatabase(baseDatos);
         // PASO 3: Obtenemos una coleccion para trabajar con ella
         MongoCollection<Document> collection = db.getCollection(colecion);
         long contadorDocumento;
         String documento = "1";  
         
         //se comparan las últimas dos consultas
         while (!(documento.equals(documentoTemp)))
         {
         //se guarda en documentoTemp la consulta anterior
         documentoTemp=documento;
         wait(10000);
         contadorDocumento = collection.countDocuments();
         //se rellena la barra de progreso en función de número de documentos
         Long progreso=(contadorDocumento/1000);
         String progresoS = Long.toString(progreso);
         int progresoI=Integer.parseInt(progresoS);
         //se guarda en documento la consulta actual
         documento = Long.toString(contadorDocumento); 
         parent.getPgrAvance2().setValue(progresoI);                
         }

         combo.removeAllItems();// elimina todos item para no repetir las coleciones
         // se actualiza el Combo Box con las colecciones de los años         
         VerColecciones.listaComboBox(combo,"tfg"); 
         parent.getPgrAvance2().setValue(45000);
         parent.getPgrAvance2().setString(rb.getString("CargaCompleta"));
         mongoClient.close();// cierre de base de datos
         }catch (Exception ex) { // error devuelve si no hay conexión a base de dstos
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    rb.getString("errorConexion"), JOptionPane.ERROR_MESSAGE);
          }}
}}



