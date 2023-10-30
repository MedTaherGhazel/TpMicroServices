package com.example.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp ) {
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/restcountries/**")
                        .filters(f->f
                                .addRequestHeader("x-rapidapi-host","restcountries-v1.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key", "f6b06808925msh5c3d3694ac98647p1a1741jsnf273ee2671bf")
                                .rewritePath("/restcountries/(?<segment>.*)","/${segment}"))
                        .uri("https://restcountries-v1.p.rapidapi.com"))

                .route(r->r.path("/muslimsalat/**")
                .filters(f->f
                        .addRequestHeader("x-rapidapi-host","muslimsalat.p.rapidapi.com")
                        .addRequestHeader("x-rapidapi-key", "6b06808925msh5c3d3694ac98647p1a1741jsnf273ee2671bf")
                                .rewritePath("/muslimsalat/(?<segment>.*)","/${segment}"))
                        .uri("https://muslimsalat.p.rapidapi.com"))
                .build();
    }
}
