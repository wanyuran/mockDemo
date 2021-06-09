package com.example.restservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class GreetingStubTest {

    @Test
    public void exactUrlOnly() throws IOException {
        //WireMock server启动
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8089)
//                        .extensions(new ResponseTemplateTransformer(true))
        ); //No-args constructor will start on port 8080, no HTTPS

        wireMockServer.start();

        //client配置
        configureFor(8089);

        //stub设置
        //方式一：通过wiremock提供的方法直接针对path，mock返回
        // Low priority catch-all proxies to other host.com by default
        stubFor(get(urlEqualTo("/greeting/specific-resource")).atPriority(1).willReturn(aResponse().withHeader("Content-Type", "text/plain").withBody("Hello, World")));

        stubFor(get(urlMatching("/greeting/.*")).atPriority(2).willReturn(aResponse().withHeader("Content-Type", "text/plain").withBody("{\"status\":\"Error\",\"message\":\"Endpoint not found\"}")));

        //方法二：使用withBodyFile()方法来mock json格式的response
        stubFor(get(urlEqualTo("/api")).atPriority(2).willReturn(aResponse().withHeader("Content-Type", "text/plain").withBodyFile("hello.json")));

        // High priority stub will send a Service Unavailable response
// if the specified URL is requested
//        stubFor(get(urlEqualTo("/api/override/123")).atPriority(10)
//                .willReturn(aResponse().proxiedFrom("https://ceshiren.com").withTransformer()));


        System.in.read();


        WireMock.reset();

// Finish doing stuff

        wireMockServer.stop();
    }
}
