package com.constuction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "basic_details_id",referencedColumnName = "id")
	private BasicDetails basicDetails;

	@OneToOne
	@JoinColumn(name = "location_details_id", referencedColumnName = "id")
	private LocationDetails locationDetails;
	
}
