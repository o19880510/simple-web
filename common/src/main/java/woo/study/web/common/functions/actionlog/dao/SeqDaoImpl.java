package woo.study.web.common.functions.actionlog.dao;

import java.io.Serializable;





import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import woo.study.web.common.dao.EntityDaoSupport;
import woo.study.web.common.functions.actionlog.model.entity.Seq;

@Component("seqDao")
public class SeqDaoImpl extends EntityDaoSupport<Seq> implements SeqDao {
	
	
	private static final String UPDATE_SEQ_NEXT_VALUE = " UPDATE Seq SET "
		+" nextValue = ? "
		+" WHERE tableName = ? ";
	/* (non-Javadoc)
	 * @see woo.study.web.common.common.business.base.functions.actionlog.dao.ISeqDao#updateSeqNextValue(woo.study.web.common.common.business.base.functions.actionlog.model.entity.Seq)
	 */
	@Override
	public void updateSeqNextValue(Seq seq) {
		Object[] params = new Object[] { seq.getNextValue(), seq.getTableName() };
		
		update(UPDATE_SEQ_NEXT_VALUE, params);
	}
	
	
	private static final String HQL_QUERY_AND_LOCK = "FROM Seq WHERE tableName = ? ";
	/* (non-Javadoc)
	 * @see woo.study.web.common.common.business.base.functions.actionlog.dao.ISeqDao#get(java.io.Serializable)
	 */
	@Override
	public Seq get(Serializable id) {
		TypedQuery<Seq> queryRecords = getEntityManager().createQuery(HQL_QUERY_AND_LOCK, Seq.class);
		queryRecords.setLockMode(LockModeType.PESSIMISTIC_WRITE); // mysql 行级锁 FOR UPDATE
		queryRecords.setParameter(1, id);
		return queryRecords.getSingleResult();
	}

}
