/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.util.Date;
import java.util.List;

import org.tony.annotationvalidate.AnnotationValidable;
import org.tony.annotationvalidate.ValidateDigit;
import org.tony.annotationvalidate.ValidateInt;
import org.tony.annotationvalidate.ValidateNotEmpty;
import org.tony.annotationvalidate.ValidateNotLaterThan;
import org.tony.annotationvalidate.ValidateNotNull;
import org.tony.annotationvalidate.ValidatePattern;
import org.tony.annotationvalidate.ValidateSize;
import org.tony.annotationvalidate.ValidateStringIn;


/**
 *
 * @author gxiahou
 */
public class AnnotationValidateModelStub implements AnnotationValidable{

	@ValidateNotLaterThan(laterTime="endTime")
    private Date startTime;
    private Date endTime;

    @ValidateStringIn(value="Critical,Major,Minor,Warning,Alarms,All")
    private String alarmClass;
    
    @ValidateStringIn(value="Communication,Environment,Equipment,Processing,Quality of Service,All")
    private String alarmType;

    @ValidateStringIn(value="Active,All")
    private String alarmStatus;

    @ValidateDigit
    @ValidateSize(minSize="0",maxSize="9")
    private String alarmNumber;

    @ValidateNotEmpty
    private List<Object> moList;
    
    @ValidateNotNull
    private String suppleInfo;
    
    @ValidatePattern(pattern="\\d{0,9}")
    private String gid;
    
    @ValidateInt(min=1,max=100)
    private Integer maxRows;

    public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Integer getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	public String getSuppleInfo() {
		return suppleInfo;
	}

	public void setSuppleInfo(String suppleInfo) {
		this.suppleInfo = suppleInfo;
	}

	public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass.equalsIgnoreCase("All")? "" : alarmClass;
    }

   

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus.equalsIgnoreCase("All")? "" : alarmStatus;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType.equalsIgnoreCase("All")? "" : alarmType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Object> getMoList() {
		return moList;
	}

	public void setMoList(List<Object> moList) {
		this.moList = moList;
	}

	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

	public String serializeToString() {
		return null;
	}
}
