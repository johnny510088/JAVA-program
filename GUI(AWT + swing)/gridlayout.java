import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gridlayout extends JPanel implements ActionListener{
	JLabel	top_label = new JLabel("記帳");
	JButton red = new JButton("紅");
	JButton orange = new JButton("橙");
	JButton yellow = new JButton("黃");
	JButton green = new JButton("綠");
	JButton blue = new JButton("藍");
	
	public static void main(String[] args) {
		// 建立 JFrame 容器物件
		gridlayout p = new gridlayout();
		
		p.setLayout(new GridLayout(2,1,40,0));
		JFrame f = new JFrame("Gridlayout");
		f.getContentPane().add(p);

		// 設定當使用者關閉視窗時, 即結束程式
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(800,1000);  // 設定視窗的寬與高
		f.setVisible(true);  // 將視窗設為要顯示
		//f.setBounds(640,500,320,240);
	}
	public gridlayout(){
		add(top_label);
		add(red);
		add(orange);
		add(yellow);
		add(green);
		add(blue);
		
		red.addActionListener(this);
		orange.addActionListener(this);
		yellow.addActionListener(this);
		green.addActionListener(this);
		blue.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		JButton s = (JButton) e.getSource();
		if(s==red)		setBackground(Color.red);		
		if(s==orange)	setBackground(Color.orange);		
		if(s==yellow)	setBackground(Color.yellow);
		if(s==green)	setBackground(Color.green);
		if(s==blue)		setBackground(Color.blue);		
	}
}
