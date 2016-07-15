package woo.study.web.common.functions.actionlog.dao;

import java.io.Serializable;

import woo.study.web.common.functions.actionlog.model.entity.Seq;

public interface SeqDao {

	public void updateSeqNextValue(Seq seq);

	public Seq get(Serializable id);

}