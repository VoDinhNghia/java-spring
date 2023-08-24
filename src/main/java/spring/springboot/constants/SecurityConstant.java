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
}
