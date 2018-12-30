/**
 * Created by shallwe on 2018/12/27.
 */
public class Statistic
{
    String createDate = "";
    int cityBeijing;
    int cityShanghai;
    int cityChengdu;
    int cityHangzhou;
    int cityShenzheng;
    int sourceDouYin;
    int sourceTaoBao;
    int sourcePengYou;
    int sourceWeiXin;
    int sourceTouTiao;
    int sourceBaiDu;
    int sourceQiTa;
    int sourceUnselected;

    public Statistic(String createDate)
    {
        this.createDate = createDate;
        int cityBeijing = 0;
        int cityShanghai = 0;
        int cityChengdu = 0;
        int cityHangzhou = 0;
        int cityShenzheng = 0;
        int sourceDouYin = 0;
        int sourceTaoBao = 0;
        int sourcePengYou = 0;
        int sourceWeiXin = 0;
        int sourceTouTiao = 0;
        int sourceBaiDu = 0;
        int sourceQiTa = 0;
        int sourceUnselected = 0;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void addCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getCityBeijing()
    {
        return cityBeijing;
    }

    public void addCityBeijing()
    {
        this.cityBeijing += 1;
    }

    public int getCityShanghai()
    {
        return cityShanghai;
    }

    public void addCityShanghai()
    {
        this.cityShanghai += 1;
    }

    public int getCityChengdu()
    {
        return cityChengdu;
    }

    public void addCityChengdu()
    {
        this.cityChengdu += 1;
    }

    public int getCityHangzhou()
    {
        return cityHangzhou;
    }

    public void addCityHangzhou()
    {
        this.cityHangzhou += 1;
    }

    public int getCityShenzheng()
    {
        return cityShenzheng;
    }

    public void addCityShenzheng()
    {
        this.cityShenzheng += 1;
    }

    public int getSourceDouYin()
    {
        return sourceDouYin;
    }

    public void addSourceDouYin()
    {
        this.sourceDouYin += 1;
    }

    public int getSourceTaoBao()
    {
        return sourceTaoBao;
    }

    public void addSourceTaoBao()
    {
        this.sourceTaoBao += 1;
    }

    public int getSourcePengYou()
    {
        return sourcePengYou;
    }

    public void addSourcePengYou()
    {
        this.sourcePengYou += 1;
    }

    public int getSourceWeiXin()
    {
        return sourceWeiXin;
    }

    public void addSourceWeiXin()
    {
        this.sourceWeiXin += 1;
    }

    public int getSourceTouTiao()
    {
        return sourceTouTiao;
    }

    public void addSourceTouTiao()
    {
        this.sourceTouTiao += 1;
    }

    public int getSourceBaiDu()
    {
        return sourceBaiDu;
    }

    public void addSourceBaiDu()
    {
        this.sourceBaiDu += 1;
    }

    public int getSourceQiTa()
    {
        return sourceQiTa;
    }

    public void addSourceQiTa()
    {
        this.sourceQiTa += 1;
    }

    public int getSourceUnselected()
    {
        return sourceUnselected;
    }

    public void addSourceUnselected()
    {
        this.sourceUnselected += 1;
    }

    public int countAllCity()
    {
        return cityBeijing + cityShanghai + cityChengdu + cityHangzhou + cityShenzheng;
    }

    public int countAllSource()
    {
        return sourceDouYin + sourceTaoBao + sourcePengYou + sourceWeiXin + sourceTouTiao + sourceBaiDu + sourceQiTa
                + sourceUnselected;
    }

}
