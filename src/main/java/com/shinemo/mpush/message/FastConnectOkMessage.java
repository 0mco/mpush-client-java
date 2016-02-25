package com.shinemo.mpush.message;


import com.shinemo.mpush.api.connection.Connection;
import com.shinemo.mpush.api.protocol.Packet;
import com.shinemo.mpush.util.WrappedByteBuffer;

import java.nio.ByteBuffer;

/**
 * Created by ohun on 2015/12/28.
 */
public final class FastConnectOkMessage extends ByteBufMessage {
    public String serverHost;
    public long serverTime;
    public int heartbeat;

    public FastConnectOkMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    public static FastConnectOkMessage from(BaseMessage src) {
        return new FastConnectOkMessage(src.createResponse(), src.connection);
    }

    @Override
    public void decode(ByteBuffer body) {
        serverHost = decodeString(body);
        serverTime = decodeLong(body);
        heartbeat = decodeInt(body);
    }

    @Override
    public void encode(WrappedByteBuffer body) {
        encodeString(body, serverHost);
        encodeLong(body, serverTime);
        encodeInt(body, heartbeat);
    }


    public FastConnectOkMessage setServerHost(String serverHost) {
        this.serverHost = serverHost;
        return this;
    }

    public FastConnectOkMessage setServerTime(long serverTime) {
        this.serverTime = serverTime;
        return this;
    }

    public FastConnectOkMessage setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
        return this;
    }

    @Override
    public String toString() {
        return "FastConnectOkMessage{" +
                "serverHost='" + serverHost + '\'' +
                ", serverTime=" + serverTime +
                ", heartbeat=" + heartbeat +
                '}';
    }
}
