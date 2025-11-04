package bigdatatfg;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.ResourceBundle;
import static java.lang.Character.isDigit;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import static jdk.nashorn.internal.objects.NativeString.substring;

// Interface gráfico para el proyecto BigDataTFG desde aquí se accede a todas las acciones
// que realiza el programa

public class BigDataTFG extends JFrame {
    
    private final JFileChooser selectorArchivoDescarga;//  declaración selector de archivos
    private File archivo;                        //  guarda archivo seleccionado 
    static final String BASE_DATOS= "tfg";       //  declaración de la base de datos mongoDB usada
    String colecion="";                          //  declaración colecciones usadas en mongoDB
    Object []datos=new Object[12];               //  array guarda la prediccion de los algoritmos timeseriesforecasting
    Object []mes={1,2,3,4,5,6,7,8,9,10,11,12};   //  columna mes de la tabla con las predicciones
    DefaultTableModel modelo;                    //  modelo de tabla con las predicciones    
    private ResourceBundle rb;
    private ResourceBundle sp;
    private ResourceBundle en;
    private ArrayList<String> estacionesClasificacion=new ArrayList<String>();
      
        public BigDataTFG() {
                
        //seleccion del idioma de la aplicación
        String lang="en";
        String country ="UK";
        Locale e=new Locale (lang,country);
        this.en=ResourceBundle.getBundle("bigdatatfg/english",e);
        String lang2="sp";
        String country2 ="SP";
        Locale s=new Locale (lang2,country2);
        this.sp=ResourceBundle.getBundle("bigdatatfg/spanish",s);
        this.rb=en;          
        String[] values = {"Spanish", "English"};
        boolean noRespondido=true;
        String lenguajeElegido=new String();
        while (noRespondido)
        {Object selected = JOptionPane.showInputDialog(null, "Please select your language", "Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
            if (selected !=null)
            {    
            lenguajeElegido = selected.toString(); 
            if("Spanish".equals(lenguajeElegido))
            {rb=sp;
            noRespondido=false;} 
            else if ("English".equals(lenguajeElegido))
            {rb=en;
            noRespondido=false;}
            }  
        } 
        initComponents();
        //se pone como invisibles las partes particulares de diferentes técnicas de DM
        //al elegir la técnica de DM se hacen visibles los elementos correspondientes
        jLabel12.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton4.doClick();
        jRadioButton5.setVisible(false);
        jRadioButton6.setVisible(false);
        jRadioButton7.setVisible(false);
        jButton7.setVisible(false);
        jLabel13.setVisible(false);
        jComboBox10.setVisible(false);
        jComboBox9.setVisible(false);
        jButton13.setVisible(false);
        jLabel18.setVisible(false);
        this.setLocationRelativeTo(null);    
        //ver los años descargados en una lista desplegables
        VerColecciones.listaComboBox(jComboBox1, "tfg");   
        //ver las bases de datos creadas en desplegables
        VerBasesDeDatos.listaComboBox(jComboBox4);    
        VerBasesDeDatos.listaComboBox(jComboBox6); 
       
         
        //ver estaciones/paises/regiones en listas desplegables correspondientes
        VerTabla.listaComboBox(jComboBox10,"opciones","estaciones","estacion","$estacion", this,rb);
        VerTabla.listaComboBox(jComboBox2,"opciones","regiones",rb.getString("region"),rb.getString("$region"), this,rb);       
        VerTabla.listaComboBox(jComboBox3,"opciones","paises",rb.getString("pais"),rb.getString("$pais"), this,rb);
        VerTabla.listaComboBox(jComboBox5,"opciones","estaciones","estacion","$estacion", this,rb);
        
        selectorArchivoDescarga = new JFileChooser();// crea selector de archivos
        // añade los jRadioButton a grupo de botones para solo poder seleccionar 1 simultaneamente 
        // grupo de botones para graficar y crear archivo arff
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton8);
        // añade los jRadioButton a grupo de botones para solo poder seleccionar 1 simultaneamente
        // grupo de método de prediccion 
        buttonGroup2.add(jRadioButton4);
        buttonGroup2.add(jRadioButton5);
        buttonGroup2.add(jRadioButton6);
        buttonGroup2.add(jRadioButton7);
        // tabla que presenta predicciones
        modelo=new DefaultTableModel();  // creacción
        modelo.addColumn(rb.getString("mes"),mes);     // columna mes  
        this.jTable1.setModel(modelo);   // se hace visible
    }// fin de método constructor
    @SuppressWarnings("unchecked")   
    // código generado automáticamente por netbeans
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField3 = new javax.swing.JTextField();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton2 = new javax.swing.JButton();
        jProgressBar2 = new javax.swing.JProgressBar();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        jYearChooser3 = new com.toedter.calendar.JYearChooser();
        jYearChooser4 = new com.toedter.calendar.JYearChooser();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jComboBox3 = new javax.swing.JComboBox<String>();
        jComboBox5 = new javax.swing.JComboBox<String>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<String>();
        jLabel6 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<String>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<String>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        jProgressBar3 = new javax.swing.JProgressBar();
        jComboBox8 = new javax.swing.JComboBox<String>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jButton5 = new javax.swing.JButton();
        jComboBox10 = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(rb.getString("titulo"));
        setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText(rb.getString("año"));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 63, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 91, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText(rb.getString("descargar"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jProgressBar1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setText(rb.getString("descomprimir"));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jProgressBar2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jProgressBar2.setStringPainted(true);
        getContentPane().add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setText(rb.getString("csvamongo"));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 72, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText(rb.getString("elegirAño"));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton2.setText(rb.getString("grafica"));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton3.setText(rb.getString("arff"));
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, -1, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton6.setText(rb.getString("Enviar"));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText(rb.getString("Hasta"));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText(rb.getString("Desde"));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                rb.getString("Mes"), rb.getString("Predicción")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 370, 410, 220));

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton4.setText("GaussianProcesses (GP)");
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, -1, -1));

        jRadioButton5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton5.setText("LinearRegression (LR)");
        getContentPane().add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, -1, -1));

        jRadioButton6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton6.setText("SMOreg (SMO)");
        getContentPane().add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, -1, -1));

        jRadioButton7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton7.setText("MultilayerPerceptron (MLP)");
        getContentPane().add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, -1, -1));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton7.setText(rb.getString("predecir"));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 560, -1, -1));
        getContentPane().add(jYearChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));
        getContentPane().add(jYearChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, -1, -1));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, -1, -1));

        jComboBox5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        getContentPane().add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, -1, -1));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton8.setText(rb.getString("ver"));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, -1, -1));

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton9.setText(rb.getString("crearRegion"));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, -1, -1));

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton10.setText(rb.getString("crearPais"));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 160, -1, -1));

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton11.setText(rb.getString("crearEstacion"));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 230, -1, -1));

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 140, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText(rb.getString("elegirBD"));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton12.setText(rb.getString("ActualizarMongoDB"));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("REGION");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText(rb.getString("ESTACION"));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText(rb.getString("PAIS"));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText(rb.getString("bd"));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, -1, -1));

        jComboBox6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        getContentPane().add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Variable");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        jComboBox7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { rb.getString("tempmin"), rb.getString("tempminf"),rb.getString("tempmax"), rb.getString("tempmaxf"),rb.getString("rain") }));
        getContentPane().add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, -1, -1));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 314, -1, -1));

        jProgressBar3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jProgressBar3.setStringPainted(true);
        getContentPane().add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        jComboBox8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { rb.getString("1v"),rb.getString("mv"),rb.getString("clas") }));
        getContentPane().add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText(rb.getString("seleccionar"));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, -1, -1));

        jComboBox9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { rb.getString("tempmin"), rb.getString("tempminf"),rb.getString("tempmax"), rb.getString("tempmaxf"),rb.getString("rain") }));
        getContentPane().add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 490, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText(rb.getString("seleccionar2"));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setText(rb.getString("PredecirST"));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 70, -1));

        jRadioButton8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jRadioButton8.setText(rb.getString("arff2"));
        getContentPane().add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, -1, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton5.setText(rb.getString("paraClasificacion"));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, -1, -1));

        jComboBox10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        getContentPane().add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText(rb.getString("seleccionar3"));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, -1, -1));

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton13.setText(rb.getString("predecir"));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 520, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 220, 290));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 940, 10));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 530, 10));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 50, 270));

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel14.setText(rb.getString("año_1_en_1"));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 130, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText(rb.getString("paso5"));
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 330, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText(rb.getString("paso1"));
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText(rb.getString("paso2"));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText(rb.getString("paso4"));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText(rb.getString("paso3"));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, 20));

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton14.setText(rb.getString("limpiar"));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 600, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("jLabel18");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 350, -1, -1));

        jProgressBar4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jProgressBar4.setStringPainted(true);
        getContentPane().add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Acciones al pulsar botón descargar ...
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // Obtenemos el posible nombre del archivo a descargar del jTextField1
    String ficheroAno=  jTextField1.getText(); // cadena con el año del fichero que queremos descargar
    if ("all_programador".equals(ficheroAno)) {// si ponemos all_programador descarga todos o los que pongo en bucle for
        for(int ano=1986 ;ano <= 2019;ano++){  // código reservado para programador
        String enteroString = Integer.toString(ano);
        String urlDatos = "https://www1.ncdc.noaa.gov/pub/data/ghcn/daily/by_year/"+enteroString+".csv.gz";
        String posibleNombreArchivo = enteroString+".csv.gz";
        JFileChooser selectorArchivos = new JFileChooser();
        JOptionPane.showMessageDialog(this, 
 rb.getString("rutaDescarga"), "",JOptionPane.INFORMATION_MESSAGE);   
 int seleccionD = selectorArchivos.showSaveDialog(this);
 if (seleccionD == JFileChooser.APPROVE_OPTION)
{
   File ficheroD = selectorArchivos.getSelectedFile(); 
        String ruta = ficheroD+enteroString+".csv.gz";       
        DownLoadFichero descarga = new DownLoadFichero(urlDatos, ruta, this, rb);
        Thread thread = new Thread(descarga);
        thread.start();
       }  }  // fin bucle for con los años a descargar   esto no lo implementare
    }       //fin if  de código implementado sólo para el programador
    else{   // descarga el fichero del año que pedimos
        
// http://noaa-ghcn-pds.s3.amazonaws.com/csv/1788.csv       la otra ya no funciona
// http://noaa-ghcn-pds.s3.amazonaws.com/csv.gz/1788.csv.gz  o esta
// https://www1.ncdc.noaa.gov/pub/data/ghcn/daily/by_year/  por la huelga de la administración está cerrada
       
         String urlDatos = "http://noaa-ghcn-pds.s3.amazonaws.com/csv.gz/"+jTextField1.getText()+".csv.gz";
//String urlDatos = "https://www1.ncdc.noaa.gov/pub/data/ghcn/daily/by_year/"+jTextField1.getText()+".csv.gz";
         String nombreArchivo = jTextField1.getText()+".csv.gz";// renombramos el fichero con el año + extensión .csv.gz    
// Muestra el diálogo para indicar el lugar donde se descargará el archivo
    File archivoDescarga = new File(nombreArchivo);            // crea fichero con nombre año.csv.gz
    JOptionPane.showMessageDialog(this, 
rb.getString("rutaDescarga"), "",JOptionPane.INFORMATION_MESSAGE);  
    selectorArchivoDescarga.setSelectedFile(archivoDescarga);  // selector ruta descarga
    int opcion = selectorArchivoDescarga.showSaveDialog(this); //
// instancia de DownloadFichero que hace la descarga de la página web 
    if (opcion == JFileChooser.APPROVE_OPTION) {
        String ruta = selectorArchivoDescarga.getSelectedFile().getPath();   //ruta de descarga
        DownLoadFichero descarga = new DownLoadFichero(urlDatos, ruta, this,rb);//descarga fichero de url a ruta descarga
        Thread thread = new Thread(descarga);// hilo descarga
        thread.start();
    }// fin if hilo de descarga
    }// fin else que hace la descarga
     // si el fichero no es válido nos da error de fichero no encontrado
    
    //JOptionPane.showMessageDialog(this,rb.getString("task_completed"));
    }//GEN-LAST:event_jButton1ActionPerformed

// escuchador botón descomprimir, descomprime los ficheros descargados de la web
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         try {
JOptionPane.showMessageDialog(this, 
rb.getString("rutaaDescomprimir"), "",JOptionPane.INFORMATION_MESSAGE);   
 JFileChooser selectorArchivos2 = new JFileChooser();             
// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
selectorArchivos2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
// indica cual fue la accion de usuario sobre el jfilechooser
int resultado = selectorArchivos2.showOpenDialog(this);
archivo = selectorArchivos2.getSelectedFile(); // obtiene el archivo seleccionado
// muestra error si es inválido 
if ((archivo == null) || (archivo.getName().equals(""))) {
    JOptionPane.showMessageDialog(this, rb.getString("nombreInvalido"), rb.getString("nombreInvalido"), JOptionPane.ERROR_MESSAGE);
} // fin de if
else{
// quita la extensión .gz al nuevo nombre del archivo quedando con extensión .csv
    String nombreArchivoConExtension =archivo.getName();
    String[] token =  nombreArchivoConExtension.split(".gz");
    int cantidadToken = token.length;
    String nombreArchivoSinExtension = token[cantidadToken - 1]; 
 JOptionPane.showMessageDialog(this, rb.getString("rutaDescomprimido"), "",JOptionPane.INFORMATION_MESSAGE);   
 int seleccion = selectorArchivos2.showSaveDialog(this);
if (seleccion == JFileChooser.APPROVE_OPTION)
{
   String fichero = selectorArchivos2.getSelectedFile().getPath();  
   String rutaDescarga = fichero+"/"+nombreArchivoSinExtension;   
        int index = nombreArchivoConExtension.lastIndexOf('.');
        String extension=nombreArchivoConExtension.substring(index + 1);
        if(extension.equals("gz")){    
// hilo que descomprime el archivo
    Descompresor descomprime = new Descompresor(archivo,rutaDescarga,this,rb);  // descomprime archivo
    Thread thread = new Thread(descomprime);// hilo descarga
    thread.start();
    JOptionPane.showMessageDialog(this,rb.getString("task_completed"));
// si no es .gx saca emergente
    }  else JOptionPane.showMessageDialog(this, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
}
// catch para informar del error al descomprimir    
         }} catch (HeadlessException ex) {    
             Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
    }//GEN-LAST:event_jButton2ActionPerformed
}
// importa fichero csv a mongoDB lo hace todo el archivo a la vez en vez de documento a documento
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
JOptionPane.showMessageDialog(this, 
rb.getString("rutaaGuardar"), "",JOptionPane.INFORMATION_MESSAGE);   
JFileChooser selectorArchivosCSV = new JFileChooser();
//para que muestre solo archivos de extensión csv
FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
selectorArchivosCSV.setFileFilter(filter);
// indica cual fue la accion de usuario sobre el jfilechooser si es cancelar o error saca emergente
int resultado = selectorArchivosCSV.showOpenDialog(this);
if((resultado==1)||(resultado==-1 )) { // 1 =cancelar , -1 =error
  // System.exit(0);// se cierra aplicación no se implementa
  JOptionPane.showMessageDialog(this,rb.getString("debeSerCsv"));// saca emergente con fallo
  }else{   
        File archivoCSV = selectorArchivosCSV.getSelectedFile(); // obtiene el archivo seleccionado
// para crear coleción con nombre del año   ej 2000
        String colecion1 =archivoCSV.getName();
        String[] token =  colecion1.split(".csv");
        int cantidadToken = token.length;
        colecion = token[cantidadToken - 1];
// comprueba extensión de fichero 
        int index = colecion1.lastIndexOf('.');
        String extension=colecion1.substring(index + 1);
        if(extension.equals("csv")){
 // si el fichero tiene extensión .csv  le insertará en base de datos tfg con comando mongoimport
 // crea instancia de MongoDB
       MongoDB a =new MongoDB(archivoCSV,BASE_DATOS,colecion,this);
       LeerMongoRunnable progresoMongo = new LeerMongoRunnable(BASE_DATOS,colecion,this,jComboBox1,rb);
       Thread thread2 = new Thread(progresoMongo);
       thread2.start();
       JOptionPane.showMessageDialog(this,rb.getString("task_completed"));
        } else  //  si no tiene extensión .csv saca emergente de error
        JOptionPane.showMessageDialog(this,rb.getString("debeSerCsv"));// saca emergente con fallo
      } // fin else  
 
    }//GEN-LAST:event_jButton3ActionPerformed
//boton enviar de los 4 radio botons paramongo graficar, limpiar tabla y crear archivos arff
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
int yearIni;
int yearFin;
String mensaje=rb.getString("revisarAnhos");// mensaje para emergente
yearIni=jYearChooser3.getValue(); //recoge año seleccionado por jYearChooser1
yearFin=jYearChooser4.getValue(); //recoge año seleccionado por jYearChooser2
String bd=(String) jComboBox6.getSelectedItem(); 
int variable=jComboBox7.getSelectedIndex();
String variableS="total_p";
switch (variable) {
  case 0:
    variableS="tmin";
    break;
  case 1:
    variableS="tmin";
    break;
  case 2:
    variableS="tmax";
    break;
  case 3:
    variableS="tmax";
    break;
 case 4:
    variableS="rainfall";
    break;
}
if (false){  // jRadioButton1 de borrar columnas tabla (limpiar tabla) 
    modelo.setColumnCount(1);//  borra las columnas de la tabla excepto la primera del mes
    }
else 
        if (jRadioButton2.isSelected()){ // jRadioButton2 de años a graficar        
            if (yearIni<=yearFin)      // comprueba que la fecha inicial es menor que la final      
            { 
            ArrayList<String> resultadoF=null;       
            // imprimir resultados de completitud de datos
            for (int anho=yearIni; anho<=yearFin;anho=anho + 1 )
            {            
            resultadoF=ConsultaWhere.resultado("opciones", "fiabilidad","fiabilidad","$fiabilidad","bd",bd,"variable",variableS,"year",Integer.toString(anho), this,rb);
            String resultadoFiabilidad=null;
            try{resultadoFiabilidad=substring(resultadoF.get(0),0,4);
            JOptionPane.showMessageDialog(this, rb.getString("infoFiabilidadp1")+anho+ " "+rb.getString("infoFiabilidadp2")+resultadoFiabilidad+"%.",rb.getString("info"),
        JOptionPane.INFORMATION_MESSAGE);
            System.out.println(anho+" "+resultadoFiabilidad);            
            }
            catch(Exception ex) { 
            JOptionPane.showMessageDialog(this, rb.getString("error1")+" "+anho+ " "+rb.getString("error2"));
            ;}             
            }
            ConsultaGraficar.consultaGraficar(yearIni,yearFin,bd,variable,rb);}
            else JOptionPane.showMessageDialog(this, mensaje);// saca emergente para revisarfechas  
            // boton para generar fichero arff para timeseries con los datos de los años que selecionemos
        }else             
            if (jRadioButton3.isSelected()){//jRadioButton3 genera fichero arff
                JOptionPane.showMessageDialog(this, rb.getString("rutaGuardararff"), "",JOptionPane.INFORMATION_MESSAGE);   
                JFileChooser selectorArchivos3 = new JFileChooser(); 
                selectorArchivos3.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int seleccion = selectorArchivos3.showSaveDialog(this);
                String rutaDescarga = "E:/8_tfg/clima.arff";
                if (seleccion == JFileChooser.APPROVE_OPTION)
                {
                String fichero = selectorArchivos3.getSelectedFile().getPath();  
                String punto= fichero.substring(fichero.length()-4,fichero.length()-3);
                String punto2= fichero.substring(fichero.length()-5,fichero.length()-4);
                System.out.println(punto);
                System.out.println(punto2);
                if(punto.equals(".")||punto2.equals("."))
                rutaDescarga = fichero; 
                else              
                rutaDescarga = fichero+"/"+"DM_timeseries.arff"; }  
            try {
                
                if (yearIni<=yearFin) {                
                FicheroArff.ficheroArff(yearIni,yearFin,bd,variable,rb,rutaDescarga);
                JOptionPane.showMessageDialog(this, rb.getString("arffcreado"));}
                else JOptionPane.showMessageDialog(this, mensaje);
                }catch (ParseException ex) {
                Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // boton para generar fichero arff para clasificación con los datos de los años que selecionemos
            else             
            if (jRadioButton8.isSelected()){
                //decir al usuario que primero tiene que elegir estaciones
                if(estacionesClasificacion.isEmpty())
                {JOptionPane.showMessageDialog(this, rb.getString("noHayEstaciones"), "",JOptionPane.INFORMATION_MESSAGE); }
                //pedir al usuario elegir ruta para guardar
                else
                {JOptionPane.showMessageDialog(this, rb.getString("rutaGuardararff"), "",JOptionPane.INFORMATION_MESSAGE);   
                JFileChooser selectorArchivos3 = new JFileChooser(); 
                selectorArchivos3.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int seleccion = selectorArchivos3.showSaveDialog(this);
                String rutaDescarga = "E:/8_tfg/clima.arff";
                if (seleccion == JFileChooser.APPROVE_OPTION)                
                {
                String fichero = selectorArchivos3.getSelectedFile().getPath();  
                String punto= fichero.substring(fichero.length()-4,fichero.length()-3);
                String punto2= fichero.substring(fichero.length()-5,fichero.length()-4);
                System.out.println(punto);
                System.out.println(punto2);
                if(punto.equals(".")||punto2.equals("."))
                rutaDescarga = fichero; 
                else
                rutaDescarga = fichero+"/"+"DM_classify.arff"; }  
            try {                
                if (yearIni<=yearFin){                 
                FicheroArff.ficheroArffClass(yearIni,yearFin,variable,rb,estacionesClasificacion,rutaDescarga,this);
                JOptionPane.showMessageDialog(this, rb.getString("arffcreado"));}
                else JOptionPane.showMessageDialog(this, mensaje);
                }catch (ParseException ex) {
                Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}
    }//GEN-LAST:event_jButton6ActionPerformed

// botón para predecir por uno de los cuatro métodos, controla los cuatro radiobotons
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
JFileChooser selectorArchivosArff = new JFileChooser();
selectorArchivosArff.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
// indica cual fue la accion de usuario sobre el jfilechooser
JOptionPane.showMessageDialog(this, rb.getString("rutaCoger"), "",JOptionPane.INFORMATION_MESSAGE); 
 selectorArchivosArff.showOpenDialog(this);
archivo = selectorArchivosArff.getSelectedFile(); // obtiene el archivo seleccionado
String ruta =archivo.getName();                   // path del archivo
// compruebo si es arrf el archivo
String[] token =  ruta.split("\\.");     // separa archivo 
int cantidadToken = token.length;
String extension = token[cantidadToken - 1];
    if("arff".equals(extension)){
       String metodo;
       int analisis=jComboBox8.getSelectedIndex(); 
       int variable=jComboBox9.getSelectedIndex(); 
       // GaussianProcesses
        if (jRadioButton4.isSelected())             {
        metodo="GP";
        datos=TimeSeriesTFG.timeSeriesTFG(metodo,archivo,this,analisis,variable);
// ConsultaGraficar.consultaGraficar(datos); no funciona era para graficar la prevision
// modelo.addColumn("mes",mes);   
        modelo.addColumn(metodo,  datos);
        }else       
          //LinearRegression    
           if (jRadioButton5.isSelected()){
           metodo="LR";
           datos=TimeSeriesTFG.timeSeriesTFG(metodo,archivo,this,analisis,variable);
// modelo.addColumn("mes",mes);   
           modelo.addColumn(metodo,  datos);
              }else // SMOreg
                if (jRadioButton6.isSelected()){
                   metodo="SMO";
                   datos=TimeSeriesTFG.timeSeriesTFG(metodo,archivo,this,analisis,variable);
// modelo.addColumn("mes",mes);   
                   modelo.addColumn(metodo,  datos);
                   }else //MultilayerPerceptron
                      {metodo="MLP";
                      datos=TimeSeriesTFG.timeSeriesTFG(metodo,archivo,this,analisis,variable);
// modelo.addColumn("mes",mes);   
                      modelo.addColumn(metodo,  datos);
                      }
        jLabel18.setText(ruta);
        jLabel18.setVisible(true);
      }else
    {
                JOptionPane.showMessageDialog(this,rb.getString("arffformat"));// saca emergente con el error
    }
    }//GEN-LAST:event_jButton7ActionPerformed

//filtrar las listas desplegables de paises y estaciones
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
JComboBox cb = (JComboBox)evt.getSource();
String region = (String)cb.getSelectedItem();
VerTabla.listaComboBox(jComboBox3,"opciones","paises",rb.getString("pais"),rb.getString("$pais"),rb.getString("region"),region,this,rb);   // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

//filtrar las listas desplegables de estaciones
    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
JComboBox cb = (JComboBox)evt.getSource();
String pais = (String)cb.getSelectedItem();
VerTabla.listaComboBox(jComboBox5,"opciones","estaciones","estacion","$estacion",rb.getString("pais"),pais, this,rb);
VerTabla.listaComboBox(jComboBox10,"opciones","estaciones","estacion","$estacion",rb.getString("pais"),pais, this,rb);
    }//GEN-LAST:event_jComboBox3ActionPerformed

//boton para ver la posicion de la estacion
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String estacion = (String)jComboBox5.getSelectedItem();
        ArrayList<String> resultado=null;
        resultado=ConsultaWhere.resultado("opciones", "estaciones", "latitud", "$latitud","estacion",estacion, this, rb);
        String latitud=resultado.get(0);  
        int largo=latitud.length();
        String lat1=latitud.substring(0, largo-4);
        String lat2=latitud.substring(largo-4);
        resultado=ConsultaWhere.resultado("opciones", "estaciones", "longitud", "$longitud","estacion",estacion, this, rb);
        String longitud=resultado.get(0);  
        largo=longitud.length();
        String lon1=longitud.substring(0, largo-4);
        String lon2=longitud.substring(largo-4);
        try {            
            java.awt.Desktop.getDesktop().browse(new URI("https://www.google.es/maps/place/@"+lat1+"."+lat2+","+lon1+"."+lon2+",15z/"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    //creacion de base de datos de region/pais/estacion
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
      CrearBDMongo.Crear((String)jComboBox2.getSelectedItem(),jComboBox4, jComboBox6, this, rb); 
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        CrearBDMongo.Crear((String)jComboBox3.getSelectedItem(),jComboBox4, jComboBox6, this, rb);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       CrearBDMongo.Crear((String)jComboBox5.getSelectedItem(),jComboBox4, jComboBox6, this, rb);
    }//GEN-LAST:event_jButton11ActionPerformed

//botón para pasar datos anuales a bases de datos particulares de region/pais/estacion    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
String anhoElegido=jComboBox1.getSelectedItem().toString();
String bdElegida=  jComboBox4.getSelectedItem().toString();
String bdElegidaEspacios=bdElegida.replace("_"," ");
String tipo=new String();
if(isDigit(bdElegidaEspacios.charAt(5)))
{tipo="estacion";}
else
{
tipo=ConsultaWhere.resultado("opciones", "tipo", "tipo", "$tipo", "bd", bdElegidaEspacios, this, rb).get(0);
}
//metodo que traslada, filtra datos y crea datos agrupados  

ColeccionFinalN nuevaCol=new ColeccionFinalN(anhoElegido,tipo, bdElegida, this,rb);
Thread thread3 = new Thread(nuevaCol);
thread3.start();
//JOptionPane.showMessageDialog(this,rb.getString("año")+ " " + anhoElegido + " " + rb.getString("successfully_uploaded_") + " " + bdElegida);
    }//GEN-LAST:event_jButton12ActionPerformed

    
//se hacen visibles los campos específicos al elegir técnica de DM    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    int analisis=jComboBox8.getSelectedIndex();  
    if (analisis !=2)
    {
    jLabel12.setVisible(true);
    jRadioButton4.setVisible(true);
    jRadioButton5.setVisible(true);
    jRadioButton6.setVisible(true);
    jRadioButton7.setVisible(true); 
    jButton7.setVisible(true);  
    jComboBox9.setVisible(true);
    jLabel13.setVisible(false);
    jComboBox10.setVisible(false);
    jButton13.setVisible(false);
    }
    else
    {
     jLabel12.setVisible(false);
     jRadioButton4.setVisible(false);
     jRadioButton5.setVisible(false);
     jRadioButton6.setVisible(false);
     jRadioButton7.setVisible(false);
     jButton7.setVisible(false);
     jComboBox9.setVisible(false);
     jLabel13.setVisible(true);
     jComboBox10.setVisible(true);
     jButton13.setVisible(true);
     JOptionPane.showMessageDialog(this, rb.getString("usaFiltros"), "",JOptionPane.INFORMATION_MESSAGE); 
    };
    }//GEN-LAST:event_jButton4ActionPerformed

//se guradan las estaciones elegidas para el modelo de clasificacion (DM)    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
String estacionElegida=(String)jComboBox6.getSelectedItem();   
if(isDigit(estacionElegida.charAt(5))){ 
estacionesClasificacion.add(estacionElegida);
JOptionPane.showMessageDialog(this,rb.getString("estacion_")+ " " + estacionElegida + " " + rb.getString("añadida_"));}
else
JOptionPane.showMessageDialog(this, rb.getString("soloEstacion"), "",JOptionPane.INFORMATION_MESSAGE);     
    }//GEN-LAST:event_jButton5ActionPerformed

//tareas de DM- clasificacion    
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    JFileChooser selectorArchivosArff = new JFileChooser();
    selectorArchivosArff.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
// indica cual fue la accion de usuario sobre el jfilechooser
    JOptionPane.showMessageDialog(this, rb.getString("rutaCoger"), "",JOptionPane.INFORMATION_MESSAGE); 
    selectorArchivosArff.showOpenDialog(this);
    String rutaDescarga = selectorArchivosArff.getSelectedFile().getPath();    
    archivo = selectorArchivosArff.getSelectedFile(); // obtiene el archivo seleccionado    
    String ruta =archivo.getName();                   // path del archivo
// compruebo si es arrf el archivo
    String[] token =  ruta.split("\\.");     // separa archivo 
    int cantidadToken = token.length;
    String extension = token[cantidadToken - 1];
    if("arff".equals(extension))
    {    
    String estacion=(String)jComboBox10.getSelectedItem();
    System.out.println(estacion);    
    File test;    
        try {        
            //se crea archivo de testeo
            test = FicheroArff.ficheroArffTest(rb, rutaDescarga, estacion, this);
        try {
            //ejecución de algoritmo
            datos=Clasificar.clasificar(archivo,test,this);   
            String metodo=rb.getString("abv");
            //visualización de datos
            modelo.addColumn(metodo,  datos);
            
            jLabel18.setText(ruta);
            jLabel18.setVisible(true);
            
        } catch (Exception ex) {
            Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (ParseException ex) {
            Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BigDataTFG.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }    
    else
    {JOptionPane.showMessageDialog(this,rb.getString("arffformat"));}    
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        modelo.setColumnCount(1);        // TODO add your handling code here:
        jLabel18.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

 // método get retorna barra de progreso para la clase DownLoadFichero 
    public JProgressBar getPgrAvance() {
        return jProgressBar1;
    }

// método get retorna barra de progreso para la clase Descompresor   
       public JProgressBar getPgrAvance1() { 
        return jProgressBar2;
    }
       
   // método get retorna barra de progreso para la clase LeerMongoRunnable      
        public JProgressBar getPgrAvance2() { 
        return jProgressBar3;
    }

   // método get retorna barra de progreso para la clase ColeccionFinalN      
        public JProgressBar getPgrAvance3() { 
        return jProgressBar4;
    }
     
        
        
    /**
     * @param args the command line arguments
     * Método main
     */
    public static void main(String args[]) {        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BigDataTFG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

 // crea y visualiza Jpannel BigDataTFG
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BigDataTFG().setVisible(true);
            }
        }
        );
    }// fin método main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private com.toedter.calendar.JYearChooser jYearChooser3;
    private com.toedter.calendar.JYearChooser jYearChooser4;
    // End of variables declaration//GEN-END:variables
}//fin clase
