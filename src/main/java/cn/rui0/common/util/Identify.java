package cn.rui0.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;


public class Identify {
  public String Check(String IdCard,String Name){
      String host = "https://eid.shumaidata.com";
      String path = "/eid/check";
      String method = "POST";
     // String appcode = "dcdb1650481d4350af1353338c099099";
      String appcode = "";
      Map<String, String> headers = new HashMap<String, String>();
      //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
      headers.put("Authorization", "APPCODE " + appcode);
      Map<String, String> querys = new HashMap<String, String>();
      querys.put("idcard", IdCard);
      querys.put("name", Name);
      Map<String, String> bodys = new HashMap<String, String>();
      try {
          /**
           * 重要提示如下:
           * HttpUtils请从
           * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
           * 下载
           *
           * 相应的依赖请参照
           * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
           */
          HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
          System.out.println(response.toString());
          //获取response的body
          return EntityUtils.toString(response.getEntity());
      } catch (Exception e) {
          e.printStackTrace();
      }
      return "";
  }
}
