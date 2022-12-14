/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.modules.changzhi.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.modules.changzhi.entity.PolicyNews;
import org.springblade.modules.changzhi.vo.PolicyNewsVO;
import org.springblade.modules.system.service.IDictService;

/**
 * 政策资讯表包装类,返回视图层所需的字段
 *
 * @author Blade
 * @since 2022-01-22
 */
public class PolicyNewsWrapper extends BaseEntityWrapper<PolicyNews, PolicyNewsVO> {


	private static final IDictService dictService;

	static {
		dictService = SpringUtil.getBean(IDictService.class);
	}


	public static PolicyNewsWrapper build() {
		return new PolicyNewsWrapper();
	}

	@Override
	public PolicyNewsVO entityVO(PolicyNews policyNews) {
		PolicyNewsVO policyNewsVO = BeanUtil.copy(policyNews, PolicyNewsVO.class);
		//查询产业名称
		String industryName = dictService.getValue("industry", policyNews.getIndustryId().intValue());
		policyNewsVO.setIndustryName(industryName);
		return policyNewsVO;
	}

}
