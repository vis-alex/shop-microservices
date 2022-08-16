package alex.vis.orderservice;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	//Этот бин нам нужен потому что в этом случае не работает наш  token relay и jwt не передается
	//по цепочке из OrderService в Inventory service, потому что мы вызываем его через Feign а
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder
						.getContext().getAuthentication();

				requestTemplate.header("Authorization", "Bearer" + token.getToken().getTokenValue());
			}
		};
	}

}
