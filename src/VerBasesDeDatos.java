package bigdatatfg;

import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class VerBasesDeDatos { 
ArrayList<String> colecionCombo;
public static void listaComboBox(JComboBox jComboBox1) {
ArrayList<String> colecionCombo = new ArrayList<>();  

    try (// PASO 1: Conexión al Server de MongoDB Pasandole el host y el puerto
            MongoClient mongoClient = new MongoClient("localhost", 27017)) {
        // PASO 2: Conexión a la base de datos 'tfg'
        List<String> dbs = mongoClient.getDatabaseNames();         

int x=dbs.size();
for(int y=0;y<x;y++){
    if ((!(dbs.get(y).equals("opciones"))) && (!(dbs.get(y).equals("tfg")))&& (!(dbs.get(y).equals("admin")))&& (!(dbs.get(y).equals("config")))&& (!(dbs.get(y).equals("local"))))
    {jComboBox1.addItem(dbs.get(y));}
}
    }
 }// fin método 
    
}
