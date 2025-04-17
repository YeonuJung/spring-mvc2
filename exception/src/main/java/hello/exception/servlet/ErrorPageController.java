package hello.exception.servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
