
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.List;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.apache.sedona;

import org.apache.spark;

public class WatchOut {
    public static void main(String[] args) {

        // Initialize Spark
        SparkConf conf = new SparkConf()
                .setAppName("OSMDataProcessor")
                .setMaster("local") // Replace with your Spark cluster configuration
                .set("spark.serializer", SedonaKryoRegistrator.class.getName());
                
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        // Ucitavanje OSM podataka u LineStringRDD
        String osmFilePath = "C:/Users/Damjan/Desktop/OSM1/europe-latest.osm.pbf";
        
        
        // Ucitavanje GPS podataka
        String gpsDataPath = "C:/Users/Damjan/Desktop/HRVGPS/GPSHR.csv";
        
        SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

        // Load converted OSM data into a DataFrame (Replace "osmData" with your converted data)
        Dataset<Row> osmDataFrame = spark.read().format("csv").option("header", "false").load(gpsDataPath);

        // Perform spatial operations or analysis on the DataFrame
        osmDataFrame = osmDataFrame.withColumn("geom", functions.expr("ST_PointFromText(_c0, ' ', ',')"));

        // Daljnja analiza i manipulacija podacima prema potrebama vašeg projekta
        // ...
        //gpsDataPath = "C:/Users/Damjan/Desktop/HRVGPS/GPS_HR.csv";
         
         System.out.println(gpsDataPath);
         System.out.println(osmFilePath);
        
        // Stop Spark
        sc.stop();
        spark.stop();
       sparkContext.stop();
    }

    
}