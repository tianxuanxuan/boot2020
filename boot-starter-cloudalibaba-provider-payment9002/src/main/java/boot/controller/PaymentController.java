package boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianxuanxuan
 * On 2020-09-21 09:58
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/nacos/{id}")
    public String payment(@PathVariable("id") Long id){
        return "Hello Nacos Discovery: " + serverPort + "\t id: " + id;
    }
}
