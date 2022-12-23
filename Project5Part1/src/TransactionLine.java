import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;



public class TransactionLine extends JPanel implements ActionListener
{
 
	private static final long serialVersionUID = 1L;
	private static ArrayList<Blocks> transaction = Blocks.getBlocks();
	private static JCheckBox jcb1;
	private static JCheckBox jcb2;
	private static JCheckBox jcb3;
	private static DefaultCategoryDataset dataset;
	private static ChartPanel panel;
  	
	public TransactionLine()
	{
		new JPanel();
		// lower panel size 
		this.setBounds(0, 0, 1650, 1080);
		this.setLayout(null);

	    jcb1 = new JCheckBox();
		jcb1.setVisible(true);
		jcb1.setText("Average Transaction Cost");
		jcb1.setEnabled(true);
		jcb1.setSelected(true);
		jcb1.addActionListener(this);
		this.add(jcb1);
	
	
		jcb2 = new JCheckBox();
		jcb2.setVisible(true);
		jcb2.setText("Lowest Transaction Cost");
		jcb2.setEnabled(true);
		jcb2.setSelected(true);
		jcb2.addActionListener(this);
		this.add(jcb2);
	
		jcb3 = new JCheckBox();
		jcb3.setVisible(true);
		jcb3.setText("Highest Transaction Cost");
		jcb3.setEnabled(true);
		jcb3.setSelected(true);
		jcb3.addActionListener(this);
		this.add(jcb3);
		
		jcb1.setBounds(380, 80, 200, 40);
		jcb2.setBounds(650, 80, 200, 40);
		jcb3.setBounds(900, 80, 200, 40);
     
	  DefaultCategoryDataset dataset = createDataSet();
	  JFreeChart chart = ChartFactory.createLineChart("Transaction Cost of Blocks", "Block No.", "Transaction Cost (ETH)", dataset);
	  chart.setBorderPaint(Color.black);
	  panel = new ChartPanel(chart);
	  panel.setLayout(null);
	  panel.setBounds(210, 140, 1100, 600);
	  this.add(panel);
	  panel.setVisible(true);
	}

	private DefaultCategoryDataset createDataSet()
	{ 
		String series1 = "Avg Trans Cost";
		String series2 = "Low Trans Cost";
		String series3 = "High Trans Cost";
	    dataset = new DefaultCategoryDataset();
		
		int count = 0;
		for (Blocks b : transaction)
		{ 
			double high = 0.0;
			double low = 0.0;
			double average = 0.0;
			ArrayList<Transaction> temp = b.getTransactions();
			if (b.getNumber() >= 15049308 && b.getNumber() <= 15049322)
			{ 
				if (temp.get(count).transactionCost() > high)
				{ 
					high = temp.get(count).transactionCost();
				}
				if (temp.get(count).transactionCost() < low)
				{ 
					low = temp.get(count).transactionCost();
				}
	        average += temp.get(count).transactionCost() / 2;
			dataset.addValue(average, series1, Integer.toString(b.getNumber()));
			dataset.addValue(low, series2, Integer.toString(b.getNumber()));
			dataset.addValue(high, series3, Integer.toString(b.getNumber()));
			}
			++count;
		}
	
		return dataset;
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		DefaultCategoryDataset temp = new DefaultCategoryDataset();
        temp = dataset;
		if (e.getSource() == jcb1)
		{ 
		temp.removeRow(0);
		}
		else if (e.getSource() == jcb2)
		{ 
		temp.removeRow(1);
		}
		else if(e.getSource() == jcb3)
		{ 
		temp.removeRow(2);
		}
	}

	

}
