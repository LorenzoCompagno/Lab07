package it.polito.tdp.poweroutages.model;

import java.time.*;

public class Blackout {
	private int id;
	private int eventType;
	private int tagId;
	private int areaId;
	private int nercId;
	private int responsibleId;
	private int customersAffected;
	private LocalDateTime dateEventBegun;
	private LocalDateTime dateEventFinish;
	private int demandLoss;
	private Long duration;
	
	public Blackout(int id, int customersAffected, LocalDateTime dateEventBegun, LocalDateTime dateEventFinish) {
		super();
		this.id = id;
		this.customersAffected = customersAffected;
		this.dateEventBegun = dateEventBegun;
		this.dateEventFinish = dateEventFinish;
		duration = Duration.between(dateEventBegun, dateEventFinish).toHours();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getNercId() {
		return nercId;
	}

	public void setNercId(int nercId) {
		this.nercId = nercId;
	}

	public int getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(int responsibleId) {
		this.responsibleId = responsibleId;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegun() {
		return dateEventBegun;
	}

	public void setDateEventBegun(LocalDateTime dateEventBegun) {
		this.dateEventBegun = dateEventBegun;
	}

	public LocalDateTime getDateEventFinish() {
		return dateEventFinish;
	}

	public void setDateEventFinish(LocalDateTime dateEventFinish) {
		this.dateEventFinish = dateEventFinish;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}

	public Long getDuration() {
		return duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dateEventBegun.getYear()+" "+dateEventBegun+" "+dateEventFinish+" "
	+ duration + " "+customersAffected+"\n";
	}
	
	
	
	
	
	

}
