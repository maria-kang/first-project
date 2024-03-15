package demo;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Product;
import model.ProductModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TEST {

	private JFrame frame;
	private JPanel panel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEST window = new TEST();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TEST() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(172, 40, 57, 15);
		panel.add(lblNewLabel);
		
		
		ShowReport();
	}


	private void ShowReport() {
		
		try {
			ProductModel productModel = new ProductModel();
			List<Map<String, ?>> dataSource = new ArrayList<Map<String,?>>();
			
			for(Product product : productModel.findAll()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", product.getId());
				m.put("name", product.getName());
				m.put("price", product.getPrice());
				m.put("quantitiy", product.getQuantity());
				
				dataSource.add(m);
			
			}
			
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			String sourceName = "C:\\workspace_jh\\JFrame_jh\\src\\demo\\ProductReport.jrxml";
			
			JasperReport report = JasperCompileManager.compileReport(sourceName);
			JasperPrint filledReport = JasperFillManager.fillReport(report, null,jrDataSource );
			
			
			panel.removeAll();
			panel.setLayout(new BorderLayout());
			panel.repaint();
			panel.add(new JRViewer(filledReport));
			panel.revalidate();
			
			//panel.add(new JRViewer(filledReport));
		
			//System.out.println("this =============" + this);
			
			
			
			//System.out.println("=============" + new JRViewer(filledReport));
			//frame.pack();
			
		}catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		
		
	}
	
	
}
