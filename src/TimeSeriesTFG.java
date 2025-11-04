package bigdatatfg;

import java.io.*;
import static java.lang.Math.exp;
import java.util.List;
import weka.core.Instances;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.timeseries.WekaForecaster;
 
public class TimeSeriesTFG {
static Object [] datos = new Object[12];// guarda prediccion
private  BigDataTFG parent;  // objeto 
// método constructor recibe el método de algoritmo a aplicar y el fichero arff
public static Object[] timeSeriesTFG(String metodo,File archivo,BigDataTFG parent, int analisis, int variable) {
 //
    try {
      // path a clima.arffto 
      File pathToWineData =archivo;
      // carga datosa
      Instances clima = new Instances(new BufferedReader(new FileReader(pathToWineData)));
      // nuevo forecaster
      WekaForecaster forecaster = new WekaForecaster();
      // set the targets we want to forecast. This method calls
      // setFieldsToLag() on the lag maker object for us
      System.out.println(analisis);
      System.out.println(variable);
      if(analisis==1)
      {forecaster.setFieldsToForecast("precipitacion,tmin,tmax");}
      else if (variable==0)
      {{forecaster.setFieldsToForecast("tmin");}}    
     else if (variable==1)
      {{forecaster.setFieldsToForecast("tmin");}}   
       else if (variable==2)
      {{forecaster.setFieldsToForecast("tmax");}}   
       else if (variable==3)
      {{forecaster.setFieldsToForecast("tmax");}}  
       else if (variable==4)
      {{forecaster.setFieldsToForecast("precipitacion");}}   
      // gaussian processes for regression instead
      if(metodo=="GP") {   
      forecaster.setBaseForecaster(new GaussianProcesses());  
      }else
      //LinearRegression 
          if(metodo=="LR"){
          forecaster.setBaseForecaster(new LinearRegression());
          }else
      //SMOreg
              if(metodo=="SMO"){
              forecaster.setBaseForecaster(new SMOreg());
              }else  
      //MultilayerPerceptron());
              forecaster.setBaseForecaster(new MultilayerPerceptron());
      
      forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
      forecaster.getTSLagMaker().setMinLag(1);  //longitud mínima de retraso
      forecaster.getTSLagMaker().setMaxLag(12); //longitud máxima de retraso
      // añade el campo mes del año
      forecaster.getTSLagMaker().setAddMonthOfYear(true);
      // añade el trimestre del año
      forecaster.getTSLagMaker().setAddQuarterOfYear(true);
      // construye el modelo
      forecaster.buildForecaster(clima, System.out);
      // prime the forecaster with enough recent historical data
      // to cover up to the maximum lag. In our case, we could just supply
      // the 12 most recent historical instances, as this covers our maximum
      // lag period
      forecaster.primeForecaster(clima);
      // forecast para 12 unidades (meses) más allá de datos de entrenamiento
      List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);
      // salida de la predicción 
      for (int i = 0; i < 12; i++) {
        List<NumericPrediction> predsAtStep = forecast.get(i);
        if(analisis==0 || variable==4 )
        //si análisis univariable o variable=precipitacion    
        {
            //coger predicción de primera variable (o única si univariable)
            NumericPrediction predForTarget = predsAtStep.get(0);
            //si variable es temperatura en fahrenheit hacer transformacion
            if(variable==3 || variable==1)            
            {datos [i] =((predForTarget.predicted())*9/5)+32;
             System.out.println(datos [i]); }  
            //si variable es precipitacion calcular exponencial
            
            else if(variable==4)
            {
            double dato=exp(predForTarget.predicted()/10);
             if( dato<0.1)
            datos [i]=0;
            else
            datos [i] =dato; 
            System.out.println(datos [i]);
            }                    
            else
            {datos [i] =predForTarget.predicted();       
            System.out.println(datos [i]);   
            }     
        }
        //si variable es temperatura mínima
        else if (variable==0 || variable==1)
             {
            NumericPrediction predForTarget = predsAtStep.get(1);
            if(variable==1)            
            { datos [i] =((predForTarget.predicted())*9/5)+32;
            System.out.println(datos [i]);   }
            else
            {datos [i] =predForTarget.predicted();      
            System.out.println(datos [i]);   }
            }     
        
        //si variable es temperatura máxima
        else if (variable==2 || variable==3)
             {             
            NumericPrediction predForTarget = predsAtStep.get(2);
            if(variable==3)            
            {datos [i] =((predForTarget.predicted())*9/5)+32;
            System.out.println(datos [i]);}   
            else
            {datos [i] =predForTarget.predicted();     
            System.out.println(datos [i]);}   
        }
        
       }// fin bucle
    } catch (Exception ex) {      
         for (int i = 0; i < 12; i++) { // pone error en la tabla
         datos [i] ="error";
          }
     }return datos;
  } // fin método
}// fin clase