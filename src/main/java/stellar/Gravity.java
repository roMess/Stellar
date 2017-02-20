package stellar;

public class Gravity {
	
	public static final double G = 6.674e-11;
	
	public static double scalarForceBetween(Body a, Body b) {
		
		if (a.getMass() == 0 || b.getMass() == 0) {
			return 0;
		}
		
		double rVec = a.getDistance(b);
		
		if (Math.abs(rVec) - 0.000000000001 < 0) {
			throw new IllegalArgumentException("Distance between these objects approached zero!");
		}
		
		
		return  G * a.getMass() * b.getMass() / (Math.pow(rVec, 2));
		
	}
	

}
