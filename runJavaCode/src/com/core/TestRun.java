package com.core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.common.io.IOUtils;

/**
 * 动态执行一段代码(生成文件->编译->执行)
 * 
 * @author n_soul
 * @version 1.0
 */
public class TestRun {

	private static final Object objLock = new Object();
	private static final String COMPILE_FAIL_MESSAGE = "编译失败,注意书写规范！";
	
	private String fileName = "TestRunCode";
	
	private String uid;

	private String getJavaName() {
		return fileName + uid + ".java";
	}
	
	private String getSimpleName() {
		return fileName + uid;
	}

	public TestRun() {
		this.uid = keyGenerator();
	}
	/**
	 * 创建java文件
	 */
	public void createJavaFile(String body) {
		String packageName = "package com.core;\r\n";
		String head = "public class "+getSimpleName()+"{\r\n  public static Object runCode(){\r\n\t\t";

		String end = "\r\n  }\r\n}";
		DataOutputStream dos = null;
		try {
			if(!"".equals(body) && !body.endsWith(";")) {
				body += ";";
			}
			if(!body.contains("return ")) {
				body += "return null;";
			}
			dos = new DataOutputStream(new FileOutputStream(getClassFolder() + getJavaName()));
			dos.writeBytes(packageName);
			dos.writeBytes(head);
			dos.write(body.getBytes("gbk"));
			dos.writeBytes(end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(dos);
		}
	}

	/**
	 * 编译
	 */
	public boolean makeJavaFile() {
		boolean ret = true;
		try {
			Runtime rt = Runtime.getRuntime();
			Process ps = rt.exec("cmd /c javac " + getClassFolder() + getJavaName());
			ps.waitFor();
			byte[] out = new byte[1024];
			DataInputStream dos = new DataInputStream(ps.getInputStream());
			while(dos.read(out) > 0) {
				//ignore
			}
			dos.close();
			String s = new String(out);
			if (s.indexOf("Exception") >= 0 || s.indexOf("错误") >= 0 || ps.exitValue() != 0) {
				ret = !ret;
				ShowResult.show(TestRun.COMPILE_FAIL_MESSAGE);
				deleteFiles(this.fileName);
			}
		} catch (Exception e) {
			ret = !ret;
			ShowResult.show(TestRun.COMPILE_FAIL_MESSAGE);
			deleteFiles(this.fileName);
			e.printStackTrace();
		}
		return ret;
	}

	private String getClassFolder() {
		return this.getClass().getClassLoader().getResource("com/core/").getPath();
	}

	/**
	 * 反射执行
	 */
	public void run() {
		
		try {
			Object obj = Class.forName("com.core."  +getSimpleName())
					.getMethod("runCode").invoke(null);
			
			if (obj != null) {
				ShowResult.show(obj);
			}
			
			deleteFiles(this.fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private  void deleteFiles(final String fileName) {
		new Thread() {
			@Override
			public void run() {
				File file = new File(getClassFolder());
				File[] files = file.listFiles();
				for(File f : files) {
					if(f.getName().startsWith(fileName) && f.isFile()) {
						f.delete();
					}
				}
			}
		}.start();
	}

	public static String formatDate(String format, Date date) {
		String formatStr = "";
		if (date != null) {
			if (null == format) {
				format = "EEEMMMdHH:mm:sszyyyy";// 美国时间格式
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			formatStr = simpleDateFormat.format(date);
		}
		return formatStr;
	}

	public static String keyGenerator() {
		StringBuffer key = new StringBuffer();
		synchronized (objLock) {
			GregorianCalendar grego = new GregorianCalendar();
			key.append(formatDate("yyyyMMddHHmmssSSS", grego.getTime()));
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			key.append(getUppercaseLetter());
		}
		return key.toString();
	}

	/**
	 * @return a uppercase character
	 */
	private static char getUppercaseLetter() {
		return (char) ((int) Math.floor(Math.random() * 26) + 65);
	}

	/**
	 * 测试
	 */
	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		sb.append("int i=10;");
		sb.append("int j=9;");

		sb.append("System.err.println(++i+j);");
		String cmd = null;
		if (args.length >= 1) {
			cmd = args[0];
		} else {
			cmd = sb.toString();
		}
		TestRun t = new TestRun();
		t.createJavaFile(cmd);
		if (t.makeJavaFile()) {
			t.run();
		}
		new File("").getAbsolutePath();
	}
}