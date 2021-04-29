import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Data {
    String name;
    String phone;
    String e_mail;
    String car;
    LocalDate rentStart;
    LocalDate rentEnd;
    String note;

    public Data(String name, String phone, String e_mail, String car, LocalDate rentStart, LocalDate rentEnd, String note) {
        this.name = name;
        this.phone = phone;
        this.e_mail = e_mail;
        this.car = car;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.note = note;
    }

    public Data(String name, String phone, String e_mail, String car, LocalDate rentStart, LocalDate rentEnd) {
        this.name = name;
        this.phone = phone;
        this.e_mail = e_mail;
        this.car = car;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        if(note != null)
            return  name + ";" +
                    phone + ";" +
                    e_mail + ";" +
                    car + ";" +
                    rentStart + ";" +
                    rentEnd + ";" +
                    note;
        else
            return  name + ";" +
                    phone + ";" +
                    e_mail + ";" +
                    car + ";" +
                    rentStart + ";" +
                    rentEnd;
    }

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        List<Data> adat = new ArrayList<>();
        File file = new File("adat.txt");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        while(scan.hasNext()) {
            String[] sor = scan.nextLine().split(";");
            if (sor.length == 7)
                adat.add(new Data(sor[0], sor[1], sor[2], sor[3], LocalDate.parse(sor[4]), LocalDate.parse(sor[5]), sor[6]));
            else
                adat.add(new Data(sor[0], sor[1], sor[2], sor[3], LocalDate.parse(sor[4]), LocalDate.parse(sor[5])));
        }
        adat.sort(Comparator.comparing(Data::getName).thenComparing(Data::getPhone));

        for(Data i : adat)
        {
            pw.println(i.toString());
        }
        pw.close();
    }
}