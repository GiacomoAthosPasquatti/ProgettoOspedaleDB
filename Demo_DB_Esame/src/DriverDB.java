import java.sql.*;
import java.util.Scanner;

public class DriverDB {
    public static Connection connectToDB() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ospedale","root","Ciao_Mario13");
            return con;
        }
        catch (SQLException SPex) {
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spPostiLettoOccupati(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{call postiLettoOccupati()}");
            ResultSet rs = Cstm.executeQuery();
            return rs;
        }
        catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spControllaVisiteDottore(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call ControllaVisiteDottore(?)}");
            System.out.println("inserire Codice Dottore");
            int CodDoc = getInt();
            Cstm.setInt(1,CodDoc);
            ResultSet rs = Cstm.executeQuery();
            return rs;
        }
        catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spControllaInterventiReparto(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call ControllaInterventiReparto(?,?)}");
            System.out.println("inserire Codice Reparto");
            int CodRep = getInt();
            Cstm.setInt(1,CodRep);
            System.out.println("Inserire data di riferimento (formato accettato YYYY-MM-DD)");
            String Data = obatainString();
            Cstm.setDate(2, Date.valueOf(Data));
            ResultSet rs = Cstm.executeQuery();
            return rs;
        }
        catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spControllaInterventiperChirurgo(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call ControllaInterventiperChirurgo(?,?)}");
            System.out.println("inserire Codice Chriurgo");
            int CodRep = getInt();
            Cstm.setInt(1,CodRep);
            System.out.println("Inserire data di riferimento (formato accettato YYYY-MM-DD)");
            String Data = obatainString();
            Cstm.setDate(2, Date.valueOf(Data));
            ResultSet rs = Cstm.executeQuery();
            return rs;

        }catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spospedalizzaPaziente(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call ospedalizzaPaziente(?,?,?,?)}");
            System.out.println("inserire Codice Fiscale Paziente");
            String CFpaz = obatainString();
            Cstm.setString(1,CFpaz);
            System.out.println("inserire Codice Reparto");
            int CodRep = getInt();
            Cstm.setInt(2,CodRep);
            System.out.println("Inserire data di inizio ospedalizzazione (formato accettato YYYY-MM-DD)");
            String Data = obatainString();
            Cstm.setDate(3, Date.valueOf(Data));
            System.out.println("Inserire data di fine ospedalizzazione (formato accettato YYYY-MM-DD)");
            String Data2 = obatainString();
            Cstm.setDate(4, Date.valueOf(Data2));
            ResultSet rs = Cstm.executeQuery();
            return rs;

        }catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spprenotaVisita(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call prenotaVisita(?,?,?)}");
            System.out.println("inserire Codice Fiscale Paziente");
            String CFpaz = obatainString();
            Cstm.setString(1,CFpaz);
            System.out.println("inserire Codice Dottore");
            int CodDoc = getInt();
            Cstm.setInt(2,CodDoc);
            System.out.println("Inserire data della visita (formato accettato YYYY-MM-DD)");
            String Data = obatainString();
            Cstm.setDate(3, Date.valueOf(Data));
            ResultSet rs = Cstm.executeQuery();
            return rs;

        }catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet sporganizzaIntervento(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{Call organizzaIntervento(?,?,?,?,?)}");
            System.out.println("inserire Codice Fiscale Paziente");
            String CFpaz = obatainString();
            Cstm.setString(1,CFpaz);
            System.out.println("inserire Codice Dottore");
            int CodDoc = getInt();
            Cstm.setInt(2,CodDoc);
            System.out.println("Inserire data dell'intervento (formato accettato YYYY-MM-DD)");
            String Data = obatainString();
            Cstm.setDate(3, Date.valueOf(Data));
            System.out.println("L'intervento sarà di lunga durata? SI = S, NO = N");
            Integer LDurata = getbool();
            Cstm.setInt(4, LDurata);
            System.out.println("Inserire Descrizione intervento");
            String Desc = obatainString();
            Cstm.setString(5, Desc);
            ResultSet rs = Cstm.executeQuery();
            return rs;

        }catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    public static ResultSet spStrumentazioneDaCollaudare(Connection con){
        try {
            CallableStatement Cstm = con.prepareCall("{call StrumentazioneDaCollaudare()}");
            ResultSet rs = Cstm.executeQuery();
            return rs;
        }
        catch (SQLException SPex){
            System.out.println(SPex.toString());
            return null;
        }
    }

    private static Integer getInt(){
        Scanner scan = new Scanner(System.in);
        try{
            return scan.nextInt();
        }
        catch (Exception ex){
            System.out.println("Valore Non valido!");
            return null;
        }
    }

    private static Integer getbool(){
        Scanner scan = new Scanner(System.in);
        try{
            String ans = scan.nextLine();
            if (ans.equals("S")) return 1;
            else if (ans.equals("N")) return 0;
            else throw new Exception();

        }
        catch (Exception ex){
            System.out.println("Valore Non valido!");
            return null;
        }
    }

    private static String obatainString(){
        Scanner scan = new Scanner(System.in);
        try{
            return scan.nextLine();
        }
        catch (Exception ex){
            System.out.println("Valore Non valido!");
            return null;
        }
    }
}



