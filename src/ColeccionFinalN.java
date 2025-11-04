package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.MongoNamespace;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.bson.Document;

public class ColeccionFinalN implements Runnable {
private final String filtrarColecion;
private final String buscado;
private final String valor;
private final BigDataTFG parent;
private final ResourceBundle rb;

public ColeccionFinalN (String filtrarColecion,String buscado, String valor, BigDataTFG parent, ResourceBundle rb){      
    this.filtrarColecion = filtrarColecion;
    this.buscado = buscado;
    this.valor = valor;
    this.parent = parent;
    this.rb = rb;
    };
        
 
    @Override
public void run(){
    
synchronized(this) {
    
        parent.getPgrAvance3().setString("");
        parent.getPgrAvance3().setMinimum(0);
        parent.getPgrAvance3().setMaximum(100);
        parent.getPgrAvance3().setValue(10);

    //se consulta mongoDB para extraer las estaciones de la region/país/estacion elegida por el usuario    
    ArrayList<String> estaciones = new ArrayList<>(); 
    int mes=1;
    //se busca las estaciones pertenecientes a pais/región
    estaciones=ConsultaWhere.resultado("opciones", "estaciones", "estacion", "$estacion",buscado, valor.replace("_", " "), parent, rb);
    float numEstaciones=estaciones.size();
    String estacionCod[] = new String[estaciones.size()];         
    for (int j = 0; j < estaciones.size(); j++) 
    {estacionCod[j] = estaciones.get(j); }
    //el año seleccionado   
   
    try
    {
           
    MongoClient mongoClient = new MongoClient();
    //acceso a base de datos con los años descargados
    MongoDatabase database = mongoClient.getDatabase("tfg");
    //acceso a base de datos seleccionada
    MongoDatabase databaseDestino = mongoClient.getDatabase(valor);
    //acceso a base de datos "opciones", a colección "fiabilidad"
    //ahí se guardan los porcentajes de completitud del dato
    MongoDatabase databaseFiabilidad = mongoClient.getDatabase("opciones");
    MongoCollection<Document> collectionFiabilidad = databaseFiabilidad.getCollection("fiabilidad");
    // Acceso a colección aaaa 
    MongoCollection<Document> collection = database.getCollection(filtrarColecion);
            parent.getPgrAvance3().setValue(15);
    //guardar los datos de precipitaciones
    FindIterable it =     collection.find(and(in("ID_estacion", estacionCod),eq("elemento","PRCP"))).
    // elemento que se guardan son lo projection
    projection(fields(include("fecha","ID_estacion", "valor_dato"), excludeId()));
     ArrayList<Document> docs = new ArrayList();
    String colecionFinal=filtrarColecion;
                parent.getPgrAvance3().setValue(25);
    it.into(docs);       
    MongoCollection<Document> col = databaseDestino.getCollection(colecionFinal);
    parent.getPgrAvance3().setValue(30);  
    docs.stream().map((doc) -> {
    // inserta los documentos en la colección aaaa
    col.insertOne((org.bson.Document) (Document) doc);        
    return doc;         
    }).forEachOrdered((doc) -> {            
    });   
   long numDocumentos=col.countDocuments();  
   //cálculo de porcentaje de datos disponibles 
   double fiabilidad=numDocumentos/(numEstaciones*365)*100; 
   if(fiabilidad>100)
   {fiabilidad=100;}    
   Document document1 = new Document("bd",valor).
   append("year",filtrarColecion).
   append("variable","rainfall").
   append("fiabilidad",fiabilidad);
   collectionFiabilidad.insertOne(document1);  
     parent.getPgrAvance3().setValue(35);    
   //guardar los datos de temperatura mínima        
   FindIterable it2 =     collection.find(and(in("ID_estacion", estacionCod),eq("elemento","TMIN"))).
   // elemento que se guardan son lo projection  
   projection(fields(include("fecha","ID_estacion", "valor_dato"), excludeId()));
   ArrayList<Document> docs2 = new ArrayList();
   String colecionFinal2=filtrarColecion+"TMIN";
           parent.getPgrAvance3().setValue(50);
   it2.into(docs2);
   MongoCollection<Document> col2 = databaseDestino.getCollection(colecionFinal2);  
      parent.getPgrAvance3().setValue(55);     
   docs2.stream().map((doc) -> {
   // inserta los documentos en la colección aaaa
   col2.insertOne((org.bson.Document) (Document) doc);        
   return doc;         
   }).forEachOrdered((doc) -> {            
   });      
   numDocumentos=col2.countDocuments();   
   fiabilidad=numDocumentos/(numEstaciones*365)*100; 
   if(fiabilidad>100)
   {fiabilidad=100;}    
   Document document2 = new Document("bd",valor).
   append("year",filtrarColecion).
   append("variable","tmin").
   append("fiabilidad",fiabilidad);
   collectionFiabilidad.insertOne(document2);   
       parent.getPgrAvance3().setValue(70);  
   //guardar los datos de temperatura máxima
   FindIterable it3 =     collection.find(and(in("ID_estacion", estacionCod),eq("elemento","TMAX"))).
   // elemento que se guardan son lo projection  
   projection(fields(include("fecha","ID_estacion", "valor_dato"), excludeId()));
   ArrayList<Document> docs3 = new ArrayList();
   String colecionFinal3=filtrarColecion+"TMAX";
               parent.getPgrAvance3().setValue(80);
   it3.into(docs3); 
           parent.getPgrAvance3().setValue(90);
   MongoCollection<Document> col3 = databaseDestino.getCollection(colecionFinal3);  
   docs3.stream().map((doc) -> {
   // inserta los documentos en la colección aaaa
   col3.insertOne((org.bson.Document) (Document) doc);        
   return doc;         
   }).forEachOrdered((doc) -> {            
   }); 
       parent.getPgrAvance3().setValue(91);  
   numDocumentos=col3.countDocuments();   
   fiabilidad=numDocumentos/(numEstaciones*365)*100; 
   if(fiabilidad>100)
   {fiabilidad=100;}    
   Document document3 = new Document("bd",valor).
   append("year",filtrarColecion).
   append("variable","tmax").
   append("fiabilidad",fiabilidad);
   collectionFiabilidad.insertOne(document3);
 
    parent.getPgrAvance3().setValue(92);     
//cambio de formato de fecha   
// primero definimos la projection con los campos que interesan
 try (MongoCursor<Document> cur = col.find().iterator()) {
             while (cur.hasNext()) {
             Document doc = cur.next();
             List list = new ArrayList(doc.values());    
             int x=(int) list.get(2);        
// va guardando todos los documentos con la fecha en formato ISODate  
          }}
FormatFecha b =new FormatFecha(filtrarColecion,valor);

 try (MongoCursor<Document> cur2 = col2.find().iterator()) {
             while (cur2.hasNext()) {
             Document doc = cur2.next();
             List list = new ArrayList(doc.values());    
             int x=(int) list.get(2);        
// va guardando todos los documentos con la fecha en formato ISODate  
          }}
FormatFecha c =new FormatFecha(filtrarColecion+"TMIN",valor);

//cambio de formato de fecha   
// primero definimos la projection con los campos que interesan
 try (MongoCursor<Document> cur3 = col3.find().iterator()) {
             while (cur3.hasNext()) {
             Document doc = cur3.next();
             List list = new ArrayList(doc.values());    
             int x=(int) list.get(2);        
// va guardando todos los documentos con la fecha en formato ISODate  
          }}
 
         parent.getPgrAvance3().setValue(95);
     
FormatFecha d =new FormatFecha(filtrarColecion+"TMAX",valor);

//guardar los datos en total_p
Document project1 =new Document("valor_dato", true).append("ID_estacion", true).append("fecha",
                   new Document("$dateFromString",new Document("dateString","$fecha")));
//projection
Document project = new Document("$project", project1);
// para la agrupación por meses 
Document groupFields = new Document( "_id", new Document("$month","$fecha")).append("Documentos",
                       new Document( "$sum", 1)).append("litros",new Document("$sum","$valor_dato"))
                       .append("media",new Document("$avg","$valor_dato"));
Document group = new Document("$group", groupFields);
Document sort = new Document("$sort", new Document("_id",1));
// tubería de los tres projection, group y sort
List<Document> pipeline = Arrays.asList(project,group,sort);
// objeto iterator
MongoCursor<Document> cur = col.aggregate(pipeline).iterator();
// colección donde irán los nuevos documentos
MongoCollection<Document> total = databaseDestino.getCollection("total_p");
// bucle que calcula para cada documento la media mensual de litros
// multiplicando por los días (30) y dividendo por 10 porque son decilitros
// por tanto multiplica por tres
// luego cada documento es insertado en la nueva colección 
int seguir=0;   
Document doc = cur.next();
List list= new ArrayList();

        parent.getPgrAvance3().setValue(96);
while (mes<13) 
    {    
    if (seguir==1)
    {try{doc = cur.next();}
    catch (Exception e1) {}}    
    list = new ArrayList(doc.values());    
    int mesCol=(int) list.get(0);    
    //compruebo si hay datos para el mes
    if(mes==mesCol)
    {
    seguir=1;
    double litros= (double) list.get(3);
      if (litros!=0)
        {
        litros=(double) list.get(3);
        litros=litros*3;
        }
// Crea los documentos que se van a insertar
      Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
      append("Mes",list.get(0)).
      append("Litros",litros);
      total.insertOne(document);  }
    //si no hay datos en un mes pongo símbolo "?"
    else 
        {
        //indico de no avanzar cogiendo documentos de la colección    
        seguir=0;
        Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
        append("Mes",mes).
        append("Litros","?");
        total.insertOne(document);    
        }
    mes=mes+1;    
}

        parent.getPgrAvance3().setValue(97);
    
//ordenacion de collection
Document sort2 = new Document("$sort", new Document("Anho",1));       
Document project2 =new Document("Anho", true).append("Mes", true).append("Litros",true);
//projection
Document project3 = new Document("$project", project2);
List<Document> pipeline2 = Arrays.asList(project3,sort2);
MongoCursor <Document> docsF = total.aggregate(pipeline2).iterator();
databaseDestino.createCollection("total_pT"); 
MongoCollection<Document> totalT = databaseDestino.getCollection("total_pT");
while (docsF.hasNext()) {
            Document itF=docsF.next();            
            totalT.insertOne(itF);
        }
total.drop(); 
MongoNamespace newName = new MongoNamespace(valor ,"total_p");
totalT.renameCollection(newName);
        
//guardar los datos en total_tmin
cur = col2.aggregate(pipeline).iterator();
// colección donde irán los nuevos documentos
total = databaseDestino.getCollection("total_tmin");
// bucle que calcula para cada documento la media mensual de temperatura
mes=1;
seguir=0;   
doc = cur.next();

        parent.getPgrAvance3().setValue(98);

while (mes<13) {    
    if (seguir==1)
    {try{doc = cur.next();}
    catch (Exception e1) {}}     
    list = new ArrayList(doc.values());
    int mesCol=(int) list.get(0);
    if(mes==mesCol)
    {
    seguir=1;
    double temp= (double) list.get(3);
    double tempF;
    temp=(double) list.get(3);
    temp=temp/10;  
    tempF=(temp*9/5)+32;    
// Crea los documentos que se van a insertar
      Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
      append("Mes",list.get(0)).
      append("Temperatura",temp).
      append("TemperaturaF",tempF);
    total.insertOne(document);     
     }
    else 
        {
        seguir=0;
        Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
        append("Mes",mes).
        append("Temperatura","?").
        append("TemperaturaF","?");
        total.insertOne(document);    
        }
    mes=mes+1;    
} 

//ordenacion de collection
Document project4 =new Document("Anho", true).append("Mes", true).append("Temperatura",true).append("TemperaturaF",true);
//projection
Document project5 = new Document("$project", project4);
List<Document> pipeline3 = Arrays.asList(project5,sort2);
MongoCursor <Document> docsTF = total.aggregate(pipeline3).iterator();
databaseDestino.createCollection("total_tminT"); 
MongoCollection<Document> totalTT = databaseDestino.getCollection("total_tminT");
while (docsTF.hasNext()) {
            Document itTF=docsTF.next();            
            totalTT.insertOne(itTF);
        }
total.drop(); 
MongoNamespace newName2 = new MongoNamespace(valor ,"total_tmin");
totalTT.renameCollection(newName2);
 
//guardar los datos en total_tmax
cur = col3.aggregate(pipeline).iterator();
// colección donde irán los nuevos documentos
total = databaseDestino.getCollection("total_tmax");
// bucle que calcula para cada documento la media mensual de temperatura
mes=1;
seguir=0;   
doc = cur.next();


while (mes<13) {    
    if (seguir==1)
    {try{doc = cur.next();}
    catch (Exception e1) {}}    
    list = new ArrayList(doc.values());
    int mesCol=(int) list.get(0);
    if(mes==mesCol)
    {
    seguir=1;
    double temp= (double) list.get(3);
    double tempF;
    temp=(double) list.get(3);
    temp=temp/10;  
    tempF=(temp*9/5)+32;    
// Crea los documentos que se van a insertar
      Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
      append("Mes",list.get(0)).
      append("Temperatura",temp).
      append("TemperaturaF",tempF);
    total.insertOne(document);     
     }
    else 
        {
        seguir=0;
        Document document = new Document("Anho",Integer.parseInt(filtrarColecion)).
        append("Mes",mes).
        append("Temperatura","?").
        append("TemperaturaF","?");
        total.insertOne(document);    
        }
    mes=mes+1;    
    }

        parent.getPgrAvance3().setValue(99);

//ordenacion de collection
docsTF = total.aggregate(pipeline3).iterator();
databaseDestino.createCollection("total_tmaxT"); 
totalTT = databaseDestino.getCollection("total_tmaxT");
while (docsTF.hasNext()) {
            Document itTF=docsTF.next();            
            totalTT.insertOne(itTF);
        }
total.drop(); 
MongoNamespace newName3 = new MongoNamespace(valor ,"total_tmax");
totalTT.renameCollection(newName3);   

mongoClient.close();//cerrar conexión

parent.getPgrAvance3().setValue(100);
parent.getPgrAvance3().setString(rb.getString("CargaCompleta"));
JOptionPane.showMessageDialog(parent,rb.getString("año")+ " " + this.filtrarColecion + " " + rb.getString("successfully_uploaded_") + " " + this.valor);

}catch (Exception ex) {     // error 
            JOptionPane.showMessageDialog(parent, ex.getMessage(), rb.getString("ErrormongoDB"), JOptionPane.ERROR_MESSAGE);
           }
  } }
}
