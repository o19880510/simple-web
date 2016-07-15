package woo.study.web.common.util.parse;

import java.util.HashMap;
import java.util.Map;

import woo.study.web.common.dao.vo.PaginationBean;
import woo.study.web.common.util.parse.data.DataProducer;

public class PaginationBeanParser extends CommonMapParser implements DataProducer{

	private PaginationBean	paginationBean;

	public PaginationBeanParser(PaginationBean paginationBean) {
		super();
		this.paginationBean = paginationBean;
	}

	@Override
	public Map<String, Object> parse() {

		Map<String, Object> result = new HashMap<String, Object>();
		
		int currentPage = paginationBean.getCurrent();
		int pageCount = paginationBean.getPageCount();
		
		if(currentPage == 0){
			currentPage = 1;
			pageCount = 1;
		}
		
		result.put("totalPage", pageCount);
		result.put("currentPage", currentPage);
		
		result.put("pageSize", paginationBean.getPageSize());
		
		result.putAll(super.parse());
		
		return result;
	}

	@Override
	public Object data() {
		return this.paginationBean.getDataList();
	}

}
