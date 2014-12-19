package sqelevator.application.ui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DirectionsUIElement extends JPanel {

	private static final long serialVersionUID = 2795520478693207825L;
	private static final String ICON_UP_ACTIVE = "res/arrow-up-red-32.png";
	private static final String ICON_DOWN_ACTIVE = "res/arrow-down-red-32.png";
	private static final String ICON_UP = "res/arrow-up-grey-32.png";
	private static final String ICON_DOWN = "res/arrow-down-grey-32.png";
	private ImageIcon iiUp, iiDown, iiUpActive, iiDownActive;
	private JLabel up, down;
	
	
	public DirectionsUIElement() {
		iiUp = new ImageIcon(getClass().getClassLoader().getResource(ICON_UP));
		iiDown = new ImageIcon(getClass().getClassLoader().getResource(ICON_DOWN));
		iiUpActive = new ImageIcon(getClass().getClassLoader().getResource(ICON_UP_ACTIVE));
		iiDownActive = new ImageIcon(getClass().getClassLoader().getResource(ICON_DOWN_ACTIVE));
		
		up = new JLabel(iiUp);
		down = new JLabel(iiDown);
		
		//layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(up);
		add(down);
	}
	
	public void setUpDirection(boolean value) {
		if(value)
			up.setIcon(iiUpActive);
		else
			up.setIcon(iiUp);
	}
	
	public void setDownDirection(boolean value) {
		if(value)
			down.setIcon(iiDownActive);
		else
			down.setIcon(iiDown);
	}
	
}
