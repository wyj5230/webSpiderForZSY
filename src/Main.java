import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

public class Main
{
    public static int count = 0;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        astronVote();
    }

    private static void astronVote() throws IOException
    {
        String loginUrl = "https://xcx.iwalkpet.com/index.php/manage/lfjoew/login/vali";
        String userPageUrl = "";
        Document doc = Jsoup.connect(loginUrl).data("username", "betty").data("password", "449865").post();
        Elements elements = doc.select("ul");
        Elements userManagementPanel = elements.get(1).select("li a");
        if (userManagementPanel.first().text().equals("客户管理"))
        {
            userPageUrl = userManagementPanel.first().attr("href");
            System.out.println(userPageUrl);
        }

        do
        {
            Document adminDoc = Jsoup.connect(userPageUrl).get();
            Elements adminElements = adminDoc.select("table.table tbody tr");
            for (Element e : adminElements)
            {

            }
        } while (true);

//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost request = new HttpPost("http://www.stepon.top/misc/voicevote/api.php?op=vote");
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();
//        request.setConfig(requestConfig);
//        StringEntity params = new StringEntity("vote-id=3025&token=" + token,
//                ContentType
//                        .APPLICATION_FORM_URLENCODED);
//        request.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; SM-G928X Build/LMY47X)
// AppleWebKit/537.36" +
//                " (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36");
//        request.addHeader("Origin", "http://www.stepon.top");
//        request.addHeader("Host", "www.stepon.top");
//        request.addHeader("Referer", "http://www.stepon.top/misc/voicevote/index" +
//                ".php?from=groupmessage&isappinstalled=0");
//        request.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
//        request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
//        request.addHeader("Accept-Encoding", "gzip, deflate");
//        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
//        request.addHeader("Connection", "keep-alive");
//        request.addHeader("Cookie", "PHPSESSID=" + phpsessid + ";
// Hm_lvt_9045f321c9885a5d2142d00c75d836e2=1495892588;");
//        request.addHeader("X-Requested-With", "XMLHttpRequest");
//        request.setEntity(params);
//
//        try {
//            HttpResponse response = httpClient.execute(request);
//            HttpEntity entity = response.getEntity();
//            String responseString = EntityUtils.toString(entity, "UTF-8");
//            System.out.println(responseString);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    private static void astronVote(HttpHost host, int i) throws IOException
    {
        //   Document doc = Jsoup.connect("http://www.stepon.top/misc/voicevote/index
        // .php?from=groupmessage&isappinstalled=0").get();
        Connection.Response res = Jsoup.connect("http://www.stepon.top/misc/voicevote/index" +
                ".php?from=groupmessage&isappinstalled=0")
                .method(Connection.Method.GET)
                .execute();
        String phpsessid = res.cookie("PHPSESSID");
        //  System.out.println(phpsessid);
        Document doc = res.parse();


        String script = doc.select("script").get(4).toString();
        String token = script.substring(script.lastIndexOf("'token'") + 9, script.lastIndexOf("'token'") + 49);


        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://www.stepon.top/misc/voicevote/api.php?op=vote");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).setProxy
                (host).build();
        request.setConfig(requestConfig);
        StringEntity params = new StringEntity("vote-id=3025&token=" + token,
                ContentType.APPLICATION_FORM_URLENCODED);
        request.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; SM-G928X Build/LMY47X) AppleWebKit/537.36" +
                " (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36");
        request.addHeader("Origin", "http://www.stepon.top");
        request.addHeader("Host", "www.stepon.top");
        request.addHeader("Referer", "http://www.stepon.top/misc/voicevote/index" +
                ".php?from=groupmessage&isappinstalled=0");
        request.addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.addHeader("Accept-Encoding", "gzip, deflate");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Cookie", "PHPSESSID=" + phpsessid);
        request.addHeader("X-Requested-With", "XMLHttpRequest");
        request.setEntity(params);

        String fakeForwardIp = "10.132." + (int) (Math.random() * 150 + 1) + "." + (int) (Math.random() * 150 + 1);
        request.addHeader("X-Forwarded-For", fakeForwardIp);
        System.out.println(fakeForwardIp);

        try
        {
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            if (responseString.substring(8, 9).equals("1") && !responseString.substring(8, 10).equals("11"))
            {
                count++;
                System.out.println("success: " + count + "; Response: " + responseString);
                astronVote(host, count + 50);
            }
            else
            {
                System.out.println(responseString);
            }
        }
        catch (Exception e)
        {
            //   System.out.println(e.getLocalizedMessage());
        }
    }


}
