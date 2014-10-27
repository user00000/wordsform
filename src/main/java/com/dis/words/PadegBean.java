package com.dis.words;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
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

    private String lastName = "���������";
    private String firstName = "����";
    private String middleName;
    private String appointment = "�������������� �� �������";
    private String office = "������������ ��������� ����������� ������� �� ��������� ����� � �����";
    private String sexStr = "true";
    private static final SelectItem[] sexItems = {
        new SelectItem("true","�������"),
        new SelectItem("false","�������"),
        new SelectItem("auto","��������������� �� ��������")
    };
    private List<ResultItem> resultItems;
    
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

    public List<ResultItem> getResultItems() {
        if (resultItems == null) {
            declAll();
        }
        return resultItems;
    }

    public void declAll() {
        resultItems = new ArrayList<PadegBean.ResultItem>();
        for (int i=1;i<=6;i++) {
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
}