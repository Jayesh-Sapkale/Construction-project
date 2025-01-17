package com.constuction.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.function.LongToDoubleFunction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "builder")
public class Builder extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "years_of_experience")
	private Double yearsOfExperience;

	@Column(name = "is_available")
	private Boolean isAvailable;

	@OneToOne
	@JoinColumn(name = "company_details_id", referencedColumnName = "id")
	private CompanyDetails companyDetails;

	@OneToOne
	@JoinColumn(name = "basic_details_id", referencedColumnName = "id")
	private BasicDetails basicDetails;

	@OneToOne
	@JoinColumn(name = "location_details_id", referencedColumnName = "id")
	private LocationDetails locationDetails;

}
