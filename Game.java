import java.security.SecureRandom;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

class Game extends JFrame {

	private SecureRandom random = new SecureRandom();

	private final int BLOCK_WIDTH = 40;
	private final int BLOCK_HEIGHT = 30;

	private final int CIRCLE_RADIUS = 20;

	private int circleX = 500, circleY = 300;
	private int x1Position, y1Position;
	private int x2Position, y2Position;
	private int x3Position, y3Position;
	private int x1Speed, y1Speed;
	private int x2Speed, y2Speed;
	private int x3Speed, y3Speed;
	private int SPEED = 1;

	private final Color skyColor = new Color(224, 240, 249);

	// ----------------------------------
	// arrow
	protected Timer timer, timer1, timer2;
	private static final long serialVersionUID = 1L;
	protected boolean changeSide = false;
	protected int up1 = 0, up2 = 0;
	protected int tailX = 855, frontX = 795, y = 550;
	protected int tailX2 = 40, frontX2 = 90, y2 = 25;
	protected double tx = 0.01, ty = 0.01, vx, vy;
	protected double tx2 = 0.01, ty2 = 0.01, vx2, vy2;
	protected int initialX = 855, initialY = 550;
	protected int initialX2 = 40, initialY2 = 25;
	protected double angle, angle2;
	protected boolean fly = false, fly2 = false;
	
	protected boolean lose = false;
	// ----------------------------------

	Game() {
		this.getContentPane().setBackground(skyColor);
		this.setTitle("Game");
		this.setResizable(false);
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		switch (random.nextInt(3)) {
		case 0:
			x1Speed = y2Speed = x3Speed = 1;
			x2Speed = y1Speed = y3Speed = -1;
			break;
		case 1:
			x1Speed = y1Speed = x2Speed = 1;
			y2Speed = y3Speed = x3Speed = -1;
			break;
		case 2:
			x2Speed = y3Speed = x2Speed = 1;
			x1Speed = y1Speed = y2Speed = -1;
			break;
		}

		x1Position = random.nextInt(750) + 150;
		x2Position = random.nextInt(750) + 150;
		x3Position = random.nextInt(750) + 150;

		y1Position = random.nextInt(450) + 150;
		y2Position = random.nextInt(450) + 150;
		y3Position = random.nextInt(450) + 150;

		circleX = circleY = 100;

		Draw draw = new Draw();
		draw.setBackground(skyColor);
		this.add(draw, BorderLayout.CENTER);

		timer = new Timer(10, new Time());
		timer.start();

		KeyAdapter keyProcessor = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				int key = e.getKeyCode();
				switch (key) {
				case 87: // w
					if(circleY <= 30 || (circleX <= 150 && circleY <= 450)) {
						//cannot move
					}
					else {
						circleY -= 20;
					}
					break;
				case 83: // s
					if(circleY >= 560 || (circleX >= 750 && circleY >= 450)) {
						//cannot move
					}
					else {
						circleY += 20;
					}
					break;
				case 65: // a
					if(circleX <= 30 || (circleX <= 150 && circleY <= 150)) {
						//cannot move
					}
					else {
						circleX -= 20;
					}
					break;
				case 68: // d
					if(circleX >= 880 || (circleX >= 750 && circleY >= 450)) {
						//cannot move
					}
					else {
						circleX += 20;
					}
					break;
				}
				repaint();

				// ----------------------------------
				// arrow
				// choose left or right arrow
				if (e.getKeyCode() == KeyEvent.VK_R) {
					changeSide = !changeSide;
					repaint();
				}

				// right one
				if (!changeSide) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						angle = 6 * up1 * Math.PI / 180;
						fly = true;
						timer1.start();
						timer.stop();
					}

					if (!timer1.isRunning()) {
						switch (e.getKeyCode()) {

						case 38:
							up1++;
							repaint();
							break;

						case 40:
							up1--;
							repaint();
							break;

						default:
							repaint();
						}
					}
				}

				// left one
				if (changeSide) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						angle2 = 6 * up2 * Math.PI / 180;
						fly2 = true;
						timer2.start();
						timer.stop();
					}

					if (!timer2.isRunning()) {
						switch (e.getKeyCode()) {

						case 38:
							up2--;
							repaint();
							break;

						case 40:
							up2++;
							repaint();
							break;

						default:
							repaint();
						}
					}
				}
				// ----------------------------------

				repaint();
			}
		};

		this.addKeyListener(keyProcessor);
		draw.addKeyListener(keyProcessor);

		// ----------------------------------
		// arrow
		timer1 = new Timer(10, new Time());
		timer2 = new Timer(10, new Time());
		// ----------------------------------
	}

	public class Time implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int width = getWidth() - 150;
			int height = getHeight() - 150;
			x1Position += x1Speed;
			y1Position += y1Speed;

			x2Position += x2Speed;
			y2Position += y2Speed;

			x3Position += x3Speed;
			y3Position += y3Speed;

			if (x1Position < 100) {
				x1Position = 100;
				x1Speed = SPEED;
			} else if (x1Position > width - BLOCK_WIDTH) {
				x1Position = width - BLOCK_WIDTH;
				x1Speed = -SPEED;
			}
			if (y1Position < 100) {
				y1Position = 100;
				y1Speed = SPEED;
			} else if (y1Position > height - BLOCK_HEIGHT) {
				y1Position = height - BLOCK_HEIGHT;
				y1Speed = -SPEED;
			}

			if (x2Position < 100) {
				x2Position = 100;
				x2Speed = SPEED;
			} else if (x2Position > width - BLOCK_WIDTH) {
				x2Position = width - BLOCK_WIDTH;
				x2Speed = -SPEED;
			}
			if (y2Position < 100) {
				y2Position = 100;
				y2Speed = SPEED;
			} else if (y2Position > height - BLOCK_HEIGHT) {
				y2Position = height - BLOCK_HEIGHT;
				y2Speed = -SPEED;
			}

			if (x3Position < 100) {
				x3Position = 100;
				x3Speed = SPEED;
			} else if (x3Position > width - BLOCK_WIDTH) {
				x3Position = width - BLOCK_WIDTH;
				x3Speed = -SPEED;
			}
			if (y3Position < 100) {
				y3Position = 100;
				y3Speed = SPEED;
			} else if (y3Position > height - BLOCK_HEIGHT) {
				y3Position = height - BLOCK_HEIGHT;
				y3Speed = -SPEED;
			}
			repaint();

			// ----------------------------------
			// arrow
			if (fly) {
				ty += 0.08;
				tx += 0.08;
				vx = (int) (200 * Math.cos(Math.toRadians(angle)));
				vy = (int) (200 * Math.sin(Math.toRadians(angle)));
				frontX = (int) (initialX - vx * tx);
				y = (int) (initialY - vy * ty);
				repaint();
			}

			if (fly2) {
				ty2 += 0.08;
				tx2 += 0.08;
				vx2 = (int) (200 * Math.cos(Math.toRadians(angle2)));
				vy2 = (int) (200 * Math.sin(Math.toRadians(angle2)));
				frontX2 = (int) (initialX2 + vx2 * tx2);
				y2 = (int) (initialY2 + vy2 * ty2);
				repaint();
			}

			if (frontX <= 0 || y <= 0) {
				fly = false;
				timer1.stop();
				timer.start();
				ty = 0.01;
				tx = 0.01;
				frontX = 795;
				y = 550;
				repaint();
			}

			if (frontX2 >= 900 || y2 >= 600) {
				fly2 = false;
				timer2.stop();
				timer.start();
				ty2 = 0.01;
				tx2 = 0.01;
				frontX2 = 90;
				y2 = 25;
				repaint();
			}
			// ----------------------------------
		}
	}

	public class Draw extends JPanel {
		@Override
		public void paintComponent(Graphics g) {

			g.setColor(Color.BLUE);
			g.fillRoundRect(x1Position, y1Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);

			g.setColor(Color.YELLOW);
			g.fillRoundRect(x2Position, y2Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);

			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x3Position, y3Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);

			g.setColor(Color.RED);
			g.fillOval(circleX - 15, circleY - 15, CIRCLE_RADIUS, CIRCLE_RADIUS);

			// ----------------------------------
			// arrow
			g.drawOval(800, 480, 200, 230);

			g.drawOval(-100, -120, 200, 230);

			// bottom right
			AffineTransform trans = new AffineTransform();
			Graphics2D line = (Graphics2D) g;

			if (!changeSide) {
				line.setColor(Color.RED);
			} else {
				line.setColor(Color.BLACK);
			}

			trans.rotate(6 * up1 * Math.PI / 180, 880, 553);
			line.setTransform(trans);

			line.setColor(Color.BLACK);
			line.drawLine(frontX - 35, y, frontX + 65, y);
			line.drawLine(frontX - 35, y + 6, frontX + 65, y + 6);
			line.drawLine(frontX + 65, y, frontX + 65, y + 6);

			line.drawLine(frontX + 35, y, frontX + 55, y - 10);
			line.drawLine(frontX + 55, y - 10, frontX + 85, y - 10);
			line.drawLine(frontX + 65, y, frontX + 85, y - 10);

			line.drawLine(frontX + 35, y + 6, frontX + 55, y + 16);
			line.drawLine(frontX + 55, y + 16, frontX + 85, y + 16);
			line.drawLine(frontX + 65, y + 6, frontX + 85, y + 16);

			line.drawLine(frontX - 40, y + 3, frontX - 25, y - 6);
			line.drawLine(frontX - 40, y + 3, frontX - 25, y + 12);

			// top left
			AffineTransform trans2 = new AffineTransform();
			Graphics2D line2 = (Graphics2D) g;

			if (changeSide) {
				line2.setColor(Color.RED);
			} else {
				line2.setColor(Color.BLACK);
			}

			trans2.rotate(6 * up2 * Math.PI / 180, 15, 28);
			line2.setTransform(trans2);

			line2.setColor(Color.BLACK);
			line2.drawLine(frontX2 + 45, y2, frontX2 - 55, y2);
			line2.drawLine(frontX2 + 45, y2 + 6, frontX2 - 55, y2 + 6);
			line2.drawLine(frontX2 - 55, y2, frontX2 - 55, y2 + 6);

			line2.drawLine(frontX2 - 25, y2, frontX2 - 45, y2 - 10);
			line2.drawLine(frontX2 - 45, y2 - 10, frontX2 - 75, y2 - 10);
			line2.drawLine(frontX2 - 55, y2, frontX2 - 75, y2 - 10);

			line2.drawLine(frontX2 - 25, y2 + 6, frontX2 - 45, y2 + 16);
			line2.drawLine(frontX2 - 45, y2 + 16, frontX2 - 75, y2 + 16);
			line2.drawLine(frontX2 - 55, y2 + 6, frontX2 - 75, y2 + 16);

			line2.drawLine(frontX2 + 50, y2 + 3, frontX2 + 35, y2 - 6);
			line2.drawLine(frontX2 + 50, y2 + 3, frontX2 + 35, y2 + 12);
			// ----------------------------------

		}
	}

}
