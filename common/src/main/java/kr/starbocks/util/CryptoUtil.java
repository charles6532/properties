/**
 * 
 */
package kr.starbocks.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This provides simple and easy way to encrypt and decrypt data.
 * @author Playdata
 *
 */
public class CryptoUtil {
	//Define the algorithm for cryptography 
	private final static String ALGORITHM_CRYPTO = "AES/CBC/PKCS5Padding";
	//Define the algorithm for hashing 
	private final static String ALGORITHM_HASH = "SHA-512";
	//Define the algorithm for secret key.
	private final static String ALGORITHM_KEY = "AES";
	//Define the algorithm for generation of random key for 256bits key
	private final static String ALGORITHM_RANDOM = "SHA1PRNG";
	//Define the character set for encoding and decoding string.
	private final static Charset CHARSET_TRANSCODE= Charset.forName("UTF-8");
	//Define the length of Key String. 32 bytes == 256 bits
	private final static int LEN_KEY = 32;
	//This is used to separate a string to a character. 
	private final static String SPLIT_PATTERN = "(?!^)";
	//Temporary key to append to an original key to fit 32bytes.
	private final static String[] TEMP_KEY ="$tar8ocks".split(SPLIT_PATTERN);
	
	//Key generator
	private static KeyGenerator KEY_GEN;
	//Secure random key
	private static SecureRandom SEC_RAND;
	
	/*
	 * Initializing the key generator and security random object
	 */
	static {
		try {
			KEY_GEN = KeyGenerator.getInstance(ALGORITHM_KEY);
			SEC_RAND = SecureRandom.getInstance(ALGORITHM_RANDOM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Blending the given plain key and the default key.
	 * If the given plain key is grater than 32, it will be shrink to 32.
	 * Otherwise, thie method will make the 32 characters from the given key.
	 */
	private static final String blendKey(final String plainKey) {
		StringBuilder blendedKey = new StringBuilder(LEN_KEY);
		
		if(LEN_KEY < plainKey.length()) blendedKey.append(plainKey.substring(0,LEN_KEY));
		else blendedKey.append(plainKey);
		
		int offset = 0;
		int idx = 0;
		int pos = 0;
		while(blendedKey.length() < LEN_KEY) {
			pos = ++offset*2-1;
			if(TEMP_KEY.length <= idx) idx=0;
			if(blendedKey.length() < pos) blendedKey.append(TEMP_KEY[idx++]);
			else blendedKey.insert(pos, TEMP_KEY[idx++]);
		}
		return blendedKey.toString();
	}
	
	/**
	 * This is used to encrypt the given message with the given key.
	 * @param plainStrKey key string to be encrypted with
	 * @param message string which is to be encrypted
	 * @return encrypted string
	 */
	public static final String encrypt(final String plainStrKey, final String message) {
		return encrypt(blendKey(plainStrKey).getBytes(CHARSET_TRANSCODE), message);
	}

	/**
	 * This is used to encrypt the given message with the given raw key.
	 * 
	 * @param rawKey the key bytes. Its length must be 32 bytes. 
	 * This key can be created by the predefined <code>getRawKey</code> method.
	 * It must be used against decrypting the encrypted message.
	 * @param message string which is to be encrypted
	 * @return encrypted string
	 */
	public static final String encrypt(final byte[] rawKey, final String message) {
		try {
			// Create secret key with the given plain key
			// The plain key will be turn into 32bytes key.
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, ALGORITHM_KEY);
			Cipher cipher = Cipher.getInstance(ALGORITHM_CRYPTO);
			//Please note that
            //IV is the abbreviation of the Initialization Vector.
			//and IVs are ciphers in feedback mode.
			//For example, DES in CBC mode and RSA ciphers with OAEP encoding operation.
			byte[] ivBlock = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivBlock);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParamsSpec);
			byte[] encrypted = cipher.doFinal(message.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This is used to decrypt the given message which is encrypted by the method - encrypt.
	 * @param plainStrKey key string to be decrypted with. This must be same with the key when you encrypt the plain message.
	 * @param message string which is to be decrypted. This must be same with the message returning the result of invoking the encrypt method. 
	 * @return decrypted string
	 */
	public static final String decrypt(final String plainStrKey, final String message) {
		return decrypt(blendKey(plainStrKey).getBytes(CHARSET_TRANSCODE), message);
	}
	/**
	 * This is used to decrypt the message which is encrypted by the given raw key.
	 * 
	 * @param rawKey the key bytes. Its length must be 32 bytes. 
	 * This key can be created by the predefined <code>getRawKey</code> method.
	 * It must be same with the key when you encrypt the plain message.
	 * @param message string which is to be decrypted. This must be same with the message returning the result of invoking the encrypt method. 
	 * @return decrypted string
	 */
	public static final String decrypt(final byte[] rawKey, final String message) {
		try {
			// Create secret key with the given plain key
			// The plain key will be turn into 32bytes key.
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, ALGORITHM_KEY);
			Cipher cipher = Cipher.getInstance(ALGORITHM_CRYPTO);
			
			//Please note that
            //IV is the abbreviation of the Initialization Vector.
			//and IVs are ciphers in feedback mode.
			//For example, DES in CBC mode and RSA ciphers with OAEP encoding operation.
			byte[] ivBlock = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivBlock);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParamsSpec);
			byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(message));
			return new String(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This is used to generate a 32 bit randomized secret key from the given seed.
	 * @param seed key string to be used for a randomized secret key
	 * @return a randomized key based on the given seed
	 */
	public static final byte[] getRawKey(final String seed) {
		byte[] rawSeed = seed.getBytes(CHARSET_TRANSCODE); // for 32byte == 256bit
		SEC_RAND.setSeed(rawSeed);
		KEY_GEN.init(256, SEC_RAND);
		return KEY_GEN.generateKey().getEncoded();
	}
	
	/**
	 * This is used to hash the given message and
	 * can be used to hash a password to be inserted into database as well.
	 * Please note that Hashed message cannot be turned into a plain text.
	 * @param message string is to be hashed.
	 * @return hashed string.
	 */
	public static final String hash(final String message) {
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM_HASH);
			byte[] msg = message.getBytes(CHARSET_TRANSCODE);
			md.update(msg);
			byte[] hash = md.digest();
			
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public static void main(String[] args) {
		//This is an example for usage of this.
		//In case of using a randomized key
		//This case can be use to encrypt a string for an authentication.		
		//So that, if you do not need a permanent key string and only need an one time encryption.
		String seedForRaw = "2019=07-12";
		byte[] rawKey = getRawKey(seedForRaw);
		String message = "This is what I do. This is who I am.";
		String encrypted = encrypt(rawKey,message);
		String decrypted = decrypt(rawKey, encrypted);
		System.out.println("Plain Message:" + message);
		System.out.println("Encrypted Message with raw key:" + encrypted);
		System.out.println("Decrypted Message with raw key:" + decrypted);

		//In case of using a plain string key
		//This case can be used to encrypt a message against a certain user.
		String plainKey = "This is the first test";
		encrypted = encrypt(plainKey,message);
		decrypted = decrypt(plainKey, encrypted);
		System.out.println("Plain Message:" + message);
		System.out.println("Encrypted Message with plain text:" + encrypted);
		System.out.println("Decrypted Message with plain text:" + decrypted);
	}
}
