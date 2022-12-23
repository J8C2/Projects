import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class TransactionBlock extends JPanel implements ActionListener
{	
private static final long serialVersionUID = 1L;
private static JButton jb;
private static JTextField jtx1;
private static JTextField jtx2;
private static JFreeChart chart;
private static long text1;
private static long text2;
private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
private static ArrayList<Blocks> block = Blocks.getBlocks();
private static Blocks b;
private static Blocks c;

public TransactionBlock()
{ 
	new JPanel();
	this.setBounds(0, 0, 1650, 1080);
	this.setLayout(null);
	this.setVisible(true);
	
	jb = new JButton("Add");
	jb.setVisible(true);
	jb.setBounds(300, 300, 60, 50);
	jb.addActionListener(this);
	this.add(jb);
	
	jtx1 = new JTextField();
	jtx1.setVisible(true);
	jtx1.setBounds(20, 300, 125, 50);
	jtx1.addActionListener(this);
	this.add(jtx1);
	
	
	jtx2 = new JTextField();
	jtx2.setVisible(true);
	jtx2.setBounds(150, 300, 125, 50);
	jtx2.addActionListener(this);
	this.add(jtx2);
	
	chart = ChartFactory.createBarChart("Block Bar Graph", "Blocks", "Time Units", dataset,
			PlotOrientation.VERTICAL, true, true, false);
	chart.setBorderPaint(Color.black);
	ChartPanel panel = new ChartPanel(chart);
	panel.setBounds(400, 100, 1000, 600);
	this.add(panel);
	panel.setVisible(true);
	
	
}

/**private CategoryDataset createDataset()
{ 
	int sec = 0;
	int min = 0;
	int hours = 0;
	block = Blocks.getBlocks();
	
	
	return dataset;
}
**/
@Override
public void actionPerformed(ActionEvent e) 
{
	if (e.getSource() == jb)
	{ 
		int sec = (int) Math.abs(text1 - text2);;
		int min = sec / 60;
		int hour = min / 60;
		sec = sec % 60;
		min = min % 60;
	
		dataset.addValue(hour, "hour", jtx1.getText() + "-" + jtx2.getText());
		dataset.addValue(min, "minutes", jtx1.getText() + "-" + jtx2.getText());
		dataset.addValue(sec, "seconds", jtx1.getText() + "-" + jtx2.getText());
	}
	if (e.getSource() == jtx1)
	{ 
		try {
			 b = Blocks.getBlockByNumber(Integer.parseInt(jtx1.getText()));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		text1 = b.getTimeStamp();
	}
	if (e.getSource() == jtx2)
	{ 
		try {
			c = Blocks.getBlockByNumber(Integer.parseInt(jtx2.getText()));
		} catch (NumberFormatException | IOException e1)
		{
			e1.printStackTrace();
		}
		text2 = c.getTimeStamp();
	}
}
	

}
