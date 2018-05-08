package edu.hust.truongvu.choviet.utils;

/**
 * Created by truon on 3/13/2018.
 */

public class Constants {
    public class Path{
        public static final String PATH_GENNYMOTION = "http://10.0.3.2:8080/electronicshop/";
        public static final String PATH_ANDROID_STUDIO = "http://127.0.0.1:8080/electronicshop/";
        public static final String PATH_REAL_DEVICE = "http://192.168.0.105:8080/electronicshop/";

        public static final String MY_PATH = PATH_GENNYMOTION;
    }

    public class User {
        public static final int TYPE_ADMIN = 1;
        public static final int TYPE_CUSTOMER = 2;

        public static final int MALE = 1;
        public static final int FEMALE = 2;
    }

    public class LoginType{
        public static final int LOGIN_FACEBOOK = 1;
        public static final int LOGIN_GOOGLE = 2;
        public static final int LOGIN_SYSTEM = 3;
    }

    public class MyTag{
        public static final String REG_EX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        public static final String SIGNIN_FRAGMENT = "Signin_Fragment";
        public static final String SIGNUP_FRAGMENT = "SignUp_Fragment";
        public static final String FORGOTPASS_FRAGMENT = "ForgotPass_Fragment";


        public static final String MY_LOGIN = "mylogin";
        public static final String USERNAME = "username";
        public static final String USERID = "userid";


        public static final String INTENT_PRODUCT = "intent_product";
        public static final String INTENT_LIST_PRODUCT = "intent_list_product";
        public static final String INTENT_SHOP = "intent_shop";
        public static final String INTENT_TYPE_LOAD_PRODUCT = "intent_type_load_product";
        public static final String ID_CATEGORY = "id_category";
        public static final String ID_BRAND = "id_brand";
        public static final String ID_SHOP ="id_shop";

        public static final int LOAD_PRODUCT_BY_CATEGORY = 1;
        public static final int LOAD_PRODUCT_BY_BRAND = 2;
        public static final int LOAD_PRODUCT_BY_SHOP = 3;
        public static final int LOAD_PRODUCT_TIME_ASC = 4;
        public static final int LOAD_PRODUCT_TIME_DESC = 5;
        public static final int LOAD_PRODUCT_PRICE_ASC = 6;
        public static final int LOAD_PRODUCT_PRICE_DESC = 7;
        public static final int LOAD_PRODUCT_BY_PRICE = 8;

        public static final int LOAD_LIST_PRODUCT_HIGHLIGHT = 1;
        public static final int LOAD_LIST_PRODUCT_LASTEST = 2;
        public static final int LOAD_LIST_PRODUCT_OTHER = 3;
        public static final int NONE_ORDER_BY_PRICE = 0;

    }

    public enum OrderStatus{
        WAITING(0), SHIPPING(1), RECEIVED(2), CANCEL(3);
        private int id;
        OrderStatus(int id){
            this.id = id;
        }

        public int getIdStatus(){
            return id;
        }

        public OrderStatus getStatusById(int id){
            switch (id){
                case 0:
                    return WAITING;
                case 1:
                    return SHIPPING;
                case 2:
                    return RECEIVED;
                case 3:
                    return CANCEL;
                default:
                    return null;
            }
        }
    }

}
