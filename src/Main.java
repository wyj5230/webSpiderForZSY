import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
    public static int count = 0;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("请按照2018-01-01格式输入日期: ");
        String inputDate = reader.next();
        reader.close();
        astronVote(inputDate);
    }

    private static void astronVote(String inputDate) throws IOException
    {
        String loginUrl = "https://xcx.iwalkpet.com/index.php/manage/lfjoew/login/vali";
        String userPageUrl = "";
        Connection.Response res = Jsoup.connect(loginUrl).data("username", "betty").data("password", "449865").method
                (Connection.Method.POST).execute();

        Document doc = res.parse();
        Elements elements = doc.select("ul");
        Elements userManagementPanel = elements.get(1).select("li a");
        if (userManagementPanel.first().text().equals("客户管理"))
        {
            userPageUrl = userManagementPanel.first().attr("href");
            System.out.println(userPageUrl);
        }
        ArrayList<Customer> customers = new ArrayList<>();
        boolean flagContinue = true;
        do
        {
            //set value
            Document adminDoc = Jsoup.connect(userPageUrl).cookies(res.cookies()).get();
            Elements adminElements = adminDoc.select("tbody tr");
            for (Element e : adminElements)
            {
                if (e.select("td").isEmpty())
                {
                    continue;
                }
                Elements customerInfo = e.select("td");
                String dateString = customerInfo.get(9).text();
                if (inputDate.compareToIgnoreCase(dateString.substring(0, 10)) <= 0)
                {
                    Customer customer = new Customer(customerInfo.get(0).text(), customerInfo.get(1).text(),
                            customerInfo.get
                                    (2).text(), customerInfo.get(3).text(), customerInfo.get(4).text(), customerInfo
                            .get(5).text
                                    (), customerInfo.get(6).text(), customerInfo.get(7).text(), customerInfo.get(8)
                            .text(),
                            customerInfo.get(9).text());
                    System.out.println(customer.toString());
                    customers.add(customer);
                }
                else
                {
                    System.out.println("已获取所有指定日期以后的数据");
                    flagContinue = false;
                    break;
                }
            }
            //set next page
            userPageUrl = null;
            Elements pages = adminDoc.select("div.c-pager a");
            for (Element e : pages)
            {
                if (e.text().equals("下一页"))
                {
                    userPageUrl = "https://xcx.iwalkpet.com" + e.attr("href");
                    break;
                }
            }
        } while (userPageUrl != null && flagContinue);
        int count1 = writeCSV(customers);
        System.out.print("详细表单总共生成" + count1 + "条数据，放置于D盘，文件名为 详细表单.csv");
        int count2 = writeCSVByResourceAndCity(customers);
        System.out.print("统计表单总共生成" + count2 + "条数据，放置于D盘，文件名为 统计表单.csv");
    }

    private static int writeCSV(ArrayList<Customer> customers)
    {
        try
        {
            File csv = new File("D://详细表单.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
            bw.write("客户名,淘宝账号,城市,所在区,详细地址,信息来源,应急联络人,备注,开启消息,创建时间");
            for (Customer customer : customers)
            {
                bw.newLine();
                bw.write(customer.getUserName() + "," + customer.getTaoBaoId() + "," + customer.getCity() + "," +
                        customer.getDistrict() + "," + customer.getAddress() + "," + customer.getInformationSource()
                        + "," + customer.getEmergencyContact() + "," + customer.getBeizhu() + "," + customer.getMsgOn
                        () + "," + customer.getCreatedTime());
            }
            bw.close();
            return customers.size();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    private static int writeCSVByResourceAndCity(ArrayList<Customer> customers)
    {
        ArrayList<String> errorMsg = new ArrayList<>();
        ArrayList<Statistic> staticsList = new ArrayList<>();
        String currentCreateTime = customers.get(0).getCreatedTime().substring(0, 10);
        Statistic currentStatistic = new Statistic(currentCreateTime);
        for (int i = 0; i < customers.size(); i++)
        {
            if (!customers.get(i).getCreatedTime().substring(0, 10).equals(currentCreateTime))
            {
                //add last date object
                staticsList.add(currentStatistic);
                //init new date object
                currentStatistic = new Statistic(customers.get(i).getCreatedTime().substring(0, 10));
                //update current time
                currentCreateTime = customers.get(i).getCreatedTime().substring(0, 10);
            }
            switch (customers.get(i).getCity())
            {
                case "上海市":
                    currentStatistic.addCityShanghai();
                    break;
                case "北京市":
                    currentStatistic.addCityBeijing();
                    break;
                case "成都市":
                    currentStatistic.addCityChengdu();
                    break;
                case "杭州市":
                    currentStatistic.addCityHangzhou();
                    break;
                case "深圳市":
                    currentStatistic.addCityShenzheng();
                    break;
                default:
                    errorMsg.add("出现未知城市：" + customers.get(i).getCity() + "。客户姓名：" + customers.get(i).getUserName());
                    break;
            }
            switch (customers.get(i).getInformationSource())
            {
                case "百度":
                    currentStatistic.addSourceBaiDu();
                    break;
                case "抖音":
                    currentStatistic.addSourceDouYin();
                    break;
                case "今天头条":
                    currentStatistic.addSourceTouTiao();
                    break;
                case "今日头条":
                    currentStatistic.addSourceTouTiao();
                    break;
                case "朋友推荐":
                    currentStatistic.addSourcePengYou();
                    break;
                case "淘宝":
                    currentStatistic.addSourceTaoBao();
                    break;
                case "微信":
                    currentStatistic.addSourceWeiXin();
                    break;
                case "其它":
                    currentStatistic.addSourceQiTa();
                    break;
                case "未选择":
                    currentStatistic.addSourceUnselected();
                    break;
                default:
                    errorMsg.add("出现未知信息来源：" + customers.get(i).getInformationSource() + "。客户姓名：" + customers.get(i)
                            .getUserName());
                    break;
            }
        }
        staticsList.add(currentStatistic);
        try
        {
            File csv = new File("D://统计表单.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
            bw.write("日期,北京市,上海市,成都市,杭州市,深证市,城市总计,百度,抖音,今日头条,朋友推荐,淘宝,微信,其他,未选择,消息来源总计");
            for (Statistic statistic : staticsList)
            {
                bw.newLine();
                bw.write(statistic.getCreateDate() + "," + statistic.getCityBeijing() + "," + statistic
                        .getCityShanghai() + "," +
                        statistic.getCityChengdu() + "," + statistic.getCityHangzhou() + "," + statistic
                        .getCityShenzheng() + "," +
                        statistic.countAllCity() + "," + statistic.getSourceBaiDu() + "," + statistic.getSourceDouYin
                        () + "," + statistic.getSourceTouTiao() + "," + statistic.getSourcePengYou() + "," +
                        statistic.getSourceTaoBao() + "," + statistic.getSourceWeiXin() + "," + statistic
                        .getSourceQiTa() + "," + statistic.getSourceUnselected() + "," + statistic.countAllSource());
            }
            for (String error : errorMsg){
                bw.newLine();
                bw.write(error);
            }
            bw.close();
            return staticsList.size();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

}
