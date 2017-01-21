/* E/13/181*/

import java.lang.*;
import java.util.*;

public class Ball {

	private static double clock = 0.0; //the universal time
	private double x;	//the x coordinate
	private double y;	//the y coordinate
	private double speed;	//the speed
	private double angle;	//the angle
	private static ArrayList<Ball> balls = new ArrayList<Ball>();		//the number of balls
	private static final double eps = 0.0001;	//the tolerance
	private static int num = 0;	//the number of balls

	public Ball(double x, double y, double speed, double angle) {
		this.x = x; //set the x coordinate
		this.y = y;	//set the y coordinate
		this.speed = speed;	//set the speed
		this.angle = angle;	//set the angle
		balls.add(this);	//add the ball to the arraylist
		num++;	//increment the number of balls
	}

	public static void updateTime(double time) {
		clock += time;	//increase the times
		for (int i = 0; i < num; i++) {
			balls.get(i).x += time * balls.get(i).speed * Math.cos(balls.get(i).angle);	//update the x coordinate
			balls.get(i).y += time * balls.get(i).speed * Math.sin(balls.get(i).angle);	//update the y coordinate
		}
	}

	public static void showAll() {
		for (int i = 0; i < num; i++) {
			System.out.printf("Ball%d : (%.1f %.1f)\n", i + 1, balls.get(i).x, balls.get(i).y);	//print the x and y coordinates
		}
	}

	public Boolean willCollide(Ball ball) {

		double t1 = (this.x - ball.x) / (ball.speed * Math.cos(ball.angle) - this.speed * Math.cos(this.angle));	// the time it would take for the x coordinates to be equal
		double t2 = (this.y - ball.y) / (ball.speed * Math.sin(ball.angle) - this.speed * Math.sin(this.angle));	// the time it would take for the y coordinates to be equal
		double d1 = ball.speed * Math.cos(ball.angle) - this.speed * Math.cos(this.angle);
		double d2 = ball.speed * Math.sin(ball.angle) - this.speed * Math.sin(this.angle);
		if (d1 <= eps || d2 <= eps) {	//catch the instance when the atleast one of the denominators are zero
			double T1 = (this.x - ball.x);
			double T2 = (this.y - ball.y);
			if (T1 <= eps && T2 <= eps) return true;
		} else if (Math.abs(t1 - t2) <= eps) return true;	//return true if t1 and t2 are equal(within tolerance)
		return false;	//else return false
	}


	public static void main(String [] args) {

		Ball b1 = new Ball(0.0, 0.0, 2.0, 0.0);
		Ball b2 = new Ball(10.0, 0.0, 1.0, 0.0);
		Ball b3 = new Ball(20.0, 0.0, 10.0, 0.0);
		Ball.updateTime(10.0);
		if (b2.willCollide(b1))
			System.out.println("B1 and B2 will collide");
		if (b3.willCollide(b1))
			System.out.println("B1 and B3 will collide");
		if (b3.willCollide(b2))
			System.out.println("B2 and B3 will collide");

	}
}