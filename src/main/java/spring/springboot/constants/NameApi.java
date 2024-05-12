package spring.springboot.constants;

public class NameApi {
    public static final String createUser = Constants.prefixPrivateAdmin + "create-user";
    public static final String getListUser = Constants.prefixPrivateAdmin + "list-users";
    public static final String authLogin = Constants.prefixPublic + "login";
    public static final String getMe = Constants.prefixCommon + "me";
}
