import java.time.LocalDate;
import java.util.*;

public class Rent {

    static Map<String,List<String>> kocsi = new TreeMap<>();

    public static boolean available(String car, int szam1, int szam2)
    {
        List<String> lista = new ArrayList<>();
        if(kocsi.containsKey(car))
        {
            if (kocsi.get(car).size() == 0) {
                lista.add(szam1 + "-" + szam2);
                kocsi.put(car,lista);
                System.out.println("A kocsi foglalas sikeres.");
                return true;
            }

            String[] tmp = kocsi.get(car).get(kocsi.get(car).size() - 1).split("-");
            if (Integer.parseInt(tmp[1]) < szam1) {
                lista = kocsi.get(car);
                lista.add(szam1 + "-" + szam2);
                kocsi.put(car, lista);
                System.out.println("A kocsi foglalas sikeres.");
                return true;
            }

            for (int i = 0; i < kocsi.get(car).size() - 1; i++) {
                String[] sor = kocsi.get(car).get(i).split("-");
                String[] sor1 = kocsi.get(car).get(i + 1).split("-");
                if (Integer.parseInt(sor[1]) < szam1 && Integer.parseInt(sor1[0]) > szam2) {
                    lista = (kocsi.get(car));
                    lista.add(i + 1,szam1 + "-" + szam2);
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
        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();
        lista1.add("1-3");
        lista1.add("4-5");
        lista1.add("8-10");
        lista2.add("1-5");
        lista2.add("8-10");
        lista2.add("11-13");
        kocsi.put("Honda", lista1);
        kocsi.put("Mazda", lista2);

        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < 3; i++)
        {
            String[] sor = scan.nextLine().split(";");
            available(sor[0], Integer.parseInt(sor[1]), Integer.parseInt(sor[2]));
        }
        for (Map.Entry<String, List<String>> i : kocsi.entrySet())
        {
            System.out.println(i.getKey() + ":" + String.join(",",i.getValue()));
        }
    }
}