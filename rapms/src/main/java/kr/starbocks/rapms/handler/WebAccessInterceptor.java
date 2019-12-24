/**
 * 
 */
package kr.starbocks.rapms.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Playdata
 *
 */
public class WebAccessInterceptor extends HandlerInterceptorAdapter {
	static final Logger logger = LoggerFactory.getLogger(WebAccessInterceptor.class);
	static final String UNKNOWN = "unknown";
	static final String START_AT = "_START_AT_";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Marking starting time
		request.setAttribute(START_AT, Long.valueOf(System.currentTimeMillis()));
		// Requested URL
		String uriPath = request.getRequestURI();
		
		// Remote Address
		String remoteAddr = request.getHeader("X-Forwarded-For");
		if(StringUtils.isBlank(remoteAddr) || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		}
		if(StringUtils.isBlank(remoteAddr) || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		if(StringUtils.isBlank(remoteAddr) || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("HTTP_CLIENT_IP");
		}
		if(StringUtils.isBlank(remoteAddr) || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(StringUtils.isBlank(remoteAddr) || UNKNOWN.equalsIgnoreCase(remoteAddr)) {
			remoteAddr = request.getRemoteAddr();
		}
		
		// User Agent
		String userAgent = request.getHeader("User-Agent");
		if(userAgent == null || userAgent.isEmpty()) {
			userAgent = "NO_AGENT";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<== ")
		.append(uriPath)
		.append(" FROM ")
		.append(remoteAddr)
		.append(" WITH ")
		.append(userAgent);
		
		logger.info(sb.toString());
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Requested at
		Long startTime = (Long)request.getAttribute(START_AT);

		// Requested URL
		String uriPath = request.getRequestURI();
		StringBuilder sb = new StringBuilder();
		sb.append("==> ")
		.append("Elapsed Time: ")
		.append(String.format("%,d",System.currentTimeMillis() - startTime.longValue()))
		.append("ms taken to process ")
		.append(uriPath);
		logger.info(sb.toString());
		super.postHandle(request, response, handler, modelAndView);
	}
}
