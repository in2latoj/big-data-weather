package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.bson.Document;

// clase para descarga todos los documentos de la colecion les modifica formato fecha
// de int a ISODate y los actualiza en la colecion
public final class FormatFecha{
private final String colecion;  //colecion climatologia
    
// método constructor recibe la colección
FormatFecha(String colecion, String bd) throws ParseException{   
this.colecion = colecion;
    try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
         MongoDatabase database = mongoClient.getDatabase(bd);
         MongoCollection<Document> col = database.getCollection(colecion);
//recorre todos los documentos para modificar fecha
         try (MongoCursor<Document> cur = col.find().iterator()) {
             while (cur.hasNext()) {
             Document doc = cur.next();
             List list = new ArrayList(doc.values());
             int x=(int) list.get(2);
// va guardando todos los documentos con la fecha en formato ISODate        
             col.updateOne(doc, new Document("$set", new Document("fecha",FechaISO(x))));
             }
          }
        }
    }
 
//convierte fecha de int a ISODate
final String FechaISO(int fecha) throws ParseException {
String fechaString = Integer.toString(fecha);       
String dateSt=fechaString;    
Date traduceDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(dateSt);
String dateISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'", Locale.ENGLISH).format(traduceDate);  
System.out.print(dateISO);
return dateISO;
 }// fin método FechaIso()   
}// fin clase
