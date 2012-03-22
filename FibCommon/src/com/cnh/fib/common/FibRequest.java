package com.cnh.fib.common;

import android.os.Parcel;
import android.os.Parcelable;

public class FibRequest implements Parcelable {
	int type;
	long n;
	
	public FibRequest(int type, long n) {
		this.type = type;
		this.n = n;
	}

	public FibRequest(Parcel parcel) {
		type = parcel.readInt();
		n = parcel.readLong();
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeInt(type);
		parcel.writeLong(n);
	}

	public static final Parcelable.Creator<FibRequest> CREATOR = new Parcelable.Creator<FibRequest>() {

		@Override
		public FibRequest createFromParcel(Parcel source) {
			return new FibRequest(source);
		}

		@Override
		public FibRequest[] newArray(int size) {
			return new FibRequest[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
