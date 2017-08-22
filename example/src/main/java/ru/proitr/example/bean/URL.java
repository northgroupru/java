package ru.proitr.example.bean;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class URL {
    private String url;
    private String title;
    public URL(){}
    public URL(String url, String title) {
        this.url = url;
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<URL> getUrlList() {
        List<URL> list = new ArrayList<>();
        list.add(new URL("http://url1.com","URL One"));
        list.add(new URL("http://url2.com","URL Two"));
        list.add(new URL("http://url3.com","URL Three"));
        return list;
    }
}