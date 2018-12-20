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
        System.out.println("Enter a number: ");
        String inputDate = reader.next();
        reader.close();
        astronVote(inputDate);
    }

    private static void astronVote(String inputDate) throws IOException
    {
        String loginUrl = "https://xcx.iwalkpet.com/index.php/manage/lfjoew/login/vali";
        String userPageUrl = "";
        Document doc = Jsoup.connect(loginUrl).data("username", "betty").data("password", "449865").post();
        Elements elements = doc.select("ul");
        Elements userManagementPanel = elements.get(1).select("li a");
        if (userManagementPanel.first().text().equals("客户管理"))
        {
            userPageUrl = userManagementPanel.first().attr("href");
            System.out.println(userPageUrl);
        }
        ArrayList<Customer> customers = new ArrayList<>();
        do
        {
            //set value
            Document adminDoc = Jsoup.connect(userPageUrl).get();
            Elements adminElements = adminDoc.select("table.table tbody tr");
            for (Element e : adminElements)
            {
                Elements customerInfo = e.select("td");
                String dateString = customerInfo.get(0).text();
                if (inputDate.compareToIgnoreCase(dateString.substring(0, 10)) <= 0)
                {
                    Customer customer = new Customer(customerInfo.get(0).text(), customerInfo.get(1).text(),
                            customerInfo.get
                            (2).text(), customerInfo.get(3).text(), customerInfo.get(4).text(), customerInfo.get(5).text
                            (), customerInfo.get(6).text(), customerInfo.get(7).text(), customerInfo.get(8).text(),
                            customerInfo.get(9).text());
                    System.out.println(customer.toString());
                    customers.add(customer);
                }
                else
                {
                    System.out.println("end");
                    break;
                }
            }
            //set next page
            userPageUrl = null;
            Elements pages = adminDoc.select("div.c-pager a");
            for (Element e : pages){
                if (e.text().equals("下一页")){
                    userPageUrl = e.attr("href");
                }
            }
        } while (userPageUrl != null);
        System.out.print(customers.size());
    }

    private static void compareDate(String inputDate, String createDate)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    }


}
