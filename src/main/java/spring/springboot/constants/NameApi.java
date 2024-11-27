package spring.springboot.constants;

public class NameApi {
    public static final String createUser = Constants.prefixPrivateAdmin + "create-user";
    public static final String getListUser = Constants.prefixPrivateAdmin + "list-users";
    public static final String authLogin = Constants.prefixPublic + "login";
    public static final String getMe = Constants.prefixCommon + "me";

    public static final String createCategory = Constants.prefixPrivateAdmin + "create-category";
    public static final String updateCategory = Constants.prefixPrivateAdmin + "update-category";
    public static final String getListCategories = Constants.prefixPublic + "list-categories";
    public static final String deleteCategory = Constants.prefixPrivateAdmin + "delete-category";

    public static final String createEls = Constants.prefixPublic + "elastic";
    public static final String getEls = Constants.prefixPublic + "elastic";

}
