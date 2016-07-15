
package woo.study.web.business.summary.model.entity;

import java.io.Serializable;









import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import woo.study.web.business.summary.model.entity.DataSummaryIndicator.IndicatorType;


@Entity
@Table ( name = "T_DATA_SUMMARY" )
public class  DataSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	@Column ( name = "ID" )
	private Integer id;
	
	@Column ( name = "NAME_ENG" )
	private String nameEng;
	
	@Column ( name = "DATA_VALUE" )
	private String dataValue;
	
	@Column ( name = "EFFECTIVE_DATE" )
	@Type ( type = "org.joda.time.contrib.hibernate.PersistentLocalDate" )
	private LocalDate effectiveDate;
	
	@Column(name = "INDICATOR_TYPE")
	@Enumerated(EnumType.STRING)
	private IndicatorType	indicatorType;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "NAME_ENG", referencedColumnName="NAME_ENG",insertable = false, updatable=false)
	private DataSummaryIndicator dataSummaryIndicator;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public DataSummaryIndicator getDataSummaryIndicator() {
		return dataSummaryIndicator;
	}

	public void setDataSummaryIndicator(DataSummaryIndicator dataSummaryIndicator) {
		this.dataSummaryIndicator = dataSummaryIndicator;
	}
	
	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	@Override
	public String toString() {
		return "DataSummary [id=" + id + ", nameEng=" + nameEng + ", dataValue=" + dataValue + ", effectiveDate="
				+ effectiveDate + ", dataSummaryIndicator=" + dataSummaryIndicator + "]";
	}
	
}

