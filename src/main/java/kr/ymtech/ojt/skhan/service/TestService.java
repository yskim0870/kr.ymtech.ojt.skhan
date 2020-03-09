/*
 * This file is generated under this project, "skhan_test_project". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2020. 3. 9. 오후 1:15:15
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
 * @since: 2020. 3. 9. 오후 1:15:15
*/
package kr.ymtech.ojt.skhan.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import open.commons.Result;

import kr.ymtech.ojt.skhan.dao.TestDao;

/**
 *
 *
 * @author: yskim
 * @date: 2020. 3. 9. 오후 1:15:15
 *
 */
@Service(TestService.BEAN_QUALIFIER)
public class TestService {

	private static final Logger logger = LogManager.getLogger(TestService.class);
	
	public static final String BEAN_QUALIFIER = "kr.ymtech.ojt.skhan.service.TestService";

	@Autowired
	@Qualifier(TestDao.BEAN_QUALIFIER)
	private TestDao testDao;

	/**
	 * 함수 설명
	 * <br>
	 *
	 * <pre> 
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2020. 3. 9.    yskim   최초작성
	 * </pre>
	 *
	 * @return
	 */
	public Result<String> testServiceFunction() {
		return testDao.testDaoFunction();
	}

}
