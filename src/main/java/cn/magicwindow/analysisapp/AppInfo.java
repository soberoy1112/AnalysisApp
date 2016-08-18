package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.collection.NoDuplicatesArrayList;
import cn.magicwindow.analysisapp.xml.model.MetaDataEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 16/8/8.
 */
public class AppInfo {

    private List<SDK> sdks;
    private List<SDK> adsdks;
    private List<SuspectedSDK> suspectedSDKs;
    private String packageName;
    private int processCount=1; // sdk进程数

    private static final AppInfo instance = new AppInfo();

    private AppInfo() {
        sdks = new NoDuplicatesArrayList<SDK>();
        adsdks = new NoDuplicatesArrayList<SDK>();
        suspectedSDKs = new ArrayList<SuspectedSDK>();
    }

    public static AppInfo getInstance() {
        return instance;
    }

    public List<SDK> getSDKs() {
        return sdks;
    }

    public void addSDK(SDK sdk) {
        if (sdk.isAd()) {
            adsdks.add(sdk);
        }
        sdks.add(sdk);
    }

    public List<SuspectedSDK> getSuspectedSDKs() {

        return suspectedSDKs;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void addSuspectedSDK(Object object) {

        if (object instanceof MetaDataEntry) {

            suspectedSDKs.add(new SuspectedSDK(object));
        }
    }

    public int getProcessCount() {
        return processCount;
    }

    public void setProcessCount(int count) {
        processCount = count;
    }

    public void clear() {
        if (sdks!=null) {
            sdks.clear();
        }
        if (adsdks!=null) {
            adsdks.clear();
        }
        processCount = 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (sdks!=null && sdks.size()>0) {

            sb.append("app 所使用的第三方sdk数:").append(sdks.size()).append(",").append("其中广告sdk数:").append(adsdks.size()).append("\r\n");

            for (SDK sdk:sdks) {
                if (sdk.isAd()) {
                    sb.append(sdk.getSdkName()).append("(广告sdk)").append("\r\n");
                } else {
                    sb.append(sdk.getSdkName()).append("\r\n");
                }
            }
        } else {
            sb.append("app 没有使用任何第三方sdk").append("\r\n");
        }

        sb.append("\r\n");
        sb.append("app 拥有的进程数:").append(processCount);

        return sb.toString();
    }
}
