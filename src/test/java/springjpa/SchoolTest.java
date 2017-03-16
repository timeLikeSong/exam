package springjpa;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lx.exam.po.PoAddress;
import com.lx.exam.po.PoSchool;
import com.lx.exam.po.PoUser;
import com.lx.exam.service.itf.AddressService;
import com.lx.exam.service.itf.SchoolService;
import com.lx.exam.service.itf.UserService;
import com.lx.exam.vo.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class SchoolTest {
	PoSchool school;
	
	@Resource
	SchoolService schoolService;
	
	
	@Test
	public void testGet(){
		school = schoolService.get(1L);
		if(school==null){
			System.out.println("null");
		}
		else{
			System.out.println(school.getName());
		}
	}
	@Test
	public void testListByName(){
		List<PoSchool> schools = schoolService.listSchoolByName("河北");
		System.out.println(schools.size());
	}
	@Test
	public void testCopyObj(){
		User user = new User(2L,"lx","2016-12-1");
		user.setAddressId(11L);
		PoUser user2 = new PoUser(user);
		System.out.println(user2);
	}
}
