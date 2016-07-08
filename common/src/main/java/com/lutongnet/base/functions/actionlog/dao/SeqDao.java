package com.lutongnet.base.functions.actionlog.dao;

import java.io.Serializable;

import com.lutongnet.base.functions.actionlog.model.entity.Seq;

public interface SeqDao {

	public void updateSeqNextValue(Seq seq);

	public Seq get(Serializable id);

}