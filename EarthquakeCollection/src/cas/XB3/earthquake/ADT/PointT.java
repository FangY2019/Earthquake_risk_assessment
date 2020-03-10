package cas.XB3.earthquake.ADT;

public class PointT {
	private double x;
	private double y;

	public PointT(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(PointT that) {
		int earthRadius = 6371;
		double latSelf = x;
		double latThat = that.getLat();
		double longSelf = y;
		double longThat = that.getLong();
		double deltaLat = Math.toRadians(latThat - latSelf);
		double deltaLong = Math.toRadians(longThat - longSelf);
		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(Math.toRadians(latSelf))
				* Math.cos(Math.toRadians(latThat)) * Math.sin(deltaLong / 2) * Math.sin(deltaLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = earthRadius * c;
		return distance;
	}
	
	//index 0 will be the smaller latitude, index 1 will be the larger latitude
	public double[] latFilter(double radius) {
		double result[] = new double[2];
        int earthRadius = 6371;
        double b=180;
        for(int i = 0; i < 2; i++) {
        	if(i == 1) b = 0;
        	b = Math.toRadians(b);
        	double latSelf = Math.toRadians(x);
        	double longSelf = Math.toRadians(y);
        	double resultLat = Math.asin(Math.sin(latSelf)*Math.cos(radius/earthRadius) +
                     Math.cos(latSelf)*Math.sin(radius/earthRadius)*Math.cos(b));
        	double resultLong = longSelf + Math.atan2(Math.sin(b)*Math.sin(radius/earthRadius)*
                      Math.cos(latSelf), Math.cos(radius/earthRadius)-Math.sin(latSelf)*
                      Math.sin(resultLat));
        	resultLat = Math.toDegrees(resultLat);
        	resultLong = Math.toDegrees(resultLong);
        	result[i] = resultLat;
        }
        if(result[0] > result[1]) {
        	double temp = result[0];
        	result[0] = result[1];
        	result[1] = temp;
        }
		return result;
	}

	public double getLat() {
		return x;
	}

	public double getLong() {
		return y;
	}
}
