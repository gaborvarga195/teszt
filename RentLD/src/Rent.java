import java.time.LocalDate;
import java.util.*;

public class Rent {

    static Map<String,List<String>> kocsi = new TreeMap<>();

    public static boolean isAvailable(String car, LocalDate szam1, LocalDate szam2)
    {
        List<String> lista = new ArrayList<>();
        if(kocsi.containsKey(car))
        {
            if (kocsi.get(car).size() == 0) {
                lista.add(szam1 + "/" + szam2);
                kocsi.put(car,lista);
                System.out.println("A kocsi foglalas sikeres.");
                return true;
            }

            String[] tmp = kocsi.get(car).get(kocsi.get(car).size() - 1).split("/");
            if (LocalDate.parse(tmp[1]).isBefore(szam1)) {
                lista = kocsi.get(car);
                lista.add(szam1 + "/" + szam2);
                kocsi.put(car, lista);
                System.out.println("A kocsi foglalas sikeres.");
                return true;
            }

            for (int i = 0; i < kocsi.get(car).size() - 1; i++) {
                String[] sor = kocsi.get(car).get(i).split("/");
                String[] sor1 = kocsi.get(car).get(i + 1).split("/");
                if (LocalDate.parse(sor[1]).isBefore(szam1) && LocalDate.parse(sor1[0]).isAfter(szam2)) {
                    lista = (kocsi.get(car));
                    lista.add(i + 1,szam1 + "/" + szam2);
                    kocsi.put(car,lista);
                    System.out.println("A kocsi foglalas sikeres.");
                    return true;
                }
            }
            System.out.println("A kocsi a megadott idoszakban foglalt.");
            return false;
        }
        System.out.println("A kocsi foglalas sikertelen. Nincs ilyen jarmu");
        return false;
    }

    public static void main(String[] args) {
        List<String> honda = new ArrayList<>();
        honda.add("2021-04-24/2021-04-26");
        honda.add("2021-04-27/2021-04-30");
        honda.add("2021-05-05/2021-05-09");
        kocsi.put("Honda", honda);

        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < 3; i++)
        {
            String[] sor = scan.nextLine().split(";");
            isAvailable(sor[0], LocalDate.parse(sor[1]), LocalDate.parse(sor[2]));
        }
        for (Map.Entry<String, List<String>> i : kocsi.entrySet())
        {
            System.out.println(i.getKey() + ": " + String.join(" , ",i.getValue()));
        }
    }
}