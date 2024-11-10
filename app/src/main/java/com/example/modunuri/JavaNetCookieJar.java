package com.example.modunuri;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class JavaNetCookieJar implements CookieJar {
    private final CookieManager cookieManager;

    public JavaNetCookieJar(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        List<HttpCookie> httpCookies = new ArrayList<>();
        for (Cookie cookie : cookies) {
            HttpCookie httpCookie = new HttpCookie(cookie.name(), cookie.value());
            httpCookie.setDomain(cookie.domain());
            httpCookie.setPath(cookie.path());
            httpCookies.add(httpCookie);
        }

        // Convert List<HttpCookie> to Map<String, List<String>>
        Map<String, List<String>> cookieHeaders = new HashMap<>();
        List<String> cookieValues = new ArrayList<>();
        for (HttpCookie httpCookie : httpCookies) {
            cookieValues.add(httpCookie.toString());
        }
        cookieHeaders.put("Set-Cookie", cookieValues);

        try {
            cookieManager.put(url.uri(), cookieHeaders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = new ArrayList<>();
        try {
            Map<String, List<String>> cookieHeaders = cookieManager.get(url.uri(), new HashMap<>());
            List<String> cookieValues = cookieHeaders.get("Cookie");
            if (cookieValues != null) {
                for (String cookieValue : cookieValues) {
                    for (HttpCookie httpCookie : HttpCookie.parse(cookieValue)) {
                        cookies.add(new Cookie.Builder()
                                .name(httpCookie.getName())
                                .value(httpCookie.getValue())
                                .domain(url.host())
                                .build());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookies;
    }
}
