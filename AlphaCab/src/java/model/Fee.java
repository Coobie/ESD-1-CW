/*
 * Licensed to JGC
 */
package model;

/**
 *
 * @author Jacob
 */
public class Fee
{
    private double vat;
    private int ppm;
    private double sDistance;
    private int xCharge;

    public Fee(double vat, int ppm, double sDistance, int xCharge)
    {
        this.vat = vat;
        this.ppm = ppm;
        this.sDistance = sDistance;
        this.xCharge = xCharge;
    }

    public double getVat()
    {
        return vat;
    }

    public void setVat(double vat)
    {
        this.vat = vat;
    }

    public int getPpm()
    {
        return ppm;
    }

    public void setPpm(int ppm)
    {
        this.ppm = ppm;
    }

    public double getsDistance()
    {
        return sDistance;
    }

    public void setsDistance(double sDistance)
    {
        this.sDistance = sDistance;
    }

    public int getxCharge()
    {
        return xCharge;
    }

    public void setxCharge(int xCharge)
    {
        this.xCharge = xCharge;
    }

    @Override
    public String toString()
    {
        return "Fee{" + "vat=" + vat + ", ppm=" + ppm + ", sDistance=" + sDistance + ", xCharge=" + xCharge + '}';
    }
    
    
}
