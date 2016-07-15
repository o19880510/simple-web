
package woo.study.web.business.summary.model.entity;

import java.io.Serializable;










import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table ( name = "T_DATA_SUMMARY_DATE" )
public class  DataSummaryDate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "ID" )
	private Integer id;
	
	@Column(name = "INDICATOR_TYPE")
	@Enumerated(EnumType.STRING)
	private DataSummaryIndicator.IndicatorType	indicatorType;
	
	@Column ( name = "DATA_DATE" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentLocalDate" )
	private LocalDate dataDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataSummaryIndicator.IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(DataSummaryIndicator.IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	public LocalDate getDataDate() {
		return dataDate;
	}

	public void setDataDate(LocalDate dataDate) {
		this.dataDate = dataDate;
	}

	@Override
	public String toString() {
		return "DataSummaryDate [id=" + id + ", indicatorType=" + indicatorType + ", dataDate=" + dataDate + "]";
	}
	
}

