package com.schoolteacher.video;


import com.schoolteacher.mylibrary.pojo.Status;

public class VideoResult {

	private Status Status;
	private VideoInfo Data;
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return Status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		Status = status;
	}
	/**
	 * @return the data
	 */
	public VideoInfo getData() {
		return Data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(VideoInfo data) {
		Data = data;
	}
}
