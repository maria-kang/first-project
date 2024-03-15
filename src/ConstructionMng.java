import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class ConstructionMng {

	private JFrame frame;
	private JTextField edt_id;
	private JPasswordField edt_ps;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel headTitlepanel;
	private JLabel lblNewLabel_3;
	private JPanel searchPanel;
	private JTextField edt_coName;
	private JTextField edt_consName;
	private JTextField edt_mngName;
	private JPanel panel_1;
	private JLabel lblNewLabel_4;
	private JPanel panel;
	private JLabel lblNewLabel_5;
	private JPanel panel_2;
	private JLabel lblNewLabel_6;
	private JButton btn_search;
	private JButton btn_print;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConstructionMng window = new ConstructionMng();
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
	public ConstructionMng() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final Construction construction = new Construction();
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		final JPanel constPanel = new JPanel();
		constPanel.setBackground(new Color(255, 255, 255));
		constPanel.setBounds(0, 0, 884, 761);
		frame.getContentPane().add(constPanel);
		constPanel.setVisible(false);
		constPanel.setLayout(null);
		
		headTitlepanel = new JPanel();
		headTitlepanel.setBounds(0, 0, 884, 34);
		headTitlepanel.setBackground(new Color(135, 206, 235));
		constPanel.add(headTitlepanel);
		headTitlepanel.setLayout(null);
		
		lblNewLabel_3 = new JLabel(" 현장목록");
		lblNewLabel_3.setBounds(0, 5, 125, 30);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		headTitlepanel.add(lblNewLabel_3);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 35, 884, 42);
		searchPanel.setBackground(SystemColor.menu);
		constPanel.add(searchPanel);
		searchPanel.setLayout(null);
		
		edt_coName = new JTextField();
		edt_coName.setBounds(94, 10, 116, 24);
		edt_coName.setFont(new Font("굴림", Font.PLAIN, 16));
		searchPanel.add(edt_coName);
		edt_coName.setColumns(10);
		
		edt_consName = new JTextField();
		edt_consName.setBounds(355, 10, 116, 24);
		edt_consName.setFont(new Font("굴림", Font.PLAIN, 16));
		edt_consName.setColumns(10);
		searchPanel.add(edt_consName);
		
		edt_mngName = new JTextField();
		edt_mngName.setBounds(606, 10, 116, 24);
		edt_mngName.setFont(new Font("굴림", Font.PLAIN, 16));
		edt_mngName.setColumns(10);
		searchPanel.add(edt_mngName);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 80, 24);
		panel_1.setBackground(SystemColor.activeCaption);
		searchPanel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_4 = new JLabel("회사명");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_4.setBounds(0, 0, 80, 24);
		panel_1.add(lblNewLabel_4);
		
		panel = new JPanel();
		panel.setBounds(270, 10, 80, 24);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		searchPanel.add(panel);
		
		lblNewLabel_5 = new JLabel("현장명");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_5.setBounds(0, 0, 80, 24);
		panel.add(lblNewLabel_5);
		
		panel_2 = new JPanel();
		panel_2.setBounds(523, 10, 80, 24);
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.activeCaption);
		searchPanel.add(panel_2);
		
		lblNewLabel_6 = new JLabel("담당자명");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_6.setBounds(0, 0, 80, 24);
		panel_2.add(lblNewLabel_6);
		
		btn_search = new JButton("조회");
		btn_search.setBounds(775, 10, 70, 24);
		btn_search.setForeground(SystemColor.desktop);
		btn_search.setFont(new Font("굴림", Font.BOLD, 16));
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String coName = edt_coName.getText();
				
				System.out.println("edt_coName==" + coName );
				
				construction.getConstructionList();
			}
		});
		btn_search.setBackground(SystemColor.textHighlight);
		searchPanel.add(btn_search);
		
		btn_print = new JButton("출력");
		btn_print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//JasperReport jasperReport = JasperCompileManager.compileReport("./DSW_1.jrxml");
					Connection conn = getConnection();
					
			        HashMap params = new HashMap();
			        params.put("aapor", 19);
			        System.out.println ("jasperPrint1==0000");
			        
			        JasperReport jasperReport1 = JasperCompileManager.compileReport("C:\\jasperreport\\DSW_3.jrxml");
			        System.out.println ("jasperPrint1==1111");
			        JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport1, params, conn);
			        
			        System.out.println ("jasperPrint1==2222");
			        //constPanel.add(new JRViewer (jasperPrint1) );
			        //searchPanel.add(new JRViewer (jasperPrint1) );
			        //frame.pack();
			        //JRViewer viewer = new JRViewer(jasperPrint1);
			        
			        String userDirectoryPath = System.getProperty("user.dir");

					System.out.println("Current Directory = \"" + userDirectoryPath + "\"" );
					
			        
			        constPanel.removeAll();
			        constPanel.setLayout(new BorderLayout());
			        constPanel.repaint();
			        constPanel.add(new JRViewer(jasperPrint1));
			        constPanel.revalidate();
			        
			        
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("오류 발생.");
					System.out.println(e1.getMessage());
				}
				
				
			}
		});
		btn_print.setBounds(848, 12, 70, 24);
		btn_print.setForeground(SystemColor.desktop);
		btn_print.setFont(new Font("굴림", Font.BOLD, 16));
		btn_print.setBackground(SystemColor.textHighlight);
		searchPanel.add(btn_print);
		//frame.setBounds(100, 100, 450, 300);
		final ImagePanel welcomPanel = new ImagePanel (new ImageIcon("./image/logIn.jpg").getImage());
		welcomPanel.setBounds(0, 0, 884, 761);
		frame.getContentPane().add(welcomPanel);
		welcomPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("로그인");
		lblNewLabel.setBounds(455, 377, 127, 40);
		lblNewLabel.setFont(new Font("돋움체", Font.BOLD, 24));
		welcomPanel.add(lblNewLabel);
		
		edt_id = new JTextField();
		edt_id.setBounds(455, 427, 121, 32);
		edt_id.setFont(new Font("굴림", Font.PLAIN, 24));
		edt_id.setToolTipText("ID");
		welcomPanel.add(edt_id);
		edt_id.setColumns(10);
		
		edt_ps = new JPasswordField();
		edt_ps.setBounds(455, 463, 121, 32);
		edt_ps.setFont(new Font("굴림", Font.PLAIN, 24));
		welcomPanel.add(edt_ps);
		
		lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(368, 427, 40, 32);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 24));
		welcomPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("PS:");
		lblNewLabel_2.setBounds(368, 463, 40, 32);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 24));
		welcomPanel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(431, 530, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("클릭...............");
				System.out.println(edt_id.getText());
				System.out.println(edt_ps.getPassword());
				
				if("aaa".equals(edt_id.getText() )) {
					
					System.out.println("id  존재 ");
				}
				
				
				if (Arrays.equals(edt_ps.getPassword() , "1111".toCharArray())) {
					System.out.println("pass  존재 ");
				}
				
				if ("aaaa".equals(edt_id.getText() ) &&  Arrays.equals(edt_ps.getPassword() , "1111".toCharArray()) ) {
					
					welcomPanel.setVisible(false);
					constPanel.setVisible(true);
				} else {
					
				    JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.");	
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\workspace_jh\\JFrame_jh\\image\\Login.png"));
		welcomPanel.add(btnNewButton);
		frame.setSize(900, 800);
		frame.setLocationRelativeTo(null); //가운데 오픈
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	


	public static Connection getConnection() {
		
		try {
            // MariaDB 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            
			// MariaDB JDBC URL - 연결할 데이터베이스의 URL을 입력하세요.
	        String jdbcUrl = "jdbc:mysql://192.168.45.183:3306/dsw";
	        String username = "dsw0311";
	        String password = "1234";
	        
            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            if (connection != null) {
                System.out.println("MariaDB에 성공적으로 연결되었습니다.");
                // 여기에서 데이터베이스 작업을 수행할 수 있습니다.
                // 예를 들어, 쿼리 실행 등의 작업을 수행할 수 있습니다.

                // 연결을 닫습니다.
                
            } else {
                System.out.println("MariaDB 연결에 실패했습니다.");
            }
            
            return connection;
			
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
		
	}
}

/*
class ImagePanel extends JPanel {
	
	private Image img;
	
	public ImagePanel (Image img) {
		
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0,0,null);
	}
	
}
*/