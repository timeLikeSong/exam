package springjpa;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lx.exam.po.PoUser;
import com.lx.exam.service.itf.UserService;
import com.lx.exam.vo.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class UserTest {
	PoUser user;
	
	@Resource(name="userService")
	UserService userService;
	
//	
	@Test
	public void testAdd(){
		user=new PoUser();
		user.setId(11L);
		user.setUsername("lx2");
		user.setEmail("15732154918@163.com");
		user.setPassword("123123");
		userService.save(user);
		
	}
//	@Test
//	public void testGet(){
//		user = userService.get(1L);
//		System.out.println(user.getUsername());
//	}
//	
//	@Test
//	public void testEdit(){
//		user=new PoUser();
//		user.setId(6L);
//		user.setUsername("lx6");
//		user.setEmail("15732154918@163.com");
//		user.setPassword("123123");
//		userService.edit(user);
//	}
//	
//	@Test
//	public void testDelete(){
//		user=new PoUser();
//		user.setId(5L);
//		userService.delete(user);
//	}
//	@Test
//	public void testDeleteById(){
//		userService.delete(4L);
//	}
//	
	@Test
	public void testFindByRealname(){
		List<PoUser> users = userService.findByRealnameIsLike("8");
		System.out.println(users.size());
	}
	@Test
	public void testImpl(){
		System.out.println(userService.getCount());
	}
	@Test
	public void testFindUsers(){
//		List<PoUser> users = userService.findUsers("8", new Date("2016/1/1"), new Date("2016/12/11"));
		List<PoUser> users = userService.findUsers("8", null,null);
		System.out.println(users.size());
	}
}
