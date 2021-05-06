import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;

public class Main 
{
    public static int invoice_id = 1;
    public static Scanner input = new Scanner(System.in);
    public static int mainMenuChoose, menuChoose;
    
    public static String[] foods = {"Kaşarlı Tost","Sucuklu Kaşarlı Tost", "Ayvalık Tostu", "İzmir Kumru", "Tavuk Döner Dürüm", "Patso"};
    public static int[] foodsQuantity = {10, 10, 10, 10, 10, 10};
    public static double[] foodsPrice = {5.0, 7.0, 10.0, 12.0, 10.0, 6.0};
    
    public static String[] drinks = {"Meşrubat (Kola vb.)", "Meyve Suyu", "Bitki Çayı", "Yayık Ayran", "Su", "Çay"};
    public static int[] drinksQuantity = {10, 10, 10, 10, 10, 10};
    public static double[] drinksPrice = {4.0, 4.0, 4.0, 3.0, 2.0, 2.0};
    
    public static String[] desserts = {"Sütlaç", "Puding", "Dondurma", "Kazandibi", "Tulumba", "Künefe"};
    public static int[] dessertsQuantity = {10, 10, 10, 10, 10, 10};
    public static double[] dessertsPrice = {10.0, 10.0, 10.0, 10.0, 5.0, 10.0};
    
    public static String mainMenu = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+
                                    "--------------------------------------ANA MENÜ-------------------------------------\n"+
                                    "  1. Menüler\n" +
                                    "  2. Ödeme Yap\n" +
                                    "  3. Çıkış Yap\n" +
                                    "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
    
    public static void menu() 
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("-----------------------------------------------------------------Söke Devlet Hastanesi Kafeteryası----------------------------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("------------------YİYECEKLER-------------------------------------------------İÇECEKLER-------------------------------------------------TATLILAR-------------------");
        System.out.format("1. %s(%d)                %.2f TL     |      7. %s(%d)                 %.2f TL     |      13. %s(%d)                       %.2f TL\n", foods[0], foodsQuantity[0], foodsPrice[0], drinks[0], drinksQuantity[0], drinksPrice[0], desserts[0], dessertsQuantity[0], dessertsPrice[0]);
        System.out.format("2. %s(%d)        %.2f TL     |      8. %s(%d)                          %.2f TL     |      14. %s(%d)                       %.2f TL\n", foods[1], foodsQuantity[1], foodsPrice[1], drinks[1], drinksQuantity[1], drinksPrice[1], desserts[1], dessertsQuantity[1], dessertsPrice[1]);
        System.out.format("3. %s(%d)              %.2f TL     |      9. %s(%d)                          %.2f TL     |      15. %s(%d)                     %.2f TL\n", foods[2], foodsQuantity[2], foodsPrice[2], drinks[2], drinksQuantity[2], drinksPrice[2], desserts[2], dessertsQuantity[2], dessertsPrice[2]);
        System.out.format("4. %s(%d)                %.2f TL     |     10. %s(%d)                         %.2f TL     |      16. %s(%d)                    %.2f TL\n", foods[3], foodsQuantity[3], foodsPrice[3], drinks[3], drinksQuantity[3], drinksPrice[3], desserts[3], dessertsQuantity[3], dessertsPrice[3]);
        System.out.format("5. %s(%d)          %.2f TL     |     11. %s(%d)                                  %.2f TL     |      17. %s(%d)                       %.2f TL\n", foods[4], foodsQuantity[4], foodsPrice[4], drinks[4], drinksQuantity[4], drinksPrice[4], desserts[4], dessertsQuantity[4], dessertsPrice[4]);
        System.out.format("6. %s(%d)                       %.2f TL     |     12. %s(%d)                                 %.2f TL     |      18. %s(%d)                       %.2f TL\n", foods[5], foodsQuantity[5], foodsPrice[5], drinks[5], drinksQuantity[5], drinksPrice[5], desserts[5], dessertsQuantity[5], dessertsPrice[5]);
        System.out.format("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
    
    public static int  quantity = 0;
    public static double total = 0.0, pay;
    
    public static void transactions() throws Exception
    {
        menu();
        System.out.print("Almak istediğiniz ürünü seçin. :");
        menuChoose = input.nextInt();
        switch(menuChoose)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.print("Kaç adet almak istiyorsunuz? :");
                quantity = input.nextInt();
                if (quantity > foodsQuantity[menuChoose - 1])
                {
                    System.out.println("Yetersiz stok. Ana menüye yönlendiriliyorsunuz.");
                    order();
                }else
                {
                    foodsQuantity[menuChoose - 1] = foodsQuantity[menuChoose - 1] - quantity;
                    total = total + (foodsPrice[menuChoose - 1] * quantity);
                    System.out.print("Ürün sepetinize eklendi. Sepetinizin toplam tutarı: " + total + " TL\n");
                    addItemInvoice(foods[menuChoose - 1], (foodsPrice[menuChoose - 1] * quantity));
                    order();
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                System.out.print("Kaç adet almak istiyorsunuz? :");
                quantity =input.nextInt();
                if (quantity > drinksQuantity[menuChoose - 7])
                {
                    System.out.println("Yetersiz stok. Ana menüye yönlendiriliyorsunuz.");
                    order();
                }else
                {
                    drinksQuantity[menuChoose - 7] = drinksQuantity[menuChoose - 7] - quantity;
                    total = total + (drinksPrice[menuChoose - 7] * quantity);
                    System.out.print("Ürün sepetinize eklendi. Sepetinizin toplam tutarı: " + total + " TL\n");
                    addItemInvoice(drinks[menuChoose - 7], (drinksPrice[menuChoose - 7] * quantity));
                    order();
                }
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                System.out.print("Kaç adet almak istiyorsunuz? :");
                quantity =input.nextInt();
                if (quantity > dessertsQuantity[menuChoose - 13])
                {
                    System.out.println("Yetersiz stok. Ana menüye yönlendiriliyorsunuz.");
                    order();
                }else
                {
                    dessertsQuantity[menuChoose - 13] = dessertsQuantity[menuChoose - 13] - quantity;
                    total = total + (dessertsPrice[menuChoose - 13] * quantity);
                    System.out.print("Ürün sepetinize eklendi. Sepetinizin toplam tutarı: " + total + " TL\n");
                    addItemInvoice(desserts[menuChoose - 13], (dessertsPrice[menuChoose - 13] * quantity));
                    order();
                }
                break;
            default:
                System.out.println("Lütfen geçerli bir değer giriniz.");
                order();
                break;
        }
    }
    
    public static void startInvoice() throws Exception
    {
        FileOutputStream writer = new FileOutputStream("invoice_" + invoice_id);
        FileWriter fw = new FileWriter("invoice_" + invoice_id, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.flush();
        bw.write("******************INVOICE******************");
        bw.newLine();
	    bw.close();
    }
    
    public static void addItemInvoice(String item, double price) throws Exception
    {
        FileWriter fw = new FileWriter("invoice_" + invoice_id, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(item + " : " + price + " TL");
        bw.newLine();
	    bw.close();
    }
    
    public static void printInvoice(double total) throws Exception
    {
        FileWriter fw = new FileWriter("invoice_" + invoice_id, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Total Amount = " + total + " TL\n");
        bw.write("******************************************");
	    bw.close();
    }

    public static void order() throws Exception
    {
        System.out.print(mainMenu);
        System.out.print("Yapmak istediğiniz işlemi seçin: ");
        mainMenuChoose = input.nextInt();
        switch(mainMenuChoose)
        {
            case 1:
                transactions();
                break;
            case 2:
                if (total != 0)
                {
                    System.out.println("Toplam tutar: " + total + " TL.");
                    System.out.print("Ödeme tutarını giriniz: ");
                    pay = input.nextDouble();
                    
                    if(pay < total)
                    {
                        System.out.println("Para yetersiz! İşleminiz iptal edildi. Menüye yönlendiriliyorsunuz.");
                        order();
                    }
                    else
                    {
                        printInvoice(total);
                        total = pay - total;
                        System.out.println("Para üstü: " + total);
                        total = 0;
                        System.out.println("Bizi tercih ettiğiniz için teşekkür ederiz :)");
                        invoice_id = invoice_id + 1;
                        startInvoice();
                        order();
                    }
                }else
                {
                    System.out.println("Sepetiniz boş.");
                    order();
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Lütfen geçerli bir değer giriniz.");
                order();
                break;
        }
    }
    public static void main(String[] args) throws Exception
    {
        startInvoice();
        order();
    }
}
