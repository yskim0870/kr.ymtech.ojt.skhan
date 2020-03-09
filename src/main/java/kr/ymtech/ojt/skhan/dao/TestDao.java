/*
 * This file is generated under this project, "skhan_test_project". 
 *
 * @author yskim
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2020. 3. 9. 오후 1:15:21
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
 * @since: 2020. 3. 9. 오후 1:15:21
*/
package kr.ymtech.ojt.skhan.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import open.commons.Result;

/**
 *
 *
 * @author: yskim
 * @date: 2020. 3. 9. 오후 1:15:21
 *
 */
@Repository(TestDao.BEAN_QUALIFIER)
public class TestDao {

	private static final Logger logger = LogManager.getLogger(TestDao.class);

	public static final String BEAN_QUALIFIER = "kr.ymtech.ojt.skhan.dao.TestDao";

	/**
	 * 함수 설명 <br>
	 *
	 * <pre>
	 *  
	 * [개정이력]
	 *      날짜      | 작성자 |       내용 
	 * ------------------------------------------
	 * 2020. 3. 9.    yskim   최초작성
	 * </pre>
	 *
	 * @return
	 */
	public Result<String> testDaoFunction() {
		return null;
	}

}
