package com.common.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author ���� : jianqing.liu
 * @version ����ʱ�䣺2018��4��16�� ����9:30:23
 * @since 2018��4��16�� ����9:30:23
 * @description ��˵��
 */
public class IOUtils {
	private IOUtils() {}
	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}
}
