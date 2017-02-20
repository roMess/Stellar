package stellar;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class GravityTest {
	
	@Test
	public void testForceValue() {
		Body a = new oneDBody(5.972e24, -6.371e6);
		Body b = new oneDBody(1, 0);
		
		double fOnA = Gravity.scalarForceBetween(a, b);
		
		Assert.assertEquals(9.819, fOnA, 0.001);
		
	}

	@Test
	public void testBScalarForceEqualsAScalarForce() {
		Body a = new oneDBody(5.972e24, -6.371e6);
		Body b = new oneDBody(10, 0);
		
		double scalarForceOnA = Gravity.scalarForceBetween(a, b);
		
		double scalarForceOnB = Gravity.scalarForceBetween(b, a);
		
		// fOnB = fOnA
		double residual = scalarForceOnA - scalarForceOnB;
		
		Assert.assertTrue("Oh No! force on A was: " + scalarForceOnA + " N, while force on B was " + scalarForceOnB + " N.", residual < 0.01);
		
	}
	
	@Test
	public void testZeroMassAZeroForce() {
		Body a = new oneDBody(5.972e24, -6.371e6);
		Body b = new oneDBody(0, 0);
		
		Assert.assertTrue(0 == Gravity.scalarForceBetween(a, b));
		
	}
	
	@Test
	public void testZeroMassBZeroForce() {
		Body a = new oneDBody(0, -6.371e6);
		Body b = new oneDBody(10, 0);
		
		Assert.assertTrue(0 == Gravity.scalarForceBetween(a, b));
		
	}
	
	@Test
	public void testZeroDistanceThrowsError() {
		Body a = new oneDBody(100000, -6.371e6);
		Body b = new oneDBody(10, -6.371e6);
		
		try {
			Gravity.scalarForceBetween(a, b);
			fail("Distance was zero, should have errored!");
		} catch (IllegalArgumentException e) {
			// Do nothing
		}
		
	}

}

class oneDBody implements Body {

	private double m;

	private double position;

	public oneDBody(double m, double x) {

		this.m = m;
		this.position = x;
	}

	public double getDistance(Body p2) {
		return p2.getPosition() - this.position;
	}

	public double getMass() {
		return m;
	}

	public double getPosition() {
		return position;
	}

}
