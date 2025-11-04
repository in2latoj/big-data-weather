package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.log;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;

public class FicheroArff{   

public static void  ficheroArff(int anoIni,int anoFin,String db, int variable, ResourceBundle rb, String rutaDescarga) throws ParseException{   
String coleccion="total_p";        
String coleccion2="total_tmin";   
String coleccion3="total_tmax";   
FileWriter escribir=null;
String sSistemaOperativo = System.getProperty("os.name");
String so=sSistemaOperativo.substring(0,3);
String saltoLinea="\n";
if(so.equals("Win"))
saltoLinea="\r\n"; 
System.out.println(sSistemaOperativo);
    try {
          //cabecera del fichero arff
          String saludo="@relation tfg"+saltoLinea;
          String atributo1="@attribute Date date \'yyyy-MM-dd\'"+saltoLinea;
          String atributo2="@attribute precipitacion numeric"+saltoLinea;
          String atributo3="@attribute tmin numeric"+saltoLinea;
          String atributo4="@attribute tmax numeric"+saltoLinea;
          String dato="@data"+saltoLinea;
        try // conexión a mongoDB, base de datos y colección
            (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase database = mongoClient.getDatabase(db);
            MongoCollection<Document> col = database.getCollection(coleccion);
            MongoCollection<Document> col2 = database.getCollection(coleccion2);
            MongoCollection<Document> col3 = database.getCollection(coleccion3);
            //Crear un objeto File clima.arff
            File archivo=new File(rutaDescarga);            
            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
            escribir = new FileWriter(archivo,false);//   false sobreescribe y true añade
            //Escribimos en el archivo con el metodo write
            escribir.write(saludo+atributo1+atributo2+atributo3+atributo4+dato);
            //filro de busqueda de documentos de los años comprendidos entre ambos pedidos
            Bson filtro = new Document("$gte", anoIni).append("$lte", anoFin); 
            // Crea documento con criterio de busqueda
            Document findDocument = new Document("Anho", filtro);
            MongoCursor<Document> cur = col.find(findDocument).iterator();
            MongoCursor<Document> cur2 = col2.find(findDocument).iterator();
            MongoCursor<Document> cur3 = col3.find(findDocument).iterator();
           {                      
            while (cur.hasNext()) {
                Document doc = cur.next();
                Document doc2 = cur2.next();
                Document doc3 = cur3.next();
                List list = new ArrayList(doc.values());
                List list2 = new ArrayList(doc2.values());
                List list3 = new ArrayList(doc3.values());               
                        escribir.write(list.get(1)+"-");
                        escribir.write((list.get(2))+"-");
                        escribir.write("01,");   
                        
                      try{
                      if((double)list.get(3)==0)
                        {escribir.write("0"+",");}
                        else
                        {escribir.write(log((double)list.get(3))*10+",");};}
                        catch (Exception e1)
                        {escribir.write(list.get(3)+",");}  
                        // escribir.write(list.get(3)+",");
                         escribir.write(list2.get(3)+",");
                         escribir.write(list3.get(3)+saltoLinea);                        
               }// fin bucle 
            escribir.close(); //Cerramos fichero
           }mongoClient.close(); //Cerramos la conexion
        }
      } catch (IOException ex) {
          Logger.getLogger(FicheroArff.class.getName()).log(Level.SEVERE, null, ex);
      } 
 }// fin método

public static void  ficheroArffClass(int anoIni,int anoFin,int variable, ResourceBundle rb, ArrayList<String> estacionesClasificacion, String rutaDescarga, BigDataTFG parent) throws ParseException
{  
//comprobacion de SO para definir carácter de salto de línea    
String sSistemaOperativo = System.getProperty("os.name");
String so=sSistemaOperativo.substring(0,3);
String saltoLinea="\n";
if(so.equals("Win"))
saltoLinea="\r\n"; 
String coleccion="total_p";
String atributo="precipitacion";
//comprobación de variable elegida
if (variable==4)
{coleccion="total_p";
atributo="precipitacion";}   
else if (variable==0 || variable==1)
{coleccion="total_tmin";
atributo="t_min";}   
else
{coleccion="total_tmax";
atributo="t_max";}   
FileWriter escribir=null;
    try {
          //cabecera del fichero arff
          String saludo="@relation tfg"+saltoLinea;
          String atributo1="@attribute anho numeric"+saltoLinea;
          String atributo2="@attribute mes numeric"+saltoLinea;
          String atributo3="@attribute "+atributo+" numeric"+saltoLinea;
          String atributo4="@attribute latitud numeric"+saltoLinea;
          String atributo5="@attribute longitud numeric"+saltoLinea;
          String atributo6="@attribute altitud numeric"+saltoLinea;
          String dato="@data"+saltoLinea;
          //iterator de la array de estaciones elegidas
          Iterator i = estacionesClasificacion.iterator(); 
          String db;
          File archivo;
          //filtro para recoger datos del periodo indicado
          Bson filtro = new Document("$gte", anoIni).append("$lte", anoFin); 
          Document findDocument = new Document("Anho", filtro);
          //creación del archivo
          archivo=new File(rutaDescarga);           
          escribir = new FileWriter(archivo,false);
          escribir.write(saludo+atributo1+atributo2+atributo3+atributo4+atributo5+atributo6+dato); 
          //bucle de estaciones elegidas
          while (i.hasNext()) 
            {
            db=(String)i.next(); 
            try // conexión a mongoDB
            (MongoClient mongoClient = new MongoClient("localhost", 27017)) 
            {       
            ArrayList<String> resultado=null;
            String latitud;
            String longitud;
            String altitud;
            MongoDatabase database;
            MongoCollection<Document> col ;   
            //recoger datod de latitud, longitud y altitud
            resultado=ConsultaWhere.resultado("opciones", "estaciones", "latitud", "$latitud","estacion",db, parent, rb);
            latitud=resultado.get(0);         
            resultado=ConsultaWhere.resultado("opciones", "estaciones", "longitud", "$longitud","estacion",db, parent, rb);
            longitud=resultado.get(0);   
            resultado=ConsultaWhere.resultado("opciones", "estaciones", "altitud", "$altitud","estacion",db, parent, rb);
            altitud=resultado.get(0); 
            //acceso a base de datos de la estacion
            database = mongoClient.getDatabase(db);
            //acceso a la coleccion de la variable elegida
            col = database.getCollection(coleccion);       
            MongoCursor<Document> cur = col.find(findDocument).iterator();
            //se guardan los datos
            while (cur.hasNext()) 
                {
                Document doc = cur.next();                
                List list = new ArrayList(doc.values()); 
                escribir.write(list.get(1)+",");
                escribir.write((list.get(2))+",");    
                //si es precipitacion se calcula ln
                if(variable==4)
                    {    
                    try
                        {
                        if((double)list.get(3)==0)
                        {escribir.write("-4"+",");}
                        else
                        {escribir.write(log((double)list.get(3))+",");};
                        }
                        catch (Exception e1)
                        {escribir.write(list.get(3)+",");}
                    }
                else
                    {escribir.write(list.get(3)+",");} 
                escribir.write(latitud+","); 
                escribir.write(longitud+","); 
                escribir.write(altitud+saltoLinea);                                             
                }// fin bucle            
            mongoClient.close(); //Cerramos la conexion
            }           
            }
         escribir.close(); //Cerramos fichero
        } 
        catch (IOException ex) 
        {Logger.getLogger(FicheroArff.class.getName()).log(Level.SEVERE, null, ex);}   
 }// fin método

public static File  ficheroArffTest(ResourceBundle rb, String rutaDescarga, String estacion, BigDataTFG parent) throws ParseException, IOException{   
String sSistemaOperativo = System.getProperty("os.name");
String so=sSistemaOperativo.substring(0,3);
String saltoLinea="\n";
if(so.equals("Win"))
saltoLinea="\r\n"; 
FileWriter escribir;
File archivo;   
int largo=rutaDescarga.length();
//acceso al archivo de entrenamiento
archivo=new File(rutaDescarga); 
Instances iTrain = new Instances(new BufferedReader(new FileReader(archivo)));
int numInst=iTrain.numInstances();
//acceso a la última instancia, a dato del año
int año=(int)iTrain.instance(numInst-1).value(0);
//establecimiento de año para predicción como +1 de año de archivo de entrenamiento
int anho=año+1;  
//creación del archivo de testeo 
rutaDescarga=rutaDescarga.substring(0, largo-5)+"Test.arff";  
archivo=new File(rutaDescarga); 
escribir = new FileWriter(archivo,false);
//acceso a atributo de archivo de entrenamiento
String atributo=iTrain.attribute(2).name();
//definición de encabezados
String saludo="@relation tfg"+saltoLinea;
String atributo1="@attribute anho numeric"+saltoLinea;
String atributo2="@attribute mes numeric"+saltoLinea;
String atributo3="@attribute "+atributo+" numeric"+saltoLinea;
String atributo4="@attribute latitud numeric"+saltoLinea;
String atributo5="@attribute longitud numeric"+saltoLinea;
String atributo6="@attribute altitud numeric"+saltoLinea;
String dato="@data"+saltoLinea;         
escribir.write(saludo+atributo1+atributo2+atributo3+atributo4+atributo5+atributo6+dato);       
ArrayList<String> resultado;
String latitud;
String longitud;
String altitud;              
//recuperación de datos de latitud, longitud y altitud
resultado=ConsultaWhere.resultado("opciones", "estaciones", "latitud", "$latitud","estacion",estacion, parent, rb);
latitud=resultado.get(0);         
resultado=ConsultaWhere.resultado("opciones", "estaciones", "longitud", "$longitud","estacion",estacion, parent, rb);
longitud=resultado.get(0);   
resultado=ConsultaWhere.resultado("opciones", "estaciones", "altitud", "$altitud","estacion",estacion, parent, rb);
altitud=resultado.get(0);
int mes=1;                         
while (mes<13) 
    {               
    escribir.write(anho+",");
    escribir.write(mes+",");             
    escribir.write("?,");
    escribir.write(latitud+","); 
    escribir.write(longitud+","); 
    escribir.write(altitud+saltoLinea);                         
    mes=mes+1;     
    }// fin bucle            
escribir.close();
return   archivo;    
}// fin método
}// fin clase
