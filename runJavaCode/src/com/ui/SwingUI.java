package com.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.core.TestRun;

/** 
 * @author ���� : jianqing.liu
 * @version ����ʱ�䣺2018��3��29�� ����5:53:29 
 * @since 2018��3��29�� ����5:53:29 
 * @description ��˵�� 
 */
public class SwingUI extends JFrame {
	 private static final long serialVersionUID = -8647932911543968288L;
	 private JPanel contentPane;
	 
	 public static void main(String[] args) {
	        try {
	        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
	        UIManager.setLookAndFeel(lookAndFeel);
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	SwingUI frame = new SwingUI();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    
	    
	    public SwingUI() {
	        setTitle("Run Simple Java Code");// ���ô���ı���
//	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô����˳�ʱ����
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//ֻ�رձ����ڡ�
//	        this.setDefaultCloseOperation(HIDE_ON_CLOSE);//ֻ���ر����ڣ����ر�
//	        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//�����κ��£�����ر���Ч
	        this.setSize(680, 500);
	        this.setLocationRelativeTo(null);//���������ʾ  
	        //this.disable();
	        //setBounds(100, 100, 250, 200);// ���ô���λ�úʹ�С
	        contentPane = new JPanel();// �����������
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������ı߿�
	        contentPane.setLayout(new BorderLayout(0, 0));// �����������Ϊ�߽粼��
	        setContentPane(contentPane);// Ӧ���������
	        
	        JPanel panel1 = new JPanel();// �������
	        FlowLayout fl_panel1 = (FlowLayout) panel1.getLayout();// ������Ĳ��ֹ�����
	        fl_panel1.setAlignment(FlowLayout.LEFT);// �����ֹ��������ó��������
	        contentPane.add(panel1, BorderLayout.NORTH);// Ӧ�����
	        
	        JLabel label = new JLabel("enter your code��");// ������ǩ
	        panel1.add(label);// Ӧ�ñ�ǩ
	        
	        JPanel panel2 = new JPanel();// �������
	        contentPane.add(panel2, BorderLayout.SOUTH);// Ӧ�����
	        
	        final JTextArea textArea = new JTextArea();// �����ı���
            textArea.setLineWrap(true); //�����ı����Զ�����
            JScrollPane scrollPane = new JScrollPane();// �����������
	        contentPane.add(scrollPane, BorderLayout.CENTER);// Ӧ�����
            scrollPane.setViewportView(textArea);// Ӧ���ı���
	        
	        JButton runBtn = new JButton("run");// ������ť
	        runBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String code = textArea.getText();
					TestRun t = new TestRun();
				    t.createJavaFile(code);
				    if (t.makeJavaFile()) {
				      t.run();
				    }
				}});
	        panel2.add(runBtn);// Ӧ�ð�ť
	        
	        JButton clearBtn = new JButton("clear");// ������ť
	        clearBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textArea.setText("");
//					����
//					dispose();
//					EventQueue.invokeLater(new Runnable() {
//			            public void run() {
//			                try {
//			                	SwingUI frame = new SwingUI();
//			                    frame.setVisible(true);
//			                } catch (Exception e) {
//			                    e.printStackTrace();
//			                }
//			            }
//			        });
				}});
	        panel2.add(clearBtn);// Ӧ�ð�ť
	    }

}
