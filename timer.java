import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
public class timer extends JFrame{
	
	int secounds = -1;
	int minutes = 0;
	int hours = 0;	
	
	Timer t;
	
	JButton butPlay, butPause, butReset;
	
	JLabel theLabel;

	public timer() {
		this.setSize(100,180);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		timer1();
		this.setVisible(true);
	}

	private void timer1() {
		JPanel thePanel = new JPanel();
		
		theLabel = new JLabel(currentTime());		
		theLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                theLabel.setText(currentTime());
            }
        };
        t = new Timer(1000, taskPerformer);
        
        thePanel.add(theLabel);	
        
        ListenForButton lForBut = new ListenForButton();
        butPlay = new JButton("Start");   
        butPlay.setPreferredSize(new Dimension(70, 30));
        butPlay.addActionListener(lForBut);
        thePanel.add(butPlay);
		
        butPause = new JButton("Pause");
        butPause.setPreferredSize(new Dimension(70, 30));
        butPause.addActionListener(lForBut);
        thePanel.add(butPause);
        
        butReset = new JButton("Reset");
        butReset.setPreferredSize(new Dimension(70, 30));
        butReset.addActionListener(lForBut);
        thePanel.add(butReset);
		
		this.add(thePanel);
	}
	
	private String currentTime() {
		
		if(secounds < 60) secounds++;
		else {
			secounds = 0;
			if(minutes < 60) minutes++;
			else {
				minutes = 0;
				if(hours < 24) hours++;
				else hours = 0;
			}
		}
		return checkTime(hours) + ":" + checkTime(minutes) + ":" + checkTime(secounds);
    }

	private String checkTime(int t) {
        String time1;
        if (t < 10) {
            time1 = ("0" + t);
        } else {
            time1 = ("" + t);
        }
        return time1;
    }
    
	private class ListenForButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == butPlay) t.start();
			if(e.getSource() == butPause) t.stop();
			if(e.getSource() == butReset) {
				t.restart();
				t.stop();
				secounds = -1;
				minutes = 0;
				hours = 0;		
				theLabel.setText(currentTime());
			}
		}
    	
	}
}

