import javax.swing.*;

public class OnlyFrame {
  public static void main(String[] args) {

    // �إ� JFrame �e������
    JFrame myframe = new JFrame("���K����");

    // �]�w��ϥΪ�����������, �Y�����{��
    myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	myframe.setBounds(640,500,320,240);

    myframe.setSize(320,240);  // �]�w�������e�P��

    myframe.setVisible(true);  // �N�����]���n���
  }
}
