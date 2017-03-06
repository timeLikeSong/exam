package springjpa;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lx.exam.po.PoConfig;
import com.lx.exam.service.itf.ConfigService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class ConfigTest {
	PoConfig config;
	
	@Resource
	ConfigService configService;
	
	@Test
	public void testGet(){
		 configService.getByKey("STARTVALID");
	}
}
