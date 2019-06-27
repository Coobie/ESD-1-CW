/*
 * Licensed to JGC
 */
package server;

import dao.FeeDao;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Fee;

/**
 *
 * @author Jacob
 */
@WebService(serviceName = "AlphaCabCalculate")
public class AlphaCabCalculate
{

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt)
    {
        return "Hello " + txt + " !";
    }

    /**
     * Web service returns the fee
     * @param distance (double) - distance in miles
     * @return int (the fee)
     */
    @WebMethod(operationName = "calculateFee")
    public Integer calculateFee(@WebParam(name = "distance") double distance)
    {
        Fee f = new FeeDao().getFeeAmount();
        //System.out.println(f);
        Integer i = 0;
        
        //Calculate fee per mile
        i = (int) ((distance * f.getPpm()));
        
        //Look if lower than shortest distance
        if (distance < f.getsDistance())
        { 
            //Apply extra charge
            i += f.getxCharge();
        }
        
        //Apply vat
        i = (int) (i * (1+f.getVat()));
        
        return i;
    }

    /**
     * Web service method for charging the fee amounts
     * @param vat (double) - VAT 0.2 = 20%
     * @param ppm (int) - price per mile - in pence
     * @param sDist (double) - less than this distance incurs a charge in miles
     * @param exChar (int) - charge for less than the distance in pence
     * @return null
     */
    @WebMethod(operationName = "changeFee")
    public Boolean changeFee(@WebParam(name = "vat") double vat, @WebParam(name = "ppm") int ppm, @WebParam(name = "sDist") double sDist, @WebParam(name = "exChar") int exChar)
    {
        new FeeDao().changeFeeAmount(new Fee(vat,ppm,sDist,exChar));
                
        return null;
    }
}
