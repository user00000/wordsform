package com.dis.words;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
 
import padeg.lib.Padeg;
 
@ManagedBean
public class PadegBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    public class ResultItem implements Serializable {
        private static final long serialVersionUID = 1L;
        private int padeg;
        private String fio;
        private String appointment;
        private String office;
        public int getPadeg() {
            return padeg;
        }
        public String getFio() {
            return fio;
        }
        public String getAppointment() {
            return appointment;
        }
        public String getOffice() {
            return office;
        }
    }
 
    public PadegBean() {
    }
 
    private String lastName = "Балаганов";
    private String firstName = "Шура";
    private String middleName;
    private String appointment = "уполномоченный по копытам";
    private String office = "Черноморское отделение Арбатовской конторы по заготовке рогов и копыт";
    private List&lt;String&gt; fioResult;
    private List&lt;String&gt; appointmentResult;
    private List&lt;String&gt; officeResult;
    private String sexStr = "true";
    private static final SelectItem[] sexItems = {
        new SelectItem("true","мужской"),
        new SelectItem("false","женский"),
        new SelectItem("auto","автоопределение по отчеству")
    };
    private List&lt;ResultItem&gt; resultItems;
 
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getAppointment() {
        return appointment;
    }
    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }
    public String getOffice() {
        return office;
    }
    public void setOffice(String office) {
        this.office = office;
    }
    public String getSexStr() {
        return sexStr;
    }
    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }
    public SelectItem[] getSexItems() {
        return sexItems;
    }
 
    public List&lt;String&gt; getFioResult() {
        if (fioResult==null) {
            declFio();
        }
        return fioResult;
    }
    public List&lt;String&gt; getAppointmentResult() {
        return appointmentResult;
    }
    public List&lt;String&gt; getOfficeResult() {
        return officeResult;
    }
    public List&lt;ResultItem&gt; getResultItems() {
        if (resultItems == null) {
            declAll();
        }
        return resultItems;
    }
 
    public void declAll() {
        resultItems = new ArrayList&lt;PadegBean.ResultItem&gt;();
        for (int i=1;i&lt;=6;i++) {
            ResultItem item = new ResultItem();
            item.padeg = i;
            resultItems.add(item);
 
            try {
                if ("auto".equals(sexStr)) {
                    item.fio = Padeg.getFIOPadegAS(lastName, firstName, middleName, i);
                } else {
                    boolean sex = Boolean.parseBoolean(sexStr);
                    item.fio = Padeg.getFIOPadeg(lastName, firstName, middleName, sex, i);
                }
            } catch (Exception e) {
                item.fio = e.getMessage();
            }
 
            try {
                //item.appointment = Padeg.getFullAppointmentPadeg(appointment, office, i);
                item.appointment = Padeg.getAppointmentPadeg(appointment, i);
            } catch (Exception e) {
                item.appointment = e.getMessage();
            }
 
            try {
                item.office = Padeg.getOfficePadeg(office, i);
            } catch (Exception e) {
                item.office = e.getMessage();
            }
        }
    }
 
    public void declFio(){
        fioResult = new ArrayList&lt;String&gt;();
        if ("auto".equals(sexStr)) {
            for (int i=1;i&lt;=6;i++) {
                try {
                    fioResult.add(Padeg.getFIOPadegAS(lastName, firstName, middleName, i));
                } catch (Exception e) {
                    fioResult.add(e.getMessage());
                }
            }
        } else {
            boolean sex = Boolean.parseBoolean(sexStr);
            for (int i=1;i&lt;=6;i++) {
                try {
                    fioResult.add(Padeg.getFIOPadeg(lastName, firstName, middleName, sex, i));
                } catch (Exception e) {
                    fioResult.add(e.getMessage());
                }
            }
        }
    }
 
    public void declAppointment(){
        appointmentResult = new ArrayList&lt;String&gt;();
        for (int i=1;i&lt;=6;i++) {
            appointmentResult.add(Padeg.getFullAppointmentPadeg(appointment, office, i));
        }
    }
 
    public void declOfice(){
        officeResult = new ArrayList&lt;String&gt;();
        for (int i=1;i&lt;=6;i++) {
            officeResult.add(Padeg.getOfficePadeg(office, i));
        }
    }
}