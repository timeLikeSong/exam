package springjpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpPostRequest {
	@Test
	public void postJingkao(){
		String url = "http://www.jingkao.net/ide/idecontroller/commitcode";
		String url2 = "http://www.jingkao.net/ide/idecontroller/getresult?answerId=xHNwPLcB2HoTFfz36wP";
		Map<String,String> map = new HashMap<String,String>();
		map.put("projectId","WZjqpUyLabMZfgcrFDK");
		String charset = "utf8";
		System.out.println(doPost(url2,map,charset));
	}
	
	public String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            System.out.println(response);
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
}
