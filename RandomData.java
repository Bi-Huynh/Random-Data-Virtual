package Random_Data_Virtual;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RandomData {

    
    // <editor-fold defaultstate="collapsed" desc="Random Ngày">
    private static int getDaySQL(Date date) {
        String dayString = date.toString().substring(8, 10);
        return Integer.parseInt(dayString);
    }

    private static int getMonthSQL(Date date) {
        String dayString = date.toString().substring(5, 7);
        return Integer.parseInt(dayString);
    }

    private static int getYearSQL(Date date) {
        String dayString = date.toString().substring(0, 4);
        return Integer.parseInt(dayString);
    }

    private static boolean checkYear(int year) {
        if (year % 400 == 0) {
            return true;
        }
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }

    /**
     * trả về Date bất kỳ và ngày này nhỏ hơn hoặc bằng ngày hiện tại ngày nhỏ
     * nhất là ngày 2005-1-1
     *
     * Date có kiểu dữ liệu là java.sql.Date
     *
     * @return sql.Date
     */
    public static Date rDateSQL() {
        String ngay = "";

        // lấy ngày hiện tại trong hệ thống
        Date date = new Date(System.currentTimeMillis());

        int yearNow = getYearSQL(date);
        int monthNow = getMonthSQL(date);
        int dayNow = getDaySQL(date);

        Random rd = new Random();
        // năm sẽ được lấy từ năm 2005 đến năm hiện tại
        int year = rd.nextInt(yearNow - 2005 + 1) + 2004;
        int month = rd.nextInt(13);

        if (year == yearNow) {
            while (month > monthNow) {
                month = rd.nextInt(13);
            }
        }
        while (month == 0) {
            month = rd.nextInt(13);
        }

        int day = 0;

        switch (month) {
            case 2:
                if (checkYear(year)) {  // năm nhuần
                    // lấy ngày từ 1 - 29
                    day = rd.nextInt(30);
                    if (year == yearNow && month == monthNow) {
                        while (day > dayNow) {
                            day = rd.nextInt(30);
                        }
                    }
                    while (day == 0) {
                        day = rd.nextInt(30);
                    }
                } else {
                    day = rd.nextInt(29);
                    if (year == yearNow && month == monthNow) {
                        while (day > dayNow || day == 0) {
                            day = rd.nextInt(29);
                        }
                    }
                    while (day == 0) {
                        day = rd.nextInt(29);
                    }
                }
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                // ngày có 31
                day = rd.nextInt(32);
                if (year == yearNow && month == monthNow) {
                    while (day > dayNow || day == 0) {
                        day = rd.nextInt(32);
                    }

                }
                while (day == 0) {
                    day = rd.nextInt(32);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                // ngày có 30
                day = rd.nextInt(31);
                if (year == yearNow && month == monthNow) {
                    while (day > dayNow) {
                        day = rd.nextInt(31);
                    }

                }
                while (day == 0) {
                    day = rd.nextInt(31);
                }
                break;
        }

        ngay = year + "-" + month + "-" + day;
        return Date.valueOf(ngay);
    }
    
    /**
     * trả về Date bất kỳ và ngày này nhỏ hơn hoặc bằng ngày hiện tại ngày nhỏ
     * nhất là ngày 2005-1-1
     * 
     * random 1 ngày theo kiểu java.util.Date
     * 
     * @return java.util.Date
    */
    public static java.util.Date rDateUtil() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        java.util.Date date = null;
        try {
            date = format.parse(rDateSQL().toString());
        } catch (ParseException ex) {
            Logger.getLogger(RandomData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Random Chuỗi">
    /**
     * trả về 1 cái chuỗi chuẩn có kích thước là người dùng nhập vào chuỗi trả
     * về sẽ có độ dài là n + 1 n : số ký tự
     *
     * chuỗi chuẩn là chuỗi có chữ cái đầu tiên viết hoa, các ký tự còn lại viết
     * thường
     *
     * @param lenght là độ dài của chuỗi sẽ trả về
     *
     * @return chuỗi
     */
    public static String rChuoiChuan(int lenght) {
        String chuoi = "";
        Random rd = new Random();
        for (int i = 0; i < lenght; i++) {
            if (i == 0) {
                chuoi += (char) (rd.nextInt(26) + 65);
            }
            chuoi += (char) (rd.nextInt(26) + 97);
        }
        return chuoi;
    }
    
    /**
     * trả về 1 chuỗi hoa bất kỳ chuỗi trả về sẽ có độ dài là n + 1
     *
     * @param n là độ dài của chuỗi sẽ trả về
     *
     * @return chuỗi
     */
    public static String rChuoi(int n) {
        String chuoi = "";
        Random rd = new Random();
        for (int i = 0; i < n; i++) {
            chuoi += (char) (rd.nextInt(26) + 65);
        }
        return chuoi;
    }

    /**
     * trả về 1 chuỗi họ tên ảo có dạng chuẩn chuỗi
     *
     * @return String name
     */
    public static String rName() {
        String name = rChuoiChuan(5) + ' ' + rChuoiChuan(5) + ' ' + rChuoiChuan(3);
        return name.trim();
    }
    
    /**
     * trả về 1 chuỗi dành cho trường ID
     *
     * @param _id là biến chuỗi mặc định
     *
     * vd : _id = DH => id = DH + 99990 => DH99990
     *
     * chuỗi số phía sau từ 1000 -> 100000 tức là có 99990 số
     *
     * @return id
     */
    public static String rID(String _id) {
        String id = _id + (rIntegerTo(0, 100000) + 1000);
        return id;
    }
    
    /**
     * hàm trả về 1 văn bản có n chữ
     *
     * @param n số chữ có trong text
     *
     * @return Text
     */
    public static String rText(int n) {
        String text = "";
        for (int i = 0; i < n; i++) {
            text += rChuoiChuan(5) + ' ';
        }
        return text.trim();
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Random Số">
    /**
     * trả về 1 con số trong khoảng từ 0 -> Integer.MAX_VALUE
     *
     * @return Integer
     */
    public static Integer rInteger() {
        return new Random().nextInt(Integer.MAX_VALUE);
    }

    /**
     * trả về 1 số trong khoảng từ <b>Min</b> đến <b>Max</b>
     *
     * @param min số nhỏ nhất có thể random được
     * @param max số lớn nhất có thể random được
     *
     * @return <b>Integer.MIN_VALUE</b> khi <b>Min</b> > <b>Max</b>
     */
    public static Integer rIntegerTo(int min, int max) {
        if (max <= min) {
            return Integer.MIN_VALUE;
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * trả về 1 con số chia hết cho số đã cho
     *
     * @param divisor : số muốn chia hết
     *
     * số trả về nằm trong đoạn từ 0 -> 100000 trong đoạn này số nào chia hết
     * cho divisor sẽ được trả về
     *
     * @return -1 divisor >= 100000
     */
    public static Integer rIPercent(int divisor) {
        if (divisor >= 100000) {
            return -1;
        }
        Random rd = new Random();
        int temp = rd.nextInt(100001);
        while (temp % divisor != 0) {
            temp = rd.nextInt(100001);
        }
        return temp;
    }

    /**
     * trả về 1 con số không chia hết cho số đã cho
     *
     * @param divisor : số muốn chia
     *
     * số trả về nằm trong đoạn từ 0 -> 100000 trong đoạn này số nào không chia
     * hết cho divisor sẽ được trả về
     *
     * @return -1 divisor >= 100000
     */
    public static Integer rNonIPercent(int divisor) {
        if (divisor >= 100000) {
            return -1;
        }
        Random rd = new Random();
        int temp = rd.nextInt(100001);
        while (temp % divisor == 0) {
            temp = rd.nextInt(100001);
        }
        return temp;
    }
    
    /**
     * random ra 1 số Float
     *
     * @return Float
     */
    public static Float rNumberFloat() {
        return new Random().nextFloat();
    }

    /**
     * random ra 1 số Double
     *
     * @return Double
     */
    public static Double rNumberDouble() {
        return new Random().nextDouble();
    }
    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Random Boolean">
    /**
     * random ra 1 Boolean
     *
     * @return Boolean
     */
    public static Boolean rIsBoolean() {
        return new Random().nextBoolean();
    }
    // </editor-fold>

}
