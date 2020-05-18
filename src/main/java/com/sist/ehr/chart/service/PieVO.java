package com.sist.ehr.chart.service;

import com.sist.ehr.cmn.DTO;

public class PieVO extends DTO {

	private String task;
	
	private int hoursPerDay;
	
	public PieVO() {}

	public PieVO(String task, int hoursPerDay) {
		super();
		this.task = task;
		this.hoursPerDay = hoursPerDay;
	}

	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * @return the hoursPerDay
	 */
	public int getHoursPerDay() {
		return hoursPerDay;
	}

	/**
	 * @param hoursPerDay the hoursPerDay to set
	 */
	public void setHoursPerDay(int hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	@Override
	public String toString() {
		return "PieVO [task=" + task + ", hoursPerDay=" + hoursPerDay + ", toString()=" + super.toString() + "]";
	}
	
	
}
