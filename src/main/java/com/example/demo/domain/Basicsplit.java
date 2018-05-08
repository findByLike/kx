package com.example.demo.domain;

import java.util.List;

public class Basicsplit {
	 private List<String> date;
	 private List<String> close;
	 private List<String> open;
	 private List<String> high;
	 private List<String> low;
	 private String min;
	 private String max;
	 
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public List<String> getDate() {
		return date;
	}
	public void setDate(List<String> date) {
		this.date = date;
	}
	public List<String> getClose() {
		return close;
	}
	public void setClose(List<String> close) {
		this.close = close;
	}
	public List<String> getOpen() {
		return open;
	}
	public void setOpen(List<String> open) {
		this.open = open;
	}
	public List<String> getHigh() {
		return high;
	}
	public void setHigh(List<String> high) {
		this.high = high;
	}
	public List<String> getLow() {
		return low;
	}
	public void setLow(List<String> low) {
		this.low = low;
	}
	 
	 
}
