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
 * @author 作者 : jianqing.liu
 * @version 创建时间：2018年3月29日 下午5:53:29 
 * @since 2018年3月29日 下午5:53:29 
 * @description 类说明 
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
	        setTitle("Run Simple Java Code");// 设置窗体的标题
//	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体退出时操作
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//只关闭本窗口。
//	        this.setDefaultCloseOperation(HIDE_ON_CLOSE);//只隐藏本窗口，不关闭
//	        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//不做任何事，点击关闭无效
	        this.setSize(680, 500);
	        this.setLocationRelativeTo(null);//窗体居中显示  
	        //this.disable();
	        //setBounds(100, 100, 250, 200);// 设置窗体位置和大小
	        contentPane = new JPanel();// 创建内容面板
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框
	        contentPane.setLayout(new BorderLayout(0, 0));// 设置内容面板为边界布局
	        setContentPane(contentPane);// 应用内容面板
	        
	        JPanel panel1 = new JPanel();// 创建面板
	        FlowLayout fl_panel1 = (FlowLayout) panel1.getLayout();// 获得面板的布局管理器
	        fl_panel1.setAlignment(FlowLayout.LEFT);// 将布局管理器设置成左侧排列
	        contentPane.add(panel1, BorderLayout.NORTH);// 应用面板
	        
	        JLabel label = new JLabel("enter your code：");// 创建标签
	        panel1.add(label);// 应用标签
	        
	        JPanel panel2 = new JPanel();// 创建面板
	        contentPane.add(panel2, BorderLayout.SOUTH);// 应用面板
	        
	        final JTextArea textArea = new JTextArea();// 创建文本域
            textArea.setLineWrap(true); //设置文本域自动换行
            JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
	        contentPane.add(scrollPane, BorderLayout.CENTER);// 应用面板
            scrollPane.setViewportView(textArea);// 应用文本域
	        
	        JButton runBtn = new JButton("run");// 创建按钮
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
	        panel2.add(runBtn);// 应用按钮
	        
	        JButton clearBtn = new JButton("clear");// 创建按钮
	        clearBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textArea.setText("");
//					重启
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
	        panel2.add(clearBtn);// 应用按钮
	    }

}
