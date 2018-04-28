package edu.hust.truongvu.choviet.utils;

/**
 * Created by truon on 3/13/2018.
 */

public class Constants {
    public class Path{
        public static final String PATH_GENNYMOTION = "http://10.0.3.2:8080/electronicshop/";
        public static final String PATH_ANDROID_STUDIO = "http://127.0.0.1:8080/electronicshop/";
        public static final String PATH_REAL_DEVICE = "http://192.168.0.105:8080/electronicshop/";

        public static final String MY_PATH = PATH_REAL_DEVICE;
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
        public static final String PRODUCT_ID = "product_id";
        public static final String MY_LOGIN = "mylogin";
        public static final String USERNAME = "username";
        public static final String USERID = "userid";
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
