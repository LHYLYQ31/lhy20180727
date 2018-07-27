package com.sinosoft.hms.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

public class JUtilities {
	private static Calendar staticCal;

	public static boolean isDate(int type) {
		return (type == 93) || (type == 91) || (type == 92);
	}

	public static boolean isNumber(int type) {
		return (isInteger(type)) || (isDecimal(type));
	}

	public static boolean isString(int type) {
		return (type == 12) || (type == 1) || (type == -1);
	}

	public static boolean isDecimal(int type) {
		return (type == 2) || (type == 3) || (type == 6) || (type == 8);
	}

	public static boolean isInteger(int type) {
		return (type == 4) || (type == 5) || (type == -5);
	}

	public static final String getAsString(Object ob) {
		if (ob == null) {
			return null;
		}
		if ((ob instanceof String))
			return (String) ob;
		if ((ob instanceof byte[]))
			return new String((byte[]) (byte[]) ob);
		if ((ob instanceof InputStream))
			try {
				return getTextFromInputStream((InputStream) ob);
			} catch (IOException ex) {
			}
		return ob.toString();
	}

	public static final String getTextFromInputStream(InputStream is) throws IOException {
		if (is == null)
			return null;
		try {
			is.reset();
		} catch (Exception ex) {
		}
		InputStreamReader r = new InputStreamReader(is);
		StringBuffer buf = new StringBuffer();
		while (true) {
			int c = r.read();
			if (c <= 0) {
				break;
			}
			buf.append((char) c);
		}
		return buf.toString();
	}

	public static int findAtStringArray(String[] a, Object o) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				if ((o == a[i]) || ((o != null) && (o.equals(a[i])))) {
					return i;
				}
			}
		}
		return -1;
	}

	public static int findAtStringArrayNoCase(String[] a, String o) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				if ((o == a[i]) || ((o != null) && (o.equalsIgnoreCase(a[i])))) {
					return i;
				}
			}
		}
		return -1;
	}

	public static final int[] findArrayIndices(String[] fromArrays, String[] findArrays, boolean bNoCase,
			boolean bFoundOnly) {
		int[] Indices = null;
		if ((findArrays != null) && (findArrays.length > 0)) {
			Indices = new int[findArrays.length];
			for (int i = 0; i < Indices.length; i++) {
				Indices[i] = -1;
				if (fromArrays != null) {
					for (int j = 0; j < fromArrays.length; j++) {
						if ((findArrays[i] == null) || (fromArrays[j] == null)
								|| (((bNoCase) || (!fromArrays[j].equals(findArrays[i])))
										&& ((!bNoCase) || (!fromArrays[j].equalsIgnoreCase(findArrays[i]))))) {
							continue;
						}

						Indices[i] = j;
						break;
					}
				}
			}

		}

		if ((Indices != null) && (bFoundOnly)) {
			int factNum = 0;
			int len1 = Indices.length;
			for (int i = 0; i < len1; i++) {
				if (Indices[i] >= 0) {
					factNum++;
				}
			}
			if (factNum < len1) {
				int[] factIndices = new int[factNum];
				int pos = 0;
				for (int i = 0; (i < len1) && (pos < factNum); i++) {
					if (Indices[i] >= 0) {
						factIndices[(pos++)] = Indices[i];
					}
				}
				return factIndices;
			}
		}
		return Indices;
	}

	public static int findAtIntArray(int[] a, int o) {
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				if (o == a[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	public static final String getTextFromFile(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		try {
			String str = getTextFromInputStream(fis);
			return str;
		} finally {
			try {
				fis.close();
			} catch (Throwable ex) {
			}
		}
	}

	public static int startWithInt(String s, char charBefore, boolean negEnable) {
		int p = s.indexOf(charBefore);
		if (p <= 0) {
			return p;
		}
		int i = 0;
		if ((negEnable) && (s.charAt(0) == '-')) {
			i++;
		}
		for (; i < p; i++) {
			char c = s.charAt(i);
			if ((c < '0') || (c > '9')) {
				return -1;
			}
		}
		return p;
	}

	public static final int parseInt(String text) {
		text = text.trim();
		int start = 0;
		int base = 10;
		if ((text.startsWith("0x")) || (text.startsWith("0X"))) {
			start = 2;
			base = 16;
		} else if ((text.startsWith("0b")) || (text.startsWith("0B"))) {
			start = 2;
			base = 2;
		}
		return Integer.parseInt(text.substring(start), base);
	}

	public static String getString(CachedRowSet crs, String str) {
		try {
			String temp = crs.getString(str);
			if ((temp != null) && (!temp.equals("null"))) {
				return temp;
			}
			return "";
		} catch (Exception ex) {
		}
		return "";
	}

	public static String getString(CachedRowSet crs, int index) {
		try {
			String temp = crs.getString(index);
			if ((temp != null) && (!temp.equals("null"))) {
				return temp;
			}
			return "";
		} catch (Exception ex) {
		}
		return "";
	}

	public static int getInt(CachedRowSet crs, String str) {
		try {
			return crs.getInt(str);
		} catch (Exception ex) {
		}
		return 0;
	}

	public static int getInt(CachedRowSet crs, int index) {
		try {
			return crs.getInt(index);
		} catch (Exception ex) {
		}
		return 0;
	}

	public static long getLong(CachedRowSet crs, String str) {
		try {
			return crs.getLong(str);
		} catch (Exception ex) {
		}
		return 0L;
	}

	public static long getLong(CachedRowSet crs, int index) {
		try {
			return crs.getLong(index);
		} catch (Exception ex) {
		}
		return 0L;
	}

	public static float getFloat(CachedRowSet crs, String str) {
		try {
			return crs.getFloat(str);
		} catch (Exception ex) {
		}
		return 0.0F;
	}

	public static float getFloat(CachedRowSet crs, int index) {
		try {
			return crs.getFloat(index);
		} catch (Exception ex) {
		}
		return 0.0F;
	}

	public static Date getDate(CachedRowSet crs, String str) {
		try {
			return crs.getDate(str);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Date getDate(CachedRowSet crs, int index) {
		try {
			return crs.getDate(index);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Timestamp getTimestamp(CachedRowSet crs, String str) {
		try {
			return crs.getTimestamp(str);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Timestamp getTimestamp(CachedRowSet crs, int index) {
		try {
			return crs.getTimestamp(index);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Blob getBlob(CachedRowSet crs, String str) {
		try {
			return crs.getBlob(str);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Blob getBolb(CachedRowSet crs, int index) {
		try {
			return crs.getBlob(index);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Clob getClob(CachedRowSet crs, String str) {
		try {
			return crs.getClob(str);
		} catch (Exception ex) {
		}
		return null;
	}

	public static Clob getClob(CachedRowSet crs, int index) {
		try {
			return crs.getClob(index);
		} catch (Exception ex) {
		}
		return null;
	}

	public static String getParameter(HttpServletRequest request, String param) {
		String str = request.getParameter(param);
		if (str != null) {
			return str.trim();
		}
		return "";
	}

	public static String getSession(HttpSession session, String sessionName) {
		String retStr = "";
		try {
			retStr = session.getAttribute(sessionName).toString();
		} catch (Exception e) {
			retStr = "";
		}
		return retStr;
	}

	public static void setMapToMap(HashMap toMap, HashMap fromMap) {
		Collection coll = fromMap.entrySet();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			if ((entry.getKey() != null) && (!entry.getKey().toString().equals("")))
				toMap.put(entry.getKey(), entry.getValue());
		}
	}

	public static String getNumChiness(String numStr) {
		char[] charArr = numStr.toCharArray();
		String str = "";
		for (int i = 0; i < charArr.length; i++) {
			switch (charArr[i]) {
			case '0':
				if (i == 0)
					continue;
				str = str + "〇";
				break;
			case '1':
				str = str + "一";
				break;
			case '2':
				str = str + "二";
				break;
			case '3':
				str = str + "三";
				break;
			case '4':
				str = str + "四";
				break;
			case '5':
				str = str + "五";
				break;
			case '6':
				str = str + "六";
				break;
			case '7':
				str = str + "七";
				break;
			case '8':
				str = str + "八";
				break;
			case '9':
				str = str + "九";
			}
		}

		return str;
	}

	public static String getNumChinesTwo(String numTwo) {
		char[] charArr = numTwo.toCharArray();
		int length = charArr.length;
		String str = "";
		for (int i = 0; i < length; i++) {
			String strTemp = "";
			if (length - i == 2) {
				strTemp = "十";
			}
			if (length - i == 3) {
				strTemp = "百";
			}
			if (length - i == 4) {
				strTemp = "千";
			}
			if (length - i == 5) {
				strTemp = "万";
			}

			switch (charArr[i]) {
			case '1':
				if ((length == 2) && (i == 0))
					str = "十" + str;
				else {
					str = str + "一" + strTemp;
				}
				break;
			case '2':
				str = str + "二" + strTemp;
				break;
			case '3':
				str = str + "三" + strTemp;
				break;
			case '4':
				str = str + "四" + strTemp;
				break;
			case '5':
				str = str + "五" + strTemp;
				break;
			case '6':
				str = str + "六" + strTemp;
				break;
			case '7':
				str = str + "七" + strTemp;
				break;
			case '8':
				str = str + "八" + strTemp;
				break;
			case '9':
				str = str + "九" + strTemp;
			}
		}

		return str;
	}
}