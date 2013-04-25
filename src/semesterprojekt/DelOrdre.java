package semesterprojekt;
public class DelOrdre
{
    private String title;
    private int vnummer, onummer, status, maengde;

    public DelOrdre(String title, int vnummer, int onummer, int status, int maengde) {
        this.title = title;
        this.vnummer = vnummer;
        this.onummer = onummer;
        this.status = status;
        this.maengde = maengde;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setOnummer(int onummer) {
        this.onummer = onummer;
    }

    public void setVnummer(int vnummer) {
        this.vnummer = vnummer;
    }

    public void setMaengde(int mængde) {
        this.maengde = mængde;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public int getOnummer() {
        return onummer;
    }

    public int getVnummer() {
        return vnummer;
    }

    public int getMaengde() {
        return maengde;
    }    
    
    @Override
    public String toString() {
        String statusname;
        if (status == 1)
        {
            statusname = "Hjemme";
        }
        else
        {
            statusname = "Ude";
        }
        
        return ""+ title + ", " + statusname;
    }

}
