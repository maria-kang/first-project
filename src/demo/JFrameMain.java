package demo;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entities.Product;
import model.ProductModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

public class JFrameMain {

	private JFrame frame;
	final JPanel constPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMain window = new JFrameMain();
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
	public JFrameMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		constPanel.setBounds(0, 0, 884, 761);
		
		constPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(constPanel);
		constPanel.setLayout(null);
		
		frame.setSize(276, 253);
		frame.setLocationRelativeTo(null); //가운데 오픈
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
			
		
			System.out.println("this =============" + this);
			
			
			
			System.out.println("=============" + new JRViewer(filledReport));
			frame.pack();
			
		}catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}
		
		
	}
	
	
	

}
