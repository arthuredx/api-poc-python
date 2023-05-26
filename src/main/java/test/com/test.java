package test.com;

import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class test {

        static Integer variavel = 4;

        private static final String UTF_8 = "UTF-8";

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        private static final int SESSION_TIME = 30;

        private static final String URL_PARAMETER_BAR = "/";

        public static final String URL_PROTOCOL_HTTP = "http";
        public static final String URL_PROTOCOL_HTTPS = "https";
        public static final String URL_PROTOCOL_HTTP_SEPARATOR = "://";

        private static final String DEFAULT_VALUE = "0";

        static String protocol;
        static String host;
        static String path;
        static String query;

        public static void main(String[] args) {

            System.out.println("testee decode"+ decode64(decode("a2V5d29yZA%3D%3D%3Fsrc%3D1")));

            LocalDateTime eventDT = LocalDateTime.parse("2022-11-28 09:15:24.919", FORMATTER);
            LocalDateTime nextEventDT = LocalDateTime.parse("2022-11-28 08:59:58.129", FORMATTER);

            System.out.println("DATA_PARSE  -  "+ LocalDateTime.parse("2022-11-28 08:59:58.129", FORMATTER));

            if (nextEventDT.isAfter(eventDT)) {
                System.out.println("A data é depois");

            } else {
                System.out.println("A data é antes");
            }



            if(isInSessionLegado("2022-12-01 09:10:34.822", "2022-12-01 08:50:55.721")){
                System.out.println("true está em seção");
            } else {
                System.out.println("false não está em seção");
            }

            if(variavel % 2 == 0){
                variavel = 5;
                // System.out.println("valor de com.hotmart.analytics.teste " +variavel);
                //System.out.println("valor de com.hotmart.analytics.teste " + this.variavel);
            }

            // try {
            //       System.out.println("TESTE OFERTA "+URLDecoder.decode("05ej1iksJWT", UTF_8));
            //  } catch (UnsupportedEncodingException e) {
            //       throw new RuntimeException(e);
            //   }

            String url = "https://clubedosferas.club.hotmart.com/lesson/R4jXL6Yd7a/lives-no-instagram-secreto";

            try {
                java.net.URL javaURL;
                if(!isHasProtocol(url)){
                    javaURL = new java.net.URL (URL_PROTOCOL_HTTP + URL_PROTOCOL_HTTP_SEPARATOR + url);
                }else{
                    javaURL = new java.net.URL (url);
                }
                protocol = javaURL.getProtocol();
                host = javaURL.getHost();
                path = javaURL.getPath();
                query = javaURL.getQuery();
            } catch (MalformedURLException e) {
                System.out.println("erro");
            }

            String pageUrl = removeLastBar(host+ path);
            System.out.println("site  " + pageUrl);

            CustomerEvent event = CustomerEvent.builder().account(null).affiliationCode("P68458494D").producerId("29776884")
                    .pageUrl("").build();
           if (!hasOwner(event)) {
                System.out.println("nao pegou o process_payment");
           } else {
               System.out.println("1 -  pegou o process_payment");
           }

            if (event.getPageUrl() != null && event.getPageUrl().contains("localhost")) {
                System.out.println("nao  pegou o process_payment");
            } else {
                System.out.println("2 -  pegou o process_payment");
            }

        }

        private static boolean hasOwner(CustomerEvent event) {
            return !(isBlank(event.getAccount()) && isBlank(event.getAffiliationCode()) && isBlank(event.getProducerId()));
        }

        public static boolean isBlank(String value) {
            return StringUtils.isBlank(value) || DEFAULT_VALUE.equals(value);
       }

        public static boolean isInSessionLegado(String begin, String end){
            LocalDateTime beginDT = LocalDateTime.parse(begin, FORMATTER);
            LocalDateTime endDT = LocalDateTime.parse(end, FORMATTER);
            System.out.println(ChronoUnit.MINUTES.between(beginDT, endDT));
            return ChronoUnit.MINUTES.between(beginDT, endDT) <= SESSION_TIME;
        }

        public static String removeLastBar(String url) {

            String strURL = url.trim();
            if (URL_PARAMETER_BAR.equals(strURL)) {
                return null;
            }

            int index = strURL.lastIndexOf(URL_PARAMETER_BAR);
            if (index > 0 && index == strURL.length() - 1) {
                return strURL.substring(0, index);
            }
            return strURL;
        }



        public static boolean isHasProtocol(String url){
            return url.contains(URL_PROTOCOL_HTTP + URL_PROTOCOL_HTTP_SEPARATOR)
                    || url.contains(URL_PROTOCOL_HTTPS + URL_PROTOCOL_HTTP_SEPARATOR);
        }

    public static String decode64(String text) {
        try {
            byte[] decoded = Base64.getDecoder().decode(text);
            return new String(decoded, UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String value) {
        try {
            if (StringUtils.isNotBlank(value)) {
                return URLDecoder.decode(value, UTF_8);
            }
        } catch (Exception e) {

        }
        return value;
    }

    }
