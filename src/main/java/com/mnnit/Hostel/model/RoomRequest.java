package com.mnnit.Hostel.model;

import org.springframework.lang.Nullable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RoomRequest {

    @EmbeddedId
    private RequestId requestId;
    private int toRoom;

    @Nullable
    private int status;

    public RoomRequest() {
    }

    public RoomRequest(RequestId requestId, int toRoom, int status) {
        this.requestId = requestId;
        this.toRoom = toRoom;
        this.status = status;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public void setRequestId(RequestId requestId) {
        this.requestId = requestId;
    }

    public int getToRoom() {
        return toRoom;
    }

    public void setToRoom(int toRoom) {
        this.toRoom = toRoom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
