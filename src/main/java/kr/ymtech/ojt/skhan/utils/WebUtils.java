/**
 * @ProgramName : WebUtils.java
 *
 * Description: This is a WebUtils, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : ksb.beeai.webtoolkit.utils
 * @Project : ksb.beeai.webtoolkit                            
 * @Type :  WebUtils
 *
 * @Revision_history:
 *   Date : 2017. 9. 19.,  Author : Park_Jun_Hong_(fafanmama_at_naver_com),  Version : 1.0
 * 
 * COPYRIGHT(c) 2016, KSB(Knowledge-converged Super Brain) Convergence Research Department, ETRI.
 * 
 * Opensource License:
 * Copyright (C) 2016 The KSB Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.ymtech.ojt.skhan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import open.commons.Result;
import open.commons.http.HttpMethod;
import open.commons.http.HttpUtils;
import open.commons.http.ResponseClient;
import open.commons.net.HttpStatusCode;

import kr.ymtech.ojt.skhan.exception.UrlInfo;

/**
 * @subject : Web Utilities
 *
 * @revision_history : Park_Jun_Hong_(fafanmama_at_naver_com), 2017. 9. 19., 1.0
 */
public class WebUtils {

	private static final Log logger = LogFactory.getLog(WebUtils.class);

	/**
	 * 
	 * @since 2017. 9. 19.
	 */
	private WebUtils() {
	}

	/**
	 * 예외처리 응답 에러 정보를 추가한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 20.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param view
	 * @param errors
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 20.
	 */
	public static void createExceptionResponseError(ModelAndView view, Object errors) {
		view.addObject("error", errors);
	}

	/**
	 * 예외처리 응답 정보를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 20.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param view
	 * @param status
	 * @param ex
	 * @param request
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 20.
	 */
	public static void createThrowableResponse(ModelAndView view, HttpStatus status, Throwable ex, HttpServletRequest request) {

		view.addObject("timestamp", System.currentTimeMillis());
		view.setStatus(status);

		HttpStatusCode httpStatusCode = HttpStatusCode.code(status.value());
		// view.addObject("status", httpStatusCode);
		// view.addObject("exception", ex.getClass());
		// view.addObject("message", ex.getMessage());

		//
		view.addObject("url", new UrlInfo(request.getMethod() //
				, (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) //
				, (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
						+ (request.getQueryString() != null ? "?" + request.getQueryString() : "") //
				, request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) //
				, null));

		view.addObject("code", httpStatusCode.getStatusCode());
		view.addObject("status", httpStatusCode.getStatus());

		view.addObject("desc", ex.getLocalizedMessage());
		view.addObject("message", ex.getLocalizedMessage());

	}

	/**
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 19.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param request
	 * @return
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 19.
	 */
	public static final String getUrlInfo(HttpServletRequest request) {

		StringBuffer sb = new StringBuffer();

		sb.append("Remote: ");
		sb.append(request.getRemoteAddr());
		sb.append(" (");
		sb.append(request.getRemoteHost());
		sb.append("), URL-Pattern: ");
		sb.append(request.getMethod());
		sb.append(" | ");
		sb.append((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
				+ (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
		sb.append(", URL-Variables: ");
		sb.append(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
		sb.append(", URL: ");
		sb.append(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
		sb.append(", Parameters: ");
		sb.append(getRequestParameters(request));

		return sb.toString();
	}

	/**
	 * Request Parameter를 String으로 반환한다. <br>
	 *
	 * <pre>
	 *  
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2020. 2. 24.    yskim   최초작성
	 * </pre>
	 *
	 * @param request
	 * @return
	 */
	private static String getRequestParameters(HttpServletRequest request) {

		Map<String, String[]> requestParameters = request.getParameterMap();

		String parameters = requestParameters.keySet()//
				.stream().map(key -> key + "=" + Arrays.toString(requestParameters.get(key)))//
				.collect(Collectors.joining(", ", "{", "}"));
		return parameters;

	}

	public static Map<String, Object> queryToParameters(String urlQuery) {

		Map<String, Object> parameters = new HashMap<>();

		String[] paramArr = null;

		for (String param : urlQuery.split("&")) {
			paramArr = param.split("=");
			parameters.put(paramArr[0], paramArr[1]);
		}

		return parameters;
	}

	/**
	 * 주어진 메시지를 이용하여 응답 데이타를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 19.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param message
	 * @return
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 19.
	 */
	public static ResponseEntity<Object> result(String message) {
		Result<Object> result = new Result<Object>();
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}

	/**
	 * 주어진 데이터와 메시지를 이용하여 응답 데이터를 생성한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2017. 9. 19.		박준홍			최초 작성
	 * </pre>
	 *
	 * @param data
	 * @param message
	 * @return
	 *
	 * @author: Park_Jun_Hong_(fafanmama_at_naver_com)
	 * @since 2017. 9. 19.
	 */
	public static <T> ResponseEntity<Object> result(T data, String message) {
		Result<T> result = new Result<T>(data, true);
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}

	/**
	 * LOG 보기 용 reponse 처리를 한다. <BR>
	 * (log 보기는 여러줄로 오기 때문에, line을 read할때마다 개행문자 '\n'를 넣어준다.) <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 7. 2.		김영석			최초 작성
	 * </pre>
	 *
	 * @param responseClient
	 * @return
	 *
	 * @author: yskim
	 * @since 2018. 7. 2.
	 */
	public static String readResponseEntity(ResponseClient responseClient) {
		String responseString = null;
		HttpResponse response = responseClient.getResponse();

		if (response != null) {
			if (response.getStatusLine().getStatusCode() == org.apache.http.HttpStatus.SC_NOT_IMPLEMENTED) {
				HttpUtils.consumeQuietly(response.getEntity());
			} else {
				HttpEntity entity = response.getEntity();

				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));

					StringBuffer sb = new StringBuffer();
					String line;

					while ((line = br.readLine()) != null) {
						sb.append(line + System.getProperty("line.separator"));
					}
					responseString = sb.toString();
				} catch (IOException e) {
					responseString = null;
				} catch (Exception e) {
					responseString = null;
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							logger.warn("Response Data를 읽는 도 중 오류가 발생하였습니다. ", e);
						}
					}
				}
			}

			try {
				responseClient.close();
			} catch (IOException ignored) {
				logger.warn("Response Data를 읽는 도 중 오류가 발생하였습니다. ", ignored);
			}
		}

		return responseString;
	}

	/**
	 * HTTPS 요청 <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2019. 11. 7.		김영석			최초 작성
	 * </pre>
	 *
	 * @param http
	 * @param method
	 * @param host
	 * @param port
	 * @param url
	 * @param requestHelper
	 * @param allowPrivateCA
	 * @return
	 *
	 * @author: yskim
	 * @since 2019. 11. 7.
	 */
	public static ResponseClient execute(HttpMethod method, String host, int port, String url, open.commons.http.AbstractDoRequestHelper requestHelper) {
		ResponseClient client = (port != 18890) //
				? HttpUtils.doRequest(method, host, port, url, requestHelper) //
				: HttpUtils.doRequestViaHttps(method, host, port, url, requestHelper, true);
		return client;
	}

}
