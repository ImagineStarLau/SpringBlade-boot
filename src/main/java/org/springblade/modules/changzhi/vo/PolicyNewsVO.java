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
package org.springblade.modules.changzhi.vo;

import org.springblade.modules.changzhi.entity.PolicyNews;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 政策资讯表视图实体类
 *
 * @author Blade
 * @since 2022-01-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PolicyNewsVO对象", description = "政策资讯表")
public class PolicyNewsVO extends PolicyNews {
	private static final long serialVersionUID = 1L;

	/**
	 * 产业名称
	 */
	private String industryName;

}
