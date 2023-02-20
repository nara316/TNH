//package go.travel.dnh.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    //    클라이언트 요청 시 JWT 인증/인가를 하기위해 설치하는 커스텀 필터 (이걸로 권한 반환 )
//    private final JwtTokenProvider jwtTokenProvider;
//    private final CustomUserDetailService customUserDetailService;
//
//
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////
////        // 1. Request Header 에서 JWT 토큰 추출
////        final String authHeader = request.getHeader("Authorization");
////        final String jwt;
////        final String userEmail;
////        String token = resolveToken((HttpServletRequest) request);
////        // 2. validateToken 으로 토큰 유효성 검사
////        if (token != null && jwtTokenProvider.validateToken(token)) {
////            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
////            Authentication authentication = jwtTokenProvider.getAuthentication(token);
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////        }
////        chain.doFilter(request, response);
////
////    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        jwt = authHeader.substring(7);
//        userEmail = jwtTokenProvider.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = customUserDetailService.loadUserByUsername(userEmail);
//            if (jwtTokenProvider.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//
//    // Request Header 에서 토큰 정보 추출
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        System.out.println("bearerToken = " + bearerToken);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//}
