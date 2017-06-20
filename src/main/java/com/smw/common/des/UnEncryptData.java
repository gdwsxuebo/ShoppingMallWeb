package com.smw.common.des;

import java.security.SecureRandom;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.spec.KeySpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.io.ByteArrayInputStream;

public class UnEncryptData {

	private String keyfile = "";

	public UnEncryptData() {
	}

	public UnEncryptData(String keyfile) {
		this.keyfile = keyfile;
	}

	public void createUnEncryptData(String encryptfile, String filename)
			throws IllegalStateException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException,
			InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, IOException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ClassNotFoundException, IllegalStateException, IllegalBlockSizeException,
			BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException,
			InvalidKeyException, IOException {
		// 验证keyfile
		if (keyfile == null || keyfile.equals("")) {
			throw new NullPointerException("无效的key文件路径");
		}

		unEncryptData(encryptfile, filename);
	}

	/**
	 * 解密类文件
	 * 
	 * @param encryptfile
	 *            String 经过加密的文件
	 * @param filename
	 *            String 解密后的文件
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IllegalStateException
	 */
	private void unEncryptData(String encryptfile, String filename)
			throws IOException, IllegalStateException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		// 获得经过加密的数据
		byte[] data = Util.readFile(encryptfile);
		// 执行解密操作
		byte decryptedData[] = getunEncryptData(data);
		// 然后将解密后的数据转化成原来的类文件。
		Util.writeFile(decryptedData, filename);
	}

	/**
	 * 解密字节数组
	 * 
	 * @param bytes
	 *            byte[]
	 * @throws IllegalStateException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @return byte[]
	 */
	public static byte[] createUnEncryptData(byte[] bytes) throws IllegalStateException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException,
			NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		bytes = getunEncryptData(bytes);
		return bytes;
	}

	/**
	 *
	 * @param bytes
	 *            byte[]
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IllegalStateException
	 * @return byte[]
	 */
	private static byte[] getunEncryptData(byte[] bytes)
			throws IOException, ClassNotFoundException, SecurityException, NoSuchMethodException,
			InvocationTargetException, IllegalArgumentException, IllegalAccessException, InstantiationException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IllegalStateException {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从密钥文件中获取原始密钥数据
		//byte[] rawKeyData = Util.readFile(keyfile);
		byte[] rawKeyData={-110, -22, -83, 110, 56, -98, 94, -101};
		// 创建一个DESKeySpec对象
		//Class classkeyspec = Class.forName(Util.getValue("keyspec"));
		Class classkeyspec = Class.forName("javax.crypto.spec.DESKeySpec");
		Constructor constructor = classkeyspec.getConstructor(new Class[] { byte[].class });
		KeySpec dks = (KeySpec) constructor.newInstance(new Object[] { rawKeyData }); // new
																						// DESKeySpec(rawKeyData);
		// 创建一个密钥工厂，然后用它把DESKeySpec对象转换成Secret Key对象
		//SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Util.getAlgorithm());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		//Cipher cipher = Cipher.getInstance(Util.getAlgorithm());
		Cipher cipher = Cipher.getInstance("DES");
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		// 获得经过加密的数据
		// 执行解密操作
		bytes = cipher.doFinal(bytes);
		// 然后将解密后的数据转化成原来的类文件。
		return bytes;
	}

	public void setKeyFile(String keyfile) {
		this.keyfile = keyfile;
	}
}
