package org.zerock.j2.controller.interceptor;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.j2.util.JWTUtil;

import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        try {

            String headerStr = request.getHeader("Authorization");

            if(headerStr == null || headerStr.length() < 7){
                throw new JWTUtil.CustomJWTException("NullToken");
            }
            //Bearer 엑세스 토큰
            String accessToken = request.getHeader("Authorization").substring(7);

            Map<String, Object> claims = jwtUtil.validateToken(accessToken);

            log.info("result: " + claims);

        }catch (Exception e){

            response.setContentType("application/json");

            //response.setStatus(HttpStatus.UNAUTHORIZED.value());

//            // {"키" : "값"} JSON 형태
//            String str = "{\"error\":}";
            Gson gson = new Gson();

            String str = gson.toJson(Map.of("error", e.getMessage()));

            response.getOutputStream().write(str.getBytes());



            return false;

        }

        log.info("-------------------------");
        log.info(handler);
        log.info("-------------------------");
        log.info(jwtUtil);
        log.info("-------------------------");
        log.info("-------------------------");

        return true;
    }
}
