package hello.typeconverter.controller;


import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request){
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data){
        System.out.println(data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort){
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
