package com.constuction.entity;

import com.constuction.enums.ConstructionType;
import com.constuction.enums.ProjectStatus;
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
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@JoinColumn(name = "location_details_id", referencedColumnName = "id")
	private LocationDetails locationDetails;

	@OneToOne
	@JoinColumn(name = "builder_id", referencedColumnName = "id")
	private Builder builder;

	@Column(name = "construction_type")
	@Enumerated(EnumType.STRING)
	private ConstructionType constructionType;

	@Column(name = "area")
	private Double area;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_status")
	@Enumerated(EnumType.STRING)
	private ProjectStatus projectStatus;

	@Column(name = "estimated_price")
	private Double estimatedPrice;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "description")
	private String description;


}
