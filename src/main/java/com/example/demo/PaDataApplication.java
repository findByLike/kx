package com.example.demo;

import com.example.demo.domain.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.untils.SpringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PaDataApplication {
    @Bean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    public static void main(String[] args) {
        SpringApplication.run(PaDataApplication.class, args);
        String url = "http://kan.2345.com/vip/list/--movie-----.html";
        PaDataApplication p = new PaDataApplication();
        String message = p.getPageContent(url, "post", 100500);
        System.out.println(message);
        p.getMessageData(message);
    }

    private void getMessageData(String message) {
        Document doc = Jsoup.parse(message);
        Element content = doc.child(0);
        Elements byTag = content.getElementsByTag("a");
        List<Map<String, Map<String, String>>> linkList = new ArrayList<>();
        for (int i = 0; i < byTag.size(); i++) {
            Map<String, Map<String, String>> stringMapMap = new HashMap<>();
            Map<String, String> stringStringMap = new HashMap<>();
            String linkHref = byTag.get(i).attr("href");
            String linkText = byTag.get(i).text();
            stringStringMap.put("name", linkText);
            stringStringMap.put("href", linkHref);
            stringMapMap.put("" + i, stringStringMap);
            linkList.add(stringMapMap);
            Movie movie = new Movie();
            movie.setName(linkText);
            movie.setHref(linkHref);
            MovieRepository movieRepository = SpringUtils.getBean(MovieRepository.class);
            movieRepository.saveMovie(movie);

        }
        System.out.println("结束");
    }

    public String getPageContent(String strUrl, String strPostRequest,
                                 int maxLength) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        try {
            URL newUrl = new URL(strUrl);
            HttpURLConnection hConnect = (HttpURLConnection) newUrl
                    .openConnection();
            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect
                        .getOutputStream());
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容
            // InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    hConnect.getInputStream(), "GB2312"));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1
                    && (maxLength <= 0 || length < maxLength); length++)
                buffer.append((char) ch);
            String s = buffer.toString();
            s.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
            rd.close();
            hConnect.disconnect();
            return buffer.toString().trim();
        } catch (Exception e) {
            return "错误:读取网页失败！";
        }
    }


}
