package com.warchlak.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "part")
public class Part extends Product
{
	public Part(String name)
	{
		super(name);
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Devices_parts_relation",
			joinColumns = {@JoinColumn(name = "part", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "device", referencedColumnName = "id")})
	private Set<Device> matchingDevices = new HashSet<>();
}
