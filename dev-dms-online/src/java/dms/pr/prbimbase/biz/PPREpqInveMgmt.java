package dms.pr.prbimbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
import fwk.constants.DmsConstants;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [PU]구성품재고조회</li>
 * <li>설  명 : <pre>[PU]구성품재고조회</pre></li>
 * <li>작성일 : 2015-07-14 20:45:43</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class PPREpqInveMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPREpqInveMgmt(){
		super();
	}

	/**
	 * <pre>[PM]단말기재고조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-09-17 09:20:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [시작일]
	 *	- field : ED_DT [종료일]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : MFACT_CD [제조사코드]
	 *	- field : MFACT_NM [제조사명]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_LIM_LIST
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : MFACT_NM [제조사명]
	 *		- field : DT_IN_QTY [기간중매입]
	 *		- field : SKT_OUT_QTY [기간중SKT출고]
	 *		- field : DT_OUT_QTY [기간중매각]
	 *		- field : LOSS_QTY [기간중제각]
	 *		- field : TOT_QTY [당일재고]
	 *		- field : SKT_OUT_TOT_QTY [당일SKT출고]
	 *		- field : RESS_CNT [당일미출고]
	 * </pre>
	 */
    public IDataSet pInqEqpInveMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
    	try {
    		responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fInqEqpInveLst", requestData, onlineCtx);
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}		
        return responseData;
    }

}