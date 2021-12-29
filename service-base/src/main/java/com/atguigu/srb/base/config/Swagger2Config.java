package com.atguigu.srb.base.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();

    }

    private ApiInfo adminApiInfo() {
       return new ApiInfoBuilder()
                .title("后台管理系统Api")
                .description("本模板为管理员模板")
                .version("1.6")
                .contact(new Contact("Jeremy", "www.fxxx.com", "fxxx@xx.com"))
                .build();
    }

    //@Bean
    //public Docket webApiConfig() {
    //
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .groupName("webApi")
    //            .apiInfo(webApiInfo())
    //            .select()
    //            .paths(Predicates.and(PathSelectors.regex("/api/.*")))
    //            .build();
    //
    //}
    //
    //private ApiInfo webApiInfo() {
    //  return   new ApiInfoBuilder()
    //            .title("后台Web管理系统Api")
    //            .description("本模板为管理员模板")
    //            .version("1.6")
    //            .contact(new Contact("Jeremy", "www.fxxx.com", "fxxx@xx.com"))
    //            .build();
    //}


    @Bean
    public Docket apiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();

    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("尚融宝-API文档")
                .description("本文档描述了尚融宝接口")
                .version("1.0")
                .contact(new Contact("Jeremy", "www.fxxx.com", "fxxx@xx.com"))
                .build();
    }

    //@Bean
    //public Docket ossConfig(){
    //
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .groupName("ossApi")
    //            .apiInfo(ossInfo())
    //            .select()
    //            //只显示admin路径下的页面
    //            .paths(Predicates.and(PathSelectors.regex("/oss/.*")))
    //            .build();
    //
    //}
    //
    //private ApiInfo ossInfo(){
    //
    //    return new ApiInfoBuilder()
    //            .title("尚融宝-API文档")
    //            .description("本文档描述了尚融宝接口")
    //            .version("1.0")
    //            .contact(new Contact("Jeremy", "www.fxxx.com", "fxxx@xx.com"))
    //            .build();
    //}

}
