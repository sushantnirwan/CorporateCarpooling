package carpool.corporatecarpooling.controllers;

/**
 * Created by ibm_admin on 20/07/2017.
 */


public class SecurityController {

    private String mobileNumber;
    private String pin;
    private String name;
    private String email;
    private String sex;


    public void getLoginInfo(String mobileNumber, String pin)
    {
        this.mobileNumber=mobileNumber;
        this.pin=pin;
        //Call Dispatcher to validate info to DB
        //verifyLoginDB();  // Dispatcher class method

    }


    public void getRegistrationInfo( String mobileNumber,String name,String email,String sex)
    {
        this.mobileNumber=mobileNumber;
        this.pin=pin;
        this.name=name;
        this.email=email;
        this.sex=sex;
        //Call Dispatcher to save info to DB
        //verify Registration verifyInfoDB & send errors to Register

    }

}
