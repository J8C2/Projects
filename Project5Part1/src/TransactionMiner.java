import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.*;
import javax.swing.event.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

@SuppressWarnings("rawtypes")
public class TransactionMiner extends JPanel
{

private static final long serialVersionUID = 1L;
private JSlider js1;
private JSlider js2;
private static PieDataset dataset;
private static ArrayList<Blocks> block = Blocks.getBlocks();
private static JFreeChart chart;
private static ChartPanel panel;

	public TransactionMiner()
	{ 
		new JPanel();
		this.setBounds(0, 0, 1650, 1080);
		this.setLayout(null);
		this.setVisible(true);
		
		js1 = new JSlider();
		js1.setBounds(250, 100, 450, 50);
		js1.setMinorTickSpacing(5);
		js1.setPaintTicks(true);
		js1.setPaintTrack(true);
		js1.setMajorTickSpacing(25);
		js1.setPaintLabels(true);
		js1.setMinimum(15049308);
		js1.setMaximum(15049407);
		this.add(js1);
		
		js2 = new JSlider();
		js2.setBounds(900, 100, 450, 50);
		js2.setMinorTickSpacing(5);
		js2.setPaintTicks(true);
		js2.setPaintTrack(true);
		js2.setMajorTickSpacing(50);
		js2.setPaintLabels(true);
		js2.setMinimum(15049309);
		js2.setMaximum(15049408);
		this.add(js2);
		
	    dataset = createDataset();
		chart = ChartFactory.createPieChart("Each Unique Miner and Frequency", dataset, true, true, false);
		chart.clearSubtitles();
		panel = new ChartPanel(chart);
		panel.setBounds(375, 150, 800, 600);
		panel.setVisible(true);
		this.add(panel);
	}
	
	@SuppressWarnings("removal")
	private static DefaultPieDataset createDataset()
	{
	DefaultPieDataset temp = new DefaultPieDataset();
	int i = 16;
	while (i < Blocks.uniqMiners.size())
	{ 
		temp.setValue(Blocks.uniqMiners.get(i) +": " + Integer.toString(Blocks.uniqMinersFreq.get(i)), new Double((Blocks.uniqMinersFreq.get(i))));
		i++;
	}
	return temp;
	}
}


