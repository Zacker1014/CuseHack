import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class Arrow  extends JComponent implements KeyListener{

	protected boolean rotateUp1 = false;
	protected boolean rotateUp2 = false;
	protected boolean change = false;
	protected int up1 = 0, up2 = 0;
	protected boolean origin = true;
	
	
	//constructor
	public Arrow(){
		super();
		addKeyListener(this);
		setFocusable(true);
	}
	
	
	
	//paint component
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawOval(800, 480, 200, 230);
		
		g.drawOval(-100, -120, 200, 230);
		
		if(origin) {
			//bottom right
			g.setColor(Color.BLACK);
			g.drawLine(760, 550, 860, 550);
			g.drawLine(760, 556, 860, 556);
			g.drawLine(860, 550, 860, 556);
			
			g.drawLine(830, 550, 850, 540);
			g.drawLine(850, 540, 880, 540);
			g.drawLine(860, 550, 880, 540);
			
			g.drawLine(830, 556, 850, 566);
			g.drawLine(850, 566, 880, 566);
			g.drawLine(860, 556, 880, 566);
			
			g.drawLine(755, 553, 770, 544);
			g.drawLine(755, 553, 770, 562);
			
			//-------------------
			//top left
			g.drawLine(135, 25, 35, 25);
			g.drawLine(135, 31, 35, 31);
			g.drawLine(35, 25, 35, 31);
			
			g.drawLine(65, 25, 45, 15);
			g.drawLine(45, 15, 15, 15);
			g.drawLine(35, 25, 15, 15);
			
			g.drawLine(65, 31, 45, 41);
			g.drawLine(45, 41, 15, 41);
			g.drawLine(35, 31, 15, 41);
			
			g.drawLine(140, 28, 125, 19);
			g.drawLine(140, 28, 125, 37);
			
			
		}
		
		
		AffineTransform trans = new AffineTransform();
		Graphics2D line = (Graphics2D)g;
		if(rotateUp1) {
			trans.rotate(5 * up1 * Math.PI / 180, 880, 553);	
			line.setTransform(trans);

			line.setColor(Color.BLACK);
			line.drawLine(760, 550, 860, 550);
			line.drawLine(760, 556, 860, 556);
			line.drawLine(860, 550, 860, 556);
			
			line.drawLine(830, 550, 850, 540);
			line.drawLine(850, 540, 880, 540);
			line.drawLine(860, 550, 880, 540);
			
			line.drawLine(830, 556, 850, 566);
			line.drawLine(850, 566, 880, 566);
			line.drawLine(860, 556, 880, 566);
			
			line.drawLine(755, 553, 770, 544);
			line.drawLine(755, 553, 770, 562);
			
			rotateUp1 = false;
		}
		
		
		if(rotateUp2) {
			trans.rotate(5 * up2 * Math.PI / 180, 15, 28);	
			line.setTransform(trans);

			line.setColor(Color.BLACK);
			line.drawLine(135, 25, 35, 25);
			line.drawLine(135, 31, 35, 31);
			line.drawLine(35, 25, 35, 31);
			
			line.drawLine(65, 25, 45, 15);
			line.drawLine(45, 15, 15, 15);
			line.drawLine(35, 25, 15, 15);
			
			line.drawLine(65, 31, 45, 41);
			line.drawLine(45, 41, 15, 41);
			line.drawLine(35, 31, 15, 41);
			
			line.drawLine(140, 28, 125, 19);
			line.drawLine(140, 28, 125, 37);
			
			rotateUp2 = false;
		}
		
		
	}
	
	


	@Override
	public void keyPressed(KeyEvent e) {
		
		//choose left or right arrow
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(change) {
				change = false;
			}
			else {
				change = true;
			}
			
		}
		
		if(!change) {
			switch (e.getKeyCode()) {
			
			case 38:
				origin = false;
				up1++;
				rotateUp1 = true;
				repaint();
				break;
			
			case 40:
				origin = false;
				up1--;
				rotateUp1 = true;
				repaint();
				break;
				
			default: 
				rotateUp1 = true;
				repaint();
			}
		}
		
		
		if(change) {
			switch (e.getKeyCode()) {
			
			case 38:
				origin = false;
				up2--;
				rotateUp2 = true;
				repaint();
				break;
			
			case 40:
				origin = false;
				up2++;
				rotateUp2 = true;
				repaint();
				break;
				
			default: 
				rotateUp2 = true;
				repaint();
			}
		}
		
		repaint();
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
