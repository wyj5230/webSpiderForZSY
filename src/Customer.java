/**
 * Created by shallwe on 2018/12/21.
 */
public class Customer
{
    String userName;
    String taoBaoId;
    String city;
    String district;
    String address;
    String informationSource;
    String emergencyContact;
    String beizhu;
    String msgOn;
    String createdTime;

    public Customer(String userName, String taoBaoId, String city, String district, String address, String
            informationSource, String emergencyContact, String beizhu, String msgOn, String createdTime)
    {
        this.userName = userName;
        this.taoBaoId = taoBaoId;
        this.city = city;
        this.district = district;
        this.address = address;
        this.informationSource = informationSource;
        this.emergencyContact = emergencyContact;
        this.beizhu = beizhu;
        this.msgOn = msgOn;
        this.createdTime = createdTime;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getTaoBaoId()
    {
        return taoBaoId;
    }

    public void setTaoBaoId(String taoBaoId)
    {
        this.taoBaoId = taoBaoId;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getInformationSource()
    {
        return informationSource;
    }

    public void setInformationSource(String informationSource)
    {
        this.informationSource = informationSource;
    }

    public String getEmergencyContact()
    {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact)
    {
        this.emergencyContact = emergencyContact;
    }

    public String getBeizhu()
    {
        return beizhu;
    }

    public void setBeizhu(String beizhu)
    {
        this.beizhu = beizhu;
    }

    public String getMsgOn()
    {
        return msgOn;
    }

    public void setMsgOn(String msgOn)
    {
        this.msgOn = msgOn;
    }

    public String getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(String createdTime)
    {
        this.createdTime = createdTime;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "userName='" + userName + '\'' +
                ", taoBaoId='" + taoBaoId + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", informationSource='" + informationSource + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", msgOn='" + msgOn + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
