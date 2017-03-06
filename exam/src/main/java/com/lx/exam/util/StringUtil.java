package com.lx.exam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class StringUtil {

	private static String defaultKey = "denaspo";

	/* encryp */
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 根据系统时间获得指定位数的随机数
	 * 
	 * @return 获得的随机数
	 */
	public static String getRandom() {
		String numberChar = "0123456789";
		Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子
		StringBuffer sb = new StringBuffer();// 装载生成的随机数
		Random random = new Random(seed);// 调用种子生成随机数
		for (int i = 0; i < 20; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return sb.toString();
	}

	// ------------------------------------------DES加密----------------------------------------//
	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	// ------------------------------------------MD5加密----------------------------------------//
	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 解密字节数组
	 * 
	 * @param bytes
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public static byte[] decrypt(Key key, byte[] bytes) throws Exception {
		Cipher cp = Cipher.getInstance("DES");
		cp.init(Cipher.DECRYPT_MODE, key);
		return cp.doFinal(bytes);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(Key key, String strIn) throws Exception {
		return new String(decrypt(key, hexStr2ByteArr(strIn)));
	}

	/**
	 * 从字符串中删除
	 * 
	 * @param source
	 *            源字符串
	 * @param cut
	 *            要删除的字符串
	 * @param prefix
	 *            2个字符串的分割符，
	 * @return
	 */
	public static String delAndNorepeat(String source, String cut, String prefix) {
		// 排重
		Map<String, String> map_s = new HashMap<String, String>();
		for (String s : source.split(prefix)) {
			if (!s.trim().equals("")) {
				map_s.put(s, s);
			}
		}
		// 删除
		for (String c : cut.split(prefix)) {
			map_s.remove(c);
		}
		// 重组
		String res = "";
		for (String s : map_s.values()) {
			if (!s.trim().equals("")) {
				res += prefix + s;
			}
		}
		res = res.startsWith(prefix) ? res.replaceFirst(prefix, "") : res;
		return res;
	}

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param originalString
	 *            要加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encodeByMD5(String originalString) {
		if (originalString != null) {
			// 创建具有指定算法名称的信息摘要
			MessageDigest md = null;
			byte[] results = null;
			try {
				md = MessageDigest.getInstance("MD5");
				results = md.digest(originalString.getBytes("UTF-8"));
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// /**
	// * 判断指定的字符串是否为数字
	// * @param str 字符串
	// * @return 返回boolean
	// */
	// public static boolean isNum(String str) {
	// String regex = "0123456789";
	//
	// if(str == null)
	// return false;
	//
	// if(str.length() ==0)
	// return false;
	//
	// for(int i=0;i<str.length();i++) {
	// if(regex.indexOf(str.charAt(i)) == -1)
	// return false;
	// }
	// return true;
	// }

	/**
	 * 加密字节数组
	 * 
	 * @param bytes
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	public static byte[] encrypt(Key key, byte[] bytes) throws Exception {
		Cipher cp = Cipher.getInstance("DES");
		cp.init(Cipher.ENCRYPT_MODE, key);
		return cp.doFinal(bytes);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(Key key, String strIn) throws Exception {
		return byteArr2HexStr(encrypt(key, strIn.getBytes()));
	}

	public static boolean endsWithList(List<String> types, String name) throws Exception {
		if (types == null || types.size() == 0) {
			types = new ArrayList<String>();
			types.add("xls");
			types.add("xlsx");
		}
		for (String str : types) {
			if (name.endsWith(str)) {
				return true;
			}
		}
		return false;
	}

	public static String endTime(String date) {
		return date + " 23:59:59";
	}

	public static String escapeXml(String str) {
		if (isEmpty(str))
			return str;
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}

	/**
	 * 过滤掉指定的特殊字符，防SQL注入
	 * 
	 * @param str
	 * @param fixed
	 *            特殊字符替换成的字符
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String filter(String str, String fixed) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符(本例保留",")
		String regEx = "[`~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；，：”“’。、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll(fixed).trim();
	}

	public static String FilterStr(String str) throws Exception {
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_-]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(str);
		return mat.replaceAll("").trim();
	}

	/**
	 * 简单的字符串格式化，性能较好。支持不多于10个占位符，从%1开始计算，数目可变。参数类型可以是字符串、Integer、Object，
	 * 甚至int等基本类型
	 * 、以及null，但只是简单的调用toString()，较复杂的情况用String.format()。每个参数可以在表达式出现多次。
	 * 
	 * @param msgWithFormat
	 * @param autoQuote
	 * @param args
	 * @return
	 */
	public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object... args) {
		int argsLen = args.length;
		boolean markFound = false;

		StringBuilder sb = new StringBuilder(msgWithFormat);

		if (argsLen > 0) {
			for (int i = 0; i < argsLen; i++) {
				String flag = "%" + (i + 1);
				int idx = sb.indexOf(flag);
				// 支持多次出现、替换的代码
				while (idx >= 0) {
					markFound = true;
					sb.replace(idx, idx + 2, toString(args[i], autoQuote));
					idx = sb.indexOf(flag);
				}
			}

			if (args[argsLen - 1] instanceof Throwable) {
				StringWriter sw = new StringWriter();
				((Throwable) args[argsLen - 1]).printStackTrace(new PrintWriter(sw));
				sb.append("\n").append(sw.toString());
			} else if (argsLen == 1 && !markFound) {
				sb.append(args[argsLen - 1].toString());
			}
		}
		return sb;
	}

	public static StringBuilder formatMsg(String msgWithFormat, Object... args) {
		return formatMsg(new StringBuilder(msgWithFormat), true, args);
	}

	/**
	 * 生成一个用于DES加密的随机密钥
	 * 
	 * @return 返回随机密钥
	 * @throws Exception
	 *             ;
	 */
	public static SecretKey getADesKey() throws Exception {
		// 获取密匙生成器,为我们选择的DES算法生成一个KeyGenerator对象
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		// 初始化
		// DES算法必须是56位
		// DESede算法可以是112位或168位
		// AES算法可以是128、192、256位
		kg.init(56);
		// 生成密匙，可用多种方法来保存密匙
		SecretKey key = kg.generateKey();
		return key;
	}

	/**
	 * 从指定字节数组生成一个用于DES加密的密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param bytes
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	public static Key getADesKey(byte[] bytes) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < bytes.length && i < arrB.length; i++) {
			arrB[i] = bytes[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	/**
	 * 从指定字符串生成一个用于DES加密的密钥，密钥所需的字符长度为8个字节长度的字符，程序自动对输入的字符串多去少补零
	 * 
	 * @param keyStr
	 *            指定生成密钥的8位字符串
	 * @return 生成的密钥
	 * @throws Exception
	 */
	public static Key getADesKey(String keyStr) throws Exception {
		byte[] bytes = keyStr.getBytes();
		return getADesKey(bytes);
	}

	public static Key getDefaultKey() throws Exception {
		return getADesKey(defaultKey);
	}

	/**
	 * 把list中指定的字段以指定的分隔符相连转成字符串
	 * 
	 * @param list
	 * @param 参与拼字串的属性名
	 * @param prefix
	 *            例：prefix设成“,”，则方法返回为"xxx,aaaa,ddd,111"
	 * @return
	 */
	@SuppressWarnings("all")
	public static String getFromList(List list, String fieldname, String prefix) {
		String str = "";
		for (Object obj : list) {
			try {
				Method m = obj.getClass().getMethod(
						"get" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1, fieldname.length()),
						null);
				Object add = m.invoke(obj, null);
				if (add != null && !add.toString().equals("0")) {
					str += prefix + add.toString();
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		if (str.startsWith(prefix)) {
			str = str.replaceFirst(prefix, "");
		}
		return str;
	}

	public static String getLongDouble(double str) {
		DecimalFormat df = new DecimalFormat("###############0.00 ");// 16位整数位，两小数位
		return df.format(str);
	}

	public static String getLongFloat(float str) {
		DecimalFormat df = new DecimalFormat("###############0.00 ");// 16位整数位，两小数位
		return df.format(str);
	}

	public static String getRate(double str1, double str2) {
		String str = "较前一年";
		double rate = 0;
		double crate = 0;
		if (str1 == 0 || str2 == 0) {
			crate = 1;
		} else {
			crate = str2;
		}

		if (str1 == str2) {
			str = str + "没有增长";
		}

		if (str1 < str2) {
			rate = (str2 - str1) / crate * 100;
			str = str + "减少" + StringUtil.getLongDouble(rate) + "%";
		}

		if (str1 > str2) {
			rate = (str1 - str2) / crate * 100;
			str = str + "增长" + StringUtil.getLongDouble(rate) + "%";
		}
		return str;
	}

	public static double getRatePie(double str1, double str2) {
		double rate = 0;
		if (str2 != 0) {
			rate = str1 / str2 * 100;
		}
		return rate;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file
	 * @return 返回文件后缀名，包含"."字符
	 */
	public static String getSuffix(File file) {
		if (file != null) {
			String fileName = file.getName();
			if (!fileName.trim().equals("")) {
				int pos = fileName.lastIndexOf(".");
				return fileName.substring(pos);
			}
		}
		return "";
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = java.util.regex.Pattern.compile(regEx_script, java.util.regex.Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = java.util.regex.Pattern.compile(regEx_style, java.util.regex.Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = java.util.regex.Pattern.compile(regEx_html, java.util.regex.Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * HTML实体编码转成普通的编码
	 * 
	 * @param dataStr
	 * @return
	 */
	public static String htmlEntityToString(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			int system = 10;// 进制
			if (start == 0) {
				int t = dataStr.indexOf("&#");
				if (start != t)
					start = t;
			}
			end = dataStr.indexOf(";", start + 2);
			String charStr = "";
			if (end != -1) {
				charStr = dataStr.substring(start + 2, end);
				// 判断进制
				char s = charStr.charAt(0);
				if (s == 'x' || s == 'X') {
					system = 16;
					charStr = charStr.substring(1);
				}
			}
			// 转换
			try {
				char letter = (char) Integer.parseInt(charStr, system);
				buffer.append(new Character(letter).toString());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			// 处理当前unicode字符到下一个unicode字符之间的非unicode字符
			start = dataStr.indexOf("&#", end);
			if (start - end > 1) {
				buffer.append(dataStr.substring(end + 1, start));
			}

			// 处理最后面的非unicode字符
			if (start == -1) {
				int length = dataStr.length();
				if (end + 1 != length) {
					buffer.append(dataStr.substring(end + 1, length));
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 数字到字符串
	 * 
	 * @param numb
	 *            要转换的数字
	 * @param length
	 *            几个字符
	 * @return
	 */
	public static String int2StringWithZero(int numb, int length) {
		String str = numb + "";
		String result = str;
		for (int i = 0; i < length - str.length(); i++) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * 判断字符串是否为null或为空
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(final String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 *            文件的物理路径
	 * @return boolean
	 */
	public static boolean isFileExist(String path) {
		if (path != null) {
			File f = new File(path);
			return f.exists();
		} else
			return false;
	}

	public static boolean isNotBlank(String str) {
		return str != null && !str.trim().equals("");
	}

	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断给定字符串是否为空
	 * 
	 * @param str
	 *            需要检查的字符串
	 * @return 如果为null或""，则返回true,否则为false
	 */
	public static boolean isNullorBlank(final String str) {
		boolean isNorB = false;
		if (str == null || str.length() <= 0) {
			isNorB = true;
		}
		return isNorB;
	}

	/**
	 * 判断某个字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String joinStr(List<Long> strs, String symbol) {
		StringBuffer sb = new StringBuffer();
		int count = strs.size();
		for (int i = 0; i < count - 1; i++) {
			sb.append(strs.get(i) + symbol);
		}
		sb.append(strs.get(count - 1));
		return sb.toString();
	}

	/**
	 * 从文件中读取文本内容, 读取时使用平台默认编码解码文件中的字节序列
	 * 
	 * @param file
	 *            目标文件
	 * @return
	 * @throws IOException
	 */
	public static String loadStringFromFile(File file) throws IOException {
		return loadStringFromFile(file, System.getProperty("file.encoding"));
	}

	/**
	 * 从文件中读取文本内容
	 * 
	 * @param file
	 *            目标文件
	 * @param encoding
	 *            目标文件的文本编码格式
	 * @return
	 * @throws IOException
	 */
	public static String loadStringFromFile(File file, String encoding) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			StringBuilder builder = new StringBuilder();
			char[] chars = new char[4096];
			int length = 0;
			while (0 < (length = reader.read(chars))) {
				builder.append(chars, 0, length);
			}
			return builder.toString();

		} finally {
			try {
				if (reader != null)
					reader.close();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		String str = "1,;DROP TABLE users";
		System.out.println(str);
		System.out.println(filter(str, "xxxe"));
	}

	public static String objectToString(Object obj) {
		String result = String.valueOf(obj);
		if (result.equals("null")) {
			result = "";
		}
		return result;
	}

	/**
	 * Remove \ / | : ? * " < > with _
	 * 
	 * @param str
	 * @return String
	 */
	public static String ReplaceInvalidChar(String str) {
		return str.replaceAll("\\/|\\/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}

	public static boolean Sort(List<String> args, String data) {
		for (String arg : args) {
			if (arg.equals(data)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把String转成html实体字符
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToHtmlEntity(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			switch (c) {
			case 0x0A:
				sb.append(c);
				break;

			case '<':
				sb.append("&lt;");
				break;

			case '>':
				sb.append("&gt;");
				break;

			case '&':
				sb.append("&amp;");
				break;

			case '\'':
				sb.append("&apos;");
				break;

			case '"':
				sb.append("&quot;");
				break;

			default:
				if ((c < ' ') || (c > 0x7E)) {
					sb.append("&#x");
					sb.append(Integer.toString(c, 16));
					sb.append(';');
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	// 转unicode
	public static String stringToUnicode(String s) {
		String unicode = "";
		char[] charAry = new char[s.length()];
		for (int i = 0; i < charAry.length; i++) {
			charAry[i] = (char) s.charAt(i);
			unicode += "\\u" + Integer.toString(charAry[i], 16);
		}
		return unicode;
	}

	/**
	 * 将给定的中文ISO字符串转换为GBK编码的字符串;若为NULL，转换成空 ("")
	 * 
	 * @param str
	 *            输入字符串
	 * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
	 */
	public static String toGBK(final String str) {
		if (isNullorBlank(str)) {
			return "";
		}
		String rn = str;
		try {
			rn = new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rn;
	}

	public static String toString(Object obj, boolean autoQuote) {
		StringBuilder sb = new StringBuilder();
		if (obj == null) {
			sb.append("NULL");
		} else {
			if (obj instanceof Object[]) {
				for (int i = 0; i < ((Object[]) obj).length; i++) {
					sb.append(((Object[]) obj)[i]).append(", ");
				}
				if (sb.length() > 0) {
					sb.delete(sb.length() - 2, sb.length());
				}
			} else {
				sb.append(obj.toString());
			}
		}
		if (autoQuote && sb.length() > 0 && !((sb.charAt(0) == '[' && sb.charAt(sb.length() - 1) == ']')
				|| (sb.charAt(0) == '{' && sb.charAt(sb.length() - 1) == '}'))) {
			sb.insert(0, "[").append("]");
		}
		return sb.toString();
	}

	/**
	 * 将给定的中文ISO字符串转换为UTF-8编码的字符串;若为NULL，转换成空 ("")
	 * 
	 * @param str
	 *            输入字符串
	 * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
	 */
	public static String toUTF8(final String str) {
		if (isNullorBlank(str)) {
			return "";
		}
		String rn = str;
		try {
			rn = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rn;
	}

	public static String unicodeToString(String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("\\\\U");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(""))
				continue;
			char c = (char) Integer.parseInt(str[i].trim(), 16);
			sb.append(c);
		}
		return sb.toString();
	}

}
