package org.omnione.did.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.vo.QrImageData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * QR Code Image Generator
 */
public class QrMaker {
    private static final QRCodeWriter QR_CODE_WRITER = new QRCodeWriter();
    private static final String DEFAULT_IMAGE_FORMAT = "PNG";
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    /**
     * Generate a QR code image from the given data.
     *
     * @param data The data to be encoded in the QR code.
     * @return The QR code image data.
     * @throws CommonSdkException If the QR code generation fails.
     */
    public static QrImageData makeQrImage(final Object data) {
        byte[] qrImage;
        qrImage = makeQrImage(JsonUtil.serializeToJson(data), DEFAULT_IMAGE_FORMAT, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        QrImageData qrImageData = new QrImageData();
        qrImageData.setFormat("image");
        qrImageData.setMediaType(DEFAULT_IMAGE_FORMAT);
        qrImageData.setQrIamge(qrImage);

        return qrImageData;

    }
    /**
     * Generate a QR code image from the given data.
     *
     * @param contents The data to be encoded in the QR code.
     * @param format The image format to use.
     * @param width The width of the QR code image.
     * @param height The height of the QR code image.
     * @return The QR code image data.
     */
    public static byte[] makeQrImage(final String contents, final String format, int width, int height) {
        try {
            BitMatrix bitMatrix = makeQrBitMatrix(contents, width, height);

            try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(bitMatrix, format, pngOutputStream);
                return pngOutputStream.toByteArray();
            } catch (IOException e) {
                throw new CommonSdkException(ErrorCode.QR_CODE_IO_ERROR);
            }
        } catch (WriterException e) {
            throw new CommonSdkException(ErrorCode.QR_CODE_ENCODING_ERROR);
        }
    }

    /**
     * Generates a QR code BitMatrix from the given content.
     *
     * @param contents The string content to be encoded in the QR code
     * @param width The desired width of the QR code
     * @param height The desired height of the QR code
     * @return BitMatrix representation of the QR code
     * @throws CommonSdkException If there's an error during QR code generation
     */
    public static BitMatrix makeQrBitMatrix(String contents, int width, int height) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        return QR_CODE_WRITER.encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
    }
}
