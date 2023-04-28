package com.sanjeev.stephan.murmu.ctsmartnet.smarthome.CCTV;

/**
 * Data Model for the CCTV Camera
 * also see {@link ViewHolderCCTV} | {@link AdapterCCTV}
 * also see {@link com.sanjeev.stephan.murmu.ctsmartnet.smarthome.MyFragments.CCTVFragment}
 * @author Sanjeev Stephan Murmu
 * @since 21th Oct 2019
 */
public class ModelCCTV
{
    int cameraIndex;
    String cameraLocation,cameraIp,ipAttributes;
    int cameraPort;
    public ModelCCTV() {
    }

    public ModelCCTV(int cameraIndex,String cameraIp, int cameraPort,String ipAttributes,String cameraLocation) {
        this.cameraIndex = cameraIndex;
        this.cameraLocation = cameraLocation;
        this.cameraIp = cameraIp;
        this.cameraPort = cameraPort;
        this.ipAttributes = ipAttributes;
    }

    public String getCameraLocation() {
        return cameraLocation;
    }

    public String getCameraIp() {
        return cameraIp;
    }

    public int getCameraPort() {
        return cameraPort;
    }

    public int getCameraIndex() {
        return cameraIndex;
    }

    public String getIpAttributes() {
        return ipAttributes;
    }
}
