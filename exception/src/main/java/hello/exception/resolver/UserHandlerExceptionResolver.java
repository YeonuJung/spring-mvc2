package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

       try {
           if (ex instanceof UserException){
               log.info("UserException to 400");
               String accept = request.getHeader("accept");
               response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

               if("application/json".equals(accept)){
                   Map<String, Object> errorResult = new HashMap<>();
                   errorResult.put("ex", ex.getClass());
                   errorResult.put("message", ex.getMessage());

                   response.setContentType("application/json");
                   response.setCharacterEncoding("UTF-8");

                   response.getWriter().write(  objectMapper.writeValueAsString(errorResult));
                    return new ModelAndView();
               }else{
                   return new ModelAndView("/error/500");
               }
           }
       }catch (Exception e) {
           log.info("resolver ex", e);
       }

        return null;
    }
}
