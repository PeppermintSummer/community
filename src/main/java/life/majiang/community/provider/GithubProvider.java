package life.majiang.community.provider;


import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    //note:post请求的时候需要用到requestbody，赋值给request对象，get请求则不需要

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            String token=string.split("&")[0].split("=")[1];
//            String[] split= string.split( "&");
//            String tokenstr=split[0];
//            String token=tokenstr.split("=")[1];
            return token;
//            System.out.println(string);
//            return string;
        } catch (IOException e) {
        }
        return null;

//        public static void main(String[] args) {
//            GithubProvider githubProvider=new GithubProvider();//对象实例化
    }

    public GithubUser getUser(String accessToken) throws IOException {
        OkHttpClient client=new OkHttpClient();      //request请求
        Request request=new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {   //Alt+shift+z
            Response response=client.newCall(request).execute();//response请求
            String string= response.body().string();
            GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;//其他情况抛异常直接null
    }
}
