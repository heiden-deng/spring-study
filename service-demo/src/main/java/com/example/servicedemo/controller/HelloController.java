package com.example.servicedemo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.servicedemo.models.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private String secret = "7kTW6AKSq2canElSrx9ZtvYVkXyd9XA9";
    private int expiration = 100;

    @RequestMapping("/hi")
    public String index() {
        return "Greetings from Spring Boot!,I'm service2 ***********************";
    }

    @RequestMapping("/hello/{consumer}")
    public String hello(@PathVariable(value = "consumer",required = true) String consumer) {
        return "Hello " + ", I am " + consumer + ",from demo:" ;
    }


    @RequestMapping(value = "/hello111/{apiName}",method = RequestMethod.PUT)
    public String hello11(@PathVariable(value ="apiName", required = true) String apiname,
                          @RequestParam String refreshInterval,
                          @RequestParam String limit,
                          @RequestParam String quota) {
        System.out.println("Hello " + apiname + ", I am " + refreshInterval + ",from demo:" + limit);
        return "Hello " + ", I am " + refreshInterval + ",from demo:" ;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createConsumers(@RequestBody User u, @RequestHeader HttpHeaders headers) {
        for(String key: headers.keySet()){
            Object o = headers.get(key);
            System.out.println("key=" + key + ",value=" + o.toString());
        }
        return u.getId() + u.getName();
    }



    /*public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", username);
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }



    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }




    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }*/

    public  Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaims();
    }
}