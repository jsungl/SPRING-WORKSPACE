package com.kh.spring.tv.model.vo;

public class SamsungTv implements Tv {
	
	private RemoteControl remocon; //의존객체
	
	public void setRemocon(RemoteControl remocon) {
		System.out.println("setRemocon : " + remocon);
		this.remocon = remocon;
	}

	public SamsungTv() {
		System.out.println("SamsungTv 객체를 생성했습니다.");
	}
	
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTv의 전원을 켰습니다.");

	}
	
	public void changeChannel(int no) {
		this.remocon.changeChannel(no);
	}

}
