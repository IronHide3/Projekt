import org.apache.sedona.core.SpatialRDD;
import org.apache.sedona.core.formatMapper.shapefileParser.ShapefileReader;



public class SedonaProject {
    public static void main(String[] args) {
        // Učitajte SpatialRDD iz Shapefile-a
        SpatialRDD spatialRDD = ShapefileReader.readToGeometryRDD("path/to/your/shapefile.shp", sparkSession);

        // Radite s vašim SpatialRDD-om
        // ...

        // Na primjer, ispišite broj geometrija u RDD-u
        System.out.println("Broj geometrija: " + spatialRDD.rawSpatialRDD().count());
    }
}