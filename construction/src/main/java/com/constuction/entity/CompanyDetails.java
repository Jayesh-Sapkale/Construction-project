package com.constuction.entity;

import com.constuction.enums.ConstructionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

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

	@Column(name = "name")
	private String name;

	@Column(name = "number_of_employee")
	private Long numberOfEmployee;

	@Column(name = "annual_revenue")
	private Double annualRevenue;

	@Column(name = "website")
	private String website;

	@Column(name = "founding_date")
	private LocalDate foundingDate;

	@Column(name = "construction_type")
	@Enumerated(EnumType.STRING)
	private ConstructionType constructionType;

	@OneToOne(cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "location_details_id", referencedColumnName = "id")
	private LocationDetails locationDetails;


}
