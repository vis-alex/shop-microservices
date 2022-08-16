package alex.vis.product_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
//Позволяет изменять бин в рантайме и все ктое его инжектит получат при вызове обновленный бин
//Тут мы это проверяем
@RefreshScope
public class TestController {

    @Value("${test.name}")
    private String name;

    @GetMapping
    public String test() {
        return name;
    }
}
