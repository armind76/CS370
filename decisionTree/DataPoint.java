public class DataPoint {
    
  public  double[] 	attributes;  // Continuous attributes
  public  String[] 	categories;  // Categorical attributes
  public  int 		classLabel;

  public DataPoint() {
	  this.categories = new String[1];
  }
  public DataPoint(double[] attributes, String[] categories, int classLabel) {
        this.attributes = attributes;
        this.categories = categories;
        this.classLabel = classLabel;
    }
}