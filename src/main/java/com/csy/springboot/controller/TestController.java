package com.csy.springboot.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csy.springboot.entity.User;
import com.csy.springboot.service.UserService;
import com.csy.springboot.szfy.ApiRestClient;
import com.csy.springboot.szfy.DeptVO;
import com.csy.springboot.szfy.GetHttpCookiesUtil;
import com.csy.springboot.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

@Api(value = "rest测试", tags = { "测试接口" })
@RestController
public class TestController {
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private UserService userService;
	@Autowired
	private ApiRestClient apiRestClient;

	@GetMapping("/helloworld")
	public String helloworld() {
		return "helloworld";
	}
	
	@ApiOperation("根据用户名查询用户信息借口")
	@ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
	@GetMapping("/findUserByName")
	public String findUserByName(@RequestParam String name) throws Exception{
		User user = userService.findUserByName(name);
		JSONObject obj = JSONObject.fromObject(user);
		return obj.toString();
	}
	
	@ApiOperation("spring boot 测试接")
	@ApiImplicitParam(name = "user", value = "用户名", dataType = "string", paramType = "query")
	@GetMapping("/sayhelloworld")
	public String sayhelloworld(@RequestParam String user) throws Exception {
		return userService.sayhelloworld(user);
	}
	
	@GetMapping("/getCacheName")
	public Collection<String> getCacheName() throws Exception{
		return  cacheManager.getCacheNames();
	}
	
	@PostMapping("/testApi")
	public void testApi(@RequestBody @ApiParam(value = "部门参数", required = true) DeptVO vo){
		JSONObject json = JSONObject.fromObject(vo);
		json.put("startTime", "2018-11-01");
		json.put("endTime", "2018-11-01");
		String jsonParam = json.toString();
		GetHttpCookiesUtil.callRestService("http://3030.ij120.zoenet.cn/api/reservation/getScheduleList", jsonParam);
	}
	
	@PostMapping("/autoCallCard")
	public Boolean autoCallCard(){
		return apiRestClient.autoCallCard(false, apiRestClient.getScheduleWorkInfo());
	}
	
	@PostMapping("/test")
	public void login(@RequestBody UserVO user){
		
	}
}
