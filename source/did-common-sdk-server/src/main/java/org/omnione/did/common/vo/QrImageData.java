package org.omnione.did.common.vo;

/**
 * QR Code Image Data
 */
public class QrImageData {
    private String mediaType;
    private String format;
    private byte[] qrIamge;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public byte[] getQrIamge() {
        return qrIamge;
    }

    public void setQrIamge(byte[] qrIamge) {
        this.qrIamge = qrIamge;
    }
}

