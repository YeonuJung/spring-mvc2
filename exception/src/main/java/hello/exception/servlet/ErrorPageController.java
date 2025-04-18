package hello.exception.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController {

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 500");
        printErrorInfo(request);
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500Api(HttpServletRequest request, HttpServletResponse response)
    {
        log.info("API error Page 500");

        HashMap<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        result.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return ResponseEntity.status(statusCode).body(result);
    }

    public void printErrorInfo(HttpServletRequest request){
        log.info("ERROR EXCEPTION {}",request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        log.info("ERROR MESSAGE {}",request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        log.info("ERROR EXCEPTION TYPE {}",request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
        log.info("ERROR REQUEST URI {}",request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI));
        log.info("ERROR SERVLET NAME {}",request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME));
        log.info("ERROR STATUS CODE {}",request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        log.info("ERROR STATUS CODE {}",request.getDispatcherType());
    }
}
