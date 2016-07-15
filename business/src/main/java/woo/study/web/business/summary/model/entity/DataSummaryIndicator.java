package woo.study.web.business.summary.model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import woo.study.web.common.loader.EnumDisplay;

@Entity
@Table(name = "T_DATA_SUMMARY_INDICATOR")
public class DataSummaryIndicator implements Serializable {

	private static final long	serialVersionUID	= 1L;

	public enum IndicatorType implements EnumDisplay {
		D("日统计"), W("周统计"), M("月统计"), Y("年统计");

		private String	desc;

		private IndicatorType(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return this.desc;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer	id;

	@Column(name = "NAME_ENG")
	private String	nameEng;

	@Column(name = "NAME_CHI")
	private String	nameChi;

	@Column(name = "INDICATOR_TYPE")
	@Enumerated(EnumType.STRING)
	private DataSummaryIndicator.IndicatorType	indicatorType;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameEng() {
		return this.nameEng;
	}

	public void setNameChi(String nameChi) {
		this.nameChi = nameChi;
	}

	public String getNameChi() {
		return this.nameChi;
	}

	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	public IndicatorType getIndicatorType() {
		return this.indicatorType;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameEng == null) ? 0 : nameEng.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataSummaryIndicator other = (DataSummaryIndicator) obj;
		if (nameEng == null) {
			if (other.nameEng != null)
				return false;
		} else if (!nameEng.equals(other.nameEng))
			return false;
		return true;
	}

	public String toString() {
		return "ataSummaryIndicator [ " + "id:" + id + ", nameEng:" + nameEng + ", nameChi:" + nameChi
				+ ", indicatorType:" + indicatorType + " ]";
	}

}
