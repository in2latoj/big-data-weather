package bigdatatfg;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComboBox;

// clase para presentarnos en desplegable del conbobox las coleciones en la base de datos 
// tfg y seleccionar la que queremos hacer el filtrado de las estaciones de CyL
public class VerColecciones {
// guardo las coleciones de la base de datos 'tfg'
ArrayList<String> colecionCombo;

//mostrar las colecciones en combobox recibe el jComboBox1
public static void listaComboBox(JComboBox jComboBox1, String basedatos) {
ArrayList<String> colecionCombo = new ArrayList<>();  
// PASO 2: Conexión a la base de datos 'tfg'
    try (// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
            MongoClient mongoClient = new MongoClient("localhost", 27017)) {
        // PASO 2: Conexión a la base de datos 'tfg'
         DB db = mongoClient.getDB(basedatos);
//nombres de todas colecciones guardadas en 'tfg'
Set coltfg = db.getCollectionNames();
Set<String> coleciones= new TreeSet<>(coltfg);
coleciones.stream().map((ss) -> {
//System.out.println(ss);
return ss;
// guardo en array las colecciones de base de datos tfg para usar en combo box
}).filter((ss) -> (!"system.js".equals(ss))).forEachOrdered((ss) -> {
    colecionCombo.add(ss);
});
// aparece en primer lugar y al pulsar filtrar refresca lista
//jComboBox1.addItem("Refrescar lista");
int x=colecionCombo.size();
//va añadiendo todas las colecciones en jComboBox1
for(int y=0;y<x;y++){
    jComboBox1.addItem(colecionCombo.get(y));
    System.out.println(colecionCombo.get(y));
}
    }
 }// fin método
}//fin clase
