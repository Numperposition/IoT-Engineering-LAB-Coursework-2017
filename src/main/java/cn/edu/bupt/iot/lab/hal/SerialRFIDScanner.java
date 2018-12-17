package cn.edu.bupt.iot.lab.hal;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.common.primitives.Bytes;

import java.util.HashMap;
import java.util.Map;

import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author asaba
 * Date: 2018/1/3
 * Under Creative Commons BY-NC-SA License.
 */
public class SerialRFIDScanner implements RFIDScanner, SerialPortDataListener {

    public SerialRFIDScanner(String port, int baudRate) {
        this.buffer = new byte[0];
        this.tags = new HashMap<String, Integer>();
        this.serial = SerialPort.getCommPort(port);
        if (null == port || serial.openPort()) {
            this.serial = SerialPort.getCommPorts()[0];
            if (this.serial.isOpen())
                this.serial.closePort();
        }
        serial.setBaudRate(baudRate);
        serial.setParity(0);

        serial.addDataListener(this);
    }

    public RFIDScanner up() {
        serial.openPort();
        return this;
    }

    public boolean down() {
        return false;
    }

    public Map<String, Integer> inventory() {
        //get Info        '04 FF 21       19 95'
        //start inventory '06 00 01 04 ff d4 39'  ---> 05 00 01 f8 69 0f
        byte[] magicNumber = parseHexBinary("06000104ffd439");
        serial.writeBytes(magicNumber, magicNumber.length);
        return tags;
    }

    public RFIDScanner use(String id) {
        return null;
    }

    public String read() {
        return null;
    }

    public RFIDScanner write(String partition, String data) {
        return null;
    }

    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    public void serialEvent(SerialPortEvent event) {
        buffer = Bytes.concat(buffer, event.getReceivedData());
        int frameSize = buffer[0] + 1;
        if (buffer.length >= frameSize) {
            // Extract a frame from buffer
            byte[] frame = new byte[frameSize];
            System.arraycopy(buffer, 0, frame, 0, frameSize);

            // Remove current extracted frames from buffer
            int framesBufferPendingSize = buffer.length - frameSize;
            byte[] tempBuffer = new byte[framesBufferPendingSize];
            System.arraycopy(buffer, 0, tempBuffer, frameSize, framesBufferPendingSize);
            buffer = tempBuffer;

            // Parse the frame
            byte[] header = new byte[4]; // Header is 4-byte
            byte[] dataBody = new byte[frame.length - (header.length + 2)];
            System.arraycopy(frame, 0, dataBody, header.length, frame.length + 2);

            int epcIdOffset;
            String epcId;
            int rssi;

            if (dataBody.length > 3) {
                switch ((int) frame[2]) {
                    case 1:     // Answer Mode
                        epcIdOffset = 3;
                        break;
                    case 238:   // Real Time Inventory Mode
                        epcIdOffset = 2;
                        break;
                    default:
                        epcIdOffset = -1;
                }
                if (epcIdOffset != -1) {
                    byte[] rawEpcId = new byte[48];
                    System.arraycopy(dataBody, epcIdOffset, rawEpcId, 0, rawEpcId.length);
                    epcId = printHexBinary(rawEpcId);
                    rssi = (int) dataBody[epcIdOffset + rawEpcId.length];
                    tags.put(epcId, rssi);
                }
            }
        }
    }

    private byte[] buffer;
    private SerialPort serial;
    private Map<String, Integer> tags; // Tags are stored in a <EPC_ID, RSSI> value pair
}