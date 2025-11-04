package bigdatatfg;

import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

// guarda ficheros csv en la base de datos tfg colecion aaaa siendo las aes el año
public class MongoDB{
 BigDataTFG parent;
 private File archivoCSV ;// fichero csv con datos de climatologia de un año
 private String baseDatos; // base de datos de mongoDB tfg
 private String colecion;  //colecion climatologia
 Process p = null;  

 // constructor recibe el fichero csv, la colección y objeto BifDataTFG
 MongoDB(File archivoCSV, String baseDatos,String colecion,BigDataTFG parent){
 this.archivoCSV = archivoCSV;
 this.baseDatos = baseDatos;
 this.colecion = colecion;
 this.parent=parent;
// comanod mongoimport que importa un fichero csv a mongoDB
String command = "mongoimport --db "+ baseDatos+" --collection "+colecion+" --type csv --file "
                  +archivoCSV+" --fields ID_estacion,fecha,elemento,valor_dato,M_flag,q_flag,s_flag,observ_time" ;
  try { // ruta de mongo mas comando mongoimport
      p= Runtime.getRuntime().exec("C:\\Program Files\\MongoDB\\Server\\4.2\\bin\\"+command);
       
       
//    System.out.println("Reading csv into Database");
//    System.out.println(command); 
      } catch(IOException e) {    // no salen estos errores
        System.out.println("EXCEPTION: " + e.getMessage());
       JOptionPane.showMessageDialog(parent, e.getMessage(), "Error fichero no cargado", JOptionPane.ERROR_MESSAGE);

}catch (Exception ex) {     // error devuelve url que ha fallado
            JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
 
 }// fin método constructor
}//fin clase