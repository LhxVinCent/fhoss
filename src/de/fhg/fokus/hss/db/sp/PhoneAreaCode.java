package de.fhg.fokus.hss.db.sp;

import java.util.Vector;
import org.apache.log4j.Logger;

public class PhoneAreaCode {
    private static Logger logger = Logger.getLogger(PhoneAreaCode.class);
    private String areaName;
    private Vector<String> areaTables;

    //private Integer tableNUms;

    public PhoneAreaCode(String areaName) {
        this.areaTables = new Vector<>();
        this.areaName = areaName;
        //this.tableNUms = 0;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void addTable(String tableName) {

        this.areaTables.add(tableName);
    }

    public String getTable(Integer index)
    {
        Integer size = areaTables.size();
        if(index < 0 || index > size - 1)
        {
            logger.error("The areaTables obj has no been created successfully!!!");
            return null;
        }
        return this.areaTables.elementAt(index);
    }

    public Integer getTableNums()
    {
        return areaTables.size();
    }

}
