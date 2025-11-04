package bigdatatfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.swing.JOptionPane;
 
// clase para descomprimir archivos .gz 
public class Descompresor implements Runnable {
private final File archivo ;
private final String rutaDescarga; // ruta para descargar fichero
private final BigDataTFG parent;  // objeto 
private final ResourceBundle rb;
// constructor  recibe el archivo a descomprimir, la ruta de descarga y objeto BigDataTFG     
public Descompresor(File archivo,String rutaDescarga,BigDataTFG parent, ResourceBundle rb){
this.archivo=archivo;
this.rutaDescarga = rutaDescarga;
this.parent = parent;
this.rb=rb;}
// hilo de descomprimir  
        @Override
public void run() {
    try { 
//se crea instancia descomprimir fichero .gz       
        try (GZIPInputStream gin = new GZIPInputStream(new FileInputStream(archivo))) 
             {int length = (int) archivo.length()*8;// ajusto la barra de progreso
             parent.getPgrAvance1().setString("");  //   barra de progreso
             parent.getPgrAvance1().setMinimum(0);
             parent.getPgrAvance1().setMaximum(length);
             parent.getPgrAvance1().setValue(0);
 //fichero descomprimido
             try (FileOutputStream fos = new FileOutputStream(rutaDescarga))
                {  byte[] buf = new byte[1024]; // buffer de descompresión
                   int len;
                   int current = 0;
                   while ((len = gin.read(buf)) > 0) { // bucle descomprime fichero
                       fos.write(buf, 0, len);
                       parent.getPgrAvance1().setValue(current);// bara de progreso de descompresión de fichero
                       current =current+len;
                       }
                   parent.getPgrAvance1().setValue(length);// barra de progreso
                   parent.getPgrAvance1().setString(rb.getString("archivoDescomprimido"));
               } //fin  buffer de descompresión
           } // fin ajusto la barra de progreso 
           } catch (IOException ex) {
                   Logger.getLogger(Descompresor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, ex.getMessage(), rb.getString("Errorgz"), JOptionPane.ERROR_MESSAGE);
           }
  } // hilo descomprimir 
}// fin clase

