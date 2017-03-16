package springjpa;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lx.exam.po.PoAddress;
import com.lx.exam.po.PoUser;
import com.lx.exam.service.itf.AddressService;
import com.lx.exam.service.itf.UserService;
import com.lx.exam.vo.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class AddressTest {
	PoAddress address;
	
	@Resource
	AddressService addressService;
	
	
	@Test
	public void testGet(){
		address = addressService.get(11L);
		System.out.println(address.getName());
	}
	@Test
	public void testGetAllAddrName(){
		 
		System.out.println(addressService.getAllAddrName(130638L));
	}
	
	@Test
	public void testCopyObj(){
		User user = new User(2L,"lx","2016-12-1");
		user.setAddressId(11L);
		PoUser user2 = new PoUser(user);
		System.out.println(user2);
	}
}
