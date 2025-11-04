package bigdatatfg;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// clase para dibujar la gráfica de los años seleccinados 
public class ConsultaGraficar{

// método constructor recibe año inicial y año final para dibujar gráfica
public static void consultaGraficar(int yearIni,int yearFin, String db, int variable, ResourceBundle rb) {
Bson filtro = new Document("$gte", yearIni).append("$lte", yearFin); //filro de busqueda
    try ( //para intentar actualizar a iSOJSON la fecha y no en Date      
        MongoClient mongoClient = new MongoClient("localhost", 27017)) {
        MongoDatabase database = mongoClient.getDatabase(db);
        String coleccion="total_p";
        String medida="mm";
        int dato=3;
        String titulo="titulo";
        
switch (variable) {
  case 0:
    coleccion="total_tmin";
    medida="°C";
    dato=3;
    titulo="titulo2";
    break;
  case 1:
    coleccion="total_tmin";
    medida="°F";
    dato=4;
    titulo="titulo2";
    break;
  case 2:
    coleccion="total_tmax";
    medida="°C";
    dato=3;  
    titulo="titulo3";
    break;
  case 3:
   coleccion="total_tmax";
   medida="°F";   
   dato=4;
   titulo="titulo3";
    break;
  case 4:   
   coleccion="total_p";
    medida="mm";
    dato=3;
    titulo="titulo";
    break;
}

        MongoCollection<Document> col = database.getCollection(coleccion);
        Document findDocument = new Document("Anho", filtro); 
        MongoCursor<Document> cur= col.find(findDocument).iterator();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        while (cur.hasNext()) {
               Document doc = cur.next();
               List list = new ArrayList(doc.values());              
               String ano=Integer.toString ((int) list.get(1));  
              try 
              {
               Double parametro=(Double) list.get(dato);                   
               dataset.addValue(parametro,medida,ano+" - "+(Comparable) list.get(2));  
              }  
              //obviar los datos que vengan como "?"
              catch (Exception err1)
              {}
             }
// crear gráfica...
JFreeChart chart = ChartFactory.createLineChart( // gráfica de lineas
rb.getString(titulo)+db, // Titulo 
rb.getString("Mes"), // Etiqueta de datos 
medida, // Etiqueta de valores 
dataset, // Datos 
PlotOrientation.VERTICAL, // orientacion 
false, // Incluye leyenda 
true, // Incluye tooltips 
false // urls 
); 
 // chart Customisation
CategoryPlot plot = (CategoryPlot) chart.getPlot();
//Indicar la etiqueta vertical posición y fuentes
final CategoryAxis domainAxis = plot.getDomainAxis();
domainAxis.setTickLabelFont(new Font("Arial", Font.BOLD, 11));
domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
//para mostrar gráfica
ChartFrame frame = new ChartFrame("TFG", chart); 
frame.pack(); 
frame.setVisible(true); 
mongoClient.close();// cerrar conexión mongoDB
}
}// fin método consultaGraficar()
}// fin clase
