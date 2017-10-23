package app.com.project215;


public class AppController extends android.support.multidex.MultiDexApplication {

	private static AppController mInstance;

	/**
	 * Global  location in register
	 */
	private double latitude_register  = 33.888630, longitude_register = 35.495480;

	public void setLocation(double  latitude_register , double longitude_register) {
		this.latitude_register = latitude_register;
        this.longitude_register = longitude_register;
	}

	public double returnLatitude() {
        return this.latitude_register;
	}
    public double returnLongitude() {
        return this.longitude_register;
    }

	@Override
	public void onCreate() {

        super.onCreate();
		mInstance = this;

	}

}
