import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Arrow  extends JComponent implements KeyListener{

	protected Timer timer,timer2;
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
	
	
	
	//constructor
	public Arrow(){
		super();
		addKeyListener(this);
		setFocusable(true);
		
		timer = new Timer(10, new TimerCallback());
		timer2 = new Timer(10, new TimerCallback());
	}
	
	
	
	//paint component
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawOval(800, 480, 200, 230);
		
		g.drawOval(-100, -120, 200, 230);
		
	
		//bottom right
		AffineTransform trans = new AffineTransform();
		Graphics2D line = (Graphics2D) g;

		trans.rotate(6 * up1 * Math.PI / 180, 880, 553);
		line.setTransform(trans);

		line.setColor(Color.BLACK);
		line.drawLine(frontX- 35, y, frontX+ 65, y);
		line.drawLine(frontX- 35, y + 6, frontX+ 65, y + 6);
		line.drawLine(frontX+ 65, y, frontX+ 65, y + 6);

		line.drawLine(frontX+ 35, y, frontX+ 55, y - 10);
		line.drawLine(frontX+ 55, y - 10, frontX+ 85, y - 10);
		line.drawLine(frontX+ 65, y, frontX+ 85, y - 10);

		line.drawLine(frontX+ 35, y + 6, frontX+ 55, y + 16);
		line.drawLine(frontX+ 55, y + 16, frontX+ 85, y + 16);
		line.drawLine(frontX+ 65, y + 6, frontX+ 85, y + 16);

		line.drawLine(frontX- 40, y + 3, frontX- 25, y - 6);
		line.drawLine(frontX- 40, y + 3, frontX- 25, y + 12);

		//-------------------------------
		
		//top left
		AffineTransform trans2 = new AffineTransform();
		Graphics2D line2 = (Graphics2D) g;
		
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

		
		
	}
	
	

	//key pressed
	@Override
	public void keyPressed(KeyEvent e) {
		
		//choose left or right arrow
		if(e.getKeyCode() == KeyEvent.VK_R) {
			changeSide = !changeSide;
			repaint();
		}
		
		
		
		//right one
		if(!changeSide) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				angle = 6 * up1 * Math.PI / 180;
				fly = true;
				timer.start();
			}
			
			if(!timer.isRunning()) {
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
		
		//left one
		if(changeSide) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				angle2 = 6 * up2 * Math.PI / 180;
				fly2 = true;
				timer2.start();
			}
			
			if(!timer2.isRunning()) {
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
		
		repaint();
	}

	
	
	protected class TimerCallback implements ActionListener{ //class TimerCallback

		@Override
		public void actionPerformed(ActionEvent e) {
			if(fly) {
				ty += 0.05;
				tx += 0.05;
				vx = (int) (200 * Math.cos(Math.toRadians(angle)));
				vy = (int) (200 * Math.sin(Math.toRadians(angle)));
				frontX = (int) (initialX - vx * tx);
				y = (int) (initialY - vy * ty);
				repaint();
			}
			
			if(fly2) {
				ty2 += 0.05;
				tx2 += 0.05;
				vx2 = (int) (200 * Math.cos(Math.toRadians(angle2)));
				vy2 = (int) (200 * Math.sin(Math.toRadians(angle2)));
				frontX2 = (int) (initialX2 + vx2 * tx2);
				y2 = (int) (initialY2 + vy2 * ty2);
				repaint();
			}
			
			if(frontX <= 0 || y <= 0) {
				fly = false;
				timer.stop();
				ty = 0.01;
				tx = 0.01;
				frontX = 795;
				y = 550;
				repaint();
			}
			
			if(frontX2 >= 900 || y2 >= 600) {
				fly2 = false;
				timer2.stop();
				ty2 = 0.01;
				tx2 = 0.01;
				frontX2 = 90;
				y2 = 25;
				repaint();
			}
			
			
		}
	
		
	}
	
	
	
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
