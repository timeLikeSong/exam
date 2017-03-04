package springjpa;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.dao.AdminRepository;
import com.lx.exam.dao.FunctionRepository;
import com.lx.exam.dao.RoleRepository;
import com.lx.exam.po.PoAdmin;
import com.lx.exam.po.PoFunction;
import com.lx.exam.po.PoRole;
import com.lx.exam.searchmodel.AdminSM;
import com.lx.exam.searchmodel.FunctionSM;
import com.lx.exam.searchmodel.RoleSM;
import com.lx.exam.service.itf.AdminService;
import com.lx.exam.service.itf.FunctionService;
import com.lx.exam.service.itf.RoleService;
import com.lx.exam.vo.Admin;
import com.lx.exam.vo.Function;
import com.lx.exam.vo.Role;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class AdminRoleFunction {
	/*------------------------------------Function-----------------------------------*/
	@Autowired
	FunctionRepository functionRepository;
	@Autowired
	FunctionService functionService;
	@Test
	public void addFunction(){
		Function function1 = new Function();
		function1.setFuncName("修改题库1");
		function1.setUrl("editQuestionDB1");
		function1.setStatus(1);
		System.out.println(functionService.add(function1));
	}
	@Test
	public void addFunction2(){
		Function function1 = new Function();
		function1.setFuncName("修改题库6");
		function1.setUrl("editQuestionDB6");
		function1.setStatus(1);
		function1.setPid(1L);
		System.out.println(functionService.add(function1));
	}
	@Test
	public void getFunction(){
		Function function = functionService.get(8L);
		System.out.println(function);
	}
	@Test
	public void deleteFunction(){
		Function function = new Function();
		function.setId(1L);
		System.out.println(functionService.delete(function));
	}
	@Test
	public void listFunction(){
		List<Function> list = (List<Function>) functionService.list(null);
		for(Function function:list){
			System.out.println(function);
		}
	}
	
	/*------------------------------------ROLE-----------------------------------*/
	@Autowired
	RoleService roleService;
	@Test
	public void addRole(){
		Role role1 = new Role();
		role1.setRoleName("超级管理员2");
		role1.setStatus(1);
		roleService.add(role1);
	}
	@Test
	public void deleteRole(){
		System.out.println(roleService.delete(1L));
	}
	@Test
	public void editRole(){
		Role role1 = new Role();
		role1.setId(1L);
		role1.setRoleName("超级管理员3");
		role1.setStatus(2);
		
		Long[] functionIds = new Long[]{3L};
		role1.setFunctionIds(functionIds);
		roleService.edit(role1);
	}
	@Test
	public void getRole(){
		Role poRole1 = roleService.get(1L);
		System.out.println(poRole1);
	}
	@Test
	public void listRole(){
		List<Role> list = roleService.list();
		for(Role poRole:list){
			System.out.println(poRole);
		}
	}
	/*------------------------------------Admin-----------------------------------*/
	@Autowired
	AdminService adminService;
	@Test
	public void addAdmin(){
		Admin poAdmin1 = new Admin();
		poAdmin1.setDescription("超级管理员4");
		poAdmin1.setEmail("liangxiaoyihao2@163.com");
		poAdmin1.setPassword("sorry2");
		poAdmin1.setPhone("15732154913");
		poAdmin1.setRealname("LX4");
		poAdmin1.setStatus(0);
		poAdmin1.setUsername("timelikesong3");
		poAdmin1.setPhoto("lx2.jpg");
		
		System.out.println(adminService.add(poAdmin1));
	}
	
	@Test
	public void editAdmin(){
		Admin admin = new Admin();
		admin.setId(1L);
		admin.setDescription("超级管理员");
		admin.setEmail("liangxiaoyihao@163.com");
		admin.setPassword("sorry");
		admin.setPhone("15732154918");
		admin.setRealname("LX");
		admin.setStatus(0);
		admin.setUsername("timelikesong");
		admin.setPhoto("lx.jpg");
		System.out.println(adminService.add(admin));
	}
	@Test
	public void editAdmin2(){
		Admin admin = new Admin();
		admin.setId(1L);
		/*为空的属性 ， 字段会成为空值 */
		admin.setDescription("是否只改了描述");
		System.out.println(adminService.edit(admin));
	}
	@Test
	public void deleteAdmin(){
		System.out.println(adminService.delete(2L));
	}
	@Test
	public void getAdmin(){
		Admin admin = adminService.get(2L);
		System.out.println(admin);
	}
	@Test
	public void listAdmin(){
		List<Admin> list= (List<Admin>) adminService.list();
		for(Admin admin:list){
			System.out.println(admin);
		}
	}
}
