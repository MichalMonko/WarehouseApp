package com.warchlak.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Product
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private int quantity;
	
	@Column
	private double price;
	
	@Column(columnDefinition = "smallint")
	@Enumerated(value = EnumType.ORDINAL)
	private ProductType type;
}
