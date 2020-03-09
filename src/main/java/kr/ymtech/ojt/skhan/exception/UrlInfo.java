/**
 * @ProgramName : UrlInfo.java
 *
 * Description: This is a UrlInfo, and is executed continuously and interrupted
 * Only to perform in case of reset or failure detection.
 * @Package : ksb.beeai.webtoolkit.controller.exception
 * @Project : ksb.beeai.webtoolkit                            
 * @Type :  UrlInfo
 *
 * @Revision_history:
 *   Date : 2017. 9. 20.,  Author : Park_Jun_Hong_(fafanmama_at_naver_com),  Version : 1.0
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
package kr.ymtech.ojt.skhan.exception;

import java.util.Map;

/**
 * @subject : URL 정보 제공 클래스.
 *
 * @revision_history : Park_Jun_Hong_(fafanmama_at_naver_com), 2017. 9. 20., 1.0
 */
public class UrlInfo {

    private final String method;

    private final String url;

    private final String urlPattern;

    private final Object variables;

    private final Map<String, Object> parameters;

    /**
     * 
     * @param method
     * @since 2017. 9. 20.
     */
    public UrlInfo(String method, String url, String urlPattern, Object variables, Map<String, Object> parameters) {
        this.method = method;
        this.url = url;
        this.urlPattern = urlPattern;
        this.variables = variables;
        this.parameters = parameters;
    }

    /**
     *
     * @return the method
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 26.
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @return the parameters
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 20.
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     *
     * @return the url
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 20.
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return the urlPattern
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 20.
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     *
     * @return the variables
     *
     * @auth Park_Jun_Hong_(fafanmama_at_naver_com)
     * @since 2017. 9. 20.
     */
    public Object getVariables() {
        return variables;
    }

}
