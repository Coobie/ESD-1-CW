/*
 * Licensed to JGC
 */
package client1;

/**
 *
 * @author Jacob
 */
public class Client
{

    /**
     * Client method for accessing web service to calculate the fee
     * @param distance (double) distance of journey in miles
     * @return fee (int) - in pence
     */
    public static Integer calculateFee(double distance)
    {
        client1.AlphaCabCalculate_Service service = new client1.AlphaCabCalculate_Service();
        client1.AlphaCabCalculate port = service.getAlphaCabCalculatePort();
        return port.calculateFee(distance);
    }

    /**
     * Client method for accessing web service to change the fee amounts
     * @param vat (double) - vat amount 0.2 = 20%
     * @param ppm (int) - price per mile in pence
     * @param sDist (double) - distance to incur an extra charge miles
     * @param exChar (int) - the charge for below the minimum distance miles
     * @return FEE (int) - from web service
     */
    public static Boolean changeFee(double vat, int ppm, double sDist, int exChar)
    {
        client1.AlphaCabCalculate_Service service = new client1.AlphaCabCalculate_Service();
        client1.AlphaCabCalculate port = service.getAlphaCabCalculatePort();
        return port.changeFee(vat, ppm, sDist, exChar);
    }
    
    
}
