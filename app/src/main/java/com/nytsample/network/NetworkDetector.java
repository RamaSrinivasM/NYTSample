package com.nytsample.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.nytsample.utility.Display;

public class NetworkDetector {
    private Context _context;

    public NetworkDetector(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo currentNetworkInfo = connectivity.getActiveNetworkInfo();
        if (currentNetworkInfo != null && currentNetworkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to get the Network type like wifi, 2G, 3G etc
     * @return int
     */
    public int getNetworkType() {
        try {
            ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null || (info !=null && (!info.isConnected() || info.getDetailedState() == NetworkInfo.DetailedState.DISCONNECTED))) {
                return 0; //If Not Connected
            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return 1; //1G
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int nt = info.getSubtype();
                switch (nt) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    case 16: // TelephonyManager.NETWORK_TYPE_GSM:
                        return 2; //2G
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    case 17: // TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                        return 3; //3G
                    case TelephonyManager.NETWORK_TYPE_LTE:  //api<11 : replace by 13
                    case 18: // TelephonyManager.NETWORK_TYPE_IWLAN:
                        return 4; //4G
                    default:
                        return 5; //Unknown
                }
            } else {
                return 5; //Unknown;
            }
        }catch (Exception e)
        {
            Display.DisplayLogD("Network Exception",(e!=null) ? e.getMessage() : "Unknown");
        }

        return 0; //Unknown
    }


}

