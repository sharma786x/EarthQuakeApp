package example.com.android.asynctaskloaderr;

import java.util.Date;

/**
 * Created by Sharma786 on 02/05/2017.
 */

public class Earthquake {

    private String  location;
    private String magnitude;
    private Date date;

    public Earthquake(String x, String y, Date z)
    {
        magnitude=x;
        location=y;
        date=z;
    }

    public String getLocation()
    {
        return location;
    }

    public String getMagnitude()
    {
        return magnitude;
    }
    public Date getDate()
    {
        return date;
    }
}

