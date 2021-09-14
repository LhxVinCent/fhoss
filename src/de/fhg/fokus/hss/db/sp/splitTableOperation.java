package de.fhg.fokus.hss.db.sp;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class splitTableOperation {
    private static Logger logger = Logger.getLogger(PhoneAreaCode.class);
    private static Map<String, PhoneAreaCode> areaCodeMap = new HashMap<>();
    public static PhoneAreaCode getPhoneAreaCode(String areaCode)
    {
        return areaCodeMap.get(areaCode);
    }

    public static void setPhoneAreaCode(PhoneAreaCode phoneAreaCode)
    {
        String areaName = phoneAreaCode.getAreaName();
        areaCodeMap.put(areaName, phoneAreaCode);
    }

    public static String getTableNameByIndex(String areaCode, Integer id)
    {
        PhoneAreaCode phoneAreaCode = getPhoneAreaCode(areaCode);
        Integer index = id % phoneAreaCode.getTableNums();
        return phoneAreaCode.getTable(index);
    }

    public static void clear()
    {
        areaCodeMap.clear();
    }

    public static String getTableNameByIdentity(String identity)
    {
        final int start = 4;
        final int sipLen = 15;
        final int endPoint = 10;
        int index;
        String str;
        PhoneAreaCode phoneAreaCode;
        String[] subString = identity.substring(start).split("@");

        if((subString[0].length() < sipLen) && (subString[0].length() > 0))
        {
           if(subString[0].charAt(0) == '+')
           {
               /*通过这个字符串查找到位于哪个区*/
               str = subString[0].substring(start - 1, endPoint);
               phoneAreaCode = getPhoneAreaCode(str);
               index = Integer.parseInt(subString[0].substring(10)) % phoneAreaCode.getTableNums();
               return phoneAreaCode.getTable(index);
           }
           else
           {
               str = subString[0].substring(0, 7);
               phoneAreaCode = getPhoneAreaCode(str);
               index = Integer.parseInt(subString[0].substring(7)) % phoneAreaCode.getTableNums();
               return phoneAreaCode.getTable(index);
           }
        }
        return null;
    }
}
