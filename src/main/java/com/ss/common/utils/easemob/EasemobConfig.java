package com.ss.common.utils.easemob;

public class EasemobConfig {

    protected static final String org_name ="1126180905177598" ;

    protected static final String app_name ="yjzjv2" ;

    protected static final String Client_ID ="YXA6VK_KsIgNEemSjOG-53O2vw" ;

    protected static final String Client_Secret ="YXA6nrvYFRmTjYKkNx5OeMEhM7WljJI" ;

    protected static final String APP_KEY = "1126180905177598#yjzjv2";

    protected static final String URL_API = "https://a1.easemob.com/";

    protected static final String SLASH= "/";

    protected static final String TOKEN_KEY= "1126180905177598#yjzjv2_TOKEN";







    /**
     * 返回API完整URI
     * @param path
     * @return
     */
    protected static String createURI(String path){

        String uri = EasemobConfig.URL_API+EasemobConfig.org_name+EasemobConfig.SLASH+EasemobConfig.app_name+path;

        return uri;
    }



}
