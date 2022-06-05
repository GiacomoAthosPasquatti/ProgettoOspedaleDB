import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = DriverDB.connectToDB();
        ResultSet rs = null;
       int procedura;
        System.out.printf("Inserire il numero della procedura che si vuole eseguire:\n" +
                "1: Visualizza posti occupati\n" +
                "2: Visualizza Visite mediche per uno specifico Dottore\n" +
                "3: Visualizza Interventi Chirurgici di un reparto in un determinato giorno\n" +
                "4: Visualizza Interventi Chirurgici relativi ad un dottore in un determinato giorno\n"+
                "5: Ospedalizza un paziente\n"+
                "6: Prenota visita per un paziente \n"+
                "7: Organizza intervento per un paziente\n"+
                "8: Controlla strumentazione da collaudare\n");
        procedura = scanner.nextInt();
        switch(procedura){
            case 1:
                rs = DriverDB.spPostiLettoOccupati(con);
                break;

            case 2:
                rs = DriverDB.spControllaVisiteDottore(con);
                break;

            case 3:
                rs = DriverDB.spControllaInterventiReparto(con);
                break;

            case 4:
                rs = DriverDB.spControllaInterventiperChirurgo(con);
                break;

            case 5:
                rs = DriverDB.spospedalizzaPaziente(con);
                System.out.println("Ospedalizzazione avvenuta. Dati ospedalizzazione:");
                break;

            case 6:
                rs = DriverDB.spprenotaVisita(con);
                System.out.println("Prenotazione avvenuta. Dati prenotazione:");
                break;

            case 7:
                rs = DriverDB.sporganizzaIntervento(con);
                System.out.println("Intervento prenotato. Dati prenotazione:");
                break;

            case 8:
                rs = DriverDB.spStrumentazioneDaCollaudare(con);
                break;

            default :
                System.out.println("Valore inserito non valido");
        }
        try{

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(", ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i)+ ": "+ columnValue);
            }
            System.out.println("");
        }
        }catch(SQLException ex){
            System.out.printf(ex.toString());
        }

    }

}
