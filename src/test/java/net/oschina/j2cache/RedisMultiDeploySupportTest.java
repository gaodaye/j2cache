package net.oschina.j2cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyw
 * @version 17/1/14 20:38
 */
public class RedisMultiDeploySupportTest {

    public static void main(String[] args) {

        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("username", "zhangyw");
        objectMap.put("email", "alemcity@foxmail.com");
        objectMap.put("git_link", "http://git.oschina.net/git-zyw");

        J2Cache.getChannel().set("user_cache", "user", objectMap);

        CacheObject object = J2Cache.getChannel().get("user_cache", "user");
        @SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) object.getValue();
        System.out.println(map.get("git_link"));
    }
}
