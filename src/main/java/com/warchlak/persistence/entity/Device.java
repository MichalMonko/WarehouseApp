package com.warchlak.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "device")
public class Device extends Product
{
	public Device(String name)
	{
		super(name);
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Devices_parts_relation",
			joinColumns = {@JoinColumn(name = "device", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "part", referencedColumnName = "id")})
	private Set<Part> parts = new HashSet<>();
}
