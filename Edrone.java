import java.io.*;
import java.net.*;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Edrone
{
    public static String sciezka = "https://edrone.me";
    public static long counter = 0;
    public static URL connection;

    public static void main(String[] args) throws Exception
    {

        URL edrone = new URL(sciezka);
        URLConnection connection = edrone.openConnection();



        URL url = new URL(sciezka);
        HttpURLConnection connectionHttp = (HttpURLConnection)url.openConnection();

        Edrone.sprawdzKod(connectionHttp);


    }




    public static void polaczPrzelicz(URLConnection connection) throws Exception {

        System.out.println("Polaczono");


        InputStream rWchodzacy = connection.getInputStream();

        byte[] buffer = new byte[8192];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int bytesRead;
        int przeczytano = 0;

        while ((bytesRead = rWchodzacy.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);

            System.out.println(przeczytano += bytesRead);


        }
    }

        public static int sprawdzKod(HttpURLConnection connectionHttp) throws Exception
        {


            int code = connectionHttp.getResponseCode();
            if (code >= 400)
            {

                Edrone.halfBreaker(connectionHttp);

            }
            else
            {
                Edrone.polaczPrzelicz(connectionHttp);

            }

            return code;


        }

        public static void openBreaker(HttpURLConnection connectionHttp) throws Exception    ///drugie
        {

            Random losowo = new Random();
            int start = 5;
            int stop = 15;
            int result = losowo.nextInt(stop - start)+stop;

            System.out.println("Serwer nie żyje, zamykam połączenie");
            connectionHttp.disconnect();

            TimeUnit.MINUTES.sleep(result);

            Edrone.closeBreaker(connectionHttp);


        }


        public static void closeBreaker(HttpURLConnection connectionHttp) throws Exception
        {


            System.out.println("zamykam Breaker - serwer wstał");

            Edrone.sprawdzKod(connectionHttp);

        }


        public static void halfBreaker(HttpURLConnection connectionHttp) throws Exception
        {


            System.out.println("Czekamy... potem zdecydujemy");

            TimeUnit.SECONDS.sleep(30*counter);
            counter++;
            System.out.println("Liczba prób" + counter);
            if (counter >= 5)
            {
                Edrone.openBreaker(connectionHttp);
            }
            else

            {
                 Edrone.sprawdzKod(connectionHttp);
            }


        }

    }


