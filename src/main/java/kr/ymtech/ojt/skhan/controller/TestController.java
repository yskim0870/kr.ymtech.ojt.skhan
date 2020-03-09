/*
 * This file is generated under this project, "skhan_test_project". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2020. 3. 9. 오후 1:10:07
*/

/**
 * This file is generated under this project, "skhan_test_project". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2020. 3. 9. 오후 1:10:07
*/
package kr.ymtech.ojt.skhan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ymtech.ojt.skhan.service.TestService;
import kr.ymtech.ojt.skhan.utils.WebUtils;

/**
 *
 *
 * @author: yskim
 * @date: 2020. 3. 9. 오후 1:10:07
 *
 */
@Controller
@RestController
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class);

	@Autowired
	@Qualifier(TestService.BEAN_QUALIFIER)
	private TestService testService;

	/**
	 * 함수설명
	 * <br>
	 *
	 * <pre> 
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2020. 3. 9.    yskim   최초작성
	 * </pre>
	 *
	 * @param entity
	 * @param id
	 * @param test
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/test/{id}", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<Object> registDevice(@RequestBody String entity, //
			@PathVariable(name = "id") String id, //
			@RequestParam(name = "test", required = false) String test, //
			HttpServletRequest request, HttpServletResponse response) {

		if (logger.isInfoEnabled()) {
			logger.info(WebUtils.getUrlInfo(request));
		}

		return ResponseEntity.ok(testService.testServiceFunction());
	}

}
