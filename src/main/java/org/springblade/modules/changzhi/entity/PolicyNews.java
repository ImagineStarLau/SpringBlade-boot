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
package org.springblade.modules.changzhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 政策资讯表实体类
 *
 * @author Blade
 * @since 2022-01-22
 */
@Data
@TableName("tb_policy_news")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PolicyNews对象", description = "政策资讯表")
public class PolicyNews extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键编号
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键编号")
	private Long id;
	/**
	 * 主键编号
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键编号")
	private Long industryId;
	/**
	 * 资讯标题
	 */
	@ApiModelProperty(value = "资讯标题")
	private String title;
	/**
	 * 资讯内容
	 */
	@ApiModelProperty(value = "资讯内容")
	private String content;


}
