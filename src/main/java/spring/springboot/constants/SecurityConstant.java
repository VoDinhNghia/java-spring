package spring.springboot.constants;

public class SecurityConstant {
    public static final String authorization = "Authorization";
    public static final String prefixBearToken = "Bearer";

    public static final String publicAuthen = Constants.prefixPublic + "**";
    public static final String privateAdminAuthen = Constants.prefixPrivateAdmin + "**";
    public static final String privateUserAuthen = Constants.prefixPrivateUser + "**";
    public static final String commonAuthen = Constants.prefixCommon + "**";
    public static final String swaggerDocs = "/swagger-ui/**";
    public static final String swaggerResource = "swagger-resources/*";
    public static final String docsApi = "/v3/api-docs/**";
    public static final String adminRole = "ADMIN";
    public static final String userRole = "USER";

    public static final String cacheControl = "Cache-Control";
    public static final String contentType = "Content-Type";
    public static final String origin = "Origin";
    public static final String frontEndUrl = "http://localhost:8000";
    public static final String accessControl = "Access-Control-Allow-Origin";
    public static final String accessControlCre = "Access-Control-Allow-Credentials";
    public static final String getMethod = "GET";
    public static final String postMethod = "POST";
    public static final String deleteMethod = "DELETE";
    public static final String putMethod = "PUT";
    public static final String corsPattern = "/**";

}
