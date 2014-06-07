import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gridlayout extends JPanel implements ActionListener{
	JLabel	top_label = new JLabel("�O�b");
	JButton red = new JButton("��");
	JButton orange = new JButton("��");
	JButton yellow = new JButton("��");
	JButton green = new JButton("��");
	JButton blue = new JButton("��");
	
	public static void main(String[] args) {
		// �إ� JFrame �e������
		gridlayout p = new gridlayout();
		
		p.setLayout(new GridLayout(2,1,40,0));
		JFrame f = new JFrame("Gridlayout");
		f.getContentPane().add(p);

		// �]�w��ϥΪ�����������, �Y�����{��
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(800,1000);  // �]�w�������e�P��
		f.setVisible(true);  // �N�����]���n���
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
