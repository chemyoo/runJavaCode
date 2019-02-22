package com.core;

import java.lang.reflect.Array;
import javax.swing.JOptionPane;

/** 
 * @author ���� : jianqing.liu
 * @version ����ʱ�䣺2018��3��29�� ����6:18:21 
 * @since 2018��3��29�� ����6:18:21 
 * @description ��˵�� 
 */
public class ShowResult {
	private ShowResult() {}
	
	public static void show(Object result) {
		JOptionPane.showMessageDialog(null, toString(result), "���н��Ϊ��", JOptionPane.PLAIN_MESSAGE);
	}
	
	private static String toString(Object result) {
		StringBuffer buffer = new StringBuffer("");
		if(result != null) {
			if(result.getClass().isArray()) {
				int length = Array.getLength(result);
				buffer.append("[");
				for(int i = 0; i < length; i++) {
					buffer.append(Array.get(result, i));
					buffer.append(", ");
				}
				buffer.delete(buffer.length() - 2, buffer.length());
				buffer.append("]");
			} else {
				return result.toString();
			}
		}
		return buffer.toString();
	}
}
