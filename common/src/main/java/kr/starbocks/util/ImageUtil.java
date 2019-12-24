/**
 * 
 */
package kr.starbocks.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * This is used to send a mail with a template. This is
 * 
 * @author Playdata
 * @since 1.8
 */
public class ImageUtil {

	public final static String DEFAULT_THUMBNAIL_FORMAT = "jpeg";

	public final static int DEFAULT_THUMBNAIL_WIDTH = 150;
	public final static int DEFAULT_THUMBNAIL_HEIGH = 150;

	public final static String PHOTO_EXTENSION = "jpg";
	public final static String PHOTO_ROOT = "photos";
	private final static String THUMBNAIL_PREFIX = "thumb_";

	/**
	 * This is used to store the uploaded JPGE image to the storage and to make a
	 * thumb nail image from it.
	 * 
	 * @param request      this is used to extract the real context path
	 *                     corresponding to URL
	 * @param uploadedFile this is the uploaded file from a certain form via HTTP
	 * @param photoId      this is the number of photo images' identification
	 * @return path where the uploaded photo image is stored under the context root
	 *         path.
	 */
	public static String storeAndMakeThumbnail(HttpServletRequest request, final CommonsMultipartFile uploadedFile,
			final long photoId) {
		// Root Path for Server real path
		String destRoot = request == null ? File.separator
				: request.getSession().getServletContext().getRealPath(File.separator);
		return storeAndMakeThumbnail(destRoot, uploadedFile, photoId);
	}

	/**
	 * This is used to store the uploaded JPGE image to the storage and to make a
	 * thumb nail image from it.
	 * 
	 * @param realRootPath this is the real context path corresponding to URL
	 * @param uploadedFile this is the uploaded file from a certain form via HTTP
	 * @param photoId      this is the number of photo images' identification
	 * @return path where the uploaded photo image is stored under the context root
	 *         path.
	 */
	public static String storeAndMakeThumbnail(final String realRootPath, final CommonsMultipartFile uploadedFile,
			final long photoId) {
		if(uploadedFile == null || photoId < 1
				|| uploadedFile == null || uploadedFile.getFileItem() == null) return null;
		// Photo Id String
		String photoIdStr = String.valueOf(photoId);
		Path destPath = null;
		Path thumbPath = null;
		String upFileExt = null;
		BufferedImage bufferedImage = null;

		boolean thumbnmailCreated = false;
		try {
			// Split name and extension
			upFileExt = StarbocksUtil.getExtensionOf(uploadedFile.getFileItem().getName());
			destPath = Paths.get(realRootPath, PHOTO_ROOT, StarbocksUtil.getPath(photoId));
			// Create folder if it does not exits.
			StarbocksUtil.createDir(destPath);

			// Make full path for the destination file to be stored.
			destPath = Paths.get(destPath.toString(), photoIdStr + StarbocksUtil.FILE_EXT_SEPERATOR + upFileExt);
			thumbPath = Paths.get(realRootPath, PHOTO_ROOT, StarbocksUtil.getPath(photoIdStr),
					THUMBNAIL_PREFIX + photoIdStr + StarbocksUtil.FILE_EXT_SEPERATOR + upFileExt);
			uploadedFile.transferTo(destPath.toFile());
			bufferedImage = ImageIO.read(destPath.toFile());
			if (bufferedImage != null) {
				thumbnmailCreated = resize(bufferedImage, thumbPath.toFile());
			}
			System.out.println(thumbnmailCreated ? "Thumbnail is created successfully" : "Creating thumbnail failed.");

			return Files.exists(destPath) ? destPath.toString().replace(realRootPath, "") : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * This is used to resize the image to the default size and the jpeg format.
	 * 
	 * @param bufferedImage to be resized to
	 * @param output        the storing photo images path and name
	 * @return true if resizing succeeded. Otherwise false
	 * @throws Exception
	 */
	public static boolean resize(final BufferedImage bufferedImage, final File output) throws Exception {
		return resize(bufferedImage, output, DEFAULT_THUMBNAIL_WIDTH, DEFAULT_THUMBNAIL_HEIGH,
				DEFAULT_THUMBNAIL_FORMAT);

	}

	/**
	 * This is used to resize the image to the given size and options.
	 * 
	 * @param bufferedImage to be resized to
	 * @param output        the storing photo images path and name
	 * @param width         the width to be expected to resize
	 * @param height        the height to be expected to resize
	 * @param format        the format string to be expected to resize - jpeg, gif
	 *                      and so on. However, jpeg is only supported as of now.
	 * @return true if resizing succeeded. Otherwise false
	 * @throws Exception
	 */
	public static boolean resize(final BufferedImage bufferedImage, final File output, final int width,
			final int height, final String format) throws Exception {

		int wd = width;
		int ht = height;
		int biWd = bufferedImage.getWidth();
		int biHt = bufferedImage.getHeight();
		try {
			// Calculate in case of none square image
			if (biWd != biHt) {
				if (biWd > biHt) {
					// Width is lager than height calculate ratio for heigh
					ht = (int) Math.ceil(biHt * (wd / (float) biWd));
				} else {
					wd = (int) Math.ceil(biWd * (ht / (float) biHt));
				}
			}
			if (biWd < wd) {
				wd = biWd;
			}
			if (ht > 1 && biHt < ht) {
				ht = biHt;
			}
			if (!output.exists()) {
				output.createNewFile();
			}
			// change permissions
			output.setReadable(true);
			output.setWritable(true);

			Image thumbnail = bufferedImage.getScaledInstance(wd, ht, Image.SCALE_SMOOTH);

			BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null), thumbnail.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);

			return ImageIO.write(bufferedThumbnail, format, output);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Return the url path string corresponding to the given id.
	 * 
	 * @param photoId
	 * @return url path
	 */
	public final static String getPhotoUrl(final long photoId) {
		return getPhotoUrl(photoId, false);
	}
	/**
	 * Return the thumbnail url path string corresponding to the given id.
	 * @param photoId
	 * @return url path
	 */
	public final static String getThumbnailPhotoUrl(final long photoId) {
		return getPhotoUrl(photoId, true);
	}
	
	private final static String getPhotoUrl(final long photoId, final boolean thumbNail) {
		StringBuilder dest = new StringBuilder(StarbocksUtil.URL_SEPARATOR);
		dest.append(PHOTO_ROOT).append(StarbocksUtil.URL_SEPARATOR).append(StarbocksUtil.getUrlPath(photoId))
		;
		if(thumbNail) dest.append(THUMBNAIL_PREFIX);
		dest.append(photoId).append(StarbocksUtil.FILE_EXT_SEPERATOR).append(PHOTO_EXTENSION);
		return dest.toString();
	}

	public static void main(String[] args) {
//		String folder = "C:\\ExpertJava\\Project\\Starbocks\\photos";
//		try {
//			Path destPath = Paths.get(folder,"20170612_130810.jpg");
//			Path output = Paths.get(folder, "20170612_130810_t.jpg");
//			BufferedImage bufferedImage = ImageIO.read(destPath.toFile());
//			resize(bufferedImage, output.toFile());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			for(int i=1;i<211;i++) {
//				Path destPath = Paths.get(folder, i+".jpg");
//				Path outputFolder = Paths.get(folder, StarbocksUtil.getPath(i));
//				if(!Files.exists(outputFolder)) {
//					Files.createDirectories(outputFolder);
//				}
//				Path output = Paths.get(outputFolder.toString(), THUMBNAIL_PREFIX+i+".jpg");
//				BufferedImage bufferedImage = ImageIO.read(destPath.toFile());
//				resize(bufferedImage, output.toFile());
//				StarbocksUtil.copy(destPath, outputFolder, true);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(StarbocksUtil.getPath(12));
		System.out.println(getPhotoUrl(12));
		System.out.println(getThumbnailPhotoUrl(12));
	}
}