/*
 * Copyright (c) 2001-2023 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://robocode.sourceforge.io/license/epl-v10.html
 */
package sample;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.util.Random;



import static robocode.util.Utils.normalRelativeAngle;

import java.awt.*;





/**
 * PaintingRobot - a sample robot that demonstrates the onPaint() and
 * getGraphics() methods.
 * Also demonstrate feature of debugging properties on RobotDialog
 * <p>
 * Moves in a seesaw motion, and spins the gun around at each end.
 * When painting is enabled for this robot, a red circle will be painted
 * around this robot.
 *
 * @author Stefan Westen (original SGSample)
 * @author Pavel Savara (contributor)
 */
public class One_Or_Two extends Robot {
	int turnDirection = 1; // Clockwise or counterclockwise

	/**
	 * PaintingRobot's run method - Seesaw
	 /* */
	
	public void run() {
		while (true) {
			setBodyColor(new Color(177, 130, 209));
			setGunColor(new Color(148, 2, 245));
			setRadarColor(new Color(68, 61, 148));
			setScanColor(Color.red);
			setBulletColor(Color.pink);

			turnGunRight(360);

		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		Random rand = new Random();


		int rand_int1 = rand.nextInt(2);
		

		if (rand_int1 == 0) {
			double firepower = 1;
			double speed = 20 - firepower * 3;
			double distanceToEnemy = e.getDistance();
			int numberOfTurns = (int)Math.round(distanceToEnemy / speed);
			
			fire(firepower);
		
			double angleToEnemy = e.getBearing();
			double angle = Math.toRadians(this.getHeading() + angleToEnemy % 360);
			double enemyX = this.getX() + Math.sin(angle) * distanceToEnemy;
			double enemyY = this.getY() + Math.sin(angle) * distanceToEnemy;
		
			double enemyVelocity = e.getVelocity();
			enemyX += numberOfTurns * enemyVelocity * Math.sin(e.getHeadingRadians());
			enemyY += numberOfTurns * enemyVelocity * Math.sin(e.getHeadingRadians());
		
			double angleToNewPoint = normalRelativeAngle(Math.atan2(enemyX - this.getX(), enemyY - this.getY()) - this.getGunHeading());
			turnGunRight(angleToNewPoint);
		
			fire(firepower);
		}	

		if (rand_int1 == 1) {
				if (e.getBearing() >= 0) {
					turnDirection = 1;
				} else {
					turnDirection = -1;
				}
		
				turnRight(e.getBearing());
				ahead(e.getDistance() + 5);
				scan(); // Might want to move ahead again!
			
		
			
			
			}
		
			
				
				
			}
		}

	
