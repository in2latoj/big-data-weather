package bigdatatfg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

//clase de descarga de ficheros de la web
//http://noaa-ghcn-pds.s3.amazonaws.com/csv/aaaa.csv  ha cambiado por la huelga administración americana
//https://www1.ncdc.noaa.gov/pub/data/ghcn/daily/by_year/xxxx
public class DownLoadFichero implements Runnable {
private final String direccion;    // url
private final String rutaDescarga; // ruta para descargar fichero
private final BigDataTFG parent;  // objeto 
private final ResourceBundle rb;

// método constructor recibe url, path descarga y objeto BigDataTFG
public DownLoadFichero(String direccion, String rutaDescarga, BigDataTFG parent, ResourceBundle rb) {//constructor
this.direccion = direccion;
this.rutaDescarga = rutaDescarga;
this.parent = parent;
this.rb=rb;
 }
@Override//hilo de descarga
// hilo 
public void run() {
    try {
         URL url = new URL(direccion);// url de descarga
         // establecemos conexion
         URLConnection conexion = url.openConnection();
         FileOutputStream fichero;
            try (InputStream stream = conexion.getInputStream()) {
//para crear la  barra de progreso de la descarga
                int length = conexion.getContentLength();
                parent.getPgrAvance().setString("");
                parent.getPgrAvance().setMinimum(0);
                parent.getPgrAvance().setMaximum(length);
                parent.getPgrAvance().setValue(0);
                fichero = new FileOutputStream(rutaDescarga);
// Lectura del fichero de la web y escritura en fichero local
                byte[] buffer = new byte[1024]; // buffer temporal de lectura.
                int leido = stream.read(buffer);
                int current = 0;
                while (leido > 0) {             // descargando fichero
                    fichero.write(buffer, 0, leido);
                    leido = stream.read(buffer);
                    parent.getPgrAvance().setValue(current);// barra de progreso
                    current = current+leido;
                }   parent.getPgrAvance().setValue(length);// bara de progreso
                parent.getPgrAvance().setString(rb.getString("descargaCompleta"));
                // cierre de conexion y fichero.
            } fichero.close();   // cerrar fichero
        } catch (IOException ex) {     // error devuelve url que ha fallado
            JOptionPane.showMessageDialog(parent, ex.getMessage(), rb.getString("errorNoEncontrado"), JOptionPane.ERROR_MESSAGE);
        }
    }// fin hilo
}// fin clase
    