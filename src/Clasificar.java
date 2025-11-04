package bigdatatfg;

import java.io.*;
import static java.lang.Math.exp;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import java.math.BigDecimal; 
import weka.classifiers.meta.Bagging;
 
public class Clasificar 
{
static Object [] datos = new Object[12];// guarda prediccion
private  BigDataTFG parent;  // objeto 
// método constructor recibe el método de algoritmo a aplicar y el fichero arff

public static Object[] clasificar(File train,File test, BigDataTFG parent) throws Exception 
{
    try 
      {      
      //archivo de conjunto de entrenamiento
      File archivoTrain = train;
      //archivo de conjunto de prueba
      File archivoTest = test;
      Instances iTrain = new Instances(new BufferedReader(new FileReader(archivoTrain)));      
      Instances iTest = new Instances(new BufferedReader(new FileReader(archivoTest))); 
      //atributo a predecir
      String atributo=iTrain.attribute(2).name();
      //selección de algoritmo
      Classifier cls = new Bagging();
      //establecer atributo de clase
      iTrain.setClassIndex(iTrain.numAttributes() - 4);      
      iTest.setClassIndex(iTest.numAttributes() - 4);  
      //ejecución de algoritmo
      cls.buildClassifier(iTrain); 
      Evaluation eval = new Evaluation(iTrain);
      //predicción
      eval.evaluateModel(cls, iTest);   
      //resultados de predicción
      for (int i = 0; i < iTest.numInstances(); i++)
        {
         double pred = cls.classifyInstance(iTest.instance(i));
        
                if (atributo.equals("precipitacion"))
            {
            double dato=exp(pred);
            if( dato<0.1)
            datos [i]=0;
            else
            datos [i] =exp(pred);     
            }   
         else    
         {datos [i] =pred;}
        }      
      }       
     catch (Exception ex) 
        {      
            for (int i = 0; i < 12; i++) 
            { 
            datos [i] ="error";
            }
        }
    return datos;
} // fin método
}// fin clase