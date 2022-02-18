package app_general;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

final class JGradientButton extends JButton{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JGradientButton(String text){
        super(text);
        setContentAreaFilled(false);
    }

	Color newColor = new Color(220,0,255);
	
    @Override
    protected void paintComponent(Graphics g){
//        Graphics2D g2 = (Graphics2D)g.create();
//        g2.setPaint(new GradientPaint(
//                new Point(0, 0), 
//                getBackground(), 
//                new Point(0, getHeight()/3), 
//                Color.GRAY));
//        g2.fillRect(0, 0, getWidth(), getHeight()/3);
//        g2.setPaint(new GradientPaint(
//                new Point(0, getHeight()/3), 
//                Color.GRAY, 
//                new Point(0, getHeight()), 
//                getBackground()));
//        g2.fillRect(0, getHeight()/3, getWidth(), getHeight());
//        g2.dispose();
//
//        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                Color.WHITE, 
                new Point(0, getHeight()), 
                newColor.darker()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}