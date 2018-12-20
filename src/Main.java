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
                            (), customerInfo.get(6).text(), customerInfo.get(7).text(), customerInfo.get(8).text(),
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
        System.out.print("总共生成"+customers.size()+"条数据，放置于D盘，文件名为result.csv");
        writeCSV(customers);
    }

    private static void writeCSV(ArrayList<Customer> customers)
    {
        try
        {
            File csv = new File("D://result.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
            bw.newLine();
            bw.write("客户名,淘宝账号,城市,所在区,详细地址,信息来源,应急联络人,备注,开启消息,创建时间");
            for (Customer customer : customers)
            {
                bw.newLine();
                bw.write(customer.getUserName() + "," + customer.getTaoBaoId() + "," + customer.getCity() + "," +
                        customer.getDistrict() + "," + customer.getAddress() + "," + customer.getInformationSource()
                        + "," + customer.getEmergencyContact() + "," + customer.getBeizhu() + "," + customer.getMsgOn() + "," + customer.getCreatedTime());
            }
            bw.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
