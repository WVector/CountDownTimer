package com.vector.countdowntimer_demo;

import java.util.List;

/**
 * Created by Vector on 2016/8/25 0025.
 */
public class Ad {

    /**
     * Code : 1
     * Msg : 成功
     * Data : [{"AdvertisementTitle":"1","Url":"http://cacheother.mobileanjian.com/exe-files/7fde7681-ef8c-4547-8276-0c39bc7312d7.png?Expires=1535175891&OSSAccessKeyId=2Od4jvtKPpuDx7la&Signature=%2FMuTCyfk9omcwI6Tfn6hSZxyBaE%3D","Link":"1"}]
     */

    public int Code;
    public String Msg;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * AdvertisementTitle : 1
         * Url : http://cacheother.mobileanjian.com/exe-files/7fde7681-ef8c-4547-8276-0c39bc7312d7.png?Expires=1535175891&OSSAccessKeyId=2Od4jvtKPpuDx7la&Signature=%2FMuTCyfk9omcwI6Tfn6hSZxyBaE%3D
         * Link : 1
         */

        public String AdvertisementTitle;
        public String Url;
        public String Link;
    }
}
