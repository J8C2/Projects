import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Graphs extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JComboBox combox;
	TransactionLine tl = new TransactionLine();
	TransactionBlock tb = new TransactionBlock();
	TransactionMiner tm = new TransactionMiner();
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{ 
		Blocks.readFile("ethereumP1data.csv");
		Blocks.sortBlocksByNumber();
		Blocks.calUniqMiners();
		new Graphs();
	}
		

    public Graphs()
    { 
    	new JFrame();
    	this.setTitle("Jace Blackwell - My project");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(1650, 1080);
    	this.setResizable(true);
    	this.setLocationRelativeTo(null);
    
    	panel = new JPanel();
    	panel.setBounds(0, 0, 1650, 1080);
    	panel.setVisible(true);
    	panel.setLayout(null);
    
    	//panel1
    	tb.setVisible(false);
    	this.add(tb);
   
    	tl.setVisible(false);
    	this.add(tl);

    	tm.setVisible(false);
    	this.add(tm);
 
    	
    	String[] pages = {"--select chart--", "Unique Miners", "Transaction Cost", "Time Difference"};
    	combox = new JComboBox(pages);
    	combox.setBounds(650, 20, 150, 60);
        this.add(combox);
    	this.add(panel);
    	combox.setEditable(true);
    	combox.addActionListener(this);
    	this.setVisible(true);
    	combox.setVisible(true);
    }
    

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == combox)
    	{ 
    		switch (combox.getSelectedItem().toString())
    		{
    			case "Unique Miners": 
    				panel.setVisible(false);
    				tm.setVisible(true);
    				tb.setVisible(false);
    				tl.setVisible(false);
    				break;
    		
    			case "Transaction Cost":
    				tm.setVisible(false);
    				panel.setVisible(false);
    				tl.setVisible(true);
    				tb.setVisible(false);
    				break;
    		
    			case "Time Difference":
    				tm.setVisible(false);
    				tl.setVisible(false);
    				panel.setVisible(false);
    				tb.setVisible(true);
    				break;

    		}
		
	}	
	}
	
}
