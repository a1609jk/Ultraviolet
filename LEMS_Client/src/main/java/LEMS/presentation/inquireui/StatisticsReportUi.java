package LEMS.presentation.inquireui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import LEMS.businesslogic.inquirebl.inquirebusinesslist.InquireBusinessList;
import LEMS.presentation.LoginUi;
import LEMS.presentation.MainFrame;
import LEMS.presentation.Table;
import LEMS.vo.financevo.PayBillVO;
import LEMS.vo.inquirevo.BusinessListVO;
import LEMS.vo.financevo.IncomeBillVO;

/**
 * @author 苏琰梓
 * 统计报表界面
 * 2015年11月25日
 */
public class StatisticsReportUi extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	private JLabel title;
	private JLabel labelDate1;
	private JLabel labelDate2;
	private JLabel name;
	private JLabel statue;	
	private JLabel subtitle1;
	private JLabel subtitle2;
	private JTextField textDate1;
	private JTextField textDate2;
	private JButton but;
	private JButton butOut;
	
	private Font font;
	private Font subfont;
	
	Table table1;
	Table table2;
	
	public StatisticsReportUi(final MainFrame mainFrame) {
		this.setBounds(0, 0, MainFrame.JFRAME_WIDTH-288, MainFrame.JFRAME_HEIGHT);
		this.setLayout(null);
		this.mainFrame = mainFrame;
		this.init();
		this.initComponent();
		this.addListener();
	}

	private void init() {
		title = new JLabel("查看统计分析");
		labelDate1 = new JLabel("日期：");
		labelDate2 = new JLabel("至");
		name = new JLabel("账号：   ");
		statue = new JLabel("身份：    ");
		textDate1 = new JTextField();
		textDate2 = new JTextField();
		font = new Font("Courier", Font.PLAIN, 26);
		subfont = new Font("Dialog", Font.PLAIN, 18);
		but = new JButton("查看");
		subtitle1 = new JLabel("付款单信息");
		subtitle2 = new JLabel("收款单信息");
		butOut = new JButton("登出");
	}
	
	private void initComponent(){
		int change = 20;
		
		title.setBounds(424,26,249,45);
		title.setFont(font);
		name.setBounds(800,25,135,28);
		statue.setBounds(800,60,183,28);
		but.setBounds(740,119-change,120,30);
		labelDate1.setBounds(217,122-change,80,25);
		labelDate2.setBounds(490,122-change,80,25);
		textDate1.setBounds(290,122-change,160,25);
		textDate2.setBounds(540,122-change,160,25);
		subtitle1.setBounds(456,156-change,169,39);
		subtitle2.setBounds(456,423-change,169,39);
		subtitle1.setFont(subfont);
		subtitle2.setFont(subfont);
		butOut.setBounds(52, 36, 120, 40);
		
		this.add(title);
		this.add(labelDate1);
		this.add(labelDate2);
		this.add(textDate1);
		this.add(textDate2);
		this.add(name);
		this.add(statue);
		this.add(but);
		this.add(subtitle1);
		this.add(subtitle2);
		this.add(butOut);
		
		
		String[] columnNames1 = { "付款日期", "付款金额", "付款机构", "付款账户" };
		int[] list1 = { 30, 148, 14, 30, 20, 202, 198-change, 611, 220 };
		// list里面参数分别为需要的列数，每一列的宽度,设置第一行字体大小,设置第一行行宽,
		// * 剩下行的行宽,表格setbounds（list[5],list[6], list[7], list[8]）
		// *
		table1 = new Table();
		add(table1.drawTable(columnNames1, list1));
		
		String[] columnNames2 = { "收款日期", "收款金额", "收款机构", "收款账户" };
		int[] list2 = { 30, 148, 14, 30, 20, 202, 462-change, 611, 220};
		// list里面参数分别为需要的列数，每一列的宽度,设置第一行字体大小,设置第一行行宽,
		// * 剩下行的行宽,表格setbounds（list[5],list[6], list[7], list[8]）
		// *
		table2 = new Table();
		add(table2.drawTable(columnNames2, list2));
	}
	
	private void addListener(){
		but.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//日期没有填写完整
				if(textDate1.getText().equals("")||textDate2.getText().equals("")){
					JOptionPane.showMessageDialog(StatisticsReportUi.this, "请将日期填写完整!");
				}
				//日期格式不符合要求
				else if(textDate1.getText().length()!=10||textDate2.getText().length()!=10){
					JOptionPane.showMessageDialog(StatisticsReportUi.this, "日期格式为:yyyy-mm-dd!");
				}
				else{
					empty();
					InquireBusinessList inquire=new InquireBusinessList();
					BusinessListVO business=inquire.getBusinessList(textDate1.getText(),textDate2.getText());
					
					//显示付款单信息
					ArrayList<PayBillVO> pays=business.getPayBill();
					if(!pays.isEmpty()){
						for(int i=0;i<pays.size();i++){
						 table1.setValueAt(i, 0, pays.get(i).getDate());
						 table1.setValueAt(i, 1, pays.get(i).getAmount()+"");
						 table1.setValueAt(i, 2, pays.get(i).getInstitution());
						 table1.setValueAt(i, 3, pays.get(i).getAccount());
						}
					}
					
					//显示收款单信息
					ArrayList<IncomeBillVO> incomes=business.getIncomeBill();
					if(!incomes.isEmpty()){
						for(int i=0;i<incomes.size();i++){
						 table2.setValueAt(i, 0, incomes.get(i).getDate());
						 table2.setValueAt(i, 1, incomes.get(i).getAmount()+"");
						 table2.setValueAt(i, 2, incomes.get(i).getInstitution());
						 table2.setValueAt(i, 3, incomes.get(i).getAccount());
						}
					}					
				}
				
			}
		});
		
		butOut.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				mainFrame.setContentPane(new LoginUi(mainFrame));
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(MainFrame.background, 0, 0, this.getWidth(), this.getHeight(), null);
		this.repaint();
	}
	 
	/**
	 * 清空上一次查询结果
	 */
	private void empty(){
		int i=table1.numOfEmpty();
		for(i=i-1;i>=0;i--){
			 table1.setValueAt(i, 0, "");
			 table1.setValueAt(i, 1, "");
			 table1.setValueAt(i, 2, "");
			 table1.setValueAt(i, 3, "");
		}
		int j=table2.numOfEmpty();
		for(j=j-1;j>=0;j--){
			 table2.setValueAt(j, 0, "");
			 table2.setValueAt(j, 1, "");
			 table2.setValueAt(j, 2, "");
			 table2.setValueAt(j, 3, "");
		}
	}
}
