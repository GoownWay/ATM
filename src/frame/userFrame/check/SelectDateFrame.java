package frame.userFrame.check;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import dao.userDao.CheckDao;
import frame.LoginFrame;
import frameInterface.FrameInterface;
import model.Chooser;
import model.MyFrame;

/**
 * @author ckx
 *日期选择和交易明细显示界面
 */
public class SelectDateFrame implements ActionListener, FrameInterface { 

	public MyFrame frame;
	public JPanel panel,panel1;
	public JLabel label,toLabel;
	JTextField startText,endText;
	Chooser startChooser,endChooser;

	@Override
	public void init() {
		frame = new MyFrame("选择日期");
		panel = new JPanel();
		panel1 = new JPanel();
		label = new JLabel("请选择查询的日期区间");
		toLabel = new JLabel("至");
		startText = new JTextField();
		endText = new JTextField();
		startChooser = Chooser.getInstance();
		endChooser = Chooser.getInstance();
	}

	@Override
	public void show() {
		init();
		panel.setLayout(null);//将panel的布局设置为空
		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片
		panel1.setLayout(null);//将panel的布局设置为空
		panel1.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		label.setForeground(Color.WHITE);//将label的字体颜色设置为白色
		toLabel.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		label.setBounds(325, 110, 400, 50);//设置标签的大小和在窗口的位置
		toLabel.setBounds(470, 200, 400, 50);//设置标签的大小和在窗口的位置
		startText.setBounds(160, 200, 250, 50);
		endText.setBounds(560, 200, 250, 50);

		startText.setEditable(false);
		endText.setEditable(false);

		//文本框显示最近一个月
		SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String nowDate = sj.format(date);//格式化时间

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);//时间减一个月
		String endDate = sj.format(calendar.getTime());

		startText.setText(endDate);
		endText.setText(nowDate);

		startChooser.register(startText);
		endChooser.register(endText);

		label.setFont(new Font("宋体", 1, 30));
		toLabel.setFont(new Font("宋体", 1, 30));
		startText.setFont(new Font("宋体", 1, 30));
		endText.setFont(new Font("宋体", 1, 30));

		panel.add(label);
		panel.add(toLabel);
		panel.add(startText);
		panel.add(endText);

		frame.setLayout(null);
		panel.setBounds(0, 0, 975, 250);
		panel1.setBounds(0, 280, 975,250);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel1);
		frame.setVisible(true);
	}

	@Override
	public void listen() {//按钮添加监听
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{
			CheckFrame f = new CheckFrame();
			f.show();
			f.listen();
			frame.dispose();
		}else//确认按钮
		{
			panel1.removeAll();//先去除之前的table
			CheckDao check = new CheckDao();
			//查询到交易记录
			String[][] list = check.detailedInquiry(LoginFrame.loginedAccount,startText.getText(),endText.getText());
			if(list!=null)
			{
				JTable table;//表格
				JScrollPane sp;//滚动条
				Object[] columnName;//列名
				//表头
				columnName = new Object[]{"序号","交易流水号","对方姓名","交易日期","交易金额","交易账号","交易类型","手续费"};
				table = new JTable(list,columnName);
				sp = new JScrollPane();

				if(table.getRowCount()<5)//如果数据的行数小于5条，则根据条数来调整table的宽度
					sp.setBounds(85, 0, 800, 55+table.getRowCount()*35);
				else  //大于5条则显示5条记录的宽度，然后利用滚动条
					sp.setBounds(85, 0, 800, 230);

				table.setEnabled(false);//tables不可编辑也不可用鼠标选取某一行
				//table.setCellSelectionEnabled(true);//tables不可用鼠标选取某一行但是能够编辑
				sp.setViewportView(table);//这句很重要
				table.setRowHeight(35); //表格单元格高度

				//设置每列的宽度
				TableColumn firsetColumn0 = table.getColumnModel().getColumn(0);
				TableColumn firsetColumn1 = table.getColumnModel().getColumn(1);
				TableColumn firsetColumn2 = table.getColumnModel().getColumn(2);
				TableColumn firsetColumn3 = table.getColumnModel().getColumn(3);
				TableColumn firsetColumn4 = table.getColumnModel().getColumn(4);
				TableColumn firsetColumn5 = table.getColumnModel().getColumn(5);
				TableColumn firsetColumn6 = table.getColumnModel().getColumn(6);
				TableColumn firsetColumn7 = table.getColumnModel().getColumn(7);
				firsetColumn0.setPreferredWidth(50);
				firsetColumn1.setPreferredWidth(200);
				firsetColumn2.setPreferredWidth(100);
				firsetColumn3.setPreferredWidth(200);
				firsetColumn4.setPreferredWidth(100);
				firsetColumn5.setPreferredWidth(210);
				firsetColumn6.setPreferredWidth(100);
				firsetColumn7.setPreferredWidth(100);

				table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 52));
				//让表格中的字居中显示设置
				DefaultTableCellRenderer r = new DefaultTableCellRenderer();    
				r.setHorizontalAlignment(JLabel.CENTER); 
				table.setDefaultRenderer(Object.class,r);
				panel1.add(sp);
				panel1.repaint();//重新绘制panel1,这句至关重要，不然的话panel1之前的东西还会显示
			}
			else
				panel1.repaint();
		}
	}
}