/**
 * 
 */
package kr.starbocks.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * This provides couple of various methods that are usable with ease, convenient and simple way.
 * Many of methods is referring to the 3rd party libraries - Apache common library. 
 * If you want to use this class, you need to confirm if those libraries were added into the class path.
 * 
 * @author Playdata
 * @since 1.8
 */
public class StarbocksUtil {
	private final static char[] AUTH_CHARS = {
			  '0','1','2','3','4'
			 ,'5','6','7','8','9'			 
			 ,'A','B','C','D','E'
			 ,'F','G','H','I','J'
			 ,'K','L','M','N','O'
			 ,'P','Q','R','S','T'
			 ,'U','V','W','X','Y'
			 ,'Z'
	};
	private final static char[] SEED_CHARS = {
			 'S','v','E','M','1'
			 ,'R','w','L','a','O'
			 ,'p','l','n','T','P'
			 ,'9','G','h','X','U'
			 ,'N','3','8','d','A'
			 ,'o','Z','e','H','j'
			 ,'y','u','t','2','g'
			 ,'V','r','B','0','F'
			 ,'5','z','Y','7','W'
			 ,'c','4','C','s','K'
			 ,'D','k','I','Q','b'
			 ,'q','x','f','i','J'
			 ,'m','6'
	};
//	private final static char[] SEED_CHARS= {
//			  '0','1','2','3','4'
//			 ,'5','6','7','8','9'
//			 ,'a','b','c','d','e'
//			 ,'f','g','h','i','j'
//			 ,'k','l','m','n','o'
//			 ,'p','q','r','s','t'
//			 ,'u','v','w','x','y'
//			 ,'z','A','B','C','D'
//			 ,'E','F','G','H','I'
//			 ,'J','K','L','M','N'
//			 ,'O','P','Q','R','S'
//			 ,'T','U','V','W','X'
//			 ,'Y','Z'
//	}
//	;
	
	/* If you want to change antilogarithm, 
	 * just set number greater than zero and less or equal than SEED_CHARS length.
	 * Please note that once you set some value into this, never change the value from that moment.
	 */
	private final static int ANTILOGARITHM = SEED_CHARS.length; 
	private final static String DEFAULT_SEPERATOR = ",";
	private final static String DEFAULT_TIMEFORMAT = "%02d:%02d:%02d";
	
	public final static char FILE_EXT_SEPERATOR = '.';
	private final static char FILE_COPY_MODE = 'C';
	private final static char FILE_MOVE_MODE = 'M';
	
	private final static int PATH_DELI_POS = (int)(2 / (ANTILOGARITHM / (float)SEED_CHARS.length));
	private final static int PATH_LEAST_LEN = 12;
	private final static char PATH_DEFAULT_PADDING = '0';
	
	private final static int PAGINATION_PAGE_CNT_PER_BLOCK = 10;
	private final static int PAGINATION_ROW_CNT_PER_PAGE = 30;
	
	private final static String PATTERN_REPLACE_PREFIX = "\\$\\{";
	private final static String PATTERN_REPLACE_SURFIX = "\\}";
	private final static Pattern PATTERN_SIBLING_NAME = Pattern.compile("\\((\\d)\\)$"); 
	
	private final static Random RANDOM = new Random();
	
	public final static String URL_SEPARATOR = "/";
	
	public final static String[] MONETRAY_UNIT_KO_KR = {"만","억","조","경"};

	/**
	 * copy the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean copy(final File src, final File dest, final boolean overwrite) {
		if(src == null || !src.exists()) return false;
		Path from = src.toPath();
		Path to = null;
		if(dest == null ) {
			to = getSiblingOf(from);
		} else {
			to = dest.toPath();
		}
		return move(from, to, overwrite);
	}
	/**
	 * copy the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean copy(final Path src, final Path dest, final boolean overwrite) {
		return copyOrMove(FILE_COPY_MODE, src, dest,overwrite);
	}

	/**
	 * copy the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean copy(final String src, final String dest, final boolean overwrite) {
		if(StringUtils.isBlank(src)) return false;
		
		Path from = FileSystems.getDefault().getPath(src);
		if(!Files.exists(from)) return false;
		
		String toPath = dest;
		if(StringUtils.isBlank(toPath)) toPath = getSiblingOf(src);
		
		Path to = FileSystems.getDefault().getPath(toPath);
		return copy(from, to, overwrite);
	}
	
	/*
	 * Along with the mode, Copy or Move the given file to the given destination file or directory.
	 * @param mode indicate the action mode = copy or move
	 * @param src the source path to be moved or be copied
	 * @param dest the destination path to move or be copied
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	private final static boolean copyOrMove(final char mode, final Path src, final Path dest, final boolean overwrite) {
		if(src==null || !Files.exists(src)) return false;
		Path toPath = dest;
		if(toPath == null) {
			toPath = getSiblingOf(src);
		}
		if(!Files.exists(toPath) && Files.isDirectory(toPath)) {
			// Create directory
			toPath = createDir(toPath);
			if(toPath==null) return false; // Creating failed.
		}
		try {
			if(Files.isDirectory(toPath)) {
				Path toName = toPath.resolve(src.getFileName());
				if(!overwrite && Files.exists(toName)) {
					toName = toPath.resolve(getSiblingOf(src.getFileName()));
				}
				toPath = toName;				
			} else {
				if(!overwrite && Files.exists(toPath)) {
					toPath = getSiblingOf(toPath);
				}
			}
			if(FILE_COPY_MODE==mode) {
				Files.copy(src, toPath, StandardCopyOption.REPLACE_EXISTING);
			} else {
				Files.move(src, toPath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			// Nothing to do here
			return false;
		}
		return true;
	}

	/**
	 * Creating a directory by creating all nonexistent parent directories with the given path first.
	 * If creating a directory succeeded and the path already exists,
	 * return path object which has been created or itself. Otherwise null will returns.
	 * @param path
	 * @return the created path with the given path if creating succeeded. Otherwise null
	 */
	public final static Path createDir(final Path path) {
		Path dir = null;
		
		try {
			dir = Files.createDirectories(path);
		} catch (IOException e) {
			//Nothing todo
		}
		
		return dir;
	}
	
	/**
	 * Creating a directory by creating all nonexistent parent directories with the given path string first.
	 * If creating a directory succeeded and the path already exists,
	 * return path object which has been created or itself. Otherwise null will returns.
	 * @param path 
	 * @return
	 */
	public final static Path createDir(final String path) {
		Path dir = null;
		try {
			dir = FileSystems.getDefault().getPath(path);			
			dir = Files.createDirectories(dir);
		} catch (IOException e) {
			//Nothing todo
		}
		
		return dir;
	}
	
	/**
	 * Delete a file or a directory if it exists on the given path.
	 * In case that the given path is a directory and it has file(s),
	 * the given path will not be deleted.
	 * @param path to be deleted
	 * @return the given path object
	 */
	public final static Path delete(final Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// nothing todo here
			return null;
		}
		return path;
	}
	
	/**
	 * Delete a file or a directory if it exists on the given string.
	 * In case that the given path is a directory and it has file(s)
	 * and it fails to convert the given string to path
	 * the given path will not be deleted.
	 * @param path path string to be deleted
	 * @return the given path string
	 */
	public final static Path delete(final String path) {
		Path p = null;
		try {
			p = FileSystems.getDefault().getPath(path);
			Files.deleteIfExists(p);
		} catch (IOException e) {
			// nothing todo here
		}
		return p;
	}
	
	/**
	 * Returns a string that the given number of seconds is formated with <code>hh:mm:ss</code>.
	 * For example, "123" is set as an argument as the second and
	 * this method will convert its value to <code>02:03</code> 
	 * If the formated string starts with '00' then first 3 that points at hours characters will be eliminated. 
	 * @param second the number of seconds to be formated with 'hh:mm:dd' 
	 * @return a string to be formated with <code>hh:mm:ss</code>.
	 */
	public static String formatTime(final int second) {
		String result = formatTime(second, DEFAULT_TIMEFORMAT);
		return result.startsWith("00")?result.substring(3):result;
	}
	
	/**
	 * Returns a string that the given number of seconds is formated with the given format string.
	 * @param second the number of seconds to be formated.
	 * @param format the format string that this will format with.
	 * @return a string to be formated with the given format string.
	 */
	public static String formatTime(final int second, final String format) {
		return String.format(format, 
				TimeUnit.SECONDS.toHours(second),
				TimeUnit.SECONDS.toMinutes(second) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(second)),
				TimeUnit.SECONDS.toSeconds(second) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(second)));
	}
	/**
	 * Returns the string for an approximate price range
	 * For example, to get an approximate string of price range for 123,000,000
	 * this is returning "1억2천만원 대" 
	 * @param price
	 * @return
	 */
	public final static String getApproximatePriceRange(final double price) {
		if(price < Math.pow(10, 4)) return "";
		
 		double unit = 0d;
		int quotient = 0;
		double remainder = price;
		StringBuilder sb = new StringBuilder();
		for(int i=MONETRAY_UNIT_KO_KR.length;0<i;i--) {
			unit = Math.pow(10, 4*i);
			quotient = (int)(remainder / unit);
			remainder = remainder - unit*quotient;
			
			if(i==1) {
				quotient/=1000;
			}
			if(0<quotient) {
				sb.append(" ");
				if(i==1) {
					if(quotient<4) sb.append("초반");
					else if(quotient>6) sb.append("후반");
					else sb.append("중반");
				}else {
					sb.append(quotient).append(MONETRAY_UNIT_KO_KR[i-1]);
				}
			}
		}
		sb.append("대");
		return sb.toString().trim();
	}
	/**
	 * Returns an extension of the given file.
	 * @param file The file object that a file extension will be extracted from.
	 * @return file extension string
	 */
	public final static String getExtensionOf( final File file ) {
		if ( file == null ) 	return null;
		return getExtensionOf(file.getName());
	}
	
	/**
	 * Returns an extension of a file on the given path 
	 * @param path the path object
	 * @return file extension string
	 */
	public final static String getExtensionOf( final Path path ) {
		if ( path == null )	return null;
		
		return getExtensionOf(path.getFileName().toString());
	}
	
	/**
	 * Returns an extension of a file on the given path.
	 * If the given path is directory, is not a file, is not readable and do not appear,
	 * <code>null</code> will return.
	 * @param path string to represent a file path
	 * @return file extension string
	 */
	public final static String getExtensionOf( final String path ) {
		if(StringUtils.isBlank(path)) return null;
		
		if (path.indexOf(FILE_EXT_SEPERATOR) < 0) return null;
		int dotIndex = path.lastIndexOf(FILE_EXT_SEPERATOR);
		return path.substring(dotIndex + 1);
	}
	
	/**
	 * Convert the formatted string to long number
	 * @param idStr the string to be converted to number
	 * @return number with long type
	 */
	public final static long getId( final String idStr ) {
		if ( StringUtils.isBlank(idStr)) return 0;
		String str = idStr.trim();
		int len = str.length();
		long id = 0L;
		char ch;
		for ( int i = 0; i < len; i++ ) {
			ch = str.charAt(i);
			id += getIndexOf(ch) * (Math.pow(ANTILOGARITHM, len - i - 1));
		}
		return id;
	}

	/**
	 * Return numbers which the given string array is converted to.
	 * @param idStrs string array to be converted to number.
	 * @return numbers with long type.
	 */
	public final static long[] getIds( final String ... idStrs ) {
		long[] ids = new long[0];
		if(ArrayUtils.isEmpty(idStrs)) return ids;
		for(String idStr : idStrs) {
			if(StringUtils.isBlank(idStr)) continue;
			ids = ArrayUtils.add(ids, getId(idStr));
		}
		return ids;
	}

	/**
	 * Convert long type number to string
	 * @param id the number to be converted to String
	 * @return The converted string 
	 */
	public static final String getIdStr(final long id) {
		StringBuilder sb = new StringBuilder();
		long quotient = id;
		long rest = 0L;
		do {
			rest = quotient % ANTILOGARITHM;
			sb.insert(0, SEED_CHARS[(int) rest]);
			quotient = quotient / ANTILOGARITHM;
		} while ( quotient > ANTILOGARITHM );
		if(id>=ANTILOGARITHM)sb.insert(0, SEED_CHARS[(int) quotient]);
		return sb.toString();
	}
	
	/**
	 * Return string array which the given number array is converted to.
	 * @param ids number array with long type to be converted to. 
	 * @return string array
	 */
	public final static String[] getIdStrs(final long ... ids) {
		String[] idStrs = new String[0];
		if(ArrayUtils.isEmpty(ids)) return idStrs;
		for(long id : ids) {
			if(id < 0) continue; //do not support negative value/
			idStrs = ArrayUtils.add(idStrs, getIdStr(id));
		}
		return idStrs;
	}
	
	/*
	 * find the index of character among the character array;
	 */
	private final static int getIndexOf( final char ch ) {
		int i = 0;
		for ( ; i < ANTILOGARITHM; i++ ) {
			if ( ch == SEED_CHARS[i] ) {
				break;
			}
		}
		return i;
	}

	/**
	 * Return the name part of the given file.
	 * If it is a directory then returns only name of the directory.
	 * @param file the file object that its name is extracted from
	 * @return a name of the given file object
	 */
	public final static String getNameOf(final File file) {
		if(file == null) return null;
		return getNameOf(file.toPath());
	}
	
	/**
	 * Return the name part of the given file.
	 * If it is a directory then returns only name of the directory.
	 * @param path the path object that its name is extracted from
	 * @return a name of the given file object
	 */
	public final static String getNameOf(final Path path) {
		if(path == null) return null;
		
		String name = path.getFileName().toString();
		if(Files.isDirectory(path)) return name;
		
		if (name.indexOf(FILE_EXT_SEPERATOR) < 0) return null;
		int dotIndex = name.lastIndexOf(FILE_EXT_SEPERATOR);
		return name.substring(0, dotIndex);
	}
	
	/**
	 * Return the name part of the given file.
	 * If it is a directory then returns only name of the directory.
	 * @param path the path string that its name is extracted from
	 * @return a name of the given file object
	 */
	public final static String getNameOf( final String path ) {
		if(StringUtils.isBlank(path)) return null;
		Path p = FileSystems.getDefault().getPath(path);
		return getNameOf(p);
	}
	
	/**
	 * Make the string for pagination with the given numbers and the default row and page block count.
	 * 
	 * @param totalCnt the total number of row count which is fetched from DB
	 * @param currentPageNo the number of current page to show.
	 * @return string pagination string to be made with the given numbers. 
	 */
	public final static String getPagination(final long totalCnt, final int currentPageNo) {
		return getPagination(totalCnt, currentPageNo, PAGINATION_ROW_CNT_PER_PAGE, PAGINATION_PAGE_CNT_PER_BLOCK);
	}
	
	/**
	 * Make the string for pagination with the given numbers and the default row and page block count.
	 * 
	 * @param totalCnt the total number of row count which is fetched from DB
	 * @param currentPageNo the number of current page to show.
	 * @param rowCntPerPage the number of row size per a page.
	 * @return string pagination string to be made with the given numbers. 
	 */
	public final static String getPagination(final long totalCnt, final int currentPageNo, final int rowCntPerPage) {
		return getPagination(totalCnt, currentPageNo, rowCntPerPage, PAGINATION_PAGE_CNT_PER_BLOCK);
	}
	
	/**
	 * Make the string for pagination with the given numbers.
	 * @param totalCnt the total number of row count which is fetched from DB
	 * @param currentPageNo the number of current page to show.
	 * @param rowCntPerPage the number of row size per a page.
	 * @param pageCntPerBlock the number of page size per a page block. 
	 * @return string pagination string to be made with the given numbers. 
	 */
	public final static String getPagination(final long totalCnt, final int currentPageNo, final int rowCntPerPage, final int pageCntPerBlock) {
		// Set current page number
		int currentPage = currentPageNo;
		int rowCntPage = (rowCntPerPage < 1) ? PAGINATION_ROW_CNT_PER_PAGE : rowCntPerPage;
		int pageCntBlock = (pageCntPerBlock < 1) ? PAGINATION_PAGE_CNT_PER_BLOCK : pageCntPerBlock;
		
		// Calculate the total count of the whole pages 
		int totalPageCnt = (int)(totalCnt / rowCntPage); // 20
		totalPageCnt += (totalCnt % rowCntPage > 0) ? 1: 0; // 21
		
		// Limit the current page number less than the last page.
		if(currentPage>totalPageCnt) currentPage = totalPageCnt;  
		if(currentPage<1) currentPage = 1;

		// Calculate the page block number which the current page belongs to.
		int curpageBlock = currentPage /  pageCntBlock ;
		curpageBlock += (currentPageNo % pageCntBlock > 0) ? 1 : 0; 
		
		// Calculate the starting and the ending index of pages on the current page block.
		int startPage = (curpageBlock-1)*pageCntBlock+1;
		int endPage = (totalPageCnt < curpageBlock*pageCntBlock) ? totalPageCnt: curpageBlock*pageCntBlock; 
		
		// Set the number for the previous page.
		int prevPage = currentPageNo -1;
		prevPage = (prevPage < 1) ? 1: prevPage;

		// Set the number for the next page.
		int nextPage = currentPageNo + 1;
		nextPage = (totalPageCnt < nextPage) ? totalPageCnt : nextPage;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class='pagination'>\n");
		System.out.println("pageCntBlock : "+pageCntBlock);
		System.out.println("currentPage : "+currentPage);
		if(pageCntBlock < currentPage) {
			sb.append("<li><a id='paging' value='" + getIdStr(1) + "'><span>First</span></a></li>\n");
			sb.append("<li><a id='paging' value='" + getIdStr(startPage - pageCntBlock) + "'>◀</a></li>\n");
		}
		for(int i=startPage-1;i<=endPage; i++ ) {
			if ( i == 0) {}
			else if(i == currentPage) {
				if(endPage > 1) sb.append("<li class='active'><a id='paging'  value='" + getIdStr(i) + "'>").append(i).append("</a></li>\n");
				else sb.append("<li><a>"+i+"</a></li>\n");
			} else {
				sb.append("<li><a id='paging' value='" + getIdStr(i) + "'>").append(i).append("</a></li>\n");
			}
		}
		if(endPage < totalPageCnt) {
			sb.append("<li><a id='paging' value='" + getIdStr(startPage + pageCntBlock) + "'>▶</a></li>\n");
			sb.append("<li><a id='paging' value='" + getIdStr(totalPageCnt) + "'><span>Last</span></a></li>\n");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	/**
	 * Return the path string with the given number 
	 * The string is separated with the length based on the antilogarithm.
	 * @param id number value with long type to retrieve a basic path
	 * @return the path string based on the given string
	 */
	public final static String getPath(final long id) {
		if(id < 0) return null; // do not support negative value;
		return getPath(String.valueOf(id));
	}
	
	/**
	 * Return the path string with the given string.
	 * The string is separated with the length based on the antilogarithm .
	 * If the length of the given string is less than least length of an default path, 
	 * it will be padded with default padding character. 
	 * @param str the base string to be split to path string
	 * @return the path string based on the given string
	 */
	public final static String getPath(final String str) {
		return getPath(str, File.separator);
	}
	
	/*
	 * This is used to make a path with the given string and separator
	 */
	private final static String getPath(final String str, final String separator) {
		if(StringUtils.isBlank(str)) return null;
		
		String base = str.length()< PATH_LEAST_LEN ? lpad(str,PATH_LEAST_LEN,PATH_DEFAULT_PADDING) : str;
		StringBuilder src = new StringBuilder(base);
		src.setLength(src.length()-PATH_DELI_POS);
		src.reverse();
		StringBuilder path = new StringBuilder();
		for(int i=0;i<src.length();i++) {
			path.append(src.charAt(i));
			if(i%PATH_DELI_POS==0) {
				path.append(separator);
			}
		}
		path.reverse();
		path.append(separator);
		return path.toString();
	}
	
	/**
	 * Return the Url path string with the given number 
	 * The string is separated with the length based on the antilogarithm.
	 * @param id number value with long type to retrieve a basic path
	 * @return the path string based on the given string
	 */
	public final static String getUrlPath(final long id) {
		if(id < 0) return null; // do not support negative value;
		return getUrlPath(String.valueOf(id));
	}
	/**
	 * Return the Url path string with the given number 
	 * The string is separated with the length based on the antilogarithm.
	 * @param id number value with long type to retrieve a basic path
	 * @return the path string based on the given string
	 */
	public final static String getUrlPath(final String str) {
		return getPath(str, URL_SEPARATOR);
	}
	
	/**
	 * Returns the randomized string within the given length.
	 * @param len the length that string will be created with random.
	 * @return the randomized string.
	 */
	public final static String getRandomStr(final int len) {
		if(len <1) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++) {
			sb.append(AUTH_CHARS[RANDOM.nextInt(AUTH_CHARS.length)]);
		}
		return sb.toString();
	}

	/*
	 * Returns a sibling name of the given path.
	 * This can be used to move or copy a file to other name as keeping the original name.
	 * @param path the original path
	 * @return path Path object with the sibling name that has index number surrounded with parenthesis.
	 */
	private final static Path getSiblingOf(final Path path) {
		if(path == null) return null;
		String name = getNameOf(path.getFileName());
		String ext = getExtensionOf(path);
		
		// need to improve logic to avoid a duplicate of sibling names.	
		// fetching file list in path's and filter it out duplication
		if(StringUtils.isBlank(name)) return null;
		Matcher m = PATTERN_SIBLING_NAME.matcher(name);
		if(m.find()) {
			String idxStr = m.group().replaceAll("\\D", "");
			int idx = 0;
			try {
				idx = Integer.parseInt(idxStr) + 1;
			} catch (NumberFormatException e) {
			}
			name = m.replaceFirst("("+idx+")");
		} else {
			name +="(0)";
		}
		name = StringUtils.isBlank(ext) ? name : name+FILE_EXT_SEPERATOR+ext;
		return path.resolveSibling(name);
	}

	/*
	 * Returns a sibling name of the given file name.
	 * This can be used to move or copy a file to other name as keeping the original name.
	 * @param fileName the original file name
	 * @return sibling filename with index number surrounded with parenthesis.
	 */
	private final static String getSiblingOf(final String path) {
		if(StringUtils.isBlank(path)) return null;
		Path sibling = getSiblingOf(FileSystems.getDefault().getPath(path));
		return sibling!=null ? sibling.toString() : null;
		
	}
	
	/**
	 * padding the given string with a character 
	 * @param str the string padded
	 * @param len the length after padding
	 * @param padding the character to be padded.
	 * @return padded string
	 */
	public final static String lpad(final String str, final int len, final char padding) {
		String base = str != null && !str.isEmpty() ? str : "";
		return String.format("%"+len+"s", base).replace(' ', padding);
	}

	/**
	 * Move the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean move(final File src, final File dest, final boolean overwrite) {
		if(src == null || !src.exists()) return false;
		Path from = src.toPath();
		Path to = null;
		if(dest == null ) {
			to = getSiblingOf(from);
		} else {
			to = dest.toPath();
		}
		return move(from, to, overwrite);
	}
	/**
	 * Move the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean move(final Path src, final Path dest, final boolean overwrite) {
		return copyOrMove(FILE_MOVE_MODE, src, dest, overwrite);
	}

	/**
	 * Move the given file to the given destination file or directory.
	 * 
	 * @param src the source path to be moved
	 * @param dest the destination path to move
	 * @param overwrite if overwrites the file on the existing file,set true. Otherwise false
	 */
	public final static boolean move(final String src, final String dest, final boolean overwrite) {
		if(StringUtils.isBlank(src)) return false;
		
		Path from = FileSystems.getDefault().getPath(src);
		if(!Files.exists(from)) return false;
		
		String toPath = dest;
		if(StringUtils.isBlank(toPath)) toPath = getSiblingOf(src);
		
		Path to = FileSystems.getDefault().getPath(toPath);
		return move(from, to, overwrite);
	}
	
	/**
	 * Replace all substrings of the given source string that matches the given target expression
	 * with the given replacement expression.
	 * For example, if you want to replace <quote><code>${foo}</code></quote>
	 * with <quote><code>bar</code></quote> among the source string,
	 * just set the target with <quote><code>foo</code></quote> and set the usePix with <quote><code>true</code></quote>.
	 * This method guarantees at most 500 milliseconds to replace string 1M times. 
	 * @param src the source string to find the given target in.
	 * @param target the expression which this string is to be matched to.
	 * @param replacement the string to be substituted for each match
	 * @param usePix the boolean to indicate if the prefix and the suffix is to be used.
	 * @return the result string.
	 */
	public final static String replaceAll(final String src, final String target, final String replacement, final boolean usePix) {
		// return null if any one among arguments is blank.
		if(StringUtils.isBlank(src) || StringUtils.isBlank(target) || StringUtils.isBlank(replacement)) return null;
		
		String targetPattern = target;
		// If the argument indicate is to use prefix and suffix, append those into the target string. 
		if(usePix) targetPattern = PATTERN_REPLACE_PREFIX + targetPattern + PATTERN_REPLACE_SURFIX;
		// Splits the source string around matches of the given target expressions.
		String[] splitStrs = src.split(targetPattern);
		
		// Create string joiner to replace each substring of these strings 
		// that matches the given target expressions
		// with the given replacement expressions.
		StringJoiner joiner = new StringJoiner(replacement);
		for(String str : splitStrs) {
			joiner.add(str);
		}
		return joiner.toString();		
	}
	/**
	 * Returns the number array with long type which is converted from the given string 
	 * after split with the default separator. 
	 * @param str
	 * @return
	 */
	public final static long[] toLong(final String str) {
		long[] rslt = new long[0];
		if(StringUtils.isBlank(str)) return rslt;
		String[] split = str.replaceAll("[^\\d|.|,]", "").split(DEFAULT_SEPERATOR);
		if(ArrayUtils.isEmpty(split)) return rslt;
		Double ele = null;
		for(String s : split) {
			if(StringUtils.isBlank(s)) continue;
			try {
				ele = Double.valueOf(s);
				rslt = ArrayUtils.add(rslt, ele.longValue());
			} catch (NumberFormatException e) {
				//nothing to do here
				//if an exception occurs. just skip converting string to long.
			}
		}
		return rslt;
	}
	
	/**
	 * You can test any of those methods in this class.
	 * @param args
	 */
	public static void main(String[] args) {
//		MailUtil mailUtil = MailUtil.getInstance();
//		Map<String, String> params = new HashMap<String,String>();
//		params.put("USER_NAME", "회원");
//		params.put("SERVER_DNS","localhost");
//		params.put("AUTH_CODE","abc");
//		System.out.println("SEND to naver");
//		int rslt = mailUtil.send(new String[] {"jotong2000@naver.com", "jotong2000@daum.net"}, "${USER_NAME}님 스타복스 회원 가입을 위한 인증 메일입니다.", "messages/auth_mail.html", params);
//		System.out.println(rslt);
//		System.out.println(getPagination(100, 1, 5, 5));
//		System.out.println(getPath(12));
//		System.out.println(getPath(123));
//		System.out.println(getUrlPath(123));
		System.out.println(getApproximatePriceRange(188055000d));
	}
}