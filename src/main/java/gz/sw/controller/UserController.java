package gz.sw.controller;


import com.alibaba.fastjson.JSONObject;
import gz.sw.common.RetVal;
import gz.sw.common.SessionUser;
import gz.sw.constant.CommonConst;
import gz.sw.entity.write.User;
import gz.sw.service.write.UserService;
import gz.sw.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("list")
	public String list() {
		return "user/list";
	}

	@PostMapping("list")
	@ResponseBody
	public Map list(String phone, String name, Integer page, Integer limit) {
		return RetVal.OK(userService.selectCount(phone, name), userService.selectList(phone, name, page, limit));
	}

	@GetMapping("insert")
	public String insert(ModelMap map) {
		map.put("pwdText", "请输入密码");
		map.put("cfmPwdText", "请输入确认密码");
		return "user/insert";
	}

	@PostMapping("insert")
	@ResponseBody
	public JSONObject insert(
			@RequestParam String phone,
			@RequestParam String name,
			@RequestParam String password,
			@RequestParam String confirmPwd,
			@RequestParam Integer admin) {
		JSONObject retval = new JSONObject();
		if( !password.equals(confirmPwd) ){
			return RetVal.Error("两次密码输入不一致");
		}
		User existUser = userService.selectByPhone(phone);
		if( existUser != null ){
			return RetVal.Error("用户已存在");
		}
		Date date = new Date();
		User user = new User();
		user.setPhone(phone);
		user.setName(name);
		user.setPassword(Md5Util.execute(CommonConst.SECREAT_KEY + password));
		user.setAdmin(admin);
		user.setCreateTime(date);
		user.setUpdateTime(date);
		userService.insert(user);

		return RetVal.OK();
	}

	@GetMapping("update/{id}")
	public String update(ModelMap map, @PathVariable("id") Integer id) {
		User user = userService.select(id);
		map.put("pwdText", "不修改密码无需输入");
		map.put("cfmPwdText", "不修改密码无需输入");
		map.put("user", user);
		return "user/insert";
	}

	@PostMapping("update/{id}")
	@ResponseBody
	public JSONObject update(
			@PathVariable("id") Integer id,
			@RequestParam String phone,
			@RequestParam String name,
			@RequestParam String password,
			@RequestParam String confirmPwd,
			@RequestParam Integer admin) {
		JSONObject retval = new JSONObject();
		User user = new User();
		if( password != null && password != "" ){
			if( !password.equals(confirmPwd) ){
				return RetVal.Error("两次密码输入不一致");
			}
			user.setPassword(Md5Util.execute(CommonConst.SECREAT_KEY + password));
		}
		user.setId(id);
		user.setPhone(phone);
		user.setName(name);
		user.setAdmin(admin);
		user.setUpdateTime(new Date());
		userService.update(user);

		return RetVal.OK();
	}

	@GetMapping("self")
	public String self(ModelMap map, HttpServletRequest request) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
		User user = userService.select(sessionUser.getId());
		map.put("pwdText", "不修改密码无需输入");
		map.put("cfmPwdText", "不修改密码无需输入");
		map.put("user", user);
		return "user/pwd";
	}

	@PostMapping("self")
	@ResponseBody
	public JSONObject self(
			HttpServletRequest request,
			@RequestParam String phone,
			@RequestParam String name,
			@RequestParam String oldPwd,
			@RequestParam String password,
			@RequestParam String confirmPwd
	) {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(CommonConst.SESSION_USER);
		JSONObject retval = new JSONObject();
		User user = userService.select(sessionUser.getId());
		if( password != null && password != "" ){
			if( !user.getPassword().equals(Md5Util.execute(CommonConst.SECREAT_KEY + oldPwd)) ){
				return RetVal.Error("旧密码不正确");
			}
			if( !password.equals(confirmPwd) ){
				return RetVal.Error("两次密码输入不一致");
			}
			user.setPassword(Md5Util.execute(CommonConst.SECREAT_KEY + password));
		}
		user.setPhone(phone);
		user.setName(name);
		user.setUpdateTime(new Date());
		userService.update(user);

		return RetVal.OK();
	}

	@PostMapping("delete")
	@ResponseBody
	@Transactional
	public JSONObject delete(Integer id) {
		userService.delete(id);
		return RetVal.OK();
	}
}
