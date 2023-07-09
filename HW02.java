package HomeWorkJAVA_02;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Scanner;   // Библиотека для возможности считывать результат
import java.io.*;           // библиотека работы с файлами
import java.util.logging.*; // логгирование
public class HW02 {
    public static void exercise01() {
        System.out.println("Домашнее задание №1.");
        /*Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса,
        * используя StringBuilder. Данные для фильтрации приведены ниже в виде строки.
        * Если значение null, то параметр не должен попадать в запрос.
        * Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"} */
        
        StringBuilder sqlReq = new StringBuilder();
        sqlReq.append("select * from students where ");
        JSONObject jobj = new JSONObject();
        jobj.put("name", "Ivanov");
        jobj.put("country", "Russia");
        jobj.put("city", "Moscow");
        jobj.put("age", "null");
        boolean flag = false;
        if ((jobj.get("name") != null) && (jobj.get("name") != "null")) {
         sqlReq.append("name = 'Ivanov'");
         flag = true;
        }
        if ((jobj.get("country") != null) && (jobj.get("country") != "null")) {
            if (flag) sqlReq.append(" AND country = 'Russia'");
            else { sqlReq.append("country = 'Russia'"); flag = true;}
        }
        if ((jobj.get("city") != null) && (jobj.get("city") != "null")){
            if (flag) sqlReq.append(" AND city = 'Moscow'");
            else {sqlReq.append("country = 'Russia'"); flag = true;}
        }
        if ((jobj.get("age") != null) && (jobj.get("age") != "null")){
            if (flag) sqlReq.append(" AND age = 'Russia'");
            else sqlReq.append("age = 'Russia'");
        }
        System.out.println(sqlReq);
    }
    
    public static void exercise02(Logger loggering) {
        System.out.println("Домашнее задание №2.");
        /*Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.*/
       
        System.out.println("Введите размер массива: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 100) - 1); 
        }
        System.out.println(arr);
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;    
                }
                loggering.info(Arrays.toString(arr));
            }
        }
        System.out.println(arr);
    }
    
    public static void exercise03() {
        System.out.println("Домашнее задание №3.");
        /*К калькулятору из предыдущего ДЗ добавить логирование */

        
    }
    public static void main(String[] args) throws IOException {
        
        Logger logger = Logger.getLogger(HW02.class.getName());
        FileHandler fn = new FileHandler("HomeWorkJAVA_02/log.xml");
        logger.addHandler(fn);
        XMLFormatter xml = new XMLFormatter();
        fn.setFormatter(xml);
        
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер домашнего задания для проверки (1-3): ");
        int exNum = in.nextInt();
        switch (exNum) {
            case 1:
                exercise01();
                break;
            case 2:
                exercise02(logger);
                break;
            case 3:
                exercise03();
                break;
            default:
                System.out.println("Введено не верное значение номера задания");
                break;
        }
    }
}
