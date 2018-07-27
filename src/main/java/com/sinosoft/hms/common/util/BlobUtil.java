package com.sinosoft.hms.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * @author kjx
 *  20170427
 *  Blob工具类  
 */
public class BlobUtil {
	
	
	/**
	 *  byte 转换为 Blob
	 * @param bytes 字节数组
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 */
	public static Blob byte2Blob(byte[] bytes) throws SerialException, SQLException  {
		Blob blobValue = new SerialBlob(bytes);
		return blobValue;
	}
	
	
	/**
	 * String 转换为Blob 
	 * @param str 需转化的字符串
	 * @param charsetName 字符集，默认为 UTF-8
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Blob str2Blob(String str,String charsetName) throws SerialException, SQLException, IOException{
		if(StringUtils.isBlank(str)){
			return null;
		}
		if (StringUtils.isBlank(charsetName)) {
			charsetName = "UTF-8";
		}
		Blob blobValue = new SerialBlob(str.getBytes(charsetName));// String
		return blobValue;
	}
	
	
	
	
	/**
	 * file文件转换为Blob
	 * @param input
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Blob file2Blob(File file) throws SerialException, SQLException, IOException{
		InputStream in = new FileInputStream(file);
		return byte2Blob(read(in));
	}
	
	
	
	
	/**
	 * input流转换为Blob
	 * @param input
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static Blob input2Blob(InputStream input) throws SerialException, SQLException, IOException{
		return byte2Blob(read(input));
	}
	
	
	
	/**
	 * input流转化为byte
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] read(InputStream input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = input.read(b)) != -1) {
				baos.write(b, 0, len);
			}

		return baos.toByteArray();
	}

	
	/**
	 * 将blob转为byte[]
	 * 
	 * @param blob 
	 * @return
	 * @throws Exception 
	 */
	public static byte[] blob2Bytes(Blob blob) throws Exception {
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			throw new Exception();
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				throw new Exception();
			}

		}
	}
	
}
