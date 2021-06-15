package de.aljoshavieth.coronadaten;

public class DistrictData {
    private String districtName;
    private int population;
    private int cases;
    private int deaths;
    private int casesPerWeek;
    private int deathsPerWeek;
    private int recovered;
    private double weekIncidence;
    private double casesPer100k;

    @Override
    public String toString() {
        return "Fälle gesamt: " + cases + "\n" +
                "Tode: " + deaths + "\n" +
                "Fälle letzte 7 Tage: " + casesPerWeek + "\n" +
                "Tode letzte 7 Tage: " + deathsPerWeek + "\n" +
                "Genesene: " + recovered + "\n" +
                "7 Tage Inzidenz: " + weekIncidence + "\n" +
                "Fälle pro 100k Einwohner: " + casesPer100k;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getCasesPerWeek() {
        return casesPerWeek;
    }

    public void setCasesPerWeek(int casesPerWeek) {
        this.casesPerWeek = casesPerWeek;
    }

    public int getDeathsPerWeek() {
        return deathsPerWeek;
    }

    public void setDeathsPerWeek(int deathsPerWeek) {
        this.deathsPerWeek = deathsPerWeek;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public double getWeekIncidence() {
        return weekIncidence;
    }

    public void setWeekIncidence(double weekIncidence) {
        this.weekIncidence = weekIncidence;
    }

    public double getCasesPer100k() {
        return casesPer100k;
    }

    public void setCasesPer100k(double casesPer100k) {
        this.casesPer100k = casesPer100k;
    }
}
