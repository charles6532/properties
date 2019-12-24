/**
 * 
 */
package kr.starbocks.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * This is used to send a mail with a template.
 * This is 
 * @author Playdata
 * @since 1.8
 */
public class MailUtil {
	/*
	 * Ihis is used to create a singleton.
	 */
	private final static MailUtil instance = new MailUtil();

	private Session session;

	private String senderMailAccount = "encore.aistudy@gmail.com";
	private String senderDisplayName = "스타복스";
	private String senderMailPasswd = "encore123!@#";
	private InternetAddress senderAddress = null;
	
	/*
	 * This constructor is used to initiate this singleton.
	 */
	private MailUtil() {
		this.session = getSession();
		try {
			senderAddress = new InternetAddress(senderMailAccount, senderDisplayName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets a <code>mailutil</code> with a default sender name and account
	 * to send a mail with easy and convenient.
	 * @return
	 */
	public final static MailUtil getInstance() {
		return instance;
	}
	
	/*
	 * This is used to create a default mail session.
	 */
	private Session getSession() {
		Session session = null;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com"); // to use google smtp
		prop.put("mail.smtp.port", "587"); // use secure port
		prop.put("mail.smtp.auth", "true"); // need to authenticate and verify user.
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderMailAccount, senderMailPasswd);
			}
		});
		
		return session;
	}
	/**
	 * This is used to send a simple mail with title and body to one recipient by an asynchronous way.
	 * @param recipient string containing mail account to get this message. these mail accounts MUST be matched to mail account format.
	 * @param title string for mail title. This needs to be encoded in UTF-8.
	 * @param msg string for mail body message. This needs to be encoded in UTF-8.
	 * @return number return -1 if any invalid arguments are set, return 0 in case that an error occurs while sending
	 * return 1 if sending a message succeeded.
	 */
	public final int send(final String recipient, final String title, final String msg) {
		return this.send(recipient, title, msg, true);
	}
	
	/**
	 * This is used to send a simple mail with title and body to one recipient.
	 * @param recipient string containing mail account to get this message. these mail accounts MUST be matched to mail account format.
	 * @param title string for mail title. This needs to be encoded in UTF-8.
	 * @param msg string for mail body message. This needs to be encoded in UTF-8.
	 * @return number return -1 if any invalid arguments are set, return 0 in case that an error occurs while sending
	 * @param async if you want to send a mail with asynchronous, set true. Otherwise set false.
	 * return 1 if sending a message succeeded.
	 */
	public final int send(final String recipient, final String title, final String msg, final boolean async) {
		int rslt = 1;
		if(StringUtils.isBlank(recipient)
				|| StringUtils.isBlank(msg)
				|| StringUtils.isBlank(msg)) return -1;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(senderAddress);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(ArrayUtils.toString(recipient)));
			message.setSubject(title);
			message.setContent(msg, "text/html");
			if(async) {
				new Thread(()->{
					try {
						Transport.send(message);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}).start();
			} else {
				Transport.send(message);
			}
		} catch (MessagingException e) {
//			e.printStackTrace();
			rslt = 0;
		}
		
		return rslt;
	}
	
	/**
	 * This is used to send a simple mail with title and body to multiple recipients by an asynchronous way.
	 * 
	 * If more than one mail account are set into recipients, recipients goes into BCC(Blind Carbon Copy)
	 * and recipients are substituted with the sender.
	 * 
	 * @param recipients string array containing mail accounts to get this message. these mail accounts MUST be matched to mail account format.
	 * @param title string for mail title. This needs to be encoded in UTF-8.
	 * @param templatePath path string for mail body message. This needs to be encoded in UTF-8.
	 * @param params keys and values set to be replaced with in the body message. 
	 * @return number return -1 if any invalid arguments are set, return 0 in case that an error occurs while sending
	 * return 1 if sending a message succeeded.
	 */
	public final int send(final String[] recipients, final String title, final String templatePath, final Map<String, String> params) {
		return this.send(recipients, title, templatePath, params, true);
	}
	/**
	 * This is used to send a simple mail with title and body to multiple recipients.
	 * 
	 * If more than one mail account are set into recipients, recipients goes into BCC(Blind Carbon Copy)
	 * and recipients are substituted with the sender.
	 * 
	 * @param recipients string array containing mail accounts to get this message. these mail accounts MUST be matched to mail account format.
	 * @param title string for mail title. This needs to be encoded in UTF-8.
	 * @param templatePath path string for mail body message. This needs to be encoded in UTF-8.
	 * @param params keys and values set to be replaced with in the body message. 
	 * @param async if you want to send a mail with asynchronous, set true. Otherwise set false.
	 * @return number return -1 if any invalid arguments are set, return 0 in case that an error occurs while sending
	 * return 1 if sending a message succeeded.
	 */
	public final int send(final String[] recipients, final String title, final String templatePath, final Map<String, String> params, final boolean async) {
		URL templateUrl = Thread.currentThread().getContextClassLoader().getResource(templatePath);
		Path path = null;
		FileChannel channel = null;
		ByteBuffer buffer = null;
		Charset charset = null;
		StringBuilder sb = new StringBuilder();
		int readCount = 0;
		int rslt = 1; 
		try {
			path = Paths.get(templateUrl.toURI());
			if(!Files.exists(path)) return -2;
			
			// Read File Body
			channel = FileChannel.open(path, StandardOpenOption.READ);
			
			buffer = ByteBuffer.allocate(1024);
			charset = Charset.defaultCharset();
			while(true) {
				readCount = channel.read(buffer);
				if(readCount<0) break;
				buffer.flip();
				sb.append(charset.decode(buffer).toString());
				buffer.clear();
			}
			// Replace the parameters' key of the mail template body with its value.
			String mailbody = sb.toString();
			for(Map.Entry<String, String> e : params.entrySet()) {
				mailbody = StarbocksUtil.replaceAll(mailbody, e.getKey(), e.getValue(), true);
			}
			// Replace the parameters' key of the mail title with its value.
			String mailTitle = title;
			for(Map.Entry<String, String> e : params.entrySet()) {
				mailTitle = StarbocksUtil.replaceAll(mailTitle, e.getKey(), e.getValue(), true);
			}
			//Send Mail
			Message message = new MimeMessage(session);
			message.setFrom(senderAddress);
			message.setSubject(mailTitle);
			message.setContent(mailbody, "text/html");
			//Check if the recipient of this message is multiple or not.
			//If the recipient of this is one, then set recipient as TO.
			//Otherwise, set recipient as CC and set the sender as TO
			if(recipients.length == 1) {
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(ArrayUtils.toString(recipients).replaceAll("\\{|\\}", "")));
			} else {
				message.setRecipients(Message.RecipientType.TO,
						new InternetAddress[] {senderAddress});
				message.setRecipients(Message.RecipientType.BCC,
						InternetAddress.parse(ArrayUtils.toString(recipients).replaceAll("\\{|\\}", "")));
			}
			if(async) {
				new Thread(()->{
					try {
						Transport.send(message);
					} catch (MessagingException e1) {
						e1.printStackTrace();
					}
				}).start();
			} else {
				Transport.send(message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			rslt = -1;
		} finally {
			try {
				if(channel != null) channel.close();
			} catch (IOException e) {
			}
		}
		
		return rslt;
	}
}