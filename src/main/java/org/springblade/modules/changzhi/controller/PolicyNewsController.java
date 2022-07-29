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
package org.springblade.modules.changzhi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.changzhi.vo.PolicyNewsLimitListVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.changzhi.entity.PolicyNews;
import org.springblade.modules.changzhi.vo.PolicyNewsVO;
import org.springblade.modules.changzhi.wrapper.PolicyNewsWrapper;
import org.springblade.modules.changzhi.service.IPolicyNewsService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 政策资讯表 控制器
 *
 * @author Blade
 * @since 2022-01-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("changzhi/policynews")
@Api(value = "政策资讯表", tags = "政策资讯表接口")
public class PolicyNewsController extends BladeController {

	private IPolicyNewsService policyNewsService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入policyNews")
	public R<PolicyNewsVO> detail(PolicyNews policyNews) {
		PolicyNews detail = policyNewsService.getOne(Condition.getQueryWrapper(policyNews));
		return R.data(PolicyNewsWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 政策资讯表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入policyNews")
	public R<IPage<PolicyNewsVO>> list(PolicyNews policyNews, Query query) {
		IPage<PolicyNews> pages = policyNewsService.page(Condition.getPage(query), Condition.getQueryWrapper(policyNews));
		return R.data(PolicyNewsWrapper.build().pageVO(pages));
	}

	/**
	 * 根据产业编号和时间区间，检索政策资讯列表 ，通过创建时间倒序，取前五条
	 */
	@GetMapping("/cus-list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "查询政策列表时间倒序前五条", notes = "传入policyNews")
	public R<List<PolicyNewsVO>> cusList(PolicyNewsLimitListVO limitListVO) {
		//根据产业编号和时间区间，检索政策资讯列表 ，通过创建时间倒序，取前五条
		List<PolicyNews> policyNews = policyNewsService.list(new QueryWrapper<PolicyNews>().lambda()
			.select(PolicyNews::getId,PolicyNews::getIndustryId,PolicyNews::getTitle,PolicyNews::getCreateTime)
			.eq(Func.isNotEmpty(limitListVO.getIndustryId()), PolicyNews::getIndustryId, limitListVO.getIndustryId())
			.between(Func.isNotEmpty(limitListVO.getStartTime()) && Func.isNotEmpty(limitListVO.getEndTime()),
				PolicyNews::getCreateTime, limitListVO.getStartTime(), limitListVO.getEndTime())
			.orderByDesc(PolicyNews::getCreateTime)
			.last("LIMIT 5")
		);

		return R.data(PolicyNewsWrapper.build().listVO(policyNews));
	}

	/**
	 * 自定义分页 政策资讯表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入policyNews")
	public R<IPage<PolicyNewsVO>> page(PolicyNewsVO policyNews, Query query) {
		IPage<PolicyNewsVO> pages = policyNewsService.selectPolicyNewsPage(Condition.getPage(query), policyNews);
		return R.data(pages);
	}

	/**
	 * 新增 政策资讯表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入policyNews")
	public R save(@Valid @RequestBody PolicyNews policyNews) {
		return R.status(policyNewsService.save(policyNews));
	}

	/**
	 * 修改 政策资讯表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入policyNews")
	public R update(@Valid @RequestBody PolicyNews policyNews) {
		return R.status(policyNewsService.updateById(policyNews));
	}

	/**
	 * 新增或修改 政策资讯表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入policyNews")
	public R submit(@Valid @RequestBody PolicyNews policyNews) {
		return R.status(policyNewsService.saveOrUpdate(policyNews));
	}


	/**
	 * 删除 政策资讯表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(policyNewsService.deleteLogic(Func.toLongList(ids)));
	}


}
