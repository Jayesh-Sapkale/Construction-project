package com.constuction.entity;

import com.constuction.enums.ConstructionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "basic_details")
@Entity
public class CompanyDetails extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Long numberOfEmployee;
	
	private Double annualRevenue;

	private String website;

	private LocalDate foundingDate;

	private ConstructionType constructionType;


	@OneToOne
	@JoinColumn(name = "location_details_id", referencedColumnName = "id")
	private LocationDetails locationDetails;


}
