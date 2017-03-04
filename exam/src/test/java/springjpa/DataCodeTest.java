package springjpa;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lx.exam.po.PoDataCode;
import com.lx.exam.service.itf.DataCodeService;
import com.lx.exam.vo.DataCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml"})
public class DataCodeTest {
	@Autowired
	DataCodeService dataCodeService;
	@Test
	public void get(){
		System.out.println(dataCodeService.get(2L));
	}
	@Test
	public void add(){
		DataCode dataCode = new DataCode();
		dataCode.setDescription("Test1");
		dataCode.setIcon("jpg1");
//		dataCode.setPid(1L);
		dataCode.setTitle("test1");
		dataCodeService.add(dataCode);
	}
	@Test
	public void add2(){
		DataCode dataCode = new DataCode();
		dataCode.setDescription("Test2");
		dataCode.setIcon("jpg3");
		dataCode.setTitle("test3");
		dataCode.setPid(1L);
		dataCodeService.add(dataCode);
	}
	@Test
	public void edit(){
		DataCode dataCode = new DataCode();
		dataCode.setId(9L);
		dataCode.setDescription("Test111");
		dataCode.setIcon("jpg111");
		dataCode.setPid(1L);
		dataCode.setTitle("test111");
		dataCodeService.edit(dataCode);
	}
	@Test
	public void delete(){
		DataCode dataCode = new DataCode();
		dataCode.setId(1L);
		dataCodeService.delete(dataCode);
	}
	@Test
	public void list(){
		List<DataCode> list = dataCodeService.list(null);
		for(DataCode dataCode:list){
			System.out.println(dataCode);
		}
	}
}
